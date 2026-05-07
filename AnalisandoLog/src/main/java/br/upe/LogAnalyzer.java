package br.upe;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LogAnalyzer {
    private final List<LogEntry> entradas;

    public LogAnalyzer(List<LogEntry> entradas) {
        this.entradas = entradas;
    }

    // Opção 1
    public List<LogEntry> recursosGrandes() {
        List<LogEntry> resultado = new ArrayList<>();
        for (LogEntry entrada : entradas) {
            if (entrada.getCodigoResposta() >= 200 && entrada.getCodigoResposta() <= 299
                    && entrada.getTamanho() > 2000) {
                resultado.add(entrada);
            }
        }
        return resultado;
    }

    // Opção 2
    public List<LogEntry> naoRespondidosNovembro() {
        List<LogEntry> resultado = new ArrayList<>();
        for (LogEntry entrada : entradas) {
            if (entrada.getCodigoResposta() >= 400 && entrada.getCodigoResposta() <= 499
                    && entrada.getDataHora().getMonthValue() == 11
                    && entrada.getDataHora().getYear() == 2021) {
                resultado.add(entrada);
            }
        }
        return resultado;
    }

    // Opção 3
    public Map<String, Double> sistemasOperacionais() {
        // implementar depois
        return new HashMap<>();
    }

    // Opção 4
    public double mediaPOST() {
        long soma = 0;
        int count = 0;
        for (LogEntry entrada : entradas) {
            if (entrada.getTipoRequisicao().equals("POST")
                    && entrada.getCodigoResposta() >= 200 && entrada.getCodigoResposta() <= 299
                    && entrada.getDataHora().getYear() == 2021) {
                soma += entrada.getTamanho();
                count++;
            }
        }
        return count == 0 ? 0 : (double) soma / count;
    }
}