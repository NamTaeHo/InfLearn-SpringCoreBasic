package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

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
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository2 = " + memberRepository);
        System.out.println("discountPolicy2 = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice,discountPrice);
    }

    //test용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
