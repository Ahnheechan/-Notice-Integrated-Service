package com.likelion.post.dto;

import com.likelion.post.entity.NoticeEntity;
import com.likelion.post.entity.NoticeTypeEntity;
import com.likelion.post.entity.UniversityEntity;
import lombok.Getter;

import javax.persistence.JoinColumn;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class GetNotice {
    private Long id;
    private String title;
    private String url;
    private String noticeDate;
    private LocalDate createdTime;
    private String universityName;
    private  NoticeTypeEntity noticetypeId;

    public GetNotice(NoticeEntity noticeEntity) {
        this.id = noticeEntity.getNoticeId();
        this.title = noticeEntity.getTitle();
        this.url = noticeEntity.getUrl();
        this.noticeDate = noticeEntity.getNoticeDate();
        this.createdTime = noticeEntity.getCreatedTime();
        this.noticetypeId=noticeEntity.getNoticeType();
        this.universityName=noticeEntity.getUniversity().getUniversityName();
    }


    @Override
    public String toString() {
        return "GetNotice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", noticeDate='" + noticeDate + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
