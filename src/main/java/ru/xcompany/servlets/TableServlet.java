package ru.xcompany.servlets;

import ru.xcompany.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        writer.println("Method GET from TableServlet");

        Model model = Model.getInstance();
        List<String> names = model.list();
        req.setAttribute("teamNames", names);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewlar/table.jsp");
        requestDispatcher.forward(req, resp);
    }

}
