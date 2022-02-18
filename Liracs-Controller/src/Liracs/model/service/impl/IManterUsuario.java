/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service.impl;

import Liracs.shared.model.domain.Usuario;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IManterUsuario {
     
    public Long cadastrar(Usuario usuario) throws PersistenciaException, NegocioException;
    public void alterar(Usuario usuario) throws PersistenciaException, NegocioException;
    public void remover(Usuario usuario) throws PersistenciaException;
    public Usuario pesquisarPorId(Long id) throws PersistenciaException;
    public List<Usuario> listarTodos() throws PersistenciaException;
    public Long autenticar(String userName, String senha) throws PersistenciaException, NegocioException;
}
