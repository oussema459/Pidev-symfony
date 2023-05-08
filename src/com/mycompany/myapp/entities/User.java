/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class User {
    
 static    private int id;
   
    private String nom;
    private String email;
    private String prenom;
    private int num_tel;
    
    private String role;
    private String password;

    
    
    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String username, String email) {
        this.nom = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", prenom=" + prenom + ", num_tel=" + num_tel + ", role=" + role + ", password=" + password + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
 

   
    
    
    
    
    
    
    
    
}