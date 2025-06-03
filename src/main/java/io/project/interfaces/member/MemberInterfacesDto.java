package io.project.interfaces.member;

public class MemberInterfacesDto {

    public record LoginRequestDto (
            String email,
            String password
    ) {
        public void validate() {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("interfaces : Email is required");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("interfaces : Password is required");
            }
        }
    }

    public record LoginResponseDto (
            String token
    ) {}

    public record SignUpRequestDto(
            String email,
            String password
    ) {
        public void validate() {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("interfaces : Email is required");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("interfaces : Password is required");
            }
        }
    }

    public record SignUpResponseDto (
            String email
    ) {}

}
