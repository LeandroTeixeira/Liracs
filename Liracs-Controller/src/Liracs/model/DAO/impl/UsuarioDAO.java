/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.DAO.impl;


import Liracs.shared.interfaceDAO.IUsuarioDAO;

import Liracs.shared.model.domain.Usuario;
import Liracs.util.DB.ConnectionManager;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author PC
 */
public class UsuarioDAO implements IUsuarioDAO{

    @Override
    public Long inserir(Usuario usuario) throws PersistenciaException {
        
        Long id = null;

        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            FileInputStream fis = null;
            File f = null;
            if(usuario.getEndFoto()!= null) {
                f = usuario.getEndFoto();
                fis = new FileInputStream(f);
            }
            String sql = "INSERT INTO Usuario ( End_Foto, Cod_Senha, Nom_Usuario, Des_Email) VALUES(?,?,?,?) RETURNING Cod_Usuario";
            //String sql = "INSERT INTO Usuario (End_Foto, Cod_Senha, Nom_Usuario) VALUES(?,?,?) RETURNING Cod_Usuario";

            //Olha a marreta caindo do céu
            //List< Usuario> list = this.listarTodos();
            //usuario.setCodUsuario((long) list.size()+1);
            //usuario.setCodUsuario((long) 8);
            //System.out.println(usuario.getDes_Email());
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //statement.setBinaryStream(1,fis, (int) f.length());
            
            //statement.setLong(1, usuario.getCodUsuario());   //pá, caiu 
            statement.setBinaryStream(1, fis, (int)f.length());
            statement.setString(2, usuario.getCod_Senha());
            statement.setString(3, usuario.getNom_Usuario());
            statement.setString(4, usuario.getDes_Email());

            ResultSet resultSet = statement.executeQuery();
            fis.close();
            if (resultSet.next()) {
                id = new Long(resultSet.getLong("Cod_Usuario"));
                usuario.setCodUsuario(id);
            }

            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return id;
    }

    @Override
    public void atualizar(Usuario usuario) throws PersistenciaException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Usuario " +
                            " SET End_Foto = ?, " +
                            "     Cod_Senha = ?" +
                    "Nom_Usuario = ?" + 
                            " WHERE Cod_Usuario = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            //statement.setBlob(1,usuario.getEndFoto());
            statement.setString(2,usuario.getCod_Senha());
            statement.setString(3, usuario.getNom_Usuario());
            statement.setLong(4, usuario.getCodUsuario());
            
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

            String sql = "DELETE FROM Usuario WHERE Cod_Usuario = ?";

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
    public Usuario consultarPorId(Long id) throws PersistenciaException {
        Usuario usuario = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Usuario WHERE Cod_Usuario = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setCodUsuario(resultSet.getLong("Cod_Usuario"));
                    byte[] b = resultSet.getBytes("End_Foto");
                    File tt = new File("src"+File.separator+"Liracs"+File.separator+"foto"+File.separator+"perfil.png");
                    FileOutputStream fos = new FileOutputStream(tt);
                    fos.write(b);
                    fos.close();
                    //InputStream is = new ByteArrayInputStream(b);
                    //Image image = new ImageIcon(b).getImage();  
                    usuario.setB(b);
                    usuario.setEndFoto(tt); //resultSet.getBlob("End_Foto"));
                    usuario.setCod_Senha(resultSet.getString("Cod_Senha"));
                    usuario.setNom_Usuario(resultSet.getString("Nom_Usuario"));
            }
            connection.close();

        } catch (Exception e) {
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() throws PersistenciaException {
        
        List<Usuario> usuarioList = new ArrayList<Usuario>();

        try{
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Usuario";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                    Usuario usuario = new Usuario();
                    usuario.setCodUsuario(resultSet.getLong("Cod_Usuario"));
                    //byte[] b = resultSet.getBytes("End_Foto");
                    //File tt = new File("perfil.png");
                    //FileOutputStream fos = new FileOutputStream(tt);
                    //fos.write(b);
                    //fos.close();
                    //InputStream is = new ByteArrayInputStream(b);
                    //Image image = new ImageIcon(b).getImage();  
                    
                    usuario.setEndFoto(null);
                    usuario.setCod_Senha(resultSet.getString("Cod_Senha"));
                    usuario.setNom_Usuario(resultSet.getString("Nom_Usuario"));
                    usuario.setDes_Email(resultSet.getString("Des_Email"));
                    usuarioList.add(usuario);
            }
            connection.close();
        }catch (Exception e){
                e.printStackTrace();
                throw new PersistenciaException(e.getMessage(), e);
        }
        return usuarioList;
    }

    @Override
    public Long autenticar(String userName, String senha) {
        try {
            List<Usuario> userList = this.listarTodos();
            for(int i=0; i< userList.size(); i++) {
                if(userName.equals(userList.get(i).getNom_Usuario()) && senha.equals(userList.get(i).getCod_Senha())){
                    return userList.get(i).getCodUsuario();
                }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long)0;
    }
    
    
}
