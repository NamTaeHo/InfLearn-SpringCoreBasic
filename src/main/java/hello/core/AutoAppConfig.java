package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberRepositoryMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) //예제 코드 AppConfig 등을 지우지 않고, 사용하기 위해 넣은 것, 원래대로라면 필요 없는 코드이다.
public class AutoAppConfig {


    @Bean(name = "memberRepositoryMemory")
    MemberRepository memberRepository(){
        return new MemberRepositoryMemory();
    }


}
