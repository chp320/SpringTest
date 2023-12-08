package app.messages;

// import org.springframework.context.annotation.Bean;    // '23.12.08, @Bean 어노테이션 붙은 메서드 주석 처리함으로써 import 도 주석 처리
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("app.messages")    // 입력된 기본 패키지(여기서는 "app.messages")부터 스캔
public class AppConfig {

  // '23.12.08, MessageService, MessageRepository 클래스에 @Component 어노테이션 추가함으로써 '빈 선언'하여 불필요한 하기 메서드는 주석 처리함
  /*
  @Bean
  public MessageRepository messageRepository() {
    return new MessageRepository();
  }

  @Bean
  MessageService messageService() {
    return new MessageService(messageRepository());
  }
  */
}
