package com.aaron_maze.maze.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Dins interceptor");
        HttpSession session = request.getSession();
        String nameUser= (String) session.getAttribute("user");

        if (nameUser == null){
            //usuario no ha fet login
            response.sendRedirect("/login");
            return false;
        }
        //ha fet login
        return true;
    }
}
