package com.back.domain.post.post.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/write")
    public String showWrite(@ModelAttribute("form") WriteForm form) {
        return "post/post/write";
    }

    @AllArgsConstructor
    @Getter
    public static class WriteForm {
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(min = 2, max = 20, message = "제목은 2 ~ 10 자 이내로 입력해주세요.")
        String title;

        @NotBlank(message = "내용을 입력해주세요.")
        @Size(min = 2, max = 100, message = "내용은 2 ~ 100 자 이내로 입력해주세요.")
        String content;
    }

    @PostMapping("/write")
    @Transactional
    public String write(
        @ModelAttribute("form") @Valid WriteForm form,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "post/post/write";
        }

        Post post = postService.write(form.getTitle(), form.getContent());

        model.addAttribute("post", post);

        return "post/post/list";
    }
}
