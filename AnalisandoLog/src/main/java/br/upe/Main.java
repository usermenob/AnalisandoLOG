package br.upe;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Diretório atual: " + System.getProperty("user.dir"));
        // instancia o buffer compartilhado entre as threads
        LogEntryBuffer buffer = new LogEntryBuffer();

        // instancia o gerenciador passando o arquivo, número de threads e o buffer
        // atenção: o nome da classe deles tem typo — LogReaderManeger
        LogReaderManeger gerenciador = new LogReaderManeger("access.log", 16, buffer);

        // dispara as threads, aguarda todas terminarem e retorna a lista pronta
        List<LogEntry> entradas = gerenciador.carregarEntrada();

        // instancia o analisador com a lista de entradas prontas
        LogAnalyzer analiser = new LogAnalyzer(entradas);

        // instancia o escritor de arquivos
        WriteFile escritor = new WriteFile();

        LogParser parser = new LogParser();
        LogEntry teste = parser.parsearLinha("sua linha aqui");
        System.out.println(teste);

        // instancia e exibe o menu em loop até o usuário digitar 0
        Menu menu = new Menu(analiser, escritor);
        menu.exibir();
    }
}