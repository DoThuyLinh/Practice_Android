package com.example.myself.model;

public class Music {
    String id;
    String name;
    String singer;
    Boolean like;

    public Music() {
    }

    public Music(String id, String name, String singer, Boolean like) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", like=" + like +
                '}';
    }
}
