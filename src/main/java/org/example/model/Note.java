package org.example.model;

public class Note {

    private Long id;
    private String title;
    private String date;
    private boolean status;
    private Long user_id;

    public Note() {
    }


    public Long getId() {
        return id;
    }


    public Note(String title, String date, boolean status, Long user_id) {
        this.title = title;
        this.date = date;
        this.status = status;
        this.user_id = user_id;
    }

    public Note(boolean status, Long user_id) {
        this.status = status;
        this.user_id = user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
