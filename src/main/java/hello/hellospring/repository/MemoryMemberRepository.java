package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository{

    //실무에서는 공유되는 객체일 경우 concurrentHashMap을 써줘야 한다.
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L; //키값을 생성? //얘도 원래는 atomLong같은거 써야함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 수 있는 경우에는 Optional로 감싸서
                                                    // 보내면 클라이언트단에서 무언가를 할 수 있때
        }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //hashMap의 value들을 stram으로 만듬
                .filter(member-> member.getName().equals(name))
                .findAny(); //findAny()는 Optional객체를 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
