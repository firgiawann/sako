package com.klp4.sako.model;

public class Admin extends User {
    public Admin(String id, String nama, String username, String password) {
        super(id, nama, username, password, "ADMIN");
    }

    @Override
    public void login() {
        System.out.println("Admin " + this.nama + " login.");
    }

    @Override
    public void lihatMenu() {
        System.out.println("Admin melihat menu (full access)...");
    }

    public void kelolaData() {
        System.out.println("Admin mengelola data...");
    }

    public void aturSistem() {
        System.out.println("Admin mengatur sistem...");
    }
}
