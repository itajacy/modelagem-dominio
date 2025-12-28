package dev.formacao.modelagem.shared;

import java.util.List;
import java.util.regex.Pattern;

import dev.formacao.modelagem.utils.Validador;

public record NomePessoa(String valor) {

    public static final String NOME_NULO = "NOME_NULO";
    public static final String NOME_VAZIO = "NOME_VAZIO";
    public static final String NOME_PEQUENO = "NOME_PEQUENO";
    public static final String NOME_GRANDE = "NOME_GRANDE";
    public static final String NOME_SEM_SOBRENOME = "NOME_SEM_SOBRENOME";
    public static final String NOME_CARACTERES_INVALIDOS = "NOME_CARACTERES_INVALIDOS";

    private static final Pattern NOME_REGEX = Pattern.compile("^[a-zA-ZÀ-ú'\\-\\.\\s]+$");

    public NomePessoa {
        valor = (valor == null) ? null : valor.trim().replaceAll("\\s+", " "); // substituindo multiplos espaços e tabs,
                                                                               // por um espaço simples

        String segundaParte = segundaParte(valor);

        List<String> erros = Validador.combinar(
                Validador.naoNulo(valor, NOME_NULO),
                Validador.naoVazio(valor, NOME_VAZIO),
                Validador.tamanhoMaiorQue(valor, 4, NOME_PEQUENO),
                Validador.tamanhoMenorQue(valor, 121, NOME_GRANDE),
                Validador.naoVazio(segundaParte, NOME_SEM_SOBRENOME),
                Validador.regex(valor, NOME_REGEX, NOME_CARACTERES_INVALIDOS)
        );

        if (!erros.isEmpty() ) {
            throw new IllegalArgumentException(String.join(", ", erros));
        }
    }

    public String completo() {
        return valor;
    }   

    public String primeiroNome() {
        return valor.split(" ")[0];
    }

    public String[] sobrenomes() {
        String[] partes = valor.split(" ");
        String[] sobrenomes = new String[partes.length - 1];
        System.arraycopy(partes, 1, sobrenomes, 0, sobrenomes.length);
        return sobrenomes;
    }

    public String ultimoSobrenome() {
        String[] partes = valor.split(" ");
        return partes[partes.length - 1];
    }

    public String iniciais() {
       String a = primeiraLetra(primeiroNome());
         String b = primeiraLetra(ultimoSobrenome());
         return (a + b).toUpperCase();
    }

    private static String primeiraLetra(String s) {
        int cp = s.codePointAt(0);
        return new String(Character.toChars(cp));
    }

    private static String segundaParte(String nome) {
        if (nome == null) {
            return null;
        }

        String[] partes = nome.split(" ");
        return (partes.length >= 2) ? partes[1] : null; // retira o primeiro nome e retorna a segunda parte
    }
}