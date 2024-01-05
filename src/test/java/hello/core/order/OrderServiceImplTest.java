package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberGrade;
import hello.core.member.MemberRepositoryMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemberRepositoryMemory memberRepository = new MemberRepositoryMemory();
        memberRepository.save(new Member(1L,"name", MemberGrade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemberRepositoryMemory(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L,"itemA",10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }

}