/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.controller.skeletons;

import Liracs.model.service.ManterComando;
import Liracs.model.service.impl.IManterComando;
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
public class SkeletonListarComandos {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    
    
    public SkeletonListarComandos(Socket s, ObjectInputStream reader, ObjectOutputStream writer) {
        this.socket = s;
        this.reader = reader;
        this.writer = writer;
    }
    
    public void ListarComandos() {
        IManterComando manterComando = new ManterComando();
        try {
            writer.writeObject(manterComando.listarTodos());
            writer.flush();
        } catch (PersistenciaException ex) {
            Logger.getLogger(SkeletonListarComandos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SkeletonListarComandos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
