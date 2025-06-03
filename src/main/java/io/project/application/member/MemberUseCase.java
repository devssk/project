package io.project.application.member;

import io.project.application.member.dto.MemberApplicationDto;
import io.project.application.member.dto.MemberApplicationDtoMapper;
import io.project.common.security.jwt.JwtTokenProvider;
import io.project.domain.member.dto.MemberDomainDto;
import io.project.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberUseCase {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberApplicationDtoMapper mapper;

    public MemberApplicationDto.LoginResult login(MemberApplicationDto.LoginCriteria criteria) {
        criteria.validate();
        log.info("application MemberUseCase login useCase started, payload : {}", criteria);

        String email = criteria.email();
        String password = criteria.password();

        MemberDomainDto.GetMemberResult getMemberResult = memberService.getMember(mapper.toGetMemberCommand(criteria));

        if (!passwordEncoder.matches(password, getMemberResult.password())) {
            throw new IllegalArgumentException("application : password error");
        }

        String token = jwtTokenProvider.createToken(email);

        log.info("application MemberUseCase login useCase finished");
        return mapper.toLoginResult(token);
    }

}
