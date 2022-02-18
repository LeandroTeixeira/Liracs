
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nelore
 */
public class Servidor {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(5433); //postgres usa 5432
            while(true) {
                
                System.out.println("Aguardado conexão");
                Socket socket = serverSocket.accept();
                
                GestorSkeleton gestor = new GestorSkeleton(socket);
                Thread t1 = new Thread(gestor);
                t1.start();
            }
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
