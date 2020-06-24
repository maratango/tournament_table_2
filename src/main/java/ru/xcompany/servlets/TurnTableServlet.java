package ru.xcompany.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xcompany.entities.Match;
import ru.xcompany.hibernateDao.DAO;
import ru.xcompany.hibernateDao.MatchHibernateDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TurnTableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //кодировка запроса и ответа
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        //подготовка к связи с бд
        SessionFactory factory = null;
        Locale.setDefault(Locale.ENGLISH);

        //чтение из бд турирной таблицы
        try {
            factory = new Configuration().configure().buildSessionFactory();
            DAO<Match, Integer> matchHibernateDao = new MatchHibernateDAO(factory);

            List<Object[]> turnTable = matchHibernateDao.turnTableMaking();

            //передача турнирной таблицы в атрибут запроса
            req.setAttribute("turnPositions", turnTable);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewlar/turntable.jsp");
            requestDispatcher.forward(req, resp);

        }  finally {
            if (factory != null) {
                factory.close();
            }
        }

    }

}
