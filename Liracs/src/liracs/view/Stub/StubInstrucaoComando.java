/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.Stub;

import Liracs.shared.domain.join.JoinIgIcC;
import Liracs.shared.interfaceDAO.IInstrucaoComandoDAO;
import Liracs.shared.model.domain.InstrucaoComando;
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
public class StubInstrucaoComando implements IInstrucaoComandoDAO{

    @Override
    public Long inserir(InstrucaoComando comando) throws PersistenciaException {
        String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Inserir instrucao-Comando");      //indica a operacao desejada
            writer.flush();
            writer.writeObject(comando);
            writer.flush();
            
            //long codComando = reader.readLong();
            
            return (long)1;
            
        } catch (IOException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long)0;

    }

    @Override
    public List<JoinIgIcC> listarPorUsuario(Long codUsuario) throws PersistenciaException {
        String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Listar comandos por usuario");      //indica a operacao desejada
            writer.flush();
            writer.writeLong(codUsuario);
            writer.flush();
            
            List<JoinIgIcC> listInstrucaoComando = (List<JoinIgIcC>) reader.readObject();
            //long codComando = reader.readLong();
            
            return listInstrucaoComando;
            
        } catch (IOException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StubInstrucaoComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void excluir(Long codInstrucao, Long codUsuario, Long CodComando) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InstrucaoComando consultarPorId(Long codInstrucao, Long codUsuario, Long CodComando) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InstrucaoComando> listarTodos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InstrucaoComando> listarPorInstrucao(Long codInstrucao) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<InstrucaoComando> listarPorComando(Long codComando) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
