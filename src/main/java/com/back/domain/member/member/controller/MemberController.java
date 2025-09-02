package com.back.domain.member.member.controller;

import com.back.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String showRegister(RegisterForm registerForm) {
        return "member/member/register";
    }

    @ToString
    @Getter
    @Setter
    public static class RegisterForm{
        @Size(min=3, max= 25)
        @NotBlank(message = "유저 ID는 필수 항목입니다.")
        private String username;

        @NotBlank(message = "패스워드는 필수 항목입니다.")
        private String password;

        @NotBlank(message = "패스워드 확인은 필수 항목입니다.")
        private String password_conform;

        @NotBlank(message = "이메일은  필수 항목입니다.")
        @Email
        private String email;
    }

    @PostMapping("/register")
    public String register(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/member/register";
        }

        System.out.println("registerForm : " + registerForm);
        // TODO: 검증 처리

        return "post/post/list";
    }
}
