package com.seuprojeto.financiamentos.modelo;

//-----Classe-----

public class Financiamento {

    //-----Atributos-----

    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;


    //-----Construtor-----

    public Financiamento(double valorDesejadoImovel,int prazoFinanciamentoAnos,double taxaJurosAnual){
        this.valorImovel = valorDesejadoImovel;
        this.prazoFinanciamento = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    //-----Métodos-----

    // Método método para calcular o pagamento mensal.

   double calcularPagamentoMensal(){
       return (this.valorImovel / (this.prazoFinanciamento * 12)) * (1 + (this.taxaJurosAnual / 12));
   }
    // Método para calcular o total do pagamento.

    double calcularPagamentoAnual(){
        return (calcularPagamentoMensal() * (this.prazoFinanciamento * 12));
    }
}
