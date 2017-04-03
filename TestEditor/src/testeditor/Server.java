/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Server 
{
    private Test test;
    private ServerSocket socketConnection;
    private ObjectOutputStream serverOutputStream;
    private serverKeeper keeper;
    
    public Server(Test t)
    {        
        keeper=new serverKeeper(t);
        keeper.start();
    }
    
    public void stopServer()
    {
        keeper.stopKeeper();
    }
    
    
    
    public class serverKeeper extends Thread
    {
        Test tmp;
        public serverKeeper(Test t){tmp=t;}
        
        @Override
        public void run()
        {
            while(true)
            {
                test=tmp;
                try
                {
                    socketConnection = new ServerSocket(11111);
                    Socket pipe = socketConnection.accept();
                    serverOutputStream=new ObjectOutputStream(pipe.getOutputStream());
                    serverOutputStream.writeObject(test);
                    serverOutputStream.close();socketConnection.close();   
                }catch(Exception ex){}
            }
        }
        
        public void stopKeeper()
        {
            try 
            {
                serverOutputStream.close();
                socketConnection.close();
                this.stop();
            } catch (IOException ex) {Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);}            
        }
    }
    
}
