/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Association;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceAssociation {

    public ArrayList<Association> association;
        public ArrayList<Categorie> categorie;

    
    public static ServiceAssociation instance=null;
    public boolean resultOK;
    
    private ConnectionRequest req;
    public ServiceAssociation() {
         req = new ConnectionRequest();
    }

    public static ServiceAssociation getInstance() {
        if (instance == null) {
            instance = new ServiceAssociation();
        }
        return instance;
    }
    


    public ArrayList<Association> parseAssociations(String jsonText){
                try {

                    System.out.println(jsonText);
            association=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Association a = new Association();
                                       
                
        String idu = ((Map<String, Object>) obj.get("user_asso_id")).get("id").toString();
        
              String id = ((Map<String, Object>) obj.get("categoryType")).get("id").toString();

              String type = ((Map<String, Object>) obj.get("categoryType")).get("type").toString();
      
                  
                a.setId((int) Float.parseFloat(obj.get("id").toString()));                
                a.setNom(obj.get("nom").toString());     
                a.setAdresse(obj.get("adresse").toString());
                a.setNum_tel((int) Float.parseFloat(obj.get("numTel").toString()));
                a.setMail(obj.get("mail").toString());
                a.setDescription(obj.get("description").toString());
                a.setImage(obj.get("image").toString());
                a.setCategorie_id((int) Float.parseFloat(id));
                a.setTypeC(type);
  a.setUser_asso_id((int) Float.parseFloat(idu));
               

                association.add(a);
            }
        } catch (IOException ex) {
            
        }
        return association;
    }
    
    
        public ArrayList<Categorie> parseCategories(String jsonText){
                try {

                    System.out.println(jsonText);
            categorie=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Categorie a = new Categorie();
                                       
                
                  
                a.setId((int) Float.parseFloat(obj.get("id").toString()));                
                a.setNomC(obj.get("type").toString());


                categorie.add(a);
            }
        } catch (IOException ex) {
            
        }
        return categorie;
    }
    public ArrayList<Association> getAllAssociations(){
        String url = Statics.BASE_URL+"association/back/mobile/all";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                association = parseAssociations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return association;
    }
    
    public ArrayList<Categorie> getAllCategories(){
        String url = Statics.BASE_URL+"association/back/mobile/CategoryType/all";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie;
    }
    


public boolean addAssociation(Association u) {
    String url = Statics.BASE_URL + "association/back/mobile/add?"
            + "nom="+u.getNom()+"&"
            + "adresse="+u.getAdresse()+""
            + "&num_tel="+u.getNum_tel()+""
            + "&mail="+u.getMail()+""
            + "&description="+u.getDescription()+""
            + "&image="+u.getImage()+""
            + "&category_type_id="+u.getCategorie_id()+""
          + "&user_asso_id="+u.getUser_asso_id()+"";
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


        public boolean editAssociation(Association u) {
    String url = Statics.BASE_URL + "association/back/mobile/edit/"+u.getId()+""
            + "?nom="+u.getNom()+"&"
            + "adresse="+u.getAdresse()+""
            + "&num_tel="+u.getNum_tel()+""
            + "&mail="+u.getMail()+""
            + "&description="+u.getDescription()+""
            + "&image="+u.getImage()+""
            + "&category_type_id="+u.getCategorie_id()+"";

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

    public boolean deleteAssociation(int id) {
    String url = Statics.BASE_URL + "association/back/mobile/delete/" + id;
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
    
    
public boolean pdf() {
    String url = Statics.BASE_URL + "association/back/mobile/pdf";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            byte[] data = (byte[]) req.getResponseData();
            try {
                String filePath = FileSystemStorage.getInstance().getAppHomePath() + "association.pdf";
OutputStream os = FileSystemStorage.getInstance().openOutputStream(filePath);

                os.write(data);
                os.close();
                // Display the PDF file using the default PDF viewer
                Display.getInstance().execute(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultOK = req.getResponseCode() == 200; 
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
}
