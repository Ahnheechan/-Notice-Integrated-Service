package com.likelion.post.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "noticetype")
public class NoticeTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notictypeId;

    private String noticetypeName;

    @OneToMany(mappedBy = "noticeType")
    private List<NoticeEntity> noticeEntityList = new ArrayList<>();

    public NoticeTypeEntity(Long notictypeId, String noticetypeName) {
        this.notictypeId = notictypeId;
        this.noticetypeName = noticetypeName;
    }
}
