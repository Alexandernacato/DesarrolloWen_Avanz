/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.espe.app.controller;

import com.espe.app.dao.UserDAO;
import com.espe.app.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "UserController", urlPatterns = {"/User"})
public class UserController extends HttpServlet {

    private final UserDAO userDao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String option = request.getParameter("option");
        if (option == null) option = "findAll";

        switch (option) {
            case "new":
                request.getRequestDispatcher("/formUser.jsp").forward(request, response);
                break;
            case "update":
                Long id = Long.parseLong(request.getParameter("id"));
                User user = userDao.findById(id);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/formUser.jsp").forward(request, response);
                break;
            case "delete":
                Long idDelete = Long.parseLong(request.getParameter("id"));
                userDao.delete(idDelete);
                response.sendRedirect(request.getContextPath() + "/User");
                break;
            default:
                List<User> users = userDao.findAll();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(name, lastname, age);

        if (idParam == null || idParam.isEmpty()) {
            userDao.save(user);
        } else {
            user.setId(Long.parseLong(idParam));
            userDao.update(user);
        }

        response.sendRedirect(request.getContextPath() + "/User");
    }
}

