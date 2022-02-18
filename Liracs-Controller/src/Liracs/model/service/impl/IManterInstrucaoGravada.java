/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service.impl;

import Liracs.shared.model.domain.InstrucaoGravada;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IManterInstrucaoGravada {
    
    public Long cadastrar(InstrucaoGravada instrucaoGravada) throws PersistenciaException, NegocioException;
    public void alterar(InstrucaoGravada instrucaoGravada) throws PersistenciaException, NegocioException;
    public void remover(InstrucaoGravada instrucaoGravada) throws PersistenciaException;
    public InstrucaoGravada pesquisarPorId(Long codInstrucao,Long codUsuario) throws PersistenciaException;
    public List<InstrucaoGravada> listarPorUsuario(Long codUsuario) throws PersistenciaException;
    public List<InstrucaoGravada> listarTodos() throws PersistenciaException;    
}
