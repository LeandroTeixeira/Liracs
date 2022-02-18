/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.interfaceDAO;


import Liracs.shared.model.domain.InstrucaoGravada;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IInstrucaoGravadaDAO {
    
    public Long inserir(InstrucaoGravada instrucaoGravada) throws PersistenciaException;
    public void atualizar(InstrucaoGravada instrucaoGravada) throws PersistenciaException;
    public void excluir(Long codInstrucao,Long codUsuario) throws PersistenciaException;
    public InstrucaoGravada consultarPorId(Long codInstrucao,Long codUsuario) throws PersistenciaException;
    public InstrucaoGravada consultarPorEndereco(String end) throws PersistenciaException;
    public List<InstrucaoGravada> listarPorUsuario(Long codUsuario) throws PersistenciaException;
    public List<InstrucaoGravada> listarTodos() throws PersistenciaException;    
}
