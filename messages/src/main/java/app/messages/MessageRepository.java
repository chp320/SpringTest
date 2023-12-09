package app.messages;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {

  // Datasource 에서 데이터베이스 연결을 얻을 수 있도록 Datasource 인스턴스 주입 요청
  private DataSource dataSource;
  private static final Log logger = LogFactory.getLog(MessageRepository.class);

  public MessageRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Message saveMessage(Message message) {
    Connection c = DataSourceUtils.getConnection(dataSource);
    try {
      String insertSql = "INSERT INTO messages (`id`, `text`, `created_date`) VALUES (null, ?, ?)";
      PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);    // RETURN_GENERATED_KEYS 는 뭐하는 애지??

      // SQL 에 필요한 매개변수 준비
      ps.setString(1, message.getText());
      ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));
      int rowsAffected = ps.executeUpdate();
      if(rowsAffected>0) {
        // 성공적으로 쿼리 수행한 상태이며, 새로 저장된 메시지 id 가져온다.
        ResultSet result = ps.getGeneratedKeys();
        if(result.next()) {
          int id = result.getInt(1);    // 새로 삽입된 하나의 row만 가져오겠음
          return new Message(id, message.getText(), message.getCreatedDate());
        } else {
          logger.error("Failed to retrieve id. No row in result set");
          return null;
        }
      } else {
        // insert 실패
        logger.error("insert failed!!");
        return null;
      }

    } catch (Exception ex) {
      logger.error("Failed to save message", ex);
      try {
        c.close();
      } catch (SQLException e) {
        logger.error("Failed to close connection", e);
      }
    } finally {
      logger.debug("sql done!");
      DataSourceUtils.releaseConnection(c, dataSource);
    }
    return null;
  }
}
