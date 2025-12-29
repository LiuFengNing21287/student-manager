package com.example.studentmanager.interceptor;

import com.example.studentmanager.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");

        // 1. 未登录
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }

        // 2. 获取访问路径
        String uri = request.getRequestURI();

        String role = user.getRole();

        // 3. 角色访问控制

        if (uri.startsWith("/teacher") && !"TEACHER".equals(role)) {
            response.sendRedirect("/login");
            return false;
        }

        if (uri.startsWith("/admin") && !"ADMIN".equals(role)) {
            response.sendRedirect("/login");
            return false;
        }

        if (uri.startsWith("/student") && !"STUDENT".equals(role)) {
            response.sendRedirect("/login");
            return false;
        }

        // 4. 允许访问
        return true;
    }
}
