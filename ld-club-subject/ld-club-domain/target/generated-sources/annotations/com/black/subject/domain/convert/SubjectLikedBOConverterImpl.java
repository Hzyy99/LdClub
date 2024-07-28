package com.black.subject.domain.convert;

import com.black.subject.infra.basic.Bo.SubjectLikedBO;
import com.black.subject.infra.basic.entity.SubjectLiked;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-28T21:48:54+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_382 (Amazon.com Inc.)"
)
public class SubjectLikedBOConverterImpl implements SubjectLikedBOConverter {

    @Override
    public SubjectLiked convertBOToEntity(SubjectLikedBO subjectLikedBO) {
        if ( subjectLikedBO == null ) {
            return null;
        }

        SubjectLiked subjectLiked = new SubjectLiked();

        subjectLiked.setId( subjectLikedBO.getId() );
        subjectLiked.setSubjectId( subjectLikedBO.getSubjectId() );
        subjectLiked.setLikeUserId( subjectLikedBO.getLikeUserId() );
        subjectLiked.setStatus( subjectLikedBO.getStatus() );
        subjectLiked.setCreatedBy( subjectLikedBO.getCreatedBy() );
        subjectLiked.setCreatedTime( subjectLikedBO.getCreatedTime() );
        subjectLiked.setUpdateBy( subjectLikedBO.getUpdateBy() );
        subjectLiked.setUpdateTime( subjectLikedBO.getUpdateTime() );
        subjectLiked.setIsDeleted( subjectLikedBO.getIsDeleted() );

        return subjectLiked;
    }

    @Override
    public List<SubjectLikedBO> convertListInfoToBO(List<SubjectLiked> subjectLikedList) {
        if ( subjectLikedList == null ) {
            return null;
        }

        List<SubjectLikedBO> list = new ArrayList<SubjectLikedBO>( subjectLikedList.size() );
        for ( SubjectLiked subjectLiked : subjectLikedList ) {
            list.add( subjectLikedToSubjectLikedBO( subjectLiked ) );
        }

        return list;
    }

    protected SubjectLikedBO subjectLikedToSubjectLikedBO(SubjectLiked subjectLiked) {
        if ( subjectLiked == null ) {
            return null;
        }

        SubjectLikedBO subjectLikedBO = new SubjectLikedBO();

        subjectLikedBO.setId( subjectLiked.getId() );
        subjectLikedBO.setSubjectId( subjectLiked.getSubjectId() );
        subjectLikedBO.setLikeUserId( subjectLiked.getLikeUserId() );
        subjectLikedBO.setStatus( subjectLiked.getStatus() );
        subjectLikedBO.setCreatedBy( subjectLiked.getCreatedBy() );
        subjectLikedBO.setCreatedTime( subjectLiked.getCreatedTime() );
        subjectLikedBO.setUpdateBy( subjectLiked.getUpdateBy() );
        subjectLikedBO.setUpdateTime( subjectLiked.getUpdateTime() );
        subjectLikedBO.setIsDeleted( subjectLiked.getIsDeleted() );

        return subjectLikedBO;
    }
}
