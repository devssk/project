package io.project.dto;

import io.project.domain.member.dto.MemberDomainDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DomainDtoTest {

    @Test
    @DisplayName("SignUpCommand 유효성 테스트 - 성공")
    public void memberDomainTest01() {
        // given
        String email = "test@test.com";
        String password = "test123";
        MemberDomainDto.SignUpCommand signUpCommand = new MemberDomainDto.SignUpCommand(email, password);

        // when
        signUpCommand.validate();

        // then
        assertEquals(email, signUpCommand.email());
        assertEquals(password, signUpCommand.password());
    }

    @Test
    @DisplayName("SignUpCommand 유효성 테스트 - email 검사")
    public void memberDomainTest02() {
        // given
        String email = "test";
        String password = "test123";
        MemberDomainDto.SignUpCommand signUpCommand = new MemberDomainDto.SignUpCommand(email, password);

        // when
        Throwable throwable = assertThrows(IllegalArgumentException.class, signUpCommand::validate);

        // then
        assertEquals("domain : invalid email format", throwable.getMessage());
    }

}
