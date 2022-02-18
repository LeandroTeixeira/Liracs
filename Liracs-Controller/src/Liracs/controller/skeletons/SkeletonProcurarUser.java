/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.controller.skeletons;

import Liracs.model.service.ManterUsuario;
import Liracs.model.service.impl.IManterUsuario;
import Liracs.shared.model.domain.Usuario;
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
public class SkeletonProcurarUser {
    
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public SkeletonProcurarUser(Socket s, ObjectInputStream reader, ObjectOutputStream writer) {
        this.socket = s;
        this.reader = reader;
        this.writer = writer;
    }
    
    public void procurarUser(){
        try {
            long id = reader.readLong();
            IManterUsuario manterUsuario = new ManterUsuario();
            Usuario user = manterUsuario.pesquisarPorId(id);
            writer.writeObject(user);
            writer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(SkeletonProcurarUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(SkeletonProcurarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
