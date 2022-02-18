/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service.impl;

import Liracs.shared.domain.join.JoinIgIcC;
import Liracs.shared.model.domain.InstrucaoComando;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IManterInstrucaoComando {
  
    public void cadastrar(InstrucaoComando comando) throws PersistenciaException, NegocioException;
    public void remover(InstrucaoComando ic) throws PersistenciaException;
    public InstrucaoComando pesquisarPorId(Long codInstrucao, Long codUsuario, Long CodComando) throws PersistenciaException;
    public List<InstrucaoComando> listarTodos() throws PersistenciaException;    
    public List<InstrucaoComando> listarPorInstrucao(Long codInstrucao) throws PersistenciaException;
    public List<JoinIgIcC> listarPorUsuario(Long codUsuario) throws PersistenciaException;
    public List<InstrucaoComando> listarPorComando(Long codComando) throws PersistenciaException;
}
