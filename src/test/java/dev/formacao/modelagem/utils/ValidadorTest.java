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

}
