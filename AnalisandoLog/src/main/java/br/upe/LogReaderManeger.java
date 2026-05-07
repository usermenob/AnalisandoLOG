package br.upe;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class LogReaderManeger{
	
	//Os atributos dessa classe são referentes as threads
	
	//O primeiro atributo é o caminho do arquivo a ser lido
	private final String caminhoArquivo;
	//O segundo atributo é referente a quantidade de threads
	private final int quantidadeThreads;
	//O último atributo é referente a ao buffer, que é a lista contendo as linhas do Log
	private final LogEntryBuffer buffer;
	
	//Esse é o método construtor da classe
	public LogReaderManeger(String caminhoArquivo, int quantidadeThreads, LogEntryBuffer buffer) {
		this.caminhoArquivo = caminhoArquivo;
		this.quantidadeThreads = quantidadeThreads;
		this.buffer = buffer;
	}
	
	public List<LogEntry> carregarEntrada() {
		//Instancia o objeto do tipo file que recebe o caminho do arquivo recebido para poder dividi-lo
		File entrada = new File(caminhoArquivo);
		//calcula o tamanho total do arquivo recebido
		long tamanhoTotal = entrada.length();
		
		
		//Calcula os intervalos de bytes para dividir cada thread
		List<long[]> intervalos = dividirArquivo(tamanhoTotal);
		
		
		//Cria a dispara as threads
		List<LogThread> tarefas = new ArrayList<>();
		LogParser parser = new LogParser();
		
		for (int i =0;i<quantidadeThreads;i++) {
			//define o Byte de inicio e byte de fim para cada thread
			long byteInicio = intervalos.get(i)[0];
			long byteFim = intervalos.get(i)[1];
			
			//instancia o as threads
			LogThread thread = new LogThread(caminhoArquivo, byteInicio, byteFim, i+1, buffer, parser);
			thread.start();
			tarefas.add(thread);
		}
		
		//Chama o método que espera todas as threads terminarem de ler, poderia ser o join direto
		aguardarThreads(tarefas);
		
		//Retorna a lista com todas as linhas formatadas
		return buffer.obterTodos();
	}
	
	private List<long[]> dividirArquivo(long tamanhoTotal){
		//cria a lista para o retorno de intevalos
		List<long[]> intervalos = new ArrayList<>();
		//determina o tamanho de cada thread
		long tamanhoPorThread = tamanhoTotal / quantidadeThreads;
		
		for (int i = 0;i < quantidadeThreads; i++) {
			//define o byte de inicio para cada thread, já que são divididas igualmente
			long byteInicio = i*tamanhoPorThread;
			
			long byteFim;
			if(i == quantidadeThreads - 1) {
				//Garante que a última thread pega até o fim do arquivo
				byteFim = tamanhoTotal;
			} else {
				// pega o próximo múltiplo de tamanhoPorThread
				byteFim = (i+1) * tamanhoPorThread;
			}
			
			intervalos.add(new long[]{byteInicio, byteFim});
		}
		return intervalos;
	}
		
	private void aguardarThreads(List<LogThread> tarefas) {
		for (LogThread tarefa : tarefas) {
			try {
				//o for vai esperar cada thread terminar
				tarefa.join();
			} catch (InterruptedException e) {
				System.out.println("Thread interrompida: "+e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
		
	}
}