package com.example.app.web;

import com.example.app.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/passchange.jhtml")
public class PasschangeServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/passchange.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getSession().getAttribute("username").toString();
        String oldPassword = req.getParameter("oldpass");
        String newPassword = req.getParameter("newpass");
        String repPassword = req.getParameter("reppass");
        String action = req.getParameter("action");

        if (action.equals("СМЕНИТЬ")) {
            if (username != null && oldPassword != null && newPassword != null) {
                if (userService.checkPassword(username, oldPassword)) {
                    if (Objects.equals(repPassword, newPassword)) {
                        userService.changePassword(username, newPassword);
                        req.getSession().setAttribute("password", newPassword);
                        resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
                    } else {
                        req.setAttribute("errorMessage", "Пароли не совпадают");
                        req.getRequestDispatcher("/WEB-INF/jsp/passchange.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("errorMessage", "Старый пароль неверен");
                    req.getRequestDispatcher("/WEB-INF/jsp/passchange.jsp").forward(req, resp);
                }
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        }


    }
}
