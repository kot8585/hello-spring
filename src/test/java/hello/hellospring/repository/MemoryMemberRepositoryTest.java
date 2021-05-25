package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void clear() { //이거 안해주면 에러남!!!!
        // 테스트는 서로 의존관계가 없도록 설계되어야 한다.
        //그래서 하나의 테스트가 끝나면 공용 데이터들을 깔끔하게 정리해줘야 한다.
        repository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("효정");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("효정1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("효정2");
        repository.save(member2);

        Member result = repository.findByName("효정2").get();
        assertThat(member2).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("효정1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("효정2");
        repository.save(member2);

        List<Member> result =  repository.findAll();
        //하나씩 꺼내서 비교해봐야되나...? 안에꺼까지 비교해주는ㄴ 메소드 있을껄?
       //아니면 notnull?
        assertThat(result.size()).isEqualTo(2);
    }
}
