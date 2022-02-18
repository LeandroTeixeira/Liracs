/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.controller.skeletons;

import Liracs.model.service.ManterUsuario;
import Liracs.model.service.impl.IManterUsuario;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelore
 */
public class SkeletonAutenticar {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    
    public SkeletonAutenticar(Socket s, ObjectInputStream reader, ObjectOutputStream writer) {
        this.socket = s;
        this.reader = reader;
        this.writer = writer;
    }
    
    public void autenticar() {
        
        try {
            String userName = reader.readUTF();
            String userPass = reader.readUTF();
            IManterUsuario manterUsuario = new ManterUsuario();
            Long userId = manterUsuario.autenticar(userName, userPass);
            writer.writeLong(userId);
            writer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(SkeletonAutenticar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(SkeletonAutenticar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NegocioException ex) {
            Logger.getLogger(SkeletonAutenticar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
