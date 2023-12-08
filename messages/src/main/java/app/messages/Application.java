package app.messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// '23.12.09, 애플리케이션 컨테이너 관련 기술 주석 처리
/*
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
*/

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // '23.12.08, HTTP 요청 처리하기 위한 웹 애플리케이션 동작 기능 수정
        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MessageService messageService = context.getBean(MessageService.class);
        messageService.save("Hello, Spring!");

        ((ConfigurableApplicationContext) context).close(); 
        */

        // '23.12.09, 애플리케이션 실행 시 스프링 컨테이너 구동 설정
        SpringApplication.run(Application.class, args);
    }
}