package com.example.app.web;

import com.example.app.domain.User;
import com.example.app.service.ServiceFactory;
import com.example.app.service.UserService;
import com.example.app.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class EditUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        //User user = Storage.getInstance().getUser(username);
        UserService userService = new ServiceFactory().getUserService();
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
            String role = user.getRole();
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("email", email);
            req.setAttribute("lastname", lastname);
            req.setAttribute("firstname", firstname);
            req.setAttribute("patronymic", patronymic);
            req.setAttribute("birthday", birthday);
            req.setAttribute("role", role);
            req.getRequestDispatcher("/WEB-INF/jsp/edit_user.jsp").forward(req, resp);
        }

    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("login");
        UserService userService = new ServiceFactory().getUserService();
        User user = userService.findByUsername(name);
        //User user = Storage.getInstance().getUser(name);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String patronymic = req.getParameter("patronymic");
        String birthday = req.getParameter("birthday");
        String role = req.getParameter("role");

        userService.update(user.getId(), username, password, email, lastname, firstname, patronymic, birthday, role);
        /*user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setLastname(lastname);
        user.setFirstname(firstname);
        user.setPatronymic(patronymic);
        user.setBirthday(birthday);
        user.setRole(role);*/

        HttpSession session = req.getSession();

        if (session.getAttribute("username").equals(name)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("email", email);
            session.setAttribute("lastname", lastname);
            session.setAttribute("firstname", firstname);
            session.setAttribute("patronymic", patronymic);
            session.setAttribute("birthday", birthday);
            session.setAttribute("role", role);
        }

        resp.sendRedirect(req.getContextPath() + "/users.jhtml");
    }
}
