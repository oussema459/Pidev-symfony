/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author 21692
 */
public class Evenement {

    
    private int id;
    private String event_titre;
    private String event_adresse;
    private String event_content;
    private float event_price_std;
    private float event_price_vip;
    private int max_tickets;
    private String image;
    private Date event_date_start;
    private String datee;
    private Date event_date_end;
    private String datee_fin;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_titre() {
        return event_titre;
    }

    public void setEvent_titre(String event_titre) {
        this.event_titre = event_titre;
    }

    public String getEvent_adresse() {
        return event_adresse;
    }

    public void setEvent_adresse(String event_adresse) {
        this.event_adresse = event_adresse;
    }

    public String getEvent_content() {
        return event_content;
    }

    public void setEvent_content(String event_content) {
        this.event_content = event_content;
    }

    public float getEvent_price_std() {
        return event_price_std;
    }

    public void setEvent_price_std(float event_price_std) {
        this.event_price_std = event_price_std;
    }

    public float getEvent_price_vip() {
        return event_price_vip;
    }

    public void setEvent_price_vip(float event_price_vip) {
        this.event_price_vip = event_price_vip;
    }

    public int getMax_tickets() {
        return max_tickets;
    }

    public void setMax_tickets(int max_tickets) {
        this.max_tickets = max_tickets;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getEvent_date_start() {
        return event_date_start;
    }

    public void setEvent_date_start(Date event_date_start) {
        this.event_date_start = event_date_start;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public Date getEvent_date_end() {
        return event_date_end;
    }

    public void setEvent_date_end(Date event_date_end) {
        this.event_date_end = event_date_end;
    }

    public String getDatee_fin() {
        return datee_fin;
    }

    public void setDatee_fin(String datee_fin) {
        this.datee_fin = datee_fin;
    }

   
        
}
