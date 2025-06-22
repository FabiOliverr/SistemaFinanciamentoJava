package com.seuprojeto.financiamentos.modelo;

public class Casa extends Financiamento {
    private double areaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual,
                double areaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);

        if (areaConstruida <= 0 || tamanhoTerreno <= 0) {
            throw new IllegalArgumentException("Área construída e tamanho do terreno devem ser maiores que zero.");
        }
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        int numeroParcelas = getPrazoFinanciamento() * 12;
        double taxaMensal = getTaxaJurosAnual() / 12;

        double jurosMensal;
        double pagamentoBaseSemJuros;

        if (taxaMensal == 0) {
            pagamentoBaseSemJuros = getValorImovel() / numeroParcelas;
            jurosMensal = 0;
        } else {
            double pagamentoComJurosBase = (getValorImovel() * taxaMensal) / (1 - Math.pow(1 + taxaMensal, -numeroParcelas));
            pagamentoBaseSemJuros = getValorImovel() / numeroParcelas;
            jurosMensal = pagamentoComJurosBase - pagamentoBaseSemJuros;
        }

        if (80.0 > jurosMensal / 2) {
            throw new AumentoMaiorDoQueJurosException(
                    "O acréscimo de R$ 80 no financiamento da casa é maior que a metade dos juros mensais! " +
                            "Juros mensais: R$ " + String.format("%.2f", jurosMensal) +
                            ", Metade dos juros: R$ " + String.format("%.2f", jurosMensal / 2)
            );
        }

        if (taxaMensal == 0) {
            return (getValorImovel() / numeroParcelas) + 80.0;
        }
        double pagamentoMensalFinal = (getValorImovel() * taxaMensal) / (1 - Math.pow(1 + taxaMensal, -numeroParcelas));
        return pagamentoMensalFinal + 80.0;
    }

    @Override
    public void exibirDadosFinanciamento() {
        System.out.println("\n--- DETALHES DO FINANCIAMENTO DE CASA ---");
        System.out.printf("Valor do Imóvel: R$ %.2f\n", getValorImovel());
        System.out.printf("Prazo: %d anos\n", getPrazoFinanciamento());
        System.out.printf("Taxa de Juros Anual: %.2f%%\n", getTaxaJurosAnual() * 100);
        System.out.printf("Área Construída: %.2f m²\n", getAreaConstruida()); // Novo atributo
        System.out.printf("Tamanho do Terreno: %.2f m²\n", getTamanhoTerreno()); // Novo atributo
        System.out.printf("Parcela Mensal: R$ %.2f\n", calcularPagamentoMensal());
        System.out.printf("Total Pago: R$ %.2f\n", calcularTotalPagamento());
        System.out.println("-----------------------------------------");
    }
}