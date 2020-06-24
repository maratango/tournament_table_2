package ru.xcompany.entities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xcompany.hibernateDao.DAO;
import ru.xcompany.hibernateDao.MatchHibernateDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class fileReader {

    public void readFileWriteBD(String filename) throws FileNotFoundException {

        //создаём Сканер, указываем путь до файла чтения
        File file = new File("C:/TestProjects/tournament_table_2/src/main/resources/uploads/" + filename);
        Scanner sc = new Scanner(file);
        System.out.println("путь к файлу:" + file);

        //подготовка к связи с бд
        SessionFactory factory = null;
        Locale.setDefault(Locale.ENGLISH);

        //чтение файла и запись в бд
        try {
            factory = new Configuration().configure().buildSessionFactory();
            DAO<Match, Integer> matchHibernateDao = new MatchHibernateDAO(factory);

            while(sc.hasNext()) {
                final Match match = new Match();
                match.setHome_team(sc.next());
                match.setHome_goal(sc.nextInt());
                match.setGuest_team(sc.next());
                match.setGuest_goal(sc.nextInt());
                matchHibernateDao.create(match);
            }
            //длы вовода в консоль получившейся турнирной таблицы
            List<Object[]> turnTable = matchHibernateDao.turnTableMaking();

        }  finally {
            if (factory != null) {
                factory.close();
            }
        }

    }

}
