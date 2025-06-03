package io.project.interfaces.member;

import io.project.domain.member.dto.MemberDomainDto;
import io.project.domain.member.service.MemberService;
import io.project.interfaces.ResponseCode;
import io.project.interfaces.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberInterfacesDtoMapper mapper;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDto> signUp(@RequestBody MemberInterfacesDto.SignUpRequestDto request) {
        request.validate();
        log.info("interfaces MemberController [POST] /members/signUp payload: {}", request);

        MemberDomainDto.SignUpResult result = memberService.signUp(mapper.toSignUpCommand(request));

        MemberInterfacesDto.SignUpResponseDto response = mapper.toSignUpResponseDto(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

}
