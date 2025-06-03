package io.project.application.member.dto;

public class MemberApplicationDto {

    public record LoginCriteria(
            String email,
            String password
    ) {
        public void validate() {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("application : email is required");
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                throw new IllegalArgumentException("invalid email format");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("application : password is required");
            }
            if (password.length() > 20) {
                throw new IllegalArgumentException("password is too long, less than 20 characters");
            }
        }
    }

    public record LoginResult(
            String token
    ) {}

}
