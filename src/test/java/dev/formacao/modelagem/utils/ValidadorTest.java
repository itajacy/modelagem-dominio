package dev.formacao.modelagem.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;


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
       assertEquals(MSG_ERRO,Validador.naoNulo(null, MSG_ERRO));
    }
    
    @Test
    void deveRetornarNuloQuandoStringForValida() {
        assertNull(Validador.naoVazio("Texto válido", MSG_ERRO));
    }

    @Test
    void deveRetornarMensagemQuandoStringForNula() {
        assertEquals(MSG_ERRO, Validador.naoVazio(null, MSG_ERRO));
    }

    @Test
    void deveRetornarMensagemQuandoStringForVazia() {
        assertEquals( MSG_ERRO, Validador.naoVazio("", MSG_ERRO));
        assertEquals( MSG_ERRO, Validador.naoVazio("   ", MSG_ERRO));
    }

    @Test
    void deveValidarTamanhoMenorQueParaString() {
        assertNull(Validador.tamanhoMenorQue("Teste", 10, MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.tamanhoMenorQue("TesteMuitoLongo", 10, MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.tamanhoMenorQue((CharSequence)null, 10, MSG_ERRO));
    }

    @Test
    void deveValidarTamanhoMenorQueParaColecao() {
        assertNull(Validador.tamanhoMenorQue((Collection<?>)null, 5, MSG_ERRO));
        assertNull(Validador.tamanhoMenorQue(List.of(1, 2, 3), 5, MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.tamanhoMenorQue(List.of(1, 2, 3, 4, 5, 6), 5, MSG_ERRO));
    }

    @Test
    void deveValidarTamanhoMaiorQueParaString() {
        assertNull(Validador.tamanhoMaiorQue("TextoLongo", 5, MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.tamanhoMaiorQue("Curto", 10, MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.tamanhoMaiorQue((CharSequence)null, 5, MSG_ERRO));
    }

    @Test
    void deveValidarTamanhoMaiorQueParaColecao() {
        assertEquals(MSG_ERRO, Validador.tamanhoMaiorQue((Collection<?>)null, 2, MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.tamanhoMaiorQue(List.of(1), 2, MSG_ERRO));
        assertNull(Validador.tamanhoMaiorQue(List.of(1, 2, 3), 2, MSG_ERRO));
    }

    @Test
    void deveValidarRegex() { 
        assertNull(Validador.regex("abc123", Pattern.compile("^[a-z]+\\d+$"), MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.regex("123abc", Pattern.compile("^[a-z]+\\d+$"), MSG_ERRO));
        assertEquals(MSG_ERRO, Validador.regex(null, Pattern.compile("^[a-z]+\\d+$"), MSG_ERRO));
    }

 
}
