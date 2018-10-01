package com.quicktutorialz.jax.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Todo {
    private String id;
    private String description;
    private LocalDate date;

    public Todo(String id, String description, LocalDate date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public Todo() {
    }

    public Todo(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.date = LocalDate.now();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    public String toJson(){
        return "{" +
                "'id' : '" + id + '\'' +
                ", 'description' : '" + description + '\'' +
                ", 'date' : '" + date +
                "'}";
    }

    public String toXml(){
        return "<Todo><id>" + id + "</id><description>" + description + "</description><date></date>" + date + "</Todo>";
    }
}
