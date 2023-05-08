/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Reservation;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import java.io.IOException;
import java.io.OutputStream;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceReservation {

    public ArrayList<Reservation> reservation;
        public ArrayList<Evenement> evenement;

    
    public static ServiceReservation instance=null;
    public boolean resultOK;
    
    private ConnectionRequest req;
    public ServiceReservation() {
         req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }
    


    public ArrayList<Reservation> parseReservations(String jsonText){
                try {

                    System.out.println(jsonText);
            reservation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reservation a = new Reservation();
                                       
                
      
              String id = ((Map<String, Object>) obj.get("event")).get("id").toString();

              String name = ((Map<String, Object>) obj.get("event")).get("name").toString();
                            
                a.setId((int) Float.parseFloat(obj.get("id").toString()));                
                a.setReservation_type(obj.get("reservation_type").toString());
                                a.setReservation_name_holder(obj.get("reservation_name_holder").toString());
                a.setUser_id_id((int) Float.parseFloat(obj.get("user_id").toString()));                

                a.setReservation_nb_ticket((int) Float.parseFloat(obj.get("reservation_nb_ticket").toString()));
try {
    String dateString = (String) obj.get("date_reservation");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(dateString);
    a.setDate_reservation(date);
} catch (ParseException e) {
    // Handle the exception, such as logging an error message or throwing a custom exception
}                a.setEvent_id_id((int) Float.parseFloat(id));
                a.setEvent_titre(name);


                reservation.add(a);
            }
        } catch (IOException ex) {
            
        }
        return reservation;
    }
    
    
        public ArrayList<Evenement> parseEvenements(String jsonText){
                try {

                    System.out.println(jsonText);
            evenement=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenement a = new Evenement();
                                       
                
                  
                a.setId((int) Float.parseFloat(obj.get("id").toString()));   
                try {
    String dateString = (String) obj.get("event_date_start");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(dateString);
    a.setEvent_date_start(date);
} catch (ParseException e) {
    // Handle the exception, such as logging an error message or throwing a custom exception
}      
  try {
    String dateString = (String) obj.get("event_date_end");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(dateString);
    a.setEvent_date_end(date);
} catch (ParseException e) {
    // Handle the exception, such as logging an error message or throwing a custom exception
}  
                
                
                a.setEvent_price_std((float) Float.parseFloat(obj.get("event_price_std").toString()));
                a.setEvent_price_vip((float) Float.parseFloat(obj.get("event_price_vip").toString()));
                a.setMax_tickets((int) Float.parseFloat(obj.get("max_tickets").toString()));
                a.setImage(obj.get("image").toString());
                a.setEvent_content(obj.get("event_content").toString());
                 a.setEvent_adresse(obj.get("event_adresse").toString());
                 a.setEvent_titre(obj.get("event_titre").toString());



                evenement.add(a);
            }
        } catch (IOException ex) {
            
        }
        return evenement;
    }
    public ArrayList<Reservation> getAllReservations(){
        String url = Statics.BASE_URL+"reservation/mobile/all";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservation = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return reservation;
    }
    
    public ArrayList<Evenement> getAllEvenements(){
        String url = Statics.BASE_URL+"reservation/mobile/event/all";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenement = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return evenement;
    }
    


public boolean addReservation(Reservation u) {
    String url = Statics.BASE_URL + "reservation/mobile/add"
            + "?event_id="+u.getEvent_id_id()+""
            + "&reservation_name_holder="+u.getReservation_name_holder()+""
            + "&reservation_type="+u.getReservation_type()+""
            + "&reservation_nb_ticket="+u.getReservation_nb_ticket()+""
            + "&user_id="+u.getUser_id_id()+""
            + "&date_reservation="+u.getDatee()+"";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}


        public boolean editReservation(Reservation u) {
    String url = Statics.BASE_URL + "reservation/mobile/edit/"+u.getId()+""
            + "?event_id="+u.getEvent_id_id()+""
            + "&reservation_name_holder="+u.getReservation_name_holder()+""
            + "&reservation_type="+u.getReservation_type()+""
            + "&reservation_nb_ticket="+u.getReservation_nb_ticket()+""
            + "&user_id="+u.getUser_id_id()+""
            + "&date_reservation="+u.getDatee()+"";


    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; // HTTP OK status
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }

    public boolean deleteReservation(int id) {
    String url = Statics.BASE_URL + "reservation/mobile/delete/"+id;
    req.setUrl(url);
    req.setHttpMethod("DELETE");
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }




}
