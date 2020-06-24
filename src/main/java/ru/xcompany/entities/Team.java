package ru.xcompany.entities;

public class Team {

    private Integer id;
    private String name;
    private String country;
    private String city;

    //конструкторы
    public Team(){

    }
    public Team(Integer idt, String namet, String countryt, String cityt){
        id = idt;
        name = namet;
        country = countryt;
        city = cityt;
    }

    //id
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //country
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    //city
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

}
