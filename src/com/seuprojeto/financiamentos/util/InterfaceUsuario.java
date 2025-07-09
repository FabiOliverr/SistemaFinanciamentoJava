package com.seuprojeto.financiamentos.util;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class InterfaceUsuario {
    private Scanner scanner;

    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
    }

    public double pedirValorImovel() {
        double valor = 0;
        boolean entradaValida = false;
        do {
            System.out.print("Digite o valor do imóvel (R$): ");
            try {
                valor = scanner.nextDouble();

                if (valor <= 0) {
                    System.out.println("O valor do imóvel deve ser maior que zero. Tente novamente.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para o valor do imóvel.");
                scanner.next();
            }
        } while (!entradaValida || valor <= 0);
        return valor;
    }

    public int pedirPrazoFinanciamento() {
        int prazo = 0;
        boolean entradaValida = false;
        do {
            System.out.print("Digite o prazo do financiamento em anos: ");
            try {
                prazo = scanner.nextInt();
                if (prazo <= 0) {
                    System.out.println("O prazo do financiamento deve ser maior que zero. Tente novamente.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro para o prazo.");
                scanner.next();
            }
        } while (!entradaValida || prazo <= 0);
        return prazo;
    }

    public double pedirTaxaJurosAnual() {
        double taxa = 0;
        boolean entradaValida = false;
        do {
            System.out.print("Digite a taxa de juros anual (ex: 0.08 para 8%): ");
            try {
                taxa = scanner.nextDouble();
                if (taxa < 0) {
                    System.out.println("A taxa de juros anual não pode ser negativa. Tente novamente.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para a taxa de juros.");
                scanner.next();
            }
        } while (!entradaValida || taxa < 0);
        return taxa;
    }

    public void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}