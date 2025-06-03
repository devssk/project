package io.project.domain.member.dto;

public class MemberDomainDto {

    public record SignUpCommand(
            String email,
            String password
    ) {
        public void validate() {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("domain : email is required");
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                throw new IllegalArgumentException("domain : invalid email format");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("domain : password is required");
            }
            if (password.length() > 20) {
                throw new IllegalArgumentException("domain : password is too long, less than 20 characters");
            }
        }
    }

    public record GetMemberCommand(
            String email
    ) {
        public void validate() {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("domain : email is required");
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                throw new IllegalArgumentException("domain : invalid email format");
            }
        }
    }

    public record SignUpResult(
            String email
    ) {}

    public record GetMemberResult(
            String email,
            String password
    ) {}

}
