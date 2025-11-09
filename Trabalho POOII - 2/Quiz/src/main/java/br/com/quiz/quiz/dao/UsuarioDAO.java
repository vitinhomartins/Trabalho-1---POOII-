package br.com.quiz.quiz.dao;

import br.com.quiz.quiz.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    public void insere(Usuario usuario) throws SQLException;
    public void atualiza(Usuario usuario) throws SQLException;
    public void remove(Usuario usuario) throws SQLException;
    public Usuario buscaPorId(int idUsuario) throws SQLException;
    public Usuario buscaPorUsername(String username) throws SQLException;
    public List<Usuario> listaTodos() throws SQLException;
    public void atualizaUltimoLogin(int idUsuario) throws SQLException;
}
