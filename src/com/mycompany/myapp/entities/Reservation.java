/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author msi
 */
public class Reservation {
    
    private int id;   
    private Date date_reservation;
    private String datee;
    private int reservation_nb_ticket;
    private String reservation_name_holder;
    private String reservation_type;
    private int user_id_id;  
    private int event_id_id;
    private String event_titre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public int getReservation_nb_ticket() {
        return reservation_nb_ticket;
    }

    public void setReservation_nb_ticket(int reservation_nb_ticket) {
        this.reservation_nb_ticket = reservation_nb_ticket;
    }

    public String getReservation_name_holder() {
        return reservation_name_holder;
    }

    public void setReservation_name_holder(String reservation_name_holder) {
        this.reservation_name_holder = reservation_name_holder;
    }

    public String getReservation_type() {
        return reservation_type;
    }

    public void setReservation_type(String reservation_type) {
        this.reservation_type = reservation_type;
    }

    public int getUser_id_id() {
        return user_id_id;
    }

    public void setUser_id_id(int user_id_id) {
        this.user_id_id = user_id_id;
    }

    public int getEvent_id_id() {
        return event_id_id;
    }

    public void setEvent_id_id(int event_id_id) {
        this.event_id_id = event_id_id;
    }

    public String getEvent_titre() {
        return event_titre;
    }

    public void setEvent_titre(String event_titre) {
        this.event_titre = event_titre;
    }

   
    

}
