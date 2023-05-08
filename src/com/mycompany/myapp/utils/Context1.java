package com.mycompany.myapp.utils;

import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omar
 */
public class Context1 {  
   private static Context1 instance;  
   public static Context1 getInstance(){  
     return instance == null ? new Context1() : instance;  
   }  
   private static Map<String, Object> contextObjects = new HashMap<String, Object>();  
   private static Stage currentStage;  
   public Map<String, Object> getContextObjects(){  
     return contextObjects;  
   }  
   public Object getContextObject(String key){  
     return contextObjects.get(key);  
   }  
   public Object removeContextObject(String key){  
     return contextObjects.remove(key);  
   }  
   public void addContextObject(String key, Object value){  
     contextObjects.put(key, value);  
   }  
   public void clearContextObjects(){  
     contextObjects.clear();  
   }  
   public Stage getCurrentStage() {  
     return currentStage;  
   }  
   public void setCurrentStage(Stage stage) {  
     currentStage = stage;  
   }  
 } 