package br.upe;

import java.util.List; //lista para armazenar as entradas filtradas
import java.util.ArrayList; //implementação de list para criar listas mutaveis
import java.util.Map; //pares chabe-valor para armazenar os sistemas operacionais e suas porcentagens
import java.util.HashMap; //implementação de map para armazenar os percentuais

public class LogAnalyzer {
    //lista com todas as entradas do log , final pois a lista de entradas nao muda depois de recebida
    private final List<LogEntry> entradas;

    //recebe a lista de logentry preenchida pelo leitor. tudo vai ser analisado com base nisso aqui
    public LogAnalyzer(List<LogEntry> entradas) {
        this.entradas = entradas;
    }

    // Opção 1 vai filtrar as requisicoes respondidas com sucesso e com tamanho grande
    public List<LogEntry> recursosGrandes() {
        //vai acumular as entradas que atendem aos criterios
        List<LogEntry> resultado = new ArrayList<>();
        for (LogEntry entrada : entradas) {
            //se foi respondida com sucesso (200-299) e tem tamanho > 2000, entao adiciona na lista de resultado   
            if (entrada.getCodigoResposta() >= 200 && entrada.getCodigoResposta() <= 299
                    && entrada.getTamanho() > 2000) {
                resultado.add(entrada);
            }
        }
        //retorna a lista para o WriteFile salvar no arquivo recursosGrandes.txt
        return resultado;
    }

    // Opção 2 filtra requisicoes nao respondidas em nov de 2021
    public List<LogEntry> naoRespondidosNovembro() {
        List<LogEntry> resultado = new ArrayList<>();
        for (LogEntry entrada : entradas) {
            //se tem o codigo de resposta entre 400 e 499, e a data é de novembro de 2021, entao adiciona na lista de resultado
            if (entrada.getCodigoResposta() >= 400 && entrada.getCodigoResposta() <= 499
                    && entrada.getDataHora().getMonth() == Nov
                    && entrada.getDataHora().getYear() == 2021) {
                resultado.add(entrada);
            }
        }
        //retorna a lista para o WriteFile salvar no arquivo naoRespondidosNovembro.txt
        return resultado;
    }

    // Opção 3 vai calcular o percentual de acessos por sistema operacional em 2021
    public Map<String, Double> sistemasOperacionais() {
        // implementar depois
        //vai retornar um map onde a chave é o nome do sistema operacional e o valor é o percentual de acessos daquele sistema em 2021
        return new HashMap<>();
    }

    // Opção 4 calcula a media de tamanho das requisicoes POST respondidas com sucesso em 2021
    public double mediaPOST() {
        long soma = 0; //long para evitar que estore com muitas requisicoes grandes
        int count = 0; //contador para saber quantas requisicoes atendem aos criterios
        for (LogEntry entrada : entradas) {
            //vai filtrar por POST, sucesso e ano 2021
            if (entrada.getTipoRequisicao().equals("POST")
                    && entrada.getCodigoResposta() >= 200 && entrada.getCodigoResposta() <= 299
                    && entrada.getDataHora().getYear() == 2021) {
                soma += entrada.getTamanho();
                count++;
            }
        }
        //se nao encontrar vai retornar 0 e evita a divisao por zero e leva para double para retornar a media com casas decimais
        return count == 0 ? 0 : (double) soma / count;
    }
}