/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.Stub;

import Liracs.shared.interfaceDAO.IInstrucaoGravadaDAO;
import Liracs.shared.model.domain.InstrucaoGravada;
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
public class StubInstrucaoGravada implements IInstrucaoGravadaDAO {

    @Override
    public Long inserir(InstrucaoGravada instrucaoGravada) throws PersistenciaException {
        String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Inserir instrucao");      //indica a operacao desejada
            writer.flush();
            writer.writeObject(instrucaoGravada);
            writer.flush();
            
            long userID = reader.readLong();
            
            return userID;
            
        } catch (IOException ex) {
            Logger.getLogger(StubUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long)0;

    
    }

    @Override
    public void atualizar(InstrucaoGravada instrucaoGravada) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Long codInstrucao, Long codUsuario) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InstrucaoGravada consultarPorId(Long codInstrucao, Long codUsuario) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InstrucaoGravada consultarPorEndereco(String end) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InstrucaoGravada> listarPorUsuario(Long codUsuario) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InstrucaoGravada> listarTodos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
