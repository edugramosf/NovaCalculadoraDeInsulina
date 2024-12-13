package com.lightcode.calculadoradeinsulina;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraDeGlicemiaTest {

    @Test
    void testeCalcularInsulina_glicemia450_retorna15() {
        CalculadoraDeGlicemia calculadora = new CalculadoraDeGlicemia();

        int glicemia = 450;
        int unidadesInsulina = calculadora.calcularDoseInsulina(glicemia);
        assertEquals(15, unidadesInsulina);
    }

    @Test
    void testeCalcularDoseInsulina_glicemiaNormal_retorno() {
        CalculadoraDeGlicemia calculadora = new CalculadoraDeGlicemia();
        int glicemia = 100;
        int unidadeInsulina = calculadora.calcularDoseInsulina(glicemia);
        assertEquals(0, unidadeInsulina);
    }

    @Test
    void testeCalcularDoseInsulina_glicemiaBaixa_retorna0() {
        CalculadoraDeGlicemia calculadora = new CalculadoraDeGlicemia();
        int glicemia = 70;
        int unidadeInsulina = calculadora.calcularDoseInsulina(glicemia);
        assertEquals(0, unidadeInsulina);
    }

    @Test
    void testeCalcularDoseInsulina_glicemiaProximaAlvo_retorna0() {
        CalculadoraDeGlicemia calculadora = new CalculadoraDeGlicemia();
        int glicemia = 98;
        int unidadeInsulina = calculadora.calcularDoseInsulina(glicemia);
        assertEquals(0, unidadeInsulina);
    }

    @Test
    void testeCalcularDoseInsulina_glicemiaInvalida_lancaExcecao() {
        CalculadoraDeGlicemia calculadora = new CalculadoraDeGlicemia();
        assertThrows(IllegalArgumentException.class, () -> {
                calculadora.calcularDoseInsulina(-10);
        });
    }
}
