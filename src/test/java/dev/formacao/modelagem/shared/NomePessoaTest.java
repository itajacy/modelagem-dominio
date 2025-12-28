package dev.formacao.modelagem.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NomePessoaTest {
    
    @Test
    void deveLancarExcecoesQuandoNomeForNulo() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new NomePessoa(null));
        assertTrue(ex.getMessage().contains(NomePessoa.NOME_VAZIO));
        assertTrue(ex.getMessage().contains(NomePessoa.NOME_SEM_SOBRENOME));
    }

    @Test
    void deveLancarExcecoesQuandoNomeForVazio() {
        var ex1 = assertThrows(IllegalArgumentException.class, () -> new NomePessoa(""));
        assertTrue(ex1.getMessage().contains(NomePessoa.NOME_VAZIO));
        assertTrue(ex1.getMessage().contains(NomePessoa.NOME_SEM_SOBRENOME));

        
        var ex2 = assertThrows(IllegalArgumentException.class, () -> new NomePessoa("   "));
        assertTrue(ex2.getMessage().contains(NomePessoa.NOME_VAZIO));
        assertTrue(ex2.getMessage().contains(NomePessoa.NOME_SEM_SOBRENOME));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForPequeno() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new NomePessoa("A B"));
        assertTrue(ex.getMessage().contains(NomePessoa.NOME_PEQUENO));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForGrande() {
        String nomeGrande = "A".repeat(122) + " Silva";
        var ex = assertThrows(IllegalArgumentException.class, () -> new NomePessoa(nomeGrande));
        assertTrue(ex.getMessage().contains(NomePessoa.NOME_GRANDE));
    }

    @Test
    void deveLancarExcecaoQuandoNomeNaoTiverSobrenome() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new NomePessoa("Leonardo"));
        assertEquals(NomePessoa.NOME_SEM_SOBRENOME,ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeTiverCaracteresInvalidos() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new NomePessoa("João Silva 123"));
        assertEquals(NomePessoa.NOME_CARACTERES_INVALIDOS, ex.getMessage());
    }

    @Test
    void deveNormalizarEspacosQuandoNomeForValido() {
        NomePessoa nome = new NomePessoa("  Maria   Clara  Souza  ");
        assertEquals("Maria Clara Souza", nome.completo());
    }   

    @Test
    void deveRetornarPropriedadesBasicasQuandoNomeForValido() {
        NomePessoa nome = new NomePessoa("      Ana     Beatriz     Oliveira    ");

        assertEquals("Ana Beatriz Oliveira", nome.completo());
        assertEquals("Ana Beatriz Oliveira", nome.valor());
        assertEquals("Ana", nome.primeiroNome());
        assertArrayEquals(new String[] { "Beatriz", "Oliveira" }, nome.sobrenomes());
        assertEquals("Oliveira", nome.ultimoSobrenome());
    }   

    @Test
    void deveRetornarAsIniciaisQuandoForValido() {
        NomePessoa nome = new NomePessoa("Carlos Eduardo Mendes");

        assertEquals("CM", nome.iniciais());
    }   

    @Test
    void deveRetornarIniciaisQuandoNomeTiverVariosSobrenomes() {
        NomePessoa nome = new NomePessoa("Luisa Fernanda Gomes da Silva");

        assertEquals("LS", nome.iniciais());
    }

    @Test
    void deveRetornarIniciaisEmMaiusculasQuandoNomeEstiverEmMinusculas() {
        NomePessoa nome = new NomePessoa("pedro henrique alves");

        assertEquals("PA", nome.iniciais());
    }

    @Test
    void deveRetornarIniciaisQuandoNoveTiverAcentos() {
        NomePessoa nome = new NomePessoa("Érica Ávila-O'Connor");

        assertEquals("ÉÁ", nome.iniciais());
    }

    @Test
    void deveRetornarIniciaisQuandoNomeTiverApostrofoOuHifen() {
        NomePessoa nome = new NomePessoa("Ana-Maria O'Neill");

        assertEquals("AO", nome.iniciais());
    }

    @Test
    void deveLancarExcecaoQuandoNomeNaoTiverSobrenomeComEspacosNormalizados() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new NomePessoa("   Leonardo    "));
        assertEquals(NomePessoa.NOME_SEM_SOBRENOME, ex.getMessage());
    }

    @Test
    void deveCombinarMultiplosErrosQuandoNomeForInvalido() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new NomePessoa("!"));
        assertTrue(ex.getMessage().contains(NomePessoa.NOME_PEQUENO));
        assertTrue(ex.getMessage().contains(NomePessoa.NOME_SEM_SOBRENOME));
        assertTrue(ex.getMessage().contains(NomePessoa.NOME_CARACTERES_INVALIDOS));
    }

}