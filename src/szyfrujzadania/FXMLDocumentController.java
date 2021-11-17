/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szyfrujzadania;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

/**
 *
 * @author Arek
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private PasswordField psF;
    
    @FXML
    private TextArea tA;
    
    private String passwd = "muminki123";
    
    final String secretKey = "superswietnykuczszyfrujacydsjkfhdkghkfshdfkdhskdhgfdkghdskjghfksjhkldsjfdslkfjdslkfjsdlgrhfskjfhjgffjefljewpofjfids^%&^%^&%^#$^$^sdnfbsdmfbnd";
    
    fileEncryption fe = new fileEncryption();
    
    @FXML
    private void decryptButton(ActionEvent event) {
        if(psF.getText().equals(passwd)){
            String path = SzyfrujZadania.class.getProtectionDomain()
                                    .getCodeSource().getLocation().getPath();
                String filename = path.replace("szyfrujZadania.jar", "tasks Config.txt");
                filename = filename.replace("/", "\\");
                filename = filename.replace("%20", " ");
                fe.decryptFile(filename, secretKey);
                filename = filename.replace("tasks Config.txt", "db Config.txt");
                fe.decryptDB(filename, secretKey);
                //fe.decryptFile("C:\\Users\\Arek\\Desktop\\testEncrypt.txt", secretKey);
                label.setText("Zrobione!");
        }
        else{
        label.setText("Złe hasło");
        psF.clear();
        psF.requestFocus();
        }
    }
    
    @FXML
    private void encryptButton(ActionEvent event) throws IOException {
        if(psF.getText().equals(passwd)){
            String path = SzyfrujZadania.class.getProtectionDomain()
                                    .getCodeSource().getLocation().getPath();
                String filename = path.replace("szyfrujZadania.jar", "tasks Config.txt");
                filename = filename.replace("/", "\\");
                filename = filename.replace("%20", " ");
                fe.encryptFile(filename, secretKey);
                filename = filename.replace("tasks Config.txt", "db Config.txt");
                fe.encryptDB(filename, secretKey);
                
                //fe.encryptFile("C:\\Users\\Arek\\Desktop\\testEncrypt.txt", secretKey);
                label.setText("Zrobione!");
        }
        else{
        label.setText("Złe hasło");
        psF.clear();
        psF.requestFocus();
        }
    }
    
    public void appendText(String str) {
    Platform.runLater(() -> tA.appendText(str));
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            PrintStream defaultOut = System.out;
           
            OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
                defaultOut.write(b);
            }


            };
            System.setOut(new PrintStream(out, true));
            System.setErr(new PrintStream(out,true));
        
    }   
    
}
