package com.itsweb.backend.controller;

import com.itsweb.backend.domian.Post;
import com.itsweb.backend.domian.PostEditDTO;
import com.itsweb.backend.domian.PostWriteDTO;
import com.itsweb.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    //게시글 전체조회
    @GetMapping("/post")
    public List<Post> getAllPost(){
        List<Post> list = postService.findAllPost();
        return list;
    }
    //게시글 상세조회
    @GetMapping("/post/{id}")
    public Post getDetailPost(@PathVariable("id") Long id) throws NoSuchElementException {
        Post post = postService.findPostDetail(id);
        return post;
    }
//    //게시글 작성
    @PostMapping("/post")
    public ResponseEntity<?> writePost(@RequestBody PostWriteDTO postWriteDTO){
        Post post = new Post();
        post.setTitle(postWriteDTO.getTitle());
        post.setContent(postWriteDTO.getContent());
        postService.save(post);
        return ResponseEntity.ok().body(post.getId());
    }
//    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
        postService.deleteById(id);
        return ResponseEntity.ok().body(id);
    }
//    //게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<?> editPost(@RequestBody PostEditDTO postEditDTO, @PathVariable Long id){
        Post post = postService.findPostDetail(id);
        post.setTitle(postEditDTO.getTitle());
        post.setContent(postEditDTO.getContent());
        postService.save(post);
        return ResponseEntity.ok().body(id);
    }
}
