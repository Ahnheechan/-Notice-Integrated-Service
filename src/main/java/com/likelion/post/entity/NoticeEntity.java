package com.likelion.post.entity;


import com.likelion.post.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column(name="title")
    private String title;

    @Column(name="url")
    private String url;

    @Column(name="notice_date")
    private String noticeDate;

    @Column(name="created_time")
    private LocalDate createdTime;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="noticetype_id")
    private NoticeTypeEntity noticeType;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="university_id")
    private UniversityEntity university;

}
