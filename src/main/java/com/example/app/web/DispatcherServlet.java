package com.example.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.jhtml")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if(servletPath.equals("/welcome.jhtml")){
            WelcomeServlet welcomeServlet = new WelcomeServlet();
            welcomeServlet.doGet(req, resp);
        }
        else if(servletPath.equals("/login.jhtml")){
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doGet(req, resp);
        }
        else if(servletPath.equals("/passchange.jhtml")){
            PasschangeServlet passchangeServlet = new PasschangeServlet();
            passchangeServlet.doGet(req, resp);
        }
        else if (servletPath.equals("/users.jhtml")) {
            UsersServlet usersServlet = new UsersServlet();
            usersServlet.doGet(req, resp);
        }
        else if (servletPath.equals("/edituser.jhtml")) {
            EditUserServlet editUserServlet = new EditUserServlet();
            editUserServlet.doGet(req, resp);
        }
        else if (servletPath.equals("/adduser.jhtml")) {
            AddUserServlet addUserServlet = new AddUserServlet();
            addUserServlet.doGet(req, resp);
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if(servletPath.equals("/welcome.jhtml")){
            WelcomeServlet welcomeServlet = new WelcomeServlet();
            welcomeServlet.doPost(req, resp);
        }
        else if(servletPath.equals("/login.jhtml")){
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doPost(req, resp);
        }
        else if(servletPath.equals("/passchange.jhtml")){
            PasschangeServlet passchangeServlet = new PasschangeServlet();
            passchangeServlet.doPost(req, resp);
        }
        else if(servletPath.equals("/users.jhtml")){
            UsersServlet usersServlet = new UsersServlet();
            usersServlet.doPost(req, resp);
        }
        else if(servletPath.equals("/edituser.jhtml")){
            EditUserServlet editUserServlet = new EditUserServlet();
            editUserServlet.doPost(req, resp);
        }
        else if(servletPath.equals("/adduser.jhtml")){
            AddUserServlet addUserServlet = new AddUserServlet();
            addUserServlet.doPost(req, resp);
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}