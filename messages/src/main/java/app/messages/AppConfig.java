package app.messages;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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

  // '23.12.09, 필터 등록 (FilterRegistrationBean 생성)
  /*
   * 필터 처리하기 위해 생성한 AuditingFilter 를 스프링 부트에 등록하기 위한 2가지 방법 존재
   * 방법1) web.xml 파일에 <filter>, <filter-mapping> 추가
   * 방법2) FilterRegistrationBean 만들어 설정 클래스인 AppConfig에 등록
   */
  @Bean
  public FilterRegistrationBean<AuditingFilter> auditingFilterRegistrationBean() {
    FilterRegistrationBean<AuditingFilter> registration = new FilterRegistrationBean<AuditingFilter>();
    AuditingFilter filter = new AuditingFilter();
    registration.setFilter(filter);
    registration.setOrder(Integer.MAX_VALUE);   // ???
    registration.setUrlPatterns(Arrays.asList("/messages/*"));    // /messages/ 로 시작하는 요청만 처리함

    return registration;
  }
}
