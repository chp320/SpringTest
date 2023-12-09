package app.messages;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

/**
 * 서블릿에 도착하기 전, HTTP 요청에 대한 필터링 작업 수행을 위한 클래스(필터)
 * 필터 생성 방법
 * 방법1) javax.servlet.Filter 인터페이스 구현(implements)
 * 방법2) org.springframework.web.filter.GenericFilterBean 확장(extends)    <-- 여기서 수행할 방법
 */
public class AuditingFilter extends GenericFilterBean{

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
        long start = new Date().getTime();
        chain.doFilter(req, res);

        long elapsed = new Date().getTime() - start;
        HttpServletRequest request = (HttpServletRequest) req;
        logger.debug("Request[uri=" + request.getRequestURI() + ", method=" + request.getMethod() + "] completed in " + elapsed + " ms" );
  }
}
