/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverPackage;

/**
 *
 * @author Ranjeet
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messagePackage.messageData;

public class HandleClient implements Runnable {
    private Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    messageData msgobj;
    public HandleClient(Socket socket) throws IOException {
        this.socket = socket;
         objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
         objectOutputStream.writeObject("Connected....");
         objectOutputStream.flush();
        // objectOutputStream.close();
    }

    public void run() {
        while (true) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                msgobj = (messageData)objectInputStream.readObject();
                //objectInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(msgobj.getmessagetype());
            if("chatSend".equals(msgobj.getmessagetype())){
                msgobj.setmessagetype("chatReceive");
                System.out.println(Server.slist.size());
                for(Socket s : Server.slist){
                    try {
                        System.out.println(msgobj.getMessage());
                        objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                        objectOutputStream.writeObject(msgobj);
                        objectOutputStream.flush();
                       // objectOutputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if("fileSend".equals(msgobj.getmessagetype())){
                msgobj.setmessagetype("fileReceive");
                System.out.println(Server.slist.size());
                for(Socket s : Server.slist){
                    try {
                        System.out.println(msgobj.getMessage());
                        objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                        objectOutputStream.writeObject(msgobj);
                        objectOutputStream.flush();
                       // objectOutputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }


}
