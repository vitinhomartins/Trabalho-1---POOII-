package br.com.quiz.quiz.model;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private float score_time;

    public Usuario(int id, String username, String password, float score_time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score_time = score_time;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public float getScore_time() {
        return score_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore_time(float score_time) {
        this.score_time = score_time;
    }
}