package com.back.domain.post.post.controller;

import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/write")
    @ResponseBody
    public String write() {
        return """
                <form action="http://localhost:8080/posts/doWrite" target="_blank">
                  <input type="text" name="title" placeholder="제목" value="안녕">
                  <br>
                  <textarea name="content" placeholder="내용">반가워</textarea>
                  <br>
                  <input type="submit" value="작성">
                </form>
                """;
    }
}
