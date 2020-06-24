package ru.xcompany.model;

import ru.xcompany.entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {

    private static Model instance = new Model();

    private List<Team> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(Team team) {
        model.add(team);
    }

    public List<String> list() {
        return model.stream()
                .map(Team::getName)
                .collect(Collectors.toList());
    }

}
