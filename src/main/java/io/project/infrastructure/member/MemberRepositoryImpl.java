package io.project.infrastructure.member;

import io.project.domain.member.entity.Member;
import io.project.domain.member.repository.MemberRepository;
import io.project.infrastructure.member.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

    @Override
    public Member getByEmail(String email) {
        return memberJpaRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("infrastructure : member not found")
        );
    }
}
