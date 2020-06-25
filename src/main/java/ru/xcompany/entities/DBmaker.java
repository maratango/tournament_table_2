// не используется, создан для тестовых целей

package ru.xcompany.entities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xcompany.hibernateDao.DAO;
import ru.xcompany.hibernateDao.MatchHibernateDAO;

import java.util.Locale;

public class DBmaker {

    public void addRecord() {
        SessionFactory factory = null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            factory = new Configuration().configure().buildSessionFactory();
            DAO<Match, Integer> matchHibernateDao = new MatchHibernateDAO(factory);

            final Match match = new Match();
//            match.setGame_id(2);
            match.setHome_team("Ливерпуль");
            match.setHome_goal(2);
            match.setGuest_team("Челси");
            match.setGuest_goal(1);
            matchHibernateDao.create(match);
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

}
