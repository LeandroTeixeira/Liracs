/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.Stub;

import Liracs.shared.interfaceDAO.IUsuarioDAO;
import Liracs.shared.model.domain.Usuario;
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
public class StubUsuario implements IUsuarioDAO {
    
    public StubUsuario() {
    }
    
    @Override
    public Long autenticar(String userName, String senha) {
        String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Autenticar usuario");      //indica a operacao desejada
            writer.flush();
            writer.writeUTF(userName);
            writer.flush();
            writer.writeUTF(senha);
            writer.flush();
            
            
            long userID = reader.readLong();
            
            return userID;
            
        } catch (IOException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long)0;

    }

    @Override
    public Long inserir(Usuario usuario) throws PersistenciaException {
        String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Cadastrar usuario");      //indica a operacao desejada
            writer.flush();
            writer.writeObject(usuario);
            writer.flush();
            
            long userID = reader.readLong();
            
            return userID;
            
        } catch (IOException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long)0;

    }

    @Override
    public void atualizar(Usuario usuario) throws PersistenciaException {
        String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Atualizar usuario");      //indica a operacao desejada
            writer.flush();
            writer.writeObject(usuario);
            writer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void excluir(Long id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario consultarPorId(Long id) throws PersistenciaException {
         String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Procurar usuario");      //indica a operacao desejada
            writer.flush();
            writer.writeLong(id);
            writer.flush();
            
            Usuario user = (Usuario)reader.readObject();
            return user;
        } catch (IOException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
