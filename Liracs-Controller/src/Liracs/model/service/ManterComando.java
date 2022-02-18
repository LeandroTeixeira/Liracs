/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service;

import Liracs.model.DAO.impl.ComandoDAO;
import Liracs.model.service.impl.IManterComando;
import Liracs.shared.interfaceDAO.IComandoDAO;
import Liracs.shared.model.domain.Comando;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ManterComando implements IManterComando{
    IComandoDAO comandoDAO;
    public ManterComando(){
        comandoDAO=new ComandoDAO();
    }
    @Override
    public void cadastrar(Comando comando) throws PersistenciaException, NegocioException {
        if(comando==null)
             throw new NegocioException("Comando nao informado!");
        
        if(comando.getEnd_Comando()==null)
            throw new NegocioException("Endereço nao informado!");
        
        if(comandoDAO.consultarPorEndereco(comando.getEnd_Comando())!= null)
             throw new NegocioException("Já existe um comando relacionado a instrucao selecionada!");
        comandoDAO.inserir(comando);
    }
    
    @Override
    public void alterar(Comando comando) throws PersistenciaException, NegocioException{
        Comando aux=comandoDAO.consultarPorId(comando.getCod_Comando());
        
        if(comando==null)
             throw new NegocioException("Comando nao informado!");
        
        if(comando.getEnd_Comando()==null)
            throw new NegocioException("Endereço nao informado!");
        
        if(comandoDAO.consultarPorEndereco(comando.getEnd_Comando())!=null && !comando.getEnd_Comando().equals(aux.getEnd_Comando()))
            throw new NegocioException("Já existe um comando relacionado a instrucao selecionada!");
        
        comandoDAO.atualizar(comando);
        
    }
    
    @Override
    public void remover(Comando comando) throws PersistenciaException {
        comandoDAO.excluir(comando.getCod_Comando()); 
    }

    @Override
    public Comando pesquisarPorId(Long id) throws PersistenciaException {
       Comando result=comandoDAO.consultarPorId(id);
       return result;
    }

    @Override
    public ArrayList<Comando> listarTodos() throws PersistenciaException {
        ArrayList<Comando> result=comandoDAO.listarTodos();
        return result;
    }

   
}
