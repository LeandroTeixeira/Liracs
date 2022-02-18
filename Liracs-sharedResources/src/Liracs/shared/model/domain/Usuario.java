/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.model.domain;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author Nelore
 */
public class Usuario implements Serializable {
    
    private Long codUsuario;
    private String cod_Senha;
    private String nom_Usuario;
    private String des_Email;
    private File endFoto;  
    private byte[] b;
    //String ?
    
    public Usuario(){}

    public byte[] getB() {
        return b;
    }

    public void setB(byte[] b) {
        this.b = b;
    }

    public String getDes_Email() {
        return des_Email;
    }

    public void setDes_Email(String des_Email) {
        this.des_Email = des_Email;
    }

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getCod_Senha() {
        return cod_Senha;
    }

    public void setCod_Senha(String cod_Senha) {
        this.cod_Senha = cod_Senha;
    }

    public String getNom_Usuario() {
        return nom_Usuario;
    }

    public void setNom_Usuario(String nom_Usuario) {
        this.nom_Usuario = nom_Usuario;
    }

    public File getEndFoto() {
        return endFoto;
    }

    public void setEndFoto(File endFoto) {
        this.endFoto = endFoto;
    }
    
    
}
