package dev.formacao.modelagem.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ValidadorTest {

    private static final String MSG_ERRO = "Erro genérico";

    @Test
    void deveRetornarNuloQuandoObjetoForValido() {
        assertNull(Validador.naoNulo("ABC",  MSG_ERRO));
        assertNull(Validador.naoNulo("123",  MSG_ERRO));
        assertNull(Validador.naoNulo(new Object(),  MSG_ERRO));
        // String resultado = Validador.naoNulo("objeto valido", "Objeto nulo");
        // assert resultado == null;

    }

    @Test
    void deveRetornarMensagemQuandoObjetoForNulo() {
       assertEquals(Validador.naoNulo(null, MSG_ERRO), MSG_ERRO);
    }
    
    @Test
    void deveRetornarNuloQuandoStringForValida() {
        assertNull(Validador.naoVazio("Texto válido", MSG_ERRO));
    }

    @Test
    void deveRetornarMensagemQuandoStringForNula() {
        assertEquals(Validador.naoVazio(null,   MSG_ERRO), MSG_ERRO);
    }

    @Test
    void deveRetornarMensagemQuandoStringForVazia() {
        assertEquals(Validador.naoVazio("", MSG_ERRO), MSG_ERRO);
        assertEquals(Validador.naoVazio("   ", MSG_ERRO), MSG_ERRO);
    }

}
