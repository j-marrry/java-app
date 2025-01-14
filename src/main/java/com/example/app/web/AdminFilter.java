package com.example.app.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/users.jhtml", "/adduser.jhtml", "/edituser.jhtml"})
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String welcomeURI = request.getContextPath() + "/welcome.jhtml";
        String loginURI = request.getContextPath() + "/login.jhtml";
        boolean loggedIn = session != null && session.getAttribute("username") != null;

        if (loggedIn) {
            String role = (String) session.getAttribute("role");
            if (role.equals("admin")) {
                chain.doFilter(request, response);
            }
            else {
                response.sendRedirect(welcomeURI);
            }
        }
        else {
            response.sendRedirect(loginURI);
        }
    }

}