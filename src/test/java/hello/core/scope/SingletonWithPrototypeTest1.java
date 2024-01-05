package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);


        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }


    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);


    }

    @Scope("singleton")
    @Component
    @RequiredArgsConstructor
    static class ClientBean {
   /*
        private final PrototypeBean prototypeBean; //생성 시점에 주입

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
    */


        private final Provider<PrototypeBean> prototypeBeanProvider;

        /*
        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider){
            this.prototypeBeanProvider=prototypeBeanProvider;
        }
*/
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

        //Provider는 DI가 아니고 DL만 수행한다.
        //즉 실행할때마다 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.
        //그러므로   싱글톤 내부에 직접 존재하는 프로토타입 빈이 참조만 당해서 새로 생성되지 못하는 반면
        //싱글톤 내부에 직접적으로 존재하지 않고, ObjectProvider를 통해 간접적으로 링크되어있는 프로토타입 빈은
        //참조될때마다 새롭게 생성된다.

    }


    @Scope("prototype")
    @Component
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }


    }


}
