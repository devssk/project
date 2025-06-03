package io.project.interfaces.member;

import io.project.application.member.dto.MemberApplicationDto;
import io.project.application.member.MemberUseCase;
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
@RequestMapping("/login")
@Slf4j
public class LoginController {

    private final MemberUseCase memberUseCase;
    private final MemberInterfacesDtoMapper mapper;

    @PostMapping("")
    public ResponseEntity<ResponseDto> login(@RequestBody MemberInterfacesDto.LoginRequestDto request) {
        request.validate();
        log.info("interfaces LoginController [POST] /login payload: {}", request);

        MemberApplicationDto.LoginResult result = memberUseCase.login(mapper.toLoginCriteria(request));

        MemberInterfacesDto.LoginResponseDto response = mapper.toLoginResponseDto(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

}
