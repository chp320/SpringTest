package app.messages;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageService {
  private MessageRepository repository;

  public MessageService(MessageRepository repository) {
    this.repository = repository;
  }

  public Message save(String text) {
    return repository.saveMessage(new Message(text));
  }

  @Transactional(readOnly = true)     // *Repository 에서 하이버네이트 세션 가져올 것이므로 '트랜잭션' 생성.
  public List<Message> getMessages() {
    return repository.getMessages();
  }
}
