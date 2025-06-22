package com.seuprojeto.financiamentos.modelo;

public abstract class Financiamento {

    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        if (valorImovel <= 0) {
            throw new IllegalArgumentException("O valor do imóvel deve ser maior que zero.");
        }
        if (prazoFinanciamento <= 0) {
            throw new IllegalArgumentException("O prazo do financiamento deve ser maior que zero.");
        }
        if (taxaJurosAnual < 0) {
            throw new IllegalArgumentException("A taxa de juros anual não pode ser negativa.");
        }

        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double getValorImovel() {
        return this.valorImovel;
    }

    public int getPrazoFinanciamento() {
        return this.prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    public abstract double calcularPagamentoMensal();

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * (this.prazoFinanciamento * 12);
    }

    public abstract void exibirDadosFinanciamento();
}