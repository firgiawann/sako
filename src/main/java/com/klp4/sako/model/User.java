package com.klp4.sako.model;

public abstract class User {
    protected String id;
    protected String nama;
    protected String username;
    protected String password;
    protected String role;

    public User(String id, String nama, String username, String password, String role) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public abstract void login();
    public abstract void lihatMenu();
}
