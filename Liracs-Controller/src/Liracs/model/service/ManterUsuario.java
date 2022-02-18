/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.model.service;

import Liracs.model.service.impl.IManterUsuario;
import Liracs.shared.interfaceDAO.IUsuarioDAO;
import Liracs.model.DAO.impl.UsuarioDAO;
import Liracs.shared.model.domain.Usuario;
import Liracs.shared.util.exceptions.NegocioException;
import Liracs.shared.util.exceptions.PersistenciaException;
import java.util.List;

/**
 *
 * @author PC
 */
public class ManterUsuario implements IManterUsuario{
    IUsuarioDAO usuarioDAO;
    public ManterUsuario(){
        usuarioDAO=new UsuarioDAO();
    }

    @Override
    public Long cadastrar(Usuario usuario) throws PersistenciaException, NegocioException {
        if(usuario == null)
            throw new NegocioException("Usuario nao informado!");
        if(usuario.getNom_Usuario() == null)
            throw new NegocioException("Nome do usuario nao informado!");
        if(usuario.getCod_Senha()==null)
            throw new NegocioException("Senha nao informada!");
        if(usuario.getCod_Senha().length()<=4)
            throw new NegocioException("Senha curta demais! As senhas devem ter no mínimo 5 caracteres!");
        if(usuario.getNom_Usuario().length()<3 || usuario.getNom_Usuario().length()>30)
            throw new NegocioException("O nome de usuário deve conter entre 4 e 29 caracteres!");

        return usuarioDAO.inserir(usuario);
        
    }

    @Override
    public void alterar(Usuario usuario) throws PersistenciaException, NegocioException {
        if(usuario == null)
            throw new NegocioException("Usuario nao informado!");
        if(usuario.getNom_Usuario() == null)
            throw new NegocioException("Nome do usuario nao informado!");
        if(usuario.getCod_Senha()==null)
            throw new NegocioException("Senha nao informada!");
        if(usuario.getCod_Senha().length()<=4)
            throw new NegocioException("Senha curta demais! As senhas devem ter no mínimo 5 caracteres!");
        if(usuario.getNom_Usuario().length()<3 || usuario.getNom_Usuario().length()>30)
            throw new NegocioException("O nome de usuário deve conter entre 4 e 29 caracteres!");
        usuarioDAO.atualizar(usuario);
    }

    @Override
    public void remover(Usuario usuario) throws PersistenciaException {
        usuarioDAO.excluir(usuario.getCodUsuario());
    }

    @Override
    public Usuario pesquisarPorId(Long id) throws PersistenciaException {
        Usuario result=usuarioDAO.consultarPorId(id);
        return result;
    }
    
    @Override
    public List<Usuario> listarTodos() throws PersistenciaException {
        List<Usuario> result=usuarioDAO.listarTodos();
        return result;
    }

    @Override
    public Long autenticar(String userName, String senha) throws PersistenciaException, NegocioException {
        List<Usuario> allUsers = usuarioDAO.listarTodos();
        int count = 0;
        for(int i= 0; i< allUsers.size(); i++) {
            if(userName.equals(allUsers.get(i).getNom_Usuario())){
                count++;
            }
        }
        if(count==0 || userName==null)
            throw new NegocioException("Usuário não cadastrado!");
        if(senha == null)
            throw new NegocioException("Senha inválida!");
        return usuarioDAO.autenticar(userName, senha);
    }
    
}
