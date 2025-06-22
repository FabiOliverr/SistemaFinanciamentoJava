package com.seuprojeto.financiamentos.Main;

import com.seuprojeto.financiamentos.modelo.Apartamento;
import com.seuprojeto.financiamentos.modelo.AumentoMaiorDoQueJurosException;
import com.seuprojeto.financiamentos.modelo.Casa;
import com.seuprojeto.financiamentos.modelo.Financiamento;
import com.seuprojeto.financiamentos.modelo.Terreno;
import com.seuprojeto.financiamentos.util.InterfaceUsuario;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario ui = new InterfaceUsuario();
        List<Financiamento> financiamentos = new ArrayList<>();
        System.out.println("--- Digite os dados para o primeiro financiamento (tipo Casa) ---");
        try {
            double valorImovelUsuario = ui.pedirValorImovel();
            int prazoFinanciamentoUsuario = ui.pedirPrazoFinanciamento();
            double taxaJurosAnualUsuario = ui.pedirTaxaJurosAnual();

            double areaConstruidaUsuario = 150.0;
            double tamanhoTerrenoUsuario = 300.0;
            System.out.println("Para este financiamento (Casa), área construída será 150m² e terreno 300m².");

            Casa financiamentoUsuario = new Casa(valorImovelUsuario, prazoFinanciamentoUsuario, taxaJurosAnualUsuario,
                    areaConstruidaUsuario, tamanhoTerrenoUsuario);
            financiamentos.add(financiamentoUsuario);

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar o financiamento do usuário: " + e.getMessage());
        } catch (AumentoMaiorDoQueJurosException e) {
            System.err.println("Erro no cálculo da parcela da casa do usuário: " + e.getMessage());
        }
        System.out.println("\n--- Adicionando Financiamentos de Casa (dados fixos) ---");
        try {
            financiamentos.add(new Casa(300000.0, 20, 0.07, 120.0, 250.0));
            financiamentos.add(new Casa(100000.0, 5, 0.005, 80.0, 150.0));
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar financiamento de Casa fixa: " + e.getMessage());
        } catch (AumentoMaiorDoQueJurosException e) {
            System.err.println("Erro no cálculo da parcela de Casa fixa: " + e.getMessage());
        }
        System.out.println("\n--- Adicionando Financiamentos de Apartamento (dados fixos) ---");
        try {
            financiamentos.add(new Apartamento(250000.0, 15, 0.06, 1, 5)); // Apartamento 1
            financiamentos.add(new Apartamento(350000.0, 25, 0.07, 2, 12)); // Apartamento 2
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar financiamento de Apartamento: " + e.getMessage());
        }
        System.out.println("\n--- Adicionando Financiamento de Terreno (dados fixos) ---");
        try {
            financiamentos.add(new Terreno(150000.0, 10, 0.09, "Comercial")); // Terreno
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar financiamento de Terreno: " + e.getMessage());
        }
        double somaValoresImoveis = 0;
        double somaTotalFinanciamentos = 0;

        System.out.println("\n### RESUMO DE TODOS OS FINANCIAMENTOS ###");
        for (Financiamento f : financiamentos) {
            try {
                f.exibirDadosFinanciamento();
                somaValoresImoveis += f.getValorImovel();
                somaTotalFinanciamentos += f.calcularTotalPagamento();
            } catch (AumentoMaiorDoQueJurosException e) {
                System.err.println("\nNão foi possível exibir/somar o financiamento do tipo " + f.getClass().getSimpleName() +
                        " devido a um erro: " + e.getMessage());
            }
        }

        System.out.println("\n----------------------------------------------------");
        System.out.printf("Soma total dos valores dos imóveis: R$ %.2f\n", somaValoresImoveis);
        System.out.printf("Soma total de todos os financiamentos: R$ %.2f\n", somaTotalFinanciamentos);
        System.out.println("----------------------------------------------------");

        ui.fecharScanner();
    }
}