package com.black.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.black.subject.common.entity.PageInfo;
import com.black.subject.common.enums.CacheType;
import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.common.interfaces.multipleCache;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.common.utils.IdWorkerUtil;
import com.black.subject.common.utils.LoginUtil;
import com.black.subject.domain.convert.SubjectInfoConverter;
import com.black.subject.domain.handler.subject.SubjectTypeHandler;
import com.black.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.black.subject.domain.redis.RedisUtils;
import com.black.subject.domain.service.SubjectEsService;
import com.black.subject.domain.service.SubjectInfoService;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.Dao.SubjectInfoDao;
import com.black.subject.infra.basic.Dao.SubjectMappingDao;
import com.black.subject.infra.basic.entity.SubjectInfo;
import com.black.subject.infra.basic.entity.SubjectInfoEs;
import com.black.subject.infra.basic.entity.SubjectMapping;
import com.black.subject.infra.config.PageResult;
import com.black.subject.infra.entity.UserInfo;
import com.black.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectInfoServiceimpl implements SubjectInfoService {

    @Resource
    private SubjectInfoDao subjectInfoDao;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectMappingDao subjectMappingDao;

    @Resource
    private SubjectEsService subjectEsService;

    @Resource
    private UserRpc userRpc;

    private static final String RANK_KEY = "subject_rank";
    /**
     * 添加题目
     * @param subjectInfoBO
     * @return
     */
    @Override
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        /**
         * 插入题目
         */
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoDao.save(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);

        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        LinkedList<SubjectMapping> subjectMappings = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId ->{
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMappings.add(subjectMapping);
            });
            boolean mappingresult = subjectMappingDao.saveBatch(subjectMappings);
            AssertUtil.isTrue(mappingresult, "添加题目映射失败");
        });
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setDocId(new IdWorkerUtil(1, 1, 1).nextId());
        subjectInfoEs.setSubjectId(subjectInfo.getId());
        subjectInfoEs.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());
        subjectInfoEs.setCreateTime(new Date().getTime());
        subjectInfoEs.setCreateUser("ldstudent");
        subjectInfoEs.setSubjectName(subjectInfo.getSubjectName());
        subjectInfoEs.setSubjectType(subjectInfo.getSubjectType());
        subjectEsService.insert(subjectInfoEs);
        //redis放入zadd计入排行榜
        RedisUtils.zIncrementScore(RANK_KEY, LoginUtil.getLoginId(), 1);
        return true;
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        /**
         * 分页查询
         */

        List<SubjectMapping> byLabelId = subjectMappingDao.getByLabelId(subjectInfoBO);
        ArrayList<Long> longs = new ArrayList<>();
        byLabelId.stream().forEach(subjectMapping -> {
            longs.add(subjectMapping.getSubjectId());
        });
        List<Long> collectid = longs.stream().distinct().collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNo(subjectInfoBO.getPageNo());
        pageInfo.setPageSize(subjectInfoBO.getPageSize());
        Page<SubjectInfo> subjectPage = subjectInfoDao.getSubjectPage(collectid,subjectInfoBO);
        /**
         * 封装结果
         */
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertListInfoToBO(subjectPage.getRecords());
        AssertUtil.isListEmpty(subjectInfoBOS, "查询题目列表失败");
        PageResult<SubjectInfoBO> subjectInfoBOPageResult = new PageResult<>();
        subjectInfoBOPageResult.setPageSize(subjectPage.getSize());
        subjectInfoBOPageResult.setPage(subjectPage.getCurrent());
        subjectInfoBOPageResult.setCounts(subjectPage.getTotal());
        subjectInfoBOPageResult.setItems(subjectInfoBOS);
        return subjectInfoBOPageResult;
    }
    /**
     * 查询题目详情
     * @param subjectInfoBO
     * @return
     */
    @Override
    public SubjectInfoBO getSubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        SubjectInfo quarysubjectInfo = subjectInfoDao.getById(subjectInfo);
        AssertUtil.isEmpty(quarysubjectInfo, "查询题目详情失败");
        SubjectInfoBO quarysubjectInfoBo = SubjectInfoConverter.INSTANCE.convertInfoToBo(quarysubjectInfo);
        return quarysubjectInfoBo;
    }

    @Override
    public List<SubjectInfoBO> getContributeList() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = RedisUtils.rankWithScore(RANK_KEY, 0, 5);
        if (CollectionUtils.isEmpty(typedTuples)) {
            return Collections.emptyList();
        }
        List<SubjectInfoBO> boList = new LinkedList<>();
        typedTuples.forEach((rank -> {
            SubjectInfoBO subjectInfoBO = new SubjectInfoBO();
            subjectInfoBO.setSubjectCount(rank.getScore().intValue());
            UserInfo userInfo = userRpc.getUserInfo(rank.getValue());
            subjectInfoBO.setCreateUser(userInfo.getNickName());
            subjectInfoBO.setCreateUserAvatar(userInfo.getAvatar());
            boList.add(subjectInfoBO);
        }));
        AssertUtil.isListEmpty(boList, "查询贡献榜失败");
        return boList;
    }
}
