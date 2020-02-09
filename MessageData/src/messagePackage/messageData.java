/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagePackage;

/**
 *
 * @author Ranjeet
 */

import java.io.Serializable;
import javax.swing.ImageIcon;

public class messageData implements Serializable {

    private String status;
    private ImageIcon image;
    private byte[] file;
    private String name;
    private String messagetype;
    private String sender;
    private String ip;
    private String message;
    
    public messageData(String messagetype, String sender, String ip, byte[] file){
        this.messagetype = messagetype;
        this.sender = sender;
        this.ip = ip;
        this.file = file;
    }
    public messageData(String messagetype, String sender, String ip, String message){
        this.messagetype = messagetype;
        this.sender = sender;
        this.ip = ip;
        this.message = message;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setmessagetype(String messagetype){
        this.messagetype = messagetype;
    }
    public String getmessagetype(){
        return this.messagetype;
    }
    public void setsender(String sender){
        this.sender = sender;
    }
    public String getsender(){
        return this.sender;
    }
    public void setip(String ip){
        this.ip = ip;
    }
    public String getip(){
        return this.ip;
    }
}
