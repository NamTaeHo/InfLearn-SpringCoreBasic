package hello.core.discount;

import hello.core.member.MemberGrade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == MemberGrade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
