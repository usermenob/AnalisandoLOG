package br.upe;

import java.io.BufferedWriter; // Usado para escrever texto em arquivo de forma eficiente
import java.io.File;           // Representa um arquivo ou pasta no sistema
import java.io.FileWriter;     // Abre o arquivo para escrita
import java.io.IOException;    // Captura erros de entrada/saída
import java.util.List;         // Lista de entradas do log
import java.util.Map;          // Pares chave-valor para os sistemas operacionais

public class WriteFile {

    // System.getProperty("user.dir") retorna o diretório atual de execução do programa
    // new File(diretorio, "Análise") monta o caminho de forma compatível com qualquer SO
    // evita usar "/" ou "\" hardcoded, que quebraria em outros sistemas operacionais
    private final File pastaAnalise = new File(System.getProperty("user.dir"), "Análise");

    // mkdirs() cria a pasta "Análise" se ela ainda não existir
    // se já existir, não faz nada e não gera erro — comportamento seguro
    private void criarPasta() {
        pastaAnalise.mkdirs();
    }

    // Opção 1 — salva requisições respondidas com sucesso e tamanho > 2000
    public void recursosGrandes(List<LogEntry> entradas) {
        criarPasta(); // garante que a pasta existe antes de tentar salvar

        // try-with-resources: fecha o BufferedWriter automaticamente ao terminar
        // mesmo que ocorra um erro, evitando vazamento de recursos
        try (BufferedWriter bw = new BufferedWriter(
                // FileWriter abre o arquivo para escrita
                // new File(pastaAnalise, nome) monta o caminho correto para qualquer SO
                new FileWriter(new File(pastaAnalise, "recursosGrandes.txt")))) {

            for (LogEntry entrada : entradas) {
                // escreve uma linha no formato: "200 32653 13.66.139.0"
                // conforme o exemplo do PDF
                bw.write(entrada.getRespostaHTTP() + " " + entrada.getTamanho() + " " + entrada.getIp());
                bw.newLine(); // quebra de linha compatível com qualquer SO (\n ou \r\n)
            }
            System.out.println("recursosGrandes.txt salvo");

        } catch (IOException e) {
            // captura erros de escrita, como falta de permissão ou disco cheio
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Opção 2 — salva requisições não respondidas (400-499) de novembro/2021
    public void naoRespondidosNovembro(List<LogEntry> entradas) {
        criarPasta();

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(new File(pastaAnalise, "naoRespondidosNovembro.txt")))) {

            for (LogEntry entrada : entradas) {
                // escreve no formato: 404 "http://www.almhuette-raith.at" Nov/2021
                // conforme o exemplo do PDF
                // as aspas ao redor da URL são escapadas com \"
                bw.write(entrada.getRespostaHTTP() + " \"" + entrada.getEnderecoReferencia() + "\" Nov/2021");
                bw.newLine();
            }
            System.out.println("naoRespondidosNovembro.txt salvo");

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Opção 3 — salva o percentual de acessos por sistema operacional em 2021
    // recebe Map<String, Double> pois cada SO (chave) tem um percentual (valor)
    // exemplo: "Windows" -> 51.9472
    public void sistemasOperacionais(Map<String, Double> percentuais) {
        criarPasta();

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(new File(pastaAnalise, "sistemasOperacionais.txt")))) {

            // Map.Entry permite percorrer os pares chave-valor do Map
            for (Map.Entry<String, Double> entry : percentuais.entrySet()) {
                // escreve no formato: "Windows 51.9472"
                // conforme o exemplo do PDF
                bw.write(entry.getKey() + " " + entry.getValue());
                bw.newLine();
            }
            System.out.println("sistemasOperacionais.txt salvo");

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}