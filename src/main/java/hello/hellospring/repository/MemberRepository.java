package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional을 null이 반환될 경우 그것에 대한 처리를 해주기 위해서?
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
