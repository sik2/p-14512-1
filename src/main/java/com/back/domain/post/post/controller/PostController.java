package com.back.domain.post.post.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/write")
    @ResponseBody
    public String showWrite() {
        return getWriteFormHtml("");
    }

    @PostMapping("/posts/doWrite")
    @ResponseBody
    @Transactional
    public String write(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content
    ) {
        if (title.isBlank()) return getWriteFormHtml("제목을 입력해주세요.");
        if( content.isBlank()) return getWriteFormHtml("내용을 입력해주세요.");

        Post post = postService.write(title, content);

        return "%d 번 글이 생성 되었습니다.".formatted(post.getId());
    }

    private String getWriteFormHtml(String errorMessage) {
        return """
                <div style="color:red;">%s</div>
                <form action="/posts/doWrite" method="POST">
                  <input type="text" name="title" placeholder="제목"">
                  <br>
                  <textarea name="content" placeholder="내용"></textarea>
                  <br>
                  <input type="submit" value="작성">
                </form>
                """.formatted(errorMessage);
    }
}
