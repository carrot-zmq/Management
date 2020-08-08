package soochow.zmq.filter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;
import soochow.zmq.service.AuthenticationService;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

// 对于一个api请求来说, 服务端接收到请求后，处理流程: filter1,filter2...filterN->servlet(DispatchServlet)->controller
public class AuthFilter extends OncePerRequestFilter {

    private final static int AUTH_FAIL_STATUS_CODE = 401;
    private final static String AUTH_FAIL_HEADER = "WWW-Authenticate";

    private final String loginUrl;

    private final UrlPathHelper urlPathHelper = new UrlPathHelper();
    private final Set<String> anonymousUrls;

    @Resource
    AuthenticationService authenticationService;

    public AuthFilter(Set<String> anonymousUrls, String loginUrl) {
        if (CollectionUtils.isEmpty(anonymousUrls)) {
            throw new IllegalArgumentException("anonymousUrls shall not be empty");
        }
        if (StringUtils.isBlank(loginUrl)) {
            throw new IllegalArgumentException("login url cannot be empty");
        }
        this.anonymousUrls = anonymousUrls;
        this.anonymousUrls.add(loginUrl);
        this.loginUrl = loginUrl;
    }

    private void redirectToLoginUrl(HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(AUTH_FAIL_STATUS_CODE);
        httpServletResponse.addHeader(AUTH_FAIL_HEADER, "session invalid");
       // httpServletResponse.sendRedirect("http:");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestPath = urlPathHelper.getRequestUri(httpServletRequest);
        if (!anonymousUrls.contains(requestPath) && !authenticationService.isAuthenticated(httpServletRequest)) {
            redirectToLoginUrl(httpServletResponse);
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
