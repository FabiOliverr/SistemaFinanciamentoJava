package com.seuprojeto.financiamentos.modelo;

public class Terreno extends Financiamento {
    private String tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual,
                   String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        if (tipoZona == null || tipoZona.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de zona do terreno não pode ser vazio.");
        }
        this.tipoZona = tipoZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        int numeroParcelas = getPrazoFinanciamento() * 12;
        double taxaMensal = getTaxaJurosAnual() / 12;

        if (taxaMensal == 0) {
            return (getValorImovel() / numeroParcelas) * 1.02;
        }
        double pagamentoBase = (getValorImovel() * taxaMensal) / (1 - Math.pow(1 + taxaMensal, -numeroParcelas));
        return pagamentoBase * 1.02;
    }

    @Override
    public void exibirDadosFinanciamento() {
        System.out.println("\n--- DETALHES DO FINANCIAMENTO DE TERRENO ---");
        System.out.printf("Valor do Imóvel: R$ %.2f\n", getValorImovel());
        System.out.printf("Prazo: %d anos\n", getPrazoFinanciamento());
        System.out.printf("Taxa de Juros Anual: %.2f%%\n", getTaxaJurosAnual() * 100);
        System.out.printf("Tipo de Zona: %s\n", getTipoZona());
        System.out.printf("Parcela Mensal: R$ %.2f\n", calcularPagamentoMensal());
        System.out.printf("Total Pago: R$ %.2f\n", calcularTotalPagamento());
        System.out.println("------------------------------------------");
    }
}