package io.project.application.member.dto;

import io.project.domain.member.dto.MemberDomainDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberApplicationDtoMapper {

    MemberDomainDto.GetMemberCommand toGetMemberCommand(MemberApplicationDto.LoginCriteria criteria);
    MemberApplicationDto.LoginResult toLoginResult(String token);

}
