package app.messages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * HTTP 요청을 받는 컨트롤러 기능 구현
 */

@Controller       // 실제 스프링 MVC 컨트롤러로 정의
@RequestMapping("/messages")
public class MessageController {

  private MessageService messageService;
  
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/welcome")
  public String welcome(Model model) {
    model.addAttribute("message", "Hello, Welcome to Spring Boot!");
    return "welcome";
  }

  @PostMapping("")
  public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
      Message saved = messageService.save(data.getText());
      if(saved == null) {
        return ResponseEntity.status(500).build();
      }
      return ResponseEntity.ok(saved);
  }
  
}
