package br.upe;

import java.time.LocalDate;

public class LogEntry{
	
	// O Encapsulamento é usado para garantir que nenhuma outra classe acesse aos atributos,
	// por isso a visibilidade é privada. E o final garate que o valor recebido é constante. 
	
	//Ip do usuário
	private final String ip;
	//Data do acesso(Sem a hora da solicitação, pois a mesma não é usada)
	private final LocalDate dataAcesso;
	//Tipo de requisição do servidor
	private final String tipoRequisicao;
	//Endereço do recurso solicitado
	private final String enderecoRecurso;
	//Código de status de resposta HTTP
	private final int respostaHTTP;
	//tamanho do em bytes do objeto devolvido
	private final int tamanho;
	//Endereço da onde veio a requisição
	private final String enderecoReferencia;
	//Informações do user agent 
	private final String userAgent;
	
	//método construtor que recebe todos os atributos de uma vez 
	public LogEntry(String ip, LocalDate dataAcesso, String tipoRequisicao ,String enderecoRecurso, int respostaHTTP,int tamanho, String enderecoReferencia, String userAgent){
		this.ip = ip;
		this.dataAcesso = dataAcesso;
		this.tipoRequisicao = tipoRequisicao;
		this.enderecoRecurso = enderecoRecurso;
		this.respostaHTTP = respostaHTTP;
		this.tamanho = tamanho;
		this.enderecoReferencia = enderecoReferencia;
		this.userAgent = userAgent;
		}
	
	//métodos getters para o acesso de cada atributo fora da classe LogEntry
	
	public String getIp() {
		return this.ip;
	}
	public LocalDate getDataAcesso() {
		return this.dataAcesso;
	}
	public String getTipoRequisicao() {
		return this.tipoRequisicao;
	}
	public String getEnderecoRecurso() {
		return this.enderecoRecurso;
	}
	public int getRespostaHTTP() {
		return this.respostaHTTP;
	}
	public int getTamanho() {
		return this.tamanho;
	}
	public String getEnderecoReferencia() {
		return this.enderecoReferencia;
	}
	public String getUserAgent() {
		return this.userAgent;
	}
}