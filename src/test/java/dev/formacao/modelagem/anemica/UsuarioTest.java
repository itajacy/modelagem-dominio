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

    @Test
    void deveCriarUsuariosIguais() {
        Usuario usuario1 = new Usuario();
        usuario1.setId("1");
        usuario1.setNome("João");
        usuario1.setEmail("joao@zmail.com");
        usuario1.setSenha("senha123");

        Usuario usuario2 = new Usuario();
        usuario2.setId("1");
        usuario2.setNome("João");
        usuario2.setEmail("joao@zmail.com");
        usuario2.setSenha("senha123");

        assertEquals(usuario1, usuario2);
    }

    @Test
    void deveCriarUsuariosDiferentes() {
        Usuario usuario1 = new Usuario();
        usuario1.setId("1");
        usuario1.setNome("João");
        usuario1.setEmail("joao@zmail.com");
        usuario1.setSenha("senha123");

        Usuario usuario2 = new Usuario();
        usuario2.setId("2");
        usuario2.setNome("Maria");
        usuario2.setEmail("maria@zmail.com");
        usuario2.setSenha("senha456");

        assertNotEquals(usuario1, usuario2);
    }


    @Test
    void deveCompararUsuarioComOutroObjeto() {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        usuario.setNome("João");
        usuario.setEmail("joao@zmail.com");
        usuario.setSenha("senha123");

        Object outroObjeto = new Object();

        assertNotEquals(usuario, outroObjeto);
    }

    @Test
    void deveCompararUsuarioComNull() {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        usuario.setNome("João");
        usuario.setEmail("joao@zmail.com");
        usuario.setSenha("senha123");   
        assertNotEquals(usuario, null);
    }

    @Test
    void deveCompararUsuarioComEleMesmo() {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        usuario.setNome("João");
        usuario.setEmail("joao@zmail.com");
        usuario.setSenha("senha123");

        Usuario usuario2 = usuario;

        assertEquals(usuario, usuario2);
    }

    @Test
    void deveCompararUsuarioComIdNullComUsuario() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(null);
        usuario1.setNome("João");
        usuario1.setEmail("joao@zmail.com");
        usuario1.setSenha("senha123");

        Usuario usuario2 = new Usuario();
        usuario2.setId("2");
        usuario2.setNome("João");
        usuario2.setEmail("joao@zmail.com");
        usuario2.setSenha("senha123");

        assertNotEquals(usuario1, usuario2);
    }

    @Test
    void deveCompararUsuarioComIdNullComOutroUsuarioIdNull() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(null);
        usuario1.setNome("João");
        usuario1.setEmail("joao@zmail.com");
        usuario1.setSenha("senha123");

        Usuario usuario2 = new Usuario();
        usuario2.setId(null);
        usuario2.setNome("João");
        usuario2.setEmail("joao@zmail.com");
        usuario2.setSenha("senha123");

        assertEquals(usuario1, usuario2);
    }

    @Test
    void deveGerarHashCodeCorreto() {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        usuario.setNome("João");
        usuario.setEmail("joao@zmail.com");
        usuario.setSenha("senha123");

        int expectedHashCode = usuario.getId().hashCode();
        assertEquals(expectedHashCode, usuario.hashCode());

    }

}