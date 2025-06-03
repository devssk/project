package io.project.domain.member.service;

import io.project.domain.member.dto.MemberDomainDto;
import io.project.domain.member.entity.Member;
import io.project.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDomainDto.SignUpResult signUp(MemberDomainDto.SignUpCommand command) {
        command.validate();
        log.info("domain MemberService signUp started payload : {}", command);
        String email = command.email();
        String password = command.password();

        String encodePassword = passwordEncoder.encode(password);

        Member member = new Member(email, encodePassword);
        memberRepository.save(member);

        log.info("domain MemberService signUp finished");
        return new MemberDomainDto.SignUpResult(member.getEmail());
    }

    public MemberDomainDto.GetMemberResult getMember(MemberDomainDto.GetMemberCommand command) {
        command.validate();
        log.info("domain MemberService getMember started payload : {}", command);
        String email = command.email();

        Member getMember = memberRepository.getByEmail(email);

        log.info("domain MemberService getMember finished");
        return new MemberDomainDto.GetMemberResult(getMember.getEmail(), getMember.getPassword());
    }

}
