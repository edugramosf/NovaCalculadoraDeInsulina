package com.lightcode.calculadoradeinsulina;

public class Main {
    public static void main(String[] args) {
        CalculadoraDeGlicemia calculadora = new CalculadoraDeGlicemia();
        int glicemiaAtual = calculadora.receberGlicemiaAtual();
        calculadora.calcularInsulina(glicemiaAtual);
    }
}