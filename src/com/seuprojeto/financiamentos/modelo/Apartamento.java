package com.seuprojeto.financiamentos.modelo;

public class Apartamento extends Financiamento {
    private int numeroVagasGaragem;
    private int numeroAndar;

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual,
                       int numeroVagasGaragem, int numeroAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);

        if (numeroVagasGaragem < 0 || numeroAndar <= 0) {
            throw new IllegalArgumentException("Número de vagas não pode ser negativo e número do andar deve ser positivo.");
        }
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    public int getNumeroVagasGaragem() {
        return numeroVagasGaragem;
    }

    public int getNumeroAndar() {
        return numeroAndar;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = getTaxaJurosAnual() / 12;
        int numeroParcelas = getPrazoFinanciamento() * 12;

        if (taxaMensal == 0) {
            return getValorImovel() / numeroParcelas;
        }

        double fator = Math.pow(1 + taxaMensal, numeroParcelas);
        return (getValorImovel() * fator * taxaMensal) / (fator - 1);
    }

    @Override
    public void exibirDadosFinanciamento() {
        System.out.println("\n--- DETALHES DO FINANCIAMENTO DE APARTAMENTO ---");
        System.out.printf("Valor do Imóvel: R$ %.2f\n", getValorImovel());
        System.out.printf("Prazo: %d anos\n", getPrazoFinanciamento());
        System.out.printf("Taxa de Juros Anual: %.2f%%\n", getTaxaJurosAnual() * 100);
        System.out.printf("Vagas de Garagem: %d\n", getNumeroVagasGaragem());
        System.out.printf("Número do Andar: %d\n", getNumeroAndar());
        System.out.printf("Parcela Mensal: R$ %.2f\n", calcularPagamentoMensal());
        System.out.printf("Total Pago: R$ %.2f\n", calcularTotalPagamento());
        System.out.println("----------------------------------------------");
    }
}