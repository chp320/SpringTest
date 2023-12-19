package app.messages;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * HTTP 요청을 받는 컨트롤러 기능 구현
 */

// @Controller       // 실제 스프링 MVC 컨트롤러로 정의 -> '23.12.20, 클래스 레벨의 어노테이션 제거
public class MessageController {

  private MessageService messageService;
  
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/messages")    // '23.12.20, UI 렌더링하는 핸들러 메서드 추가 (뷰의 이름을 반환)
  public String index() {
      return "index";
  }
  

  @GetMapping("welcome")
  public String welcome(Model model) {
    model.addAttribute("message", "Hello, Welcome to Spring Boot!");
    return "welcome";
  }

  // [메시지 저장] 화면과 api 분리하기 위해 POST messages  uri 를 변경 -> /api/messages
  @PostMapping("/api/messages")
  @ResponseBody
  public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
      Message saved = messageService.save(data.getText());
      if(saved == null) {
        return ResponseEntity.status(500).build();
      }
      return ResponseEntity.ok(saved);
  }
  
  // [메시지 조회]
  @GetMapping("/api/messages")
  @ResponseBody
  public ResponseEntity<List<Message>> getMessages() {
    List<Message> messages = messageService.getMessages();
    return ResponseEntity.ok(messages);
  }


}
