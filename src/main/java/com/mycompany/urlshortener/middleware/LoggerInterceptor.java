
package com.mycompany.urlshortener.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.urlshortener.PayLoad;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggerInterceptor  implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI() + getParameters(request));
        return true;
    }

    /**
     * Executed before after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        log.info("[postHandle][" + request + "]");
    }

    /**
     * Executed after complete request is finished
     **/
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        if (ex != null)
            ex.printStackTrace();

        var token = request.getHeader("Authentication");
        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        var object = new ObjectMapper().readValue(payload, PayLoad.class);

        log.info("[afterCompletion][" + object.getName() + "][exception: " + ex + "]");
    }

    private String getParameters(final HttpServletRequest request) {
        final StringBuffer posted = new StringBuffer();
        final Enumeration<?> e = request.getParameterNames();
        if (e != null)
            posted.append("?");
        while (e != null && e.hasMoreElements()) {
            if (posted.length() > 1)
                posted.append("&");
            final String curr = (String) e.nextElement();
            posted.append(curr)
                    .append("=");
            if (curr.contains("password") || curr.contains("answer") || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }

        final String ip = request.getHeader("X-FORWARDED-FOR");
        final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (ipAddr == null || ipAddr.length() == 0)
            posted.append("&_psip=" + ipAddr);
        return posted.toString();
    }

    private String getRemoteAddr(final HttpServletRequest request) {
        final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }
}
