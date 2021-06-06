package com.spade.zhang.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyFilter extends ZuulFilter {

  private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    logger.info("{} >>> {}", request.getMethod(), request.getRequestURL().toString());
    Object accessToken = request.getParameter("token");
    if (accessToken == null) {
      logger.warn("token is empty");
      ctx.setSendZuulResponse(false);
      ctx.setResponseStatusCode(401);
      try {
        ctx.getResponse().getWriter().write("token is empty");
      } catch (Exception e) {
        logger.error("", e);
      }

      return null;
    }
    logger.info("ok");
    return null;
  }
}
