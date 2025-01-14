package com.example.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getParameter("user"));
        req.setAttribute("role", req.getSession().getAttribute("role"));

        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "main":
                    resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
                    return;
                case "users":
                    resp.sendRedirect(req.getContextPath() + "/users.jhtml");
                    return;
                case "passchange":
                    resp.sendRedirect(req.getContextPath() + "/passchange.jhtml");
                    return;
            }
        }

        req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ВЫЙТИ":
                    req.getSession().invalidate();
                    resp.sendRedirect(req.getContextPath() + "/login.jhtml");
                    break;
                default:
                    break;
            }
        }
    }
}
