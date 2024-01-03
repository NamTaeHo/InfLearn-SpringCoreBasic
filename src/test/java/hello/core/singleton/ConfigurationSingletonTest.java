package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        System.out.println("--------------------1---------------------");
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("--------------------2---------------------");
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("--------------------3---------------------");
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        System.out.println("--------------------4---------------------");
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        System.out.println("--------------------5---------------------");

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }


    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        
    }

}
