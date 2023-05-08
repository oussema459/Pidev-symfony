/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package Reservation.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.services.ServiceReservation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ModifierReservation extends BaseForm {
   String Imagecode;
   String fileName="";
      int idR= 0;

                       ServiceReservation sp = new ServiceReservation();
           int selectedId = 0;


    public ModifierReservation(Resources res,Form previous,Reservation fi) {
        super("Modifier Reservation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Reservation");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
                add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(2, 
                            facebook, twitter
                    )
                )
        ));
  



       
Picker date_reservation = new Picker();
date_reservation.setDate(fi.getDate_reservation());
date_reservation.setType(Display.PICKER_TYPE_DATE);
date_reservation.setUIID("TextFieldBlack");


       addStringValue("Date Reservation", date_reservation); 
        

        
                
        Picker type = new Picker();
        type.setType(Display.PICKER_TYPE_STRINGS);
        type.setStrings("VIP", "STANDART");
        type.setUIID("TextFieldBlack");
                  addStringValue("Type de Reservation", type);

     if(fi.getReservation_type()!=null) {
                 if (fi.getReservation_type().equals("VIP")) {
            type.setSelectedString("VIP");
        } else if (fi.getReservation_type().equals("STANDART")) {
            type.setSelectedString("STANDART");
        } else {
            type.setSelectedString("Autre");
        }
     }

        TextField reservation_nb_ticket = new TextField();
        reservation_nb_ticket.setText(Float.toString(fi.getReservation_nb_ticket()));
        reservation_nb_ticket.setUIID("TextFieldBlack");
        addStringValue("nb de ticket", reservation_nb_ticket);
        
        


                
               TextField reservation_name_holder = new TextField();
               reservation_name_holder.setText(fi.getReservation_name_holder());
        reservation_name_holder.setUIID("TextFieldBlack");
        addStringValue("Name Holder", reservation_name_holder);
        
        
        
        
        
        
        
        
        
        selectedId = fi.getEvent_id_id();




         
        Button Ajouter = new Button("Modifier");
        
       
        Ajouter.addActionListener((evt) -> {

                  if (type.getText().equals("")||(reservation_nb_ticket.getText().equals("")))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
           
                                 fi.setReservation_nb_ticket((int) Float.parseFloat(reservation_nb_ticket.getText()));
            L10NManager l10n = L10NManager.getInstance();  
            fi.setDatee(l10n.formatDateShortStyle(date_reservation.getDate()));
            System.err.println(l10n.formatDateShortStyle(date_reservation.getDate()));
            fi.setReservation_type(type.getText());
            fi.setReservation_name_holder(reservation_name_holder.getText());
            //bil session baad
            fi.setUser_id_id(1);
 fi.setEvent_id_id(selectedId);
            
 sp.editReservation(fi);
            Dialog.show("Success","Reservation modifier avec success",new Command("OK"));
            new AllReservation(res).show();
                
            }      
        });

        addStringValue("", FlowLayout.encloseRightMiddle(Ajouter));
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    


}

