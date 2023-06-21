package com.likelion.post.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "university")
public class UniversityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long universityId;
    private String universityName;

    /**일대다 관계에서 참조 당하는 엔티티에 사용 */
    @OneToMany(mappedBy = "university")
    private List<NoticeEntity> noticeEntityList=new ArrayList<>();

    public UniversityEntity(Long universityId, String universityName) {
        this.universityId = universityId;
        this.universityName = universityName;
    }
}
