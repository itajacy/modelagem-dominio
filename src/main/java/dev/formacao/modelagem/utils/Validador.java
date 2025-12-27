package dev.formacao.modelagem.utils;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Validador {

    private Validador() {
    }

    public static List<String> combinar(String... erros) {
        if (erros == null || erros.length == 0) {
            return List.of();
        }

        Set<String> unicos = new LinkedHashSet<>();
        for (String erro : erros) {
            if (erro != null && !erro.isBlank()) {
                unicos.add(erro);
            }
        }
        return unicos.isEmpty() ? List.of() : List.copyOf(unicos);
    }

    public static String naoNulo(Object objeto, String erro) {
        return objeto != null ? null : erro;
    }

    public static String naoVazio(String valor, String erro) {
        if (naoNulo(valor, erro) != null) {
            return erro;
        }
        return !valor.trim().isEmpty() ? null : erro;
    }

    public static String tamanhoMenorQue(CharSequence valor, int tamanhoMaximo, String erro) {
        return( valor != null && valor.length() < tamanhoMaximo) ? null : erro;
    }

    

    public static String tamanhoMenorQue(Collection<?> valor, int tamanhoMaximo, String erro) {
        return( valor != null && valor.size() < tamanhoMaximo) ? null : erro;
    }

    public static String tamanhoMaiorQue(CharSequence valor, int tamanhoMinimo, String erro) {
        return( valor != null && valor.length() > tamanhoMinimo) ? null : erro;
    }

    public static String tamanhoMaiorQue(Collection<?> valor, int tamanhoMinimo, String erro) {
        return( valor != null && valor.size() > tamanhoMinimo) ? null : erro;
    }

    public static String regex(String valor, Pattern regex, String erro) {
        if (valor == null) {
            return erro;
        }
        return regex.matcher(valor).find() ? null : erro;
    }

}
