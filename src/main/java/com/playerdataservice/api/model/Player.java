package com.playerdataservice.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Player {
    public String playerID;
    public int birthYear;
    public int birthMonth;
    public int birthDay;
    public String birthCountry;
    public String birthState;
    public String birthCity;
    public int deathYear;
    public int deathMonth;
    public int deathDay;
    public String deathCountry;
    public String deathState;
    public String deathCity;
    public String nameFirst;
    public String nameLast;
    public String nameGiven;
    public int weight;
    public int height;
    public String bats;
    @JsonProperty("throws")
    public String myThrows;
    public LocalDate debut;
    public LocalDate finalGame;
    public String retroID;
    public String bbrefID;

}