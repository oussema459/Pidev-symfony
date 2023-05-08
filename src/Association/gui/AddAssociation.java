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

package Association.gui;

import User.gui.SessionManager;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Association;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.services.ServiceAssociation;
import com.mycompany.myapp.utils.Context1;
import com.mycompany.myapp.utils.Mailling;
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
public class AddAssociation extends BaseForm {

 
    
  int u = SessionManager.getId();
 // SessionManager u = (SessionManager) Context1.getInstance().getContextObject("UtilisateurCourant");
    
    
    
                       Association fi = new Association();
String fileName ="";
                    ServiceAssociation sp = new ServiceAssociation();
           Mailling m=new Mailling();


    public AddAssociation(Resources res,Form previous) {
        super("Ajouter Association", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Association");
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


                        
        TextComponent nom= new TextComponent().label("Nom :");
        add(nom);
                              

        ComboBox<String> categorieComboBox = new ComboBox<>();
        ArrayList<Categorie> categories = sp.getAllCategories();
for (Categorie categorie : categories) {
    categorieComboBox.addItem(categorie.getNomC());
}

        
        addStringValue("Choissiez un categorie :",categorieComboBox);
        
        TextComponent adresse= new TextComponent().label("Adresse :");
        add(adresse);
        
        
        TextComponent numTel= new TextComponent().label("Tel :");
        add(numTel);
        
        TextComponent description= new TextComponent().label("Description :");
        add(description);
        
        TextComponent mail= new TextComponent().label("Mail :");
        add(mail);
                


Button imge = new Button("Ajouter Image"); 


         
        Button Ajouter = new Button("Ajouter");
        
        
           imge.addActionListener(new ActionListener() {
     @Override
            public void actionPerformed(ActionEvent evt) {
               
               fileName =randomName()+".jpg"; 
                
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto();
                

                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                }
                cr.setFilename("file", fileName);//any unique name you want

                cr.setUrl("http://127.0.0.1/imageServer.php");
                cr.setPost(true);
               
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                System.out.println(filePath);

                NetworkManager.getInstance().addToQueueAndWait(cr);
                fi.setImage(fileName);

                       
            }
   
   });
           
           
          categorieComboBox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        int selectedIndex = categorieComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            int selectedCategoryId = categories.get(selectedIndex).getId();
            fi.setCategorie_id(selectedCategoryId);
        }
    }
}); 
           
           
           
           
           
           
        Ajouter.addActionListener((evt) -> {
                if (mail.getText().equals("")||description.getText().equals("")||(adresse.getText().equals(""))||(nom.getText().equals(""))||(numTel.getText().equals(""))||(fi.getImage().isEmpty()))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
           

            fi.setNom(nom.getText());
            fi.setDescription(description.getText());
            fi.setAdresse(adresse.getText());
            fi.setNum_tel(Integer.parseInt(numTel.getText()));
              fi.setMail(mail.getText());
              fi.setImage(fileName); 
              fi.setImage(fileName); 
                 
                     fi.setUser_asso_id((u));


            sp.addAssociation(fi);
            Dialog.show("Success","Association Ajouter avec success",new Command("OK"));
            m.sendemaill(mail.getText());
            new AllAssociation(res).show();
                
            }      
        });
                addStringValue("", FlowLayout.encloseRightMiddle(imge));

        addStringValue("", FlowLayout.encloseRightMiddle(Ajouter));
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
        String randomName() {
        Random rnd = new Random();
        String lettersAndNumbersAndSymbols = "abcdefghijklmnopqrstuvwxyz0123456789_";
        String name = "";
        for (int i = 0; i < 51; i++) {
            name += lettersAndNumbersAndSymbols.charAt(rnd.nextInt(lettersAndNumbersAndSymbols.length()));
        }
        return name;
    }

}
