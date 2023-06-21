package com.likelion.post.service;

import com.likelion.post.dto.GetNotice;
import com.likelion.post.entity.NoticeEntity;
import com.likelion.post.entity.NoticeTypeEntity;
import com.likelion.post.entity.UniversityEntity;
import com.likelion.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


//    //게시글 생성 지금 내 프로젝트에는 필요 X
//    public void  createPost(PostDto postDto){
//        NoticeEntity noticeEntity = NoticeEntity.builder()
//                .title(postDto.getTitle())
//                .content(postDto.getContent())
//                .registerName(postDto.getUsername())
//                .build();
//
//        postRepository.save(noticeEntity);
//    }

    //게시글 전부 읽어오기
    public HashMap<String, Object> readPostAll(Integer page, Integer size){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        NoticeTypeEntity noticeTypeEntity = new NoticeTypeEntity((long)1,"교과공지사항");
        NoticeTypeEntity noticeTypeEntity2 = new NoticeTypeEntity((long)2,"비교과공지사항");
        NoticeTypeEntity noticeTypeEntity3 = new NoticeTypeEntity((long)3,"학생회공지사항");
        Page<NoticeEntity> list = postRepository.findAllByNoticeType(noticeTypeEntity,
                PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"createdTime")));

        Page<NoticeEntity> list2 = postRepository.findAllByNoticeType(noticeTypeEntity2,
                PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"createdTime")));
        Page<NoticeEntity> list3 = postRepository.findAllByNoticeType(noticeTypeEntity3,
                PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"createdTime")));
        /**교과*/
        resultMap.put("list",list.stream().map(x->new GetNotice(x)).collect(Collectors.toList()));

        /**비교과*/
        resultMap.put("list2",list2.stream().map(x->new GetNotice(x)).collect(Collectors.toList()));

        /**학생회*/
        resultMap.put("list3",list3.stream().map(x->new GetNotice(x)).collect(Collectors.toList()));

        /**
         * 위 코드는 `list` 변수에 저장된 데이터를 변환하여 `resultMap`에 "list"라는 키로 저장하는 역할을 합니다.
         *
         * 1. `list.stream()`: `list` 변수에 저장된 데이터를 스트림으로 변환합니다.
         * 2. `.map(GetPost::new)`: `GetPost` 클래스의 생성자를 이용하여 스트림의 각 요소를 변환합니다. `GetPost::new`는 `GetPost` 클래스의 생성자를 참조하는 메서드 레퍼런스입니다.
         * 3. `.collect(Collectors.toList())`: 변환된 요소들을 리스트로 수집합니다. `Collectors.toList()`는 스트림의 요소를 리스트로 수집하는 역할을 합니다.
         * 4. `resultMap.put("list", ... )`: 변환된 리스트를 `resultMap`에 "list"라는 키로 저장합니다.
         *
         * 따라서, 위 코드는 `list` 변수에 저장된 데이터를 `GetPost` 객체로 변환하여 리스트로 수집한 후, `resultMap`에 "list"라는 키로 저장하는 것을 의미합니다. 이렇게 저장된 `resultMap`은 "list"라는 키를 사용하여 변환된 리스트를 검색하거나 활용할 수 있습니다.
         * */
        resultMap.put("paging",list.getPageable());
        resultMap.put("totalCnt",list.getTotalElements()); /** 게시글 번호를 보여 주기 위해 사용*/
        resultMap.put("totalPage",list.getTotalPages());

        resultMap.put("paging2",list2.getPageable());
        resultMap.put("totalCnt2",list2.getTotalElements()); /** 게시글 번호를 보여 주기 위해 사용*/
        resultMap.put("totalPage2",list2.getTotalPages());

        resultMap.put("paging3",list3.getPageable());
        resultMap.put("totalCnt3",list3.getTotalElements()); /** 게시글 번호를 보여 주기 위해 사용*/
        resultMap.put("totalPage3",list3.getTotalPages());


        return resultMap;
    }


    //게시글 하나 게시글 번호로 입력받기
    public void readPost(int id)throws Exception {
    }



    //게시글 삭제
    public void deletePost(){

    }

    //게시글 수정
    public void upadatePost(){

    }

}
