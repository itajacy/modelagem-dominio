package dev.formacao.modelagem.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ValidadorTest {

    private static final String MSG_ERRO = "Erro genérico";

       @Test
    void deveCombinarErrosUnicos() {
        var erros = Validador.combinar("Erro 1", "Erro 2", "Erro 1", "Erro 3", null, "   ", "Erro 2");

        // var erros = Validador.combinar(
        //     Validador.naoNulo(null, "Erro 1"),
        //     Validador.naoNulo("    ", "Erro 2"),
        //     Validador.naoNulo(null, "Erro 1"),
        //     Validador.naoNulo(null, "Erro 3"),
        //     Validador.naoVazio("Texto válido", "Erro 2"),"Erro 1", "Erro 3", null, "   "
        // );
        assertEquals(3, erros.size());
        assertTrue(erros.contains("Erro 1"));
        assertTrue(erros.contains("Erro 2"));
        assertTrue(erros.contains("Erro 3"));

    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumErroForFornecido() {
        var erros1 = Validador.combinar();
        assertTrue(erros1.isEmpty());
        var erros2 = Validador.combinar((String[]) null);
        assertTrue(erros2.isEmpty());
    }

    @Test
    void deveRetornarListaVaziaQuandoApenasErrosInvalidosForemFornecidos() {
        var erros = Validador.combinar(null, "   ", null);
        assertTrue(erros.isEmpty());
    }

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
