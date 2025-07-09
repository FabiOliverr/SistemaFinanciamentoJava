package com.seuprojeto.financiamentos.util;

import com.seuprojeto.financiamentos.modelo.*; // Importa todas as classes de modelo
import java.io.*; // Importa as classes de IO (Input/Output)
import java.util.ArrayList;
import java.util.List;
import java.util.Locale; // Necessário para o Double.parseDouble ler pontos

public class GerenciadorDeDados {

    private static final String SEPARADOR = ";"; // Caractere para separar os dados no arquivo de texto

    // --- MÉTODOS PARA SALVAR/LER EM ARQUIVO DE TEXTO (.txt) ---

    public static void salvarFinanciamentosTxt(List<Financiamento> financiamentos, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Financiamento f : financiamentos) {
                StringBuilder linha = new StringBuilder();
                linha.append(f.getClass().getSimpleName()).append(SEPARADOR); // Tipo do financiamento (Casa, Apartamento, Terreno)
                linha.append(String.format(Locale.US, "%.2f", f.getValorImovel())).append(SEPARADOR);
                linha.append(f.getPrazoFinanciamento()).append(SEPARADOR);
                linha.append(String.format(Locale.US, "%.4f", f.getTaxaJurosAnual())).append(SEPARADOR);

                // Adiciona atributos específicos de cada subclasse
                if (f instanceof Casa) {
                    Casa casa = (Casa) f;
                    linha.append(String.format(Locale.US, "%.2f", casa.getAreaConstruida())).append(SEPARADOR);
                    linha.append(String.format(Locale.US, "%.2f", casa.getTamanhoTerreno()));
                } else if (f instanceof Apartamento) {
                    Apartamento apto = (Apartamento) f;
                    linha.append(apto.getNumeroVagasGaragem()).append(SEPARADOR);
                    linha.append(apto.getNumeroAndar());
                } else if (f instanceof Terreno) {
                    Terreno terreno = (Terreno) f;
                    linha.append(terreno.getTipoZona());
                }
                writer.println(linha.toString());
            }
            System.out.println("Dados salvos em '" + nomeArquivo + "' com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados em arquivo de texto: " + e.getMessage());
        }
    }

    public static List<Financiamento> lerFinanciamentosTxt(String nomeArquivo) {
        List<Financiamento> financiamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(SEPARADOR);
                if (partes.length < 4) { // Pelo menos tipo, valor, prazo, taxa
                    System.err.println("Linha inválida no arquivo de texto: " + linha);
                    continue;
                }

                String tipo = partes[0];
                // Usamos Locale.US para garantir que Double.parseDouble leia corretamente números com ponto como decimal.
                // Apesar da InterfaceUsuario usar vírgula, a gravação e leitura via String.format/Double.parseDouble com Locale.US é padrão e mais segura para arquivos.
                double valor = Double.parseDouble(partes[1]);
                int prazo = Integer.parseInt(partes[2]);
                double taxa = Double.parseDouble(partes[3]);

                try {
                    switch (tipo) {
                        case "Casa":
                            if (partes.length >= 6) {
                                double areaConstruida = Double.parseDouble(partes[4]);
                                double tamanhoTerreno = Double.parseDouble(partes[5]);
                                financiamentos.add(new Casa(valor, prazo, taxa, areaConstruida, tamanhoTerreno));
                            } else {
                                System.err.println("Linha de Casa incompleta: " + linha);
                            }
                            break;
                        case "Apartamento":
                            if (partes.length >= 6) { // Ajustado para 6 pois precisa de valor,prazo,taxa,vagas,andar e o tipo
                                int numeroVagas = Integer.parseInt(partes[4]);
                                int numeroAndar = Integer.parseInt(partes[5]);
                                financiamentos.add(new Apartamento(valor, prazo, taxa, numeroVagas, numeroAndar));
                            } else {
                                System.err.println("Linha de Apartamento incompleta: " + linha);
                            }
                            break;
                        case "Terreno":
                            if (partes.length >= 5) { // Ajustado para 5 pois precisa de valor,prazo,taxa,tipoZona e o tipo
                                String tipoZona = partes[4];
                                financiamentos.add(new Terreno(valor, prazo, taxa, tipoZona));
                            } else {
                                System.err.println("Linha de Terreno incompleta: " + linha);
                            }
                            break;
                        default:
                            System.err.println("Tipo de financiamento desconhecido: " + tipo + " na linha: " + linha);
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro de formato de número ao ler linha: " + linha + " - " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro de validação ao criar financiamento da linha: " + linha + " - " + e.getMessage());
                }
            }
            System.out.println("Dados lidos de '" + nomeArquivo + "' com sucesso!");
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo '" + nomeArquivo + "' não encontrado. Criando um novo se for salvar.");
        } catch (IOException e) {
            System.err.println("Erro ao ler dados do arquivo de texto: " + e.getMessage());
        }
        return financiamentos;
    }

    // --- MÉTODOS PARA SALVAR/LER O ARRAYLIST SERIALIZADO (.ser) ---

    public static void salvarFinanciamentosSerializados(List<Financiamento> financiamentos, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(financiamentos);
            System.out.println("Lista de financiamentos serializada salva em '" + nomeArquivo + "' com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao serializar dados: " + e.getMessage());
        }
    }

    public static List<Financiamento> lerFinanciamentosSerializados(String nomeArquivo) {
        List<Financiamento> financiamentos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List) { // Verifica se o objeto lido é uma Lista
                financiamentos = (List<Financiamento>) obj; // Faz o cast
                System.out.println("Lista de financiamentos serializada lida de '" + nomeArquivo + "' com sucesso!");
            } else {
                System.err.println("Conteúdo do arquivo serializado não é uma lista de financiamentos.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo serializado '" + nomeArquivo + "' não encontrado. Criando um novo se for salvar.");
        } catch (IOException e) {
            System.err.println("Erro de IO ao desserializar dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe de financiamento não encontrada durante a desserialização: " + e.getMessage());
        } catch (ClassCastException e) {
            System.err.println("Erro de conversão de tipo ao desserializar: " + e.getMessage());
        }
        return financiamentos;
    }
}