package dev.formacao.modelagem.auth.usuario;

import java.time.Instant;

import dev.formacao.modelagem.shared.Email;

public class Usuario {
    private String id;
    private String nome;
    private Email email; // usando a classe objeto de valor Email
    private Instant dataCadastro;
}


