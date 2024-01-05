package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class OrderServiceImpl implements OrderService {

    //test용도
    @Getter
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


//private final MemberRepository memberRepository = new MemoryMemberRepository();
//private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    /*
    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository1 = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy1 = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

     */



    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        //System.out.println("memberRepository2 = " + memberRepository);
        //System.out.println("discountPolicy2 = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }

}
