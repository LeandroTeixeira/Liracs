/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.model.domain;

import Liracs.shared.model.domain.Comando;
import Liracs.shared.model.domain.InstrucaoGravada;
import java.io.Serializable;

/**
 *
 * @author Nelore
 */
public class InstrucaoComando implements Serializable{
    private long cod_Instrucao;
    private long cod_Comando;
    private long cod_Usuario;
    private InstrucaoGravada instrucaoGravada;
    private Comando comando;

    public Comando getComando() {
        return comando;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public InstrucaoGravada getInstrucaoGravada() {
        return instrucaoGravada;
    }

    public void setInstrucaoGravada(InstrucaoGravada instrucaoGravada) {
        this.instrucaoGravada = instrucaoGravada;
    }
    
    public long getCod_Instrucao() {
        return cod_Instrucao;
    }

    public void setCod_Instrucao(long cod_Instrucao) {
        this.cod_Instrucao = cod_Instrucao;
    }

    public long getCod_Comando() {
        return cod_Comando;
    }

    public void setCod_Comando(long cod_Comando) {
        this.cod_Comando = cod_Comando;
    }

    public long getCod_Usuario() {
        return cod_Usuario;
    }

    public void setCod_Usuario(long cod_Usuario) {
        this.cod_Usuario = cod_Usuario;
    }
    
    
}
