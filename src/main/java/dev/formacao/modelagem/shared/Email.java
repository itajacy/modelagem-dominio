package dev.formacao.modelagem.shared;

import java.util.List;
import java.util.regex.Pattern;

import dev.formacao.modelagem.utils.Validador;

// como eu quero que o meu registor de e-mail seja imutável, eu uso record
public record Email(String valor) {

    public static final String EMAIL_NULO = "EMAIL_NULO";
    public static final String EMAIL_VAZIO = "EMAIL_VAZIO"; 
    public static final String EMAIL_INVALIDO = "EMAIL_INVALIDO"; 
    public static final String EMAIL_LOCAL_GRANDE = "EMAIL_LOCAL_GRANDE";
    public static final String EMAIL_DOMINIO_GRANDE = "EMAIL_DOMINIO_GRANDE";  


    // private static final Pattern EMAIL_REGEX = Pattern.compile("^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    private static final Pattern EMAIL_REGEX = Pattern.compile("^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@(([^<>()[\\]\\\\.,;:\\s@\"]+\\.)+[^<>()[\\]\\\\.,;:\\s@\"]{2,})$",
            Pattern.CASE_INSENSITIVE);

    public Email {
       valor = (valor == null) ? null : valor.trim().toLowerCase();

       String local = extrairLocal(valor);
       String dominio = extrairDominio(valor);  

       List<String> erros = Validador.combinar(
            Validador.naoNulo(valor, EMAIL_NULO),
            Validador.naoVazio(valor, EMAIL_VAZIO),
            Validador.regex(valor, EMAIL_REGEX, EMAIL_INVALIDO),
            Validador.tamanhoMenorQue(local, 65, EMAIL_LOCAL_GRANDE),
            Validador.tamanhoMenorQue(dominio, 256, EMAIL_DOMINIO_GRANDE)
       );

       if (erros != null && !erros.isEmpty()) {
           throw new IllegalArgumentException(String.join(", ", erros));
       }
    }

    public String local() {
        return extrairLocal(valor);
    }

    public String dominio() {
        return extrairDominio(valor);
    }

    private static String extrairLocal(String email) {
        if (email == null) {
            return null;
        }

        int idx = email.indexOf('@');
        if (idx <= 0)  return email;
        return email.substring(0, idx);
    }

    private static String extrairDominio(String email) {
        if (email == null) {
            return null;
        }

        int idx = email.indexOf('@');
        if (idx < 0 || idx == email.length() - 1)  return "";
        return email.substring(idx + 1);
    }

}
