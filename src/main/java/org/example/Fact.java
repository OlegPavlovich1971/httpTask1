package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fact implements Comparable<Fact> {
    // аннотация @JsonProperty используется для пребразования ключа json в поле класса
    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("type")
    private String type;
    @JsonProperty("user")
    private String user;
    @JsonProperty("upvotes")
    private Integer upvotes;

    public Fact() {
    }

    public Fact setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public Fact setText(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return text;
    }

    public Fact setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public Fact setUser(String user) {
        this.user = user;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Fact setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
        return this;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Fact:" +
                "\n id = " + id +
                "\n text = " + text +
                "\n type = " + type +
                "\n user = " + user +
                "\n upvotes = " + upvotes;
    }

    @Override
    public int compareTo(Fact o) {
        return this.getUpvotes().compareTo(o.getUpvotes());
    }
}
