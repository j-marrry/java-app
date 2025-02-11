package com.example.app.web;

import com.example.app.domain.User;
import com.example.app.service.ServiceFactory;
import com.example.app.service.UserService;
import com.example.app.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private User currentUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("login");
        String password = req.getParameter("password");

        UserService userService = new ServiceFactory().getUserService();

        if (userService.checkPassword(username, password)) {
            currentUser = userService.findByUsername(username);

            req.getSession().setAttribute("username", currentUser.getUsername());
            req.getSession().setAttribute("password", currentUser.getPassword());
            req.getSession().setAttribute("email", currentUser.getEmail());
            req.getSession().setAttribute("lastname", currentUser.getLastname());
            req.getSession().setAttribute("firstname", currentUser.getFirstname());
            req.getSession().setAttribute("patronymic", currentUser.getPatronymic());
            req.getSession().setAttribute("birthday", currentUser.getBirthday());
            req.getSession().setAttribute("roles", currentUser.getRoles());

            resp.sendRedirect(req.getContextPath() + "/welcome.jhtml");
        }
        else {
            req.setAttribute("errorMessage", "Неверное имя пользователя или пароль");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
