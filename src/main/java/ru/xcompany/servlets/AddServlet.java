package ru.xcompany.servlets;

import ru.xcompany.entities.Team;
import ru.xcompany.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        writer.println("Method GET from AddServlet");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewlar/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        Team team = new Team(id, name, country, city);
        Model model = Model.getInstance();
        model.add(team);

        req.setAttribute("teamName", name);
        doGet(req, resp);
    }

}
