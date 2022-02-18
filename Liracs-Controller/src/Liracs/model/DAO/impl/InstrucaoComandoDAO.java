/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.DAO.impl;

import Liracs.shared.domain.join.JoinIgIcC;
import Liracs.shared.interfaceDAO.IComandoDAO;
import Liracs.shared.interfaceDAO.IInstrucaoComandoDAO;
import Liracs.shared.interfaceDAO.IInstrucaoGravadaDAO;
import Liracs.shared.model.domain.Comando;
import Liracs.shared.model.domain.InstrucaoComando;
import Liracs.shared.model.domain.InstrucaoGravada;
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
public class InstrucaoComandoDAO implements IInstrucaoComandoDAO {

    @Override
    public Long inserir(InstrucaoComando IC) throws PersistenciaException {

        Long id = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO InstrucaoComando (Cod_Instrucao, Cod_Usuario,Cod_Comando) VALUES(?, ?, ?) RETURNING Cod_Instrucao";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, IC.getCod_Instrucao());//   IC.getInstrucaoGravada().getCod_Instrucao());
            statement.setLong(2, IC.getCod_Usuario()); //  IC.getInstrucaoGravada().getCod_Usuario());
            statement.setLong(3, IC.getComando().getCod_Comando());//  IC.getComando().getCod_Comando());

            statement.executeQuery();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return id;
    }

    @Override
    public void excluir(Long CI, Long CU, Long CC) throws PersistenciaException {

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM InstrucaoComando WHERE Cod_Instrucao=? AND Cod_Usuario=? AND Cod_Comando= ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, CI);
            statement.setLong(2, CU);
            statement.setLong(3, CC);

            statement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public InstrucaoComando consultarPorId(Long CI, Long CU, Long CC) throws PersistenciaException {
        InstrucaoComando IC = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoComando WHERE Cod_Instrucao=? AND Cod_Usuario=? AND Cod_Comando= ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, CI);
            statement.setLong(2, CU);
            statement.setLong(3, CC);

            ResultSet resultSet = statement.executeQuery();
            IComandoDAO ComandoDAO = new ComandoDAO();
            IInstrucaoGravadaDAO IgDAO = new InstrucaoGravadaDAO();
            if (resultSet.next()) {
                IC = new InstrucaoComando();
                Comando comando = ComandoDAO.consultarPorId(resultSet.getLong("Cod_Comando"));
                IC.setComando(comando);
                InstrucaoGravada Ig = IgDAO.consultarPorId(resultSet.getLong("Cod_Instrucao"), resultSet.getLong("Cod_Usuario"));
                IC.setInstrucaoGravada(Ig);

            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return IC;
    }

    @Override
    public List<InstrucaoComando> listarTodos() throws PersistenciaException {
        InstrucaoComando IC = null;
        List<InstrucaoComando> IcList = new ArrayList<InstrucaoComando>();

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoComando";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            IComandoDAO ComandoDAO = new ComandoDAO();
            IInstrucaoGravadaDAO IgDAO = new InstrucaoGravadaDAO();
            while (resultSet.next()) {
                IC = new InstrucaoComando();
                Comando comando = ComandoDAO.consultarPorId(resultSet.getLong("Cod_Comando"));
                IC.setComando(comando);
                InstrucaoGravada Ig = IgDAO.consultarPorId(resultSet.getLong("Cod_Instrucao"), resultSet.getLong("Cod_Usuario"));
                IC.setInstrucaoGravada(Ig);
                IcList.add(IC);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return IcList;
    }

    @Override
    public List<InstrucaoComando> listarPorInstrucao(Long codInstrucao) throws PersistenciaException {
        InstrucaoComando IC = null;
        List<InstrucaoComando> IcList = new ArrayList<InstrucaoComando>();

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoComando WHERE Cod_Instrucao=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, codInstrucao);

            ResultSet resultSet = statement.executeQuery();
            IComandoDAO ComandoDAO = new ComandoDAO();
            IInstrucaoGravadaDAO IgDAO = new InstrucaoGravadaDAO();
            while (resultSet.next()) {
                IC = new InstrucaoComando();
                Comando comando = ComandoDAO.consultarPorId(resultSet.getLong("Cod_Comando"));
                IC.setComando(comando);
                InstrucaoGravada Ig = IgDAO.consultarPorId(resultSet.getLong("Cod_Instrucao"), resultSet.getLong("Cod_Usuario"));
                IC.setInstrucaoGravada(Ig);
                IcList.add(IC);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return IcList;
    }

    @Override
    public List<JoinIgIcC> listarPorUsuario(Long codUsuario) throws PersistenciaException {
        InstrucaoComando IC = null;
        List<JoinIgIcC> joinList = new ArrayList<JoinIgIcC>();

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.des_comando, A.cod_instrucao,A.cod_usuario,A.comando_voz, C.des_comando, C.cod_comando, C.end_comando \n"
                    + "FROM instrucaogravada AS A \n"
                    + "JOIN instrucaocomando AS B ON A.cod_instrucao = B.cod_instrucao \n"
                    + "JOIN comando AS C ON B.cod_comando = C.cod_comando WHERE A.cod_usuario=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, codUsuario);

            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                JoinIgIcC join = new JoinIgIcC();
                join.setCom_cod_Comando(resultSet.getLong("cod_comando"));
                join.setCod_Usuario(resultSet.getLong("cod_usuario"));
                join.setCom_des_Comando(resultSet.getString("des_comando"));
                join.setInst_cod_Instrucao(resultSet.getLong("cod_instrucao"));
                join.setInst_desc_Comando_Voz(resultSet.getString("des_comando"));
                join.setInst_concat(resultSet.getString("comando_voz"));

                String[] vetor = join.getInst_concat().split(",");
                long[] hashes = new long[vetor.length];
                for (int i = 0; i < vetor.length; i++) {
                    hashes[i] = Long.parseLong(vetor[i]);
                }
                join.setInst_audio(hashes);
                joinList.add(join);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return joinList;
    }

    @Override
    public List<InstrucaoComando> listarPorComando(Long codComando) throws PersistenciaException {
        InstrucaoComando IC = null;
        List<InstrucaoComando> IcList = new ArrayList<InstrucaoComando>();

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM InstrucaoComando WHERE Cod_Comando=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, codComando);

            ResultSet resultSet = statement.executeQuery();
            IComandoDAO ComandoDAO = new ComandoDAO();
            IInstrucaoGravadaDAO IgDAO = new InstrucaoGravadaDAO();
            while (resultSet.next()) {
                IC = new InstrucaoComando();
                Comando comando = ComandoDAO.consultarPorId(resultSet.getLong("Cod_Comando"));
                IC.setComando(comando);
                InstrucaoGravada Ig = IgDAO.consultarPorId(resultSet.getLong("Cod_Instrucao"), resultSet.getLong("Cod_Usuario"));
                IC.setInstrucaoGravada(Ig);
                IcList.add(IC);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return IcList;
    }

}
