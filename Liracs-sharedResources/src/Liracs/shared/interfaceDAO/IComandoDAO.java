/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.interfaceDAO;

import Liracs.shared.model.domain.Comando;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IComandoDAO {
    
    public Long inserir(Comando comando) throws PersistenciaException;
    public void atualizar(Comando comando) throws PersistenciaException;
    public void excluir(Long id) throws PersistenciaException;
    public Comando consultarPorId(Long id) throws PersistenciaException;
    public Comando consultarPorEndereco(String end) throws PersistenciaException;
    public ArrayList<Comando> listarTodos() throws PersistenciaException;    
       
}
