package hello.core.member;

public class Member {

    private Long id;
    private String name;
    private MemberGrade memberGrade;


    public Member(Long id, String name, MemberGrade memberGrade) {
        this.id = id;
        this.name = name;
        this.memberGrade = memberGrade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MemberGrade getGrade() {
        return memberGrade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(MemberGrade memberGrade) {
        this.memberGrade = memberGrade;
    }
}
