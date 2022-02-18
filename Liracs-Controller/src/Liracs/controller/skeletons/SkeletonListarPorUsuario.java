/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.controller.skeletons;

import Liracs.model.service.ManterInstrucaoComando;
import Liracs.model.service.impl.IManterInstrucaoComando;
import Liracs.shared.domain.join.JoinIgIcC;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nelore
 */
public class SkeletonListarPorUsuario {
    
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public SkeletonListarPorUsuario(Socket s, ObjectInputStream reader, ObjectOutputStream writer) {
        this.socket = s;
        this.reader = reader;
        this.writer = writer;
    }
    
    public void listarPorUsuario() {
        try {
            Long userLoggedID = reader.readLong();
            IManterInstrucaoComando manterInstrucaoComando = new ManterInstrucaoComando();
            List<JoinIgIcC> listInstrucaoComando = manterInstrucaoComando.listarPorUsuario(userLoggedID);
            writer.writeObject(listInstrucaoComando);
            writer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(SkeletonListarPorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(SkeletonListarPorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
