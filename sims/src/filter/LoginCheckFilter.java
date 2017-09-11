package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//用于检查用户是否登录了系统的过滤器<br>
public class LoginCheckFilter implements Filter {

  @Override
  public void init(FilterConfig cfg) throws ServletException {
     
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest)req;
      if(request.getSession().getAttribute("username")==null){
      	request.getRequestDispatcher("/login.jsp").forward(req, res);
      }else{
      	chain.doFilter(req, res);
      }
  }

  @Override
  public void destroy() {
  }
}
