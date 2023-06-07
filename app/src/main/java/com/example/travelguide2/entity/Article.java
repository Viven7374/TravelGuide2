package com.example.travelguide2.entity;

public class Article {

    public String author,release_date,title,content,type,cover_picture;
    public int id,views;

    public Article(){

    }

    public Article(String author, String release_date, String title, String content, String type, String cover_picture, int id, int views) {
        this.author = author;
        this.release_date = release_date;
        this.title = title;
        this.content = content;
        this.type = type;
        this.cover_picture = cover_picture;
        this.id = id;
        this.views = views;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCover_picture() {
        return cover_picture;
    }

    public void setCover_picture(String cover_picture) {
        this.cover_picture = cover_picture;
    }
}
