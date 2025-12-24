package dev.formacao.modelagem.anemica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void teste1() {
        assertTrue(true);
    }

    @Test
    void teste2() {
        assertFalse(false);
    }

    @Test
    void deveCriarUsuarioComAtributosCorretos() {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        usuario.setNome("João");
        usuario.setEmail("joao@zmail.com");
        usuario.setSenha("senha123");

        assertEquals("1", usuario.getId());
        assertEquals("João", usuario.getNome());
        assertEquals("joao@zmail.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
    }

    @Test
    void deveCriarUsuarioComAtributosErrados() {
        Usuario usuario = new Usuario();
        usuario.setId("    ");
        usuario.setNome("joao@zmail.com");
        usuario.setEmail("abc");
        usuario.setSenha("123456");

        assertEquals("    ", usuario.getId());
        assertEquals("joao@zmail.com", usuario.getNome());
        assertEquals("abc", usuario.getEmail());
        assertEquals("123456", usuario.getSenha());
    }

}