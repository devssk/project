package io.project.interfaces.member;

import io.project.application.member.dto.MemberApplicationDto;
import io.project.domain.member.dto.MemberDomainDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberInterfacesDtoMapper {

    MemberApplicationDto.LoginCriteria toLoginCriteria(MemberInterfacesDto.LoginRequestDto request);
    MemberInterfacesDto.LoginResponseDto toLoginResponseDto(MemberApplicationDto.LoginResult result);

    MemberDomainDto.SignUpCommand toSignUpCommand(MemberInterfacesDto.SignUpRequestDto request);
    MemberInterfacesDto.SignUpResponseDto toSignUpResponseDto(MemberDomainDto.SignUpResult result);

}
