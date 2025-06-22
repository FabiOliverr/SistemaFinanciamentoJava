package com.seuprojeto.financiamentos.Main;

import com.seuprojeto.financiamentos.util.InterfaceUsuario;

public class Main {
    public static void main(String[] args) {

        //----Chamar Interface----
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazoFinanciamento = interfaceUsuario.pedirPrazoFinanciamento();
        double taxaJurosAnual = interfaceUsuario.pedirTaxaJurosAnual();
    }
}
