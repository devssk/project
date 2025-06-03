package io.project.domain.member.repository;

import io.project.domain.member.entity.Member;

public interface MemberRepository {

    Member save(Member member);
    boolean existsByEmail(String email);
    Member getByEmail(String email);

}
