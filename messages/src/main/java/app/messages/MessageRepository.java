package app.messages;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;     // HQL 실행하기 위함
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {
  private SessionFactory sessionFactory;

  public MessageRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Message saveMessage(Message message) {
    Session session = sessionFactory.openSession();
    session.save(message);
    
    return message;
  }

  public List<Message> getMessages() {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message";      // HQL (Hibernate Query Language)
    Query<Message> query = session.createQuery(hql, Message.class);

    return query.list();
  }
}
