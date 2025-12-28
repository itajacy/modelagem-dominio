package dev.formacao.modelagem.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    void deveLancarExcecaoQuandoEmailForNulo() {

        // assertThrows espera que uma exceção seja lançada ao executar o código
        // fornecido
        // isso faz parte do JUnit 5

        var ex = assertThrows(IllegalArgumentException.class, () -> new Email(null));
        assertTrue(ex.getMessage().contains(Email.EMAIL_NULO));
        assertTrue(ex.getMessage().contains(Email.EMAIL_VAZIO));
    }

    @Test
    void deveLancarExcecaoQuandoEmailForVazio() {

        var ex = assertThrows(IllegalArgumentException.class, () -> new Email(""));
        assertTrue(ex.getMessage().contains(Email.EMAIL_VAZIO));
        assertTrue(ex.getMessage().contains(Email.EMAIL_INVALIDO));
    }

    @Test
    void deveLancarExcecaoQuandoEmailForInvalido() {

        var ex = assertThrows(IllegalArgumentException.class, () -> new Email("leo@"));
        assertEquals(Email.EMAIL_INVALIDO, ex.getMessage());
    }


    @Test
    void deveNormalizarEExtrairLocalEDominioQuandoEmailForValido() { 
        Email email = new Email("  Leo.Silva@Exemplo.COM  ");
        assertEquals("leo.silva@exemplo.com", email.valor());
        assertEquals("leo.silva", email.local());
        assertEquals("exemplo.com", email.dominio());
    }

    @Test
    void deveAceitarLocalQuandoLocaTiverAte64Caracteres() {
        String local = "a".repeat(64);
       Email email = new Email(local + "@exemplo.com");
       assertEquals(local, email.local());
       assertEquals("exemplo.com", email.dominio());
    }

    @Test
    void deveLancarExcecaoQuandoLocalTiverMaisDe64Caracteres() {
        String local = "a".repeat(65);
        var ex = assertThrows(IllegalArgumentException.class, () -> new Email(local + "@exemplo.com"));
        assertEquals(Email.EMAIL_LOCAL_GRANDE, ex.getMessage());
    }

    @Test
    void deveAceitarDominioQuandoDominioTiverAte255Caracteres() {
        String dominio = "a".repeat(251) + ".com"; // 251 + 4 = 255
        Email email = new Email("leo@" + dominio);
        assertEquals("leo", email.local());
        assertEquals(dominio, email.dominio());
    }

    @Test
    void deveLancarExcecaoQuandoDominioTiverMaisDe255Caracteres() {
        String dominio = "a".repeat(252) + ".com"; // 252 + 4 = 256
        var ex = assertThrows(IllegalArgumentException.class, () -> new Email("leo@" + dominio));
        assertEquals(Email.EMAIL_DOMINIO_GRANDE, ex.getMessage());
    }

    @Test
    void devemCombinarErrosQuandoLocalEDominioEstouraremLimite(){
        String local = "a".repeat(65);
        String dominio = "b".repeat(252) + ".com";
        var ex = assertThrows(IllegalArgumentException.class, () -> new Email(local + "@" + dominio));
        assertTrue(ex.getMessage().contains(Email.EMAIL_LOCAL_GRANDE));
        assertTrue(ex.getMessage().contains(Email.EMAIL_DOMINIO_GRANDE));
    }



}
