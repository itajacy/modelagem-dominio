package dev.formacao.modelagem.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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


}
