package br.upe;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogParser{
	//Esse atributo é o regex compilado em forma uma forma padrão para o construtor
	private final Pattern padraoRegex;
	
	//método contrutor que compila o Pattern a partir do padrão
	public LogParser() {
		this.padraoRegex = Pattern.compile("(\\S+) \\S+ \\S+ \\[(\\d{2}/\\w{3}/\\d{4}):[^\\]]+\\] \"(\\w+) (\\S+) \\S+\" (\\d{3}) (\\d+) \"([^\"]*)\" \"([^\"]*)\"");
	}
	
	//método para separar cada termo
	public LogEntry parsearLinha(String linha) {
		//Objeto Matcher criado para verificar se a linha segue o padrão
		Matcher matcher = padraoRegex.matcher(linha);
		
		//if para garantir que se a linha não for do formato, retorne nulo
		if (matcher.matches()) {
			String ip = matcher.group(1);
			LocalDate data = extrairData(matcher.group(2));
			String tipoRequisicao = matcher.group(3);
			String enderecoRecurso = matcher.group(4);
			int respostaHTTP = Integer.parseInt(matcher.group(5));
			int tamanho = Integer.parseInt(matcher.group(6));
			String enderecoReferencia = matcher.group(7);
			String userAgent = matcher.group(8);
			
			//retorno para contruir cada um dos atributos de LogEntry
			return new LogEntry(ip,data,tipoRequisicao,enderecoRecurso,respostaHTTP,tamanho,enderecoReferencia,userAgent);
		}
		else {
			return null;
		}
	}
	
	//método para transformar a String data em LocalDate
	public LocalDate extrairData(String data) {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.ENGLISH);
		return LocalDate.parse(data, formatoData);
	}
}