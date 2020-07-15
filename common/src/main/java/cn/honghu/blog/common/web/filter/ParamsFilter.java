package cn.honghu.blog.common.web.filter;

import cn.honghu.blog.common.utils.DateUtils;
import cn.honghu.blog.common.utils.InterceptorUtil;
import cn.honghu.blog.common.web.support.json.WebUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class ParamsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ParamFilter init! 过滤器初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        long start = DateUtils.nowToMillisecond();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //绑定日志打印唯一请求Id
        Object requestId = ((HttpServletRequest) request).getHeader(InterceptorUtil.REQUEST_ID);
        String logRequestId;
        if (null != requestId) {
            logRequestId = String.valueOf(requestId);
        } else {
            logRequestId = DateUtils.nowToMillisecond() + "";
        }
        MDC.put(InterceptorUtil.REQUEST_ID, logRequestId);
        String requestUrl = req.getContextPath() + req.getServletPath();
        String method = req.getMethod();
        log.info("当前请求：{} | {} |,参数为{}", method, requestUrl, WebUtil.getParams(req, method));
        filterChain.doFilter(req, rep);
        log.info("返回请求,耗时：{} ms", DateUtils.nowToMillisecond() - start);
    }

    @Override
    public void destroy() {
        log.info("ParamFilter destroy! 过滤器销毁...");
    }
}
