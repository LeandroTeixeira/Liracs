/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.interfaceDAO;

import Liracs.shared.model.domain.Usuario;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IUsuarioDAO {
    
    public Long autenticar(String userName, String senha);
    public Long inserir(Usuario usuario) throws PersistenciaException;
    public void atualizar(Usuario usuario) throws PersistenciaException;
    public void excluir(Long id) throws PersistenciaException;
    public Usuario consultarPorId(Long id) throws PersistenciaException;
    public List<Usuario> listarTodos() throws PersistenciaException;    
}
