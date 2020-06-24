package ru.xcompany.hibernateDao;

import java.util.List;

public interface DAO<Entity, Key> {
    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity);
    void delete(Entity entity);
    List<Object[]> turnTableMaking();
}
