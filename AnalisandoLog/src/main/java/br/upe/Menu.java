package br.upe;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final LogAnalyzer analiser;
    private final WriteFile escritor;

    public Menu(LogAnalyzer analiser, WriteFile escritor) {
        this.scanner  = new Scanner(System.in);
        this.analiser = analiser;
        this.escritor = escritor;
    }

    public void exibir() {
        int opcao; 

        do {
            System.out.println("1 - Recursos grandes respondidos");
            System.out.println("2 - Não respondidos");
            System.out.println("3 - % de requisições por SO");
            System.out.println("4 - Média das requisições POST");
            System.out.println("0 - Sair");

            opcao = Integer.parseInt(scanner.nextLine().trim()); // lê a opção do usuário e remove espaços em branco

            executarOpcao(opcao);

        } while (opcao != 0); // continua enquanto não digitar 0

        scanner.close();
        System.out.println("Encerrando programa...");
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                escritor.recursosGrandes(analiser.recursosGrandes());
                break;
            case 2:
                escritor.naoRespondidosNovembro(analiser.naoRespondidosNovembro());
                break;
            case 3:
                escritor.sistemasOperacionais(analiser.sistemasOperacionais());
                break;
            case 4:
                System.out.printf("Média do tamanho das requisições POST: %.2f%n", analiser.mediaPOST());
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida! Digite uma opção válida.");
        }
    }
}