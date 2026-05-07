package br.upe;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;



public class LogEntryBuffer{
	//Para esssa classa, vamos ter apenas um atributo, o buffer.
	//Ele vai ser uma lista Thread-safe para garantir que as threads não acessem a lista ao mesmo tempo com uso de ConcurrentLinkedQueue
	private final ConcurrentLinkedQueue<LogEntry> buffer = new ConcurrentLinkedQueue<>();
	
	//método criado para adicionar a entrada de cada thread na lista buffer
	public void adicionar(LogEntry entrada) {
		buffer.add(entrada);
	}
	//método criado para chamar todas as entradas do log, ele cria uma lista com todos as entradas do Log já separadas. 
	//Esse método só será chamado com o fim de todas as threads, então ninguém mais será adicionado, eliminando o risco de concorrencia
	public List<LogEntry> obterTodos(){
		return new ArrayList<>(buffer);
	}
}