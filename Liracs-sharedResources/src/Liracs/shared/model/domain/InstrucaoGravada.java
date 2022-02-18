/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.model.domain;

import java.io.File;
import Liracs.shared.model.domain.Usuario;
import java.io.Serializable;

/**
 *
 * @author Nelore
 */
public class InstrucaoGravada implements Serializable{
    private Usuario usuario;
    
    private Long cod_Instrucao;
    private long[] audio;
    private String concat;
    private String desc_Comando_Voz;
    private Long cod_Usuario;
    
    public InstrucaoGravada() {}

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public Long getCod_Usuario() {
        return cod_Usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long[] getAudio() {
        return audio;
    }

    public void setAudio(long[] audio) {
        this.audio = audio;
    }
    
    public void setCod_Usuario(Long cod_Usuario) {
        this.cod_Usuario = cod_Usuario;
    }

    public Long getCod_Instrucao() {
        return cod_Instrucao;
    }

    public void setCod_Instrucao(Long cod_Instrucao) {
        this.cod_Instrucao = cod_Instrucao;
    }

    public String getDesc_Comando_Voz() {
        return desc_Comando_Voz;
    }

    public void setDesc_Comando_Voz(String desc_Comando_Voz) {
        this.desc_Comando_Voz = desc_Comando_Voz;
    }
 
}
