package ru.xcompany.hibernateDao;

import com.sun.istack.internal.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.xcompany.entities.Match;

import java.util.List;

public class MatchHibernateDAO implements DAO<Match, Integer> {

    private final SessionFactory factory;

    public MatchHibernateDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final Match match) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(match);

            System.out.println(match.getHome_team());
            System.out.println(match.getHome_goal());
            System.out.println(match.getGuest_team());
            System.out.println(match.getGuest_goal());

            session.getTransaction().commit();
        }
    }

    @Override
    public Match read(@NotNull final Integer id) {
        try (final Session session = factory.openSession()) {

            final Match result = session.get(Match.class, id);

            return result != null ? result : new Match();
        }
    }

    @Override
    public void update(@NotNull final Match match) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(match);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Match match) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(match);

            session.getTransaction().commit();
        }
    }

    public List<Object[]> turnTableMaking () {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            List<Object[]> turnTable = session.createSQLQuery("select * from turnTable").list();

            turnTable.forEach(p -> System.out.println(
                    "team: "+p[0]+
                    " point: "+p[1]+
                    " win: "+p[2]+
                    " draw: "+p[3]+
                    " lose: "+p[4]+
                    " gs: "+p[5]+
                    " gc: "+p[6]+
                    " gd: "+p[7]));

            return turnTable;
        }
    }



}
