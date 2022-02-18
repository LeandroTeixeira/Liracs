/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.domain.join;

import java.io.Serializable;

/**
 *
 * @author aluno
 */
public class JoinIgIcC implements Serializable{
    private Long com_cod_Comando;
    private String com_des_Comando;
    private String com_end_Comando;
    
    private Long inst_cod_Instrucao;
    private long[] inst_audio;
    private String inst_desc_Comando_Voz;
    private Long cod_Usuario;
    private String inst_concat;
    
    public JoinIgIcC() {}
    
    public JoinIgIcC(Long com_cod_Comando, Long cod_Usuario) {
        this.com_cod_Comando = com_cod_Comando;
        this.cod_Usuario = cod_Usuario;
    }

    public Long getCom_cod_Comando() {
        return com_cod_Comando;
    }

    public String getInst_concat() {
        return inst_concat;
    }

    public void setInst_concat(String inst_concat) {
        this.inst_concat = inst_concat;
    }
    
    public void setCom_cod_Comando(Long com_cod_Comando) {
        this.com_cod_Comando = com_cod_Comando;
    }

    public String getCom_des_Comando() {
        return com_des_Comando;
    }

    public void setCom_des_Comando(String com_des_Comando) {
        this.com_des_Comando = com_des_Comando;
    }

    public String getCom_end_Comando() {
        return com_end_Comando;
    }

    public void setCom_end_Comando(String com_end_Comando) {
        this.com_end_Comando = com_end_Comando;
    }

    public Long getInst_cod_Instrucao() {
        return inst_cod_Instrucao;
    }

    public void setInst_cod_Instrucao(Long inst_cod_Instrucao) {
        this.inst_cod_Instrucao = inst_cod_Instrucao;
    }

    public long[] getInst_audio() {
        return inst_audio;
    }

    public void setInst_audio(long[] inst_audio) {
        this.inst_audio = inst_audio;
    }

    public String getInst_desc_Comando_Voz() {
        return inst_desc_Comando_Voz;
    }

    public void setInst_desc_Comando_Voz(String inst_desc_Comando_Voz) {
        this.inst_desc_Comando_Voz = inst_desc_Comando_Voz;
    }

    public Long getCod_Usuario() {
        return cod_Usuario;
    }

    public void setCod_Usuario(Long cod_Usuario) {
        this.cod_Usuario = cod_Usuario;
    }
    
    
}
