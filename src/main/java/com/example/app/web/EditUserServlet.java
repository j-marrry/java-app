package com.example.app.web;

import com.example.app.domain.User;
import com.example.app.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/edituser.jhtml")
public class EditUserServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        User user = userService.findByUsername(username);

        String action = req.getParameter("action");
        if (action != null) {
            if (action.equals("delete")) {
                if (username.equals(req.getSession().getAttribute("username"))) {
                    req.getSession().invalidate();
                }
                userService.delete(user.getId());
                //Storage.getInstance().deleteUser(user);
                resp.sendRedirect(req.getContextPath() + "/users.jhtml");
            }
        }
        else {
            String password = user.getPassword();
            String email = user.getEmail();
            String lastname = user.getLastname();
            String firstname = user.getFirstname();
            String patronymic = user.getPatronymic();
            String birthday = user.getBirthday();
            List<String> roles = user.getRoles();
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("email", email);
            req.setAttribute("lastname", lastname);
            req.setAttribute("firstname", firstname);
            req.setAttribute("patronymic", patronymic);
            req.setAttribute("birthday", birthday);
            req.setAttribute("roles", roles);
            req.getRequestDispatcher("/WEB-INF/jsp/edit_user.jsp").forward(req, resp);
        }

    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("login");
        User user = userService.findByUsername(name);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String patronymic = req.getParameter("patronymic");
        String birthday = req.getParameter("birthday");
        List<String> roles = Collections.singletonList(req.getParameter("roles"));

        userService.update(user.getId(), username, password, email, lastname, firstname, patronymic, birthday, roles);

        HttpSession session = req.getSession();

        if (session.getAttribute("username").equals(name)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("email", email);
            session.setAttribute("lastname", lastname);
            session.setAttribute("firstname", firstname);
            session.setAttribute("patronymic", patronymic);
            session.setAttribute("birthday", birthday);
            session.setAttribute("roles", roles);
        }

        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
