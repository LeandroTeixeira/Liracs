/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.controller.skeletons;

import Liracs.model.service.ManterUsuario;
import Liracs.model.service.impl.IManterUsuario;
import Liracs.shared.model.domain.Usuario;
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
public class SkeletonInserirUsuario {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    
    
    public SkeletonInserirUsuario(Socket s, ObjectInputStream reader, ObjectOutputStream writer) {
        this.socket = s;
        this.reader = reader;
        this.writer = writer;
    }
    
    public Long inserirUsuario() {
        try {
            Usuario user = (Usuario) reader.readObject();
            IManterUsuario manterUsuario = new ManterUsuario();
            writer.writeLong(manterUsuario.cadastrar(user));
            writer.flush();
            return (long) 0;
            
        } catch (IOException ex) {
            Logger.getLogger(SkeletonInserirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkeletonInserirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(SkeletonInserirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NegocioException ex) {
            Logger.getLogger(SkeletonInserirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long) 0;
    }
}
