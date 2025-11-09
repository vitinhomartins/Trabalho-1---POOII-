package br.com.quiz.quiz.Controller;

import br.com.quiz.quiz.model.Usuario;

public class SessionManager {
    private static Usuario usuarioLogado;

    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void logout() {
        usuarioLogado = null;
    }
}