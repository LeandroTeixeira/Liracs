/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.Stub;

import Liracs.shared.interfaceDAO.IComandoDAO;
import Liracs.shared.model.domain.Comando;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nelore
 */
public class StubComando implements IComandoDAO{

    @Override
    public Long inserir(Comando comando) throws PersistenciaException {
    /*    String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
           // writer.writeUTF("Inserir usuario");
        }catch(Exception ex){
        
        } */ return (long) 0;
    }

    @Override
    public void atualizar(Comando comando) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Long id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comando consultarPorId(Long id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comando consultarPorEndereco(String end) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Comando> listarTodos() throws PersistenciaException {
        String serverAddress = "localhost";
        int serverPort = 5433;

        Socket socket;
        try {
            socket = new Socket(serverAddress, serverPort);
            
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            
            writer.writeUTF("Listar comandos");
            writer.flush();
            
            ArrayList<Comando> listComando=(ArrayList<Comando>) reader.readObject();
            return listComando;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    return null;
    }
}
