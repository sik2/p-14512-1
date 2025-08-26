package com.back.domain.post.post.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@Validated
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/write")
    @ResponseBody
    public String showWrite() {
        return getWriteFormHtml("", "", "");
    }

    @PostMapping("/posts/doWrite")
    @ResponseBody
    @Transactional
    public String write(
            @NotBlank
            @Size(min = 2, max = 20)
            @RequestParam(defaultValue = "")
            String title,

            @NotBlank
            @Size(min = 2, max = 100)
            @RequestParam(defaultValue = "")
            String content
    ) {
        Post post = postService.write(title, content);

        return "%d 번 글이 생성 되었습니다.".formatted(post.getId());
    }

    private String getWriteFormHtml(
            String errorMessage,
            String title,
            String content
    ) {
        return """
                <div style="color:red;">%s</div>
                <form action="/posts/doWrite" method="POST">
                  <input type="text" name="title" placeholder="제목" value="%s" autofocus>
                  <br>
                  <textarea name="content" placeholder="내용">%s</textarea>
                  <br>
                  <input type="submit" value="작성">
                </form>
                """.formatted(errorMessage, title, content);
    }
}
