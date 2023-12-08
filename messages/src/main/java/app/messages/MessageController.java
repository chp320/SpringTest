package app.messages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HTTP 요청을 받는 컨트롤러 기능 구현
 */

@Controller       // 실제 스프링 MVC 컨트롤러로 정의
@RequestMapping("/messages")
public class MessageController {
  @GetMapping("/welcome")
  public String welcome() {
    return "Hello, Welcome to Spring Boot!";
  }
}
