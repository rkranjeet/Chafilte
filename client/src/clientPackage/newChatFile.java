/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import messagePackage.messageData;

/**
 *
 * @author Ranjeet
 */
public class newChatFile implements Runnable{
    private chatPage mychat;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objOutputStream;
    private messageData msgobj;
    public newChatFile(chatPage mychat) throws IOException{
        this.mychat = mychat;
        socket = new Socket(mychat.getIp(), 5440);
        System.out.println("newChatFile");
    }
    @Override
    public void run(){
        while(true){
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(newChatFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                msgobj = (messageData)objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(newChatFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(msgobj.getmessagetype());
            if("chatReceive".equals(msgobj.getmessagetype())){
                String nmsg;
                nmsg = msgobj.getsender() + " >> " + msgobj.getMessage();
                try {
                    mychat.addText(nmsg);
                } catch (BadLocationException ex) {
                    Logger.getLogger(newChatFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if("fileReceive".equals(msgobj.getmessagetype())){
                 mychat.modAddElement(msgobj);
                 System.out.println(msgobj.getName()+" Received");
            }
        }
    }
}
