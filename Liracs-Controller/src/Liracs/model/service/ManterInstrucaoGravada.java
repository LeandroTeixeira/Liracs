/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service;

import Liracs.model.service.impl.IManterInstrucaoGravada;
import Liracs.shared.interfaceDAO.IInstrucaoGravadaDAO;
import Liracs.model.DAO.impl.InstrucaoGravadaDAO;
import Liracs.shared.model.domain.InstrucaoGravada;

import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public class ManterInstrucaoGravada implements IManterInstrucaoGravada{
    IInstrucaoGravadaDAO igDAO;
    public ManterInstrucaoGravada(){
        igDAO=new InstrucaoGravadaDAO();
    }

    @Override
    public Long cadastrar(InstrucaoGravada ig) throws PersistenciaException, NegocioException {
        if(ig==null)
            throw new NegocioException("Instrucao nao informada!");
        if(ig.getCod_Usuario()==null)
            throw new NegocioException("Usuario nao informado!");
        //if(igDAO.consultarPorId(ig.getCod_Instrucao(), ig.getCod_Usuario()) !=null)
        //     throw new NegocioException("Já existe um comando relacionado a instrucao selecionada!");
        return igDAO.inserir(ig);
    }

    @Override
    public void alterar(InstrucaoGravada ig) throws PersistenciaException, NegocioException {
        InstrucaoGravada aux=igDAO.consultarPorId(ig.getCod_Instrucao(),ig.getUsuario().getCodUsuario());
        
        if(ig==null)
            throw new NegocioException("Instrucao nao informada!");
        if(ig.getUsuario()==null)
            throw new NegocioException("Usuario nao informado!");
        if(igDAO.consultarPorId(ig.getCod_Instrucao(), ig.getCod_Usuario()) !=null)
            throw new NegocioException("Já existe um comando relacionado a instrucao selecionada!");
        
        igDAO.atualizar(ig);
    }

    @Override
    public void remover(InstrucaoGravada ig) throws PersistenciaException {
        igDAO.excluir(ig.getCod_Instrucao(),ig.getUsuario().getCodUsuario());
    }

    @Override
    public InstrucaoGravada pesquisarPorId(Long codInstrucao, Long codUsuario) throws PersistenciaException {
        InstrucaoGravada result=igDAO.consultarPorId(codInstrucao, codUsuario);
        return result;
    }

    @Override
    public List<InstrucaoGravada> listarPorUsuario(Long codUsuario) throws PersistenciaException {
        List<InstrucaoGravada> result=igDAO.listarPorUsuario(codUsuario);
        return result;
    }

    @Override
    public List<InstrucaoGravada> listarTodos() throws PersistenciaException {
        List<InstrucaoGravada> result=igDAO.listarTodos();
        return result;
    }
    
}
