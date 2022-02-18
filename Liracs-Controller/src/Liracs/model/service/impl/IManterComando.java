/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service.impl;

import Liracs.shared.model.domain.Comando;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IManterComando {

    public void cadastrar(Comando comando) throws PersistenciaException, NegocioException;
    public void alterar (Comando comando) throws PersistenciaException, NegocioException;
    public void remover(Comando comando) throws PersistenciaException;
    public Comando pesquisarPorId(Long id) throws PersistenciaException;
    public ArrayList<Comando> listarTodos() throws PersistenciaException;    
}
