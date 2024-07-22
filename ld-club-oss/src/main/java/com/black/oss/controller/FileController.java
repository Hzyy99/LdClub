package com.black.oss.controller;

import com.black.oss.service.FileService;
import com.black.oss.util.MinioUtils;
import com.black.subject.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


/**
 * 文件传输Controller
 */
@RestController
@Slf4j
@RequestMapping("/oss")
public class FileController {

    @Resource
    private MinioUtils minioUtils;

    @Resource
    private FileService fileService;

    @RequestMapping("/test")
    public Result<List<String>> test() throws Exception {
        List<String> bucketList = minioUtils.getBucketList();
        return Result.success(bucketList);
    }





}
