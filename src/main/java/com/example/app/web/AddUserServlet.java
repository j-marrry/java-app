package com.example.app.web;

import com.example.app.service.ServiceFactory;
import com.example.app.service.UserService;
import com.example.app.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/add_user.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String patronymic = req.getParameter("patronymic");
        String birthday = req.getParameter("birthday");
        List<String> roles = Collections.singletonList(req.getParameter("roles"));

        UserService userService = new ServiceFactory().getUserService();
        userService.create(username, password, email, lastname, firstname, patronymic, birthday, roles);

        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
