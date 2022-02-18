/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.DAO.impl;

import Liracs.shared.interfaceDAO.IInstrucaoGravadaDAO;
import Liracs.shared.interfaceDAO.IUsuarioDAO;
import Liracs.shared.model.domain.InstrucaoGravada;
import Liracs.shared.model.domain.Usuario;
import Liracs.util.DB.ConnectionManager;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class InstrucaoGravadaDAO implements IInstrucaoGravadaDAO{

    @Override
    public Long inserir(InstrucaoGravada instrucaoGravada) throws PersistenciaException {
        
        Long id = null;

        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO InstrucaoGravada (Cod_Usuario, Comando_Voz, Des_Comando) VALUES(?,?,?) RETURNING Cod_Instrucao";

            PreparedStatement statement = connection.prepareStatement(sql);
            
            long[] hashes = instrucaoGravada.getAudio();
            String toText = "";
            for(int i=0; i<hashes.length; i++) {
                toText += hashes[i]+",";
            }
            statement.setLong(1, instrucaoGravada.getCod_Usuario());
            statement.setString(2, toText);
            statement.setString(3, instrucaoGravada.getDesc_Comando_Voz());
                    
            
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = new Long(resultSet.getLong("Cod_Instrucao"));
                instrucaoGravada.setCod_Instrucao(id);
            }

            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return id;
    }

    @Override
    public void atualizar(InstrucaoGravada instrucaoGravada) throws PersistenciaException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE InstrucaoGravada " +
                            " SET Comando_Voz = ?, " +
                            "     Des_Comando = ?" +
                            "     WHERE Cod_Instrucao = ? AND" +
                            "     Cod_Usuario= ?";

            
            long[] hashes = instrucaoGravada.getAudio();
            String toText = null;
            for(int i=0; i<hashes.length; i++) {
                toText= hashes[i]+",";
            }
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, toText);
            statement.setString(2, instrucaoGravada.getDesc_Comando_Voz());
            statement.setLong(3, instrucaoGravada.getCod_Instrucao());
            statement.setLong(4, instrucaoGravada.getCod_Usuario());
            
            statement.execute();

            connection.close();
        } catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public void excluir(Long codInstrucao, Long codUsuario) throws PersistenciaException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM InstrucaoGravada WHERE Cod_Instrucao = ? AND Cod_Usuario= ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, codInstrucao);
            statement.setLong(2, codUsuario);

            statement.execute();
            connection.close();
        }catch(Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public InstrucaoGravada consultarPorId(Long codInstrucao, Long codUsuario) throws PersistenciaException {
        InstrucaoGravada instrucaoGravada = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoGravada WHERE Cod_Instrucao = ? AND Cod_Usuario= ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, codInstrucao);
            statement.setLong(2, codUsuario);

            ResultSet resultSet = statement.executeQuery();
            IUsuarioDAO usuarioDAO=new UsuarioDAO();
            if(resultSet.next()) {
                    
                
                    instrucaoGravada = new InstrucaoGravada();
                    Usuario usuario=usuarioDAO.consultarPorId(resultSet.getLong("Cod_Usuario"));
                    instrucaoGravada.setUsuario(usuario);
                    instrucaoGravada.setCod_Instrucao(resultSet.getLong("Cod_Instrucao"));
                    instrucaoGravada.setDesc_Comando_Voz(resultSet.getString("Des_Comando"));
                    instrucaoGravada.setConcat(resultSet.getString("Comando_Voz"));
                    
                    String[] vetor = instrucaoGravada.getConcat().split(",");
                    long[] hashes = new long[vetor.length];
                    for(int i=0; i < vetor.length; i++) {
                        hashes[i] = Long.parseLong(vetor[i]);
                    }
                    instrucaoGravada.setAudio(hashes);
            }
            connection.close();

        } catch (Exception e) {
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return instrucaoGravada;
    }

    @Override
    public List<InstrucaoGravada> listarTodos() throws PersistenciaException {
        
        List<InstrucaoGravada> IGList = new ArrayList<InstrucaoGravada>();

        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoGravada";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            IUsuarioDAO usuarioDAO=new UsuarioDAO();

            while(resultSet.next()){
                    InstrucaoGravada instrucaoGravada = new InstrucaoGravada();
                    Usuario usuario=usuarioDAO.consultarPorId(resultSet.getLong("Cod_Usuario"));
                    instrucaoGravada.setUsuario(usuario);
                    instrucaoGravada.setCod_Instrucao(resultSet.getLong("Cod_Instrucao"));
                    instrucaoGravada.setDesc_Comando_Voz(resultSet.getString("Des_Comando"));
                    instrucaoGravada.setConcat(resultSet.getString("Comando_Voz"));
                    
                    String[] vetor = instrucaoGravada.getConcat().split(",");
                    long[] hashes = new long[vetor.length];
                    for(int i=0; i < vetor.length; i++) {
                        hashes[i] = Long.parseLong(vetor[i]);
                    }
                    instrucaoGravada.setAudio(hashes);

                    IGList.add(instrucaoGravada);
            }
            connection.close();
        }catch (Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return IGList;
    }

    @Override
    public List<InstrucaoGravada> listarPorUsuario(Long codUsuario) throws PersistenciaException {
         
        List<InstrucaoGravada> IGList = new ArrayList<InstrucaoGravada>();

        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoGravada WHERE Cod_Usuario=?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, codUsuario);
            ResultSet resultSet = statement.executeQuery();
            IUsuarioDAO usuarioDAO=new UsuarioDAO();

            while(resultSet.next()){
                    InstrucaoGravada instrucaoGravada = new InstrucaoGravada();
                    Usuario usuario=usuarioDAO.consultarPorId(resultSet.getLong("Cod_Usuario"));
                    instrucaoGravada.setUsuario(usuario);
                    instrucaoGravada.setCod_Instrucao(resultSet.getLong("Cod_Instrucao"));
                    instrucaoGravada.setDesc_Comando_Voz(resultSet.getString("Des_Comando"));
                    instrucaoGravada.setConcat(resultSet.getString("Comando_Voz"));
                    
                    String[] vetor = instrucaoGravada.getConcat().split(",");
                    long[] hashes = new long[vetor.length];
                    for(int i=0; i < vetor.length; i++) {
                        hashes[i] = Long.parseLong(vetor[i]);
                    }
                    instrucaoGravada.setAudio(hashes);

                    IGList.add(instrucaoGravada);
            }
            connection.close();
        }catch (Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return IGList;    
    }

    @Override
    public InstrucaoGravada consultarPorEndereco(String end) throws PersistenciaException {
        InstrucaoGravada instrucaoGravada = new InstrucaoGravada();
        List<InstrucaoGravada> IGList = new ArrayList<InstrucaoGravada>();
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoGravada WHERE End_Comando_Voz=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,end);
            ResultSet resultSet = statement.executeQuery();
            IUsuarioDAO usuarioDAO=new UsuarioDAO();
            if(resultSet.next()){
                    //InstrucaoGravada instrucaoGravada = new InstrucaoGravada();
                    Usuario usuario=usuarioDAO.consultarPorId(resultSet.getLong("Cod_Usuario"));
                    instrucaoGravada.setUsuario(usuario);
                    instrucaoGravada.setCod_Instrucao(resultSet.getLong("Cod_Instrucao"));
                    instrucaoGravada.setDesc_Comando_Voz(resultSet.getString("Des_Comando"));
                    instrucaoGravada.setConcat(resultSet.getString("Comando_Voz"));
                    
                    String[] vetor = instrucaoGravada.getConcat().split(",");
                    long[] hashes = new long[vetor.length];
                    for(int i=0; i < vetor.length; i++) {
                        hashes[i] = Long.parseLong(vetor[i]);
                    }
                    instrucaoGravada.setAudio(hashes);

                    IGList.add(instrucaoGravada);
            }
            connection.close();
        }catch (Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return instrucaoGravada;    
    }
    
}
