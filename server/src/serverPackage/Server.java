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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static serverPackage.Server.slist;

public class Server {
    static ArrayList<Socket> slist;
    public static void main(String args[]) {
        ServerSocket serverSocket, serverSocket2;
        Socket socket;
        slist = new ArrayList<Socket>(10);
        System.out.println("Server started");
        try {
            serverSocket = new ServerSocket(5436);
            serverSocket2 = new ServerSocket(5440);
            Thread t = new Thread(new Server2(serverSocket2));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                socket = serverSocket.accept();
                //slist.add(socket);
                System.out.println("a");
                Thread t = new Thread(new HandleClient(socket));
                System.out.println("b");
                t.start();
                System.out.println("c");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }
    }
}
class Server2 implements Runnable{
    ServerSocket serverSocket;
    Socket socket;
    public Server2(ServerSocket serverSocket){
            this.serverSocket = serverSocket;
    }
    public void run(){
        while(true){
            try {
                socket = serverSocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
            }
            Server.slist.add(socket);
            System.out.println("Socket2");
        }
    }
}

