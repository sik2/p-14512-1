package com.back.domain.member.member.service;

import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public long count() {
        return memberRepository.count();
    }

    public Member create(String username, String password, String email) {
        Member member = new Member(username, passwordEncoder.encode(password), email);

        return this.memberRepository.save(member);
    }

    public Optional findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
