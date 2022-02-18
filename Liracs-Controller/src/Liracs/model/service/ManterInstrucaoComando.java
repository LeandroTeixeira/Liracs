/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service;

import Liracs.shared.interfaceDAO.IInstrucaoComandoDAO;
import Liracs.model.DAO.impl.InstrucaoComandoDAO;
import Liracs.shared.model.domain.InstrucaoComando;
import Liracs.model.service.impl.IManterInstrucaoComando;
import Liracs.shared.domain.join.JoinIgIcC;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public class ManterInstrucaoComando implements IManterInstrucaoComando{
    IInstrucaoComandoDAO icDAO;
    public ManterInstrucaoComando(){
        icDAO=new InstrucaoComandoDAO();
    }
    @Override
    public void cadastrar(InstrucaoComando comando) throws PersistenciaException, NegocioException {
        if(comando==null)
            throw new NegocioException("Já existe um comando relacionado a instrucao selecionada!");
        if(comando.getComando()==null)
            throw new NegocioException("Já existe um comando relacionado a instrucao selecionada!");
        if(comando.getInstrucaoGravada()==null)
            throw new NegocioException("Já existe um comando relacionado a instrucao selecionada!");
        icDAO.inserir(comando);
    }

    @Override
    public void remover(InstrucaoComando comando) throws PersistenciaException {
        icDAO.excluir(comando.getInstrucaoGravada().getCod_Instrucao(), comando.getInstrucaoGravada().getUsuario().getCodUsuario(), comando.getComando().getCod_Comando());
        
    }

    @Override
    public InstrucaoComando pesquisarPorId(Long codInstrucao, Long codUsuario, Long CodComando) throws PersistenciaException {
        InstrucaoComando aux = icDAO.consultarPorId(codInstrucao, codUsuario, CodComando);
        return aux;
    }

    @Override
    public List<InstrucaoComando> listarTodos() throws PersistenciaException {
                List<InstrucaoComando> result=icDAO.listarTodos();
                return result;
    }

    @Override
    public List<InstrucaoComando> listarPorInstrucao(Long codInstrucao) throws PersistenciaException {
        List<InstrucaoComando> result=icDAO.listarPorInstrucao(codInstrucao);
                return result;
    }

    @Override
    public List<JoinIgIcC> listarPorUsuario(Long codUsuario) throws PersistenciaException {
                List<JoinIgIcC> result=icDAO.listarPorUsuario(codUsuario);
                return result;    
    }

    @Override
    public List<InstrucaoComando> listarPorComando(Long codComando) throws PersistenciaException {
                List<InstrucaoComando> result=icDAO.listarPorInstrucao(codComando);
                return result;
    }
  
}
