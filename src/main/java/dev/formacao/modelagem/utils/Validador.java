package dev.formacao.modelagem.utils;

public class Validador {

    private Validador() {
    }

    public static String naoNulo(Object objeto, String msgErro) {
        return objeto != null ? null : msgErro;
    }
    public static String naoVazio(String valor, String erro) {
        if (naoNulo(valor, erro) != null) {
            return erro;
        }
        return !valor.trim().isEmpty() ? null : erro;
    }
}
