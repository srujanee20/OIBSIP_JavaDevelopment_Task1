package io.valkycodes.trs.interceptor;

import io.valkycodes.trs.context.AuthenticatedUserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class GuestInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthenticatedUserContext authenticatedUserContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (authenticatedUserContext.isAuthenticated()) {
            response.sendRedirect("/home");
            return false;
        }

        return true;
    }
}
