package com.likelion.post.repository;

import com.likelion.post.entity.NoticeEntity;
import com.likelion.post.entity.NoticeTypeEntity;
import com.likelion.post.entity.UniversityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<NoticeEntity,Long> {
    Page<NoticeEntity> findAllByNoticeType(NoticeTypeEntity noticeTypeEntity, Pageable pageable);
//
//    @Query("select m from Member m where m.team.id = :teamId")
//    Member findByTeamJpql(@Param("teamId") Long id);
}
