package ru.xcompany.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

public class Match {

    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Integer game_id;
    private String home_team;
    private Integer home_goal;
    private String guest_team;
    private Integer guest_goal;

    //конструкторы
    public Match(){

    }
    public Match(/*Integer game_id, */String home_team, Integer home_goal, String guest_team, Integer guest_goal){ ;
//        game_id = game_id;
        home_team = home_team;
        home_goal = home_goal;
        guest_team = guest_team;
        guest_goal = guest_goal;

        System.out.println("атрибуты такие:" + home_team + home_goal + guest_team + guest_goal);
    }

    //game_id
    public Integer getGame_id() {
        return game_id;
    }
    public void setGame_id(int game_id) { this.game_id = game_id; }
    //home_team
    public String getHome_team() {
        return home_team;
    }
    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }
    //home_goal
    public Integer getHome_goal() {
        return home_goal;
    }
    public void setHome_goal(int home_goal) {
        this.home_goal = home_goal;
    }
    //guest_team
    public String getGuest_team() {
        return guest_team;
    }
    public void setGuest_team(String guest_team) {
        this.guest_team = guest_team;
    }
    //guest_goal
    public Integer getGuest_goal() {
        return guest_goal;
    }
    public void setGuest_goal(int guest_goal) {
        this.guest_goal = guest_goal;
    }

}
