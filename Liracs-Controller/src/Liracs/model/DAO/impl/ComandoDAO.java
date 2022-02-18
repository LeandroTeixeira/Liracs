/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.DAO.impl;

import Liracs.shared.interfaceDAO.IComandoDAO;
import Liracs.shared.model.domain.Comando;
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
public class ComandoDAO implements IComandoDAO{
                    
    @Override
    public Long inserir(Comando comando) throws PersistenciaException {
       
        Long id = null;

        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "INSERT INTO Comando (End_Comando, Des_Comando) VALUES(?, ?) RETURNING Cod_Comando";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, comando.getEnd_Comando());
            statement.setString(2, comando.getDes_Comando());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = new Long(resultSet.getLong("id"));
                comando.setCod_Comando(id);
            }

            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return id;
    }
    
    @Override
    public void atualizar(Comando comando) throws PersistenciaException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Comando " +
                            " SET End_Comando = ?, " +
                            "     Des_Comando = ?" +
                     " WHERE Cod_Comando = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,comando.getEnd_Comando());
            statement.setString(2, comando.getDes_Comando());
            statement.setLong(3, comando.getCod_Comando());
            
            statement.execute();

            connection.close();
        } catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public void excluir(Long id) throws PersistenciaException {
        
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Comando WHERE Cod_Comando = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            statement.execute();
            connection.close();
        }catch(Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public Comando consultarPorId(Long id) throws PersistenciaException {
        Comando comando = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Comando WHERE Cod_Comando = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                    comando = new Comando();
                    comando.setCod_Comando(resultSet.getLong("Cod_Comando"));
                    comando.setEnd_Comando(resultSet.getString("End_Comando"));
                    comando.setDes_Comando(resultSet.getString("Des_Comando"));
            }
            connection.close();

        } catch (Exception e) {
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return comando;
    }

    @Override
    public ArrayList<Comando> listarTodos() throws PersistenciaException {
        Comando comando = null;
        ArrayList<Comando> comandoList = new ArrayList<Comando>();

        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Comando";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                    comando = new Comando();
                    comando.setCod_Comando(resultSet.getLong("Cod_Comando"));
                    comando.setEnd_Comando(resultSet.getString("End_Comando"));
                    comando.setDes_Comando(resultSet.getString("Des_Comando"));
                    comandoList.add(comando);
            }
            connection.close();
        }catch (Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return comandoList;
    }
    @Override
    public Comando consultarPorEndereco(String end) throws PersistenciaException{
        Comando result=null;
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Comando WHERE End_Comando=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,end);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                    result = new Comando();
                    result.setCod_Comando(resultSet.getLong("Cod_Comando"));
                    result.setEnd_Comando(resultSet.getString("End_Comando"));
                    result.setDes_Comando(resultSet.getString("Des_Comando"));
              }
            connection.close();
        }catch (Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return result;
    }


}
