package com.lightcode.calculadoradeinsulina;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraDeGlicemia {

    private static final int SENSIBILIDADE_INSULINA = 23;
    private static final int GLICEMIA_ALVO = 104;
    private static final int META_DE_SEGURANCA = 99;
    private static final int LIMITE_HIPOGLICEMIA_GRAVE = 55;
    private static final int LIMITE_HIPOGLICEMIA = 70;

    public void calcularInsulina(int glicemiaAtual) {
        if (glicemiaAtual <= LIMITE_HIPOGLICEMIA_GRAVE) {
            exibirMensagemHipoglicemiaGrave();
            return;
        }

        if (glicemiaAtual < LIMITE_HIPOGLICEMIA) {
            exibirMensagemHipoglicemiaModerada();
            return;
        }

        if (glicemiaAtual < GLICEMIA_ALVO) {
            System.out.println("Sua glicemia já está abaixo ou próxima do alvo. Não é necessário aplicar insulina.");
            return;
        }

        int unidadesInsulina = calcularDoseInsulina(glicemiaAtual);
        int glicemiaEsperada = calcularGlicemiaEsperada(glicemiaAtual, unidadesInsulina);

        exibirResultado(unidadesInsulina, glicemiaEsperada);
        verificarMetaDeSegurança(glicemiaEsperada);
    }

    public int receberGlicemiaAtual() {
        try (Scanner scanner = new Scanner(System.in)) {
        int glicemiaAtual;

        while (true) {
            System.out.print("\nDigite o valor atual da sua glicemia (mg/dL): ");

            try {
                glicemiaAtual = scanner.nextInt();
                if (glicemiaAtual >= 40) {
                    return glicemiaAtual;
                } else {
                    System.out.println("Sua glicemia já está abaixo de 115 mg/dL. Não é necessário aplicar insulina.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido");
                scanner.nextLine();
                }
            }
        }
    }

    private int calcularDoseInsulina(int glicemiaAtual) {
        int diferencaGlicemia = glicemiaAtual - GLICEMIA_ALVO;
        return (int) Math.round((double) diferencaGlicemia / SENSIBILIDADE_INSULINA);

    }
    private int calcularGlicemiaEsperada(int glicemiaAtual, int unidadesInsulina) {
        return glicemiaAtual - (unidadesInsulina * SENSIBILIDADE_INSULINA);

    }

    private void exibirResultado(int unidadesInsulina, int glicemiaEsperada) {
        System.out.print("\n=== Resultado da Calculadora de Insulina ===\n");
        System.out.printf("Você precisa aplicar %d unidades de insulina para atingir uma glicemia de aproximadamente  %d mg/dL.", unidadesInsulina, glicemiaEsperada);

    }

    private void verificarMetaDeSegurança(int glicemiaEsperada) {
        // Verifica se a glicemia esperada está abaixo da meta de segurança
        if (glicemiaEsperada < META_DE_SEGURANCA) {
            System.out.printf("Atenção: A glicemia esperada está abaixo da meta de segurança de %d mg/dL. Considere ajustar a dosagem ou consultar um profissional de saúde.\n", META_DE_SEGURANCA);
        }
    }

    private void exibirMensagemHipoglicemiaModerada() {
        System.out.println("""
                === ALERTA DE HIPOGLICEMIA ===
                Sua glicemia está muito baixa (abaixo de 70mg/dL).
                COMA PELO MENOS 20g DE CARBOIDRATOS SIMPLES IMEDIATAMENTE. Exemplos: água com açúcar, suco de frutas, leite condensado ou mel.
                Verifique sua glicemia novamente após 15 minutos. Se continuar baixa, repita a ingestão de carboidratos.
                Após o consumo de carboidratos, avise a alguém de confiança que está com quadro de hipoglicemia.\s
                Peça para checar se está tudo bem dentro de alguns minutos.
               \s""");
    }

    private void exibirMensagemHipoglicemiaGrave() {
        System.out.println("""
                === ALERTA DE HIPOGLICEMIA GRAVE ===
               
                CUIDADO! Sua glicemia está CRITICAMENTE BAIXA (Menor ou igual a 55mg/dL).
               
                RISCO DE INCONSCIÊNCIA!!
               
                COMA 20g DE CARBOIDRATOS SIMPLES IMEDIATAMENTE!! PEÇA AJUDA A ALGUÉM!
               
                Exemplos: água com açúcar, suco de frutas, leite condensado ou mel.
               
                Em caso de desmaio, não alimentar. Procure ajuda médica urgente.
               \s""");
    }
}