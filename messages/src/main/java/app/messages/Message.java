package app.messages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "messages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;
  
  @Column(name = "text", nullable = false, length = 128)
  private String text;

  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
  
  // 하이버네이트가 DB에서 데이터 가져올 때 Message 객체를 재구성하는데, 이때 객체 생성 위해서 기본 생성자 사용함
  public Message() {
  }

  public Message(String text) {
    this.text = text;
    this.createdDate = new Date();
  }

  public Message(Integer id, String text, Date createdDate) {
    this.id = id;
    this.text = text;
    this.createdDate = createdDate;
  }

  public Integer getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  
}
