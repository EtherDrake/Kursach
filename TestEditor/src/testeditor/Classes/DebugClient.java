/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor.Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DebugClient 
{
    private Socket socketConnection;
    ObjectInputStream clientInputStream;
    private Test test;    
    
    public DebugClient()
    {        
        try
        {
            socketConnection = new Socket ("127.0.0.1", 11111);  
            clientInputStream=new ObjectInputStream(socketConnection.getInputStream());
            test=(Test)clientInputStream.readObject();
            System.out.println(test.getName());
            clientInputStream.close();    
        }catch(Exception ex){}
        
    }
}
