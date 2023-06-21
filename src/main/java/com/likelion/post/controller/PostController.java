package com.likelion.post.controller;

import com.likelion.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/index")
    public String noticeIndex(Model model,@RequestParam(required =false, defaultValue = "0") Integer page,
                              @RequestParam(required = false,defaultValue = "4")Integer size) throws Exception{
        try{
            model.addAttribute("resultMap",postService.readPostAll(page,size));
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return "/index";
    }


    @GetMapping("/post/list")
    public String getPostList(Model model,@RequestParam(required =false, defaultValue = "0") Integer page,
                              @RequestParam(required = false,defaultValue = "5")Integer size) throws Exception{
        try{
            model.addAttribute("resultMap",postService.readPostAll(page,size));
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return "/post/list";
    }

    @GetMapping("/post/list2")
    public String getPostList2(Model model,@RequestParam(required =false, defaultValue = "0") Integer page,
                              @RequestParam(required = false,defaultValue = "5")Integer size) throws Exception{
        try{
            model.addAttribute("resultMap",postService.readPostAll(page,size));
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return "/post/list2";
    }

    @GetMapping("/post/list3")
    public String getPostList3(Model model,@RequestParam(required =false, defaultValue = "0") Integer page,
                              @RequestParam(required = false,defaultValue = "5")Integer size) throws Exception{
        try{
            model.addAttribute("resultMap",postService.readPostAll(page,size));
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return "/post/list3";
    }


}
//<button type="button" class="btn btn-danger" onclick="fnDelete()">Delete</button> 삭제 버튼