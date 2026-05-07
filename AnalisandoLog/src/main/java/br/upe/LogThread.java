package br.upe;

import java.io.RandomAccessFile;

public class LogThread extends Thread{
	
	//Nessa classe a Thread é instanciada e usamos o tamanho do arquivo Access Log para separar em threads diferentes
	
	//Esse atributo é o caminho do arquivo a ser analisado
	private final String caminhoArquivo;
	//Esse atributo é o primeiro byte a ser lido
	private final long byteInicio;
	//Esse atributo é o último byte a ser lido
	private final long byteFim;
	//Esse atributo é referente ao numero de threads para a leitura
	private final int numThreads;
	//Esse atributo é a lista criada em LogEntryBuffer, que garante que não tenha a lista não perca informações
	private final LogEntryBuffer buffer;
	//Esse atributo é referente a divisão de cada linha para ser parseada e assim montarmos os atributos de LogEntry;
	private final LogParser parser;
	
	//Método construtor da classe, recebe todos os atributos de uma vez
	public LogThread(String caminhoArquivo, long byteInicio, long byteFim, int numThreads, LogEntryBuffer buffer, LogParser parser) {
		this.caminhoArquivo = caminhoArquivo;
		this.byteInicio = byteInicio;
		this.byteFim = byteFim;
		this.numThreads = numThreads;
		this.buffer = buffer;
		this.parser = parser;
	}
	
	//Esse é o método  que vai iniciar a thread, ele já existe na classe mãe e só vamos sobrescreve-lo
	@Override
	public void run() {
		try {
			//usamos randomAccessFile para abrir o arquivo
			RandomAccessFile arquivo = new RandomAccessFile(caminhoArquivo, "r");
			
			//Posiciona um ponteiro no byte inicial de cada Thread
			arquivo.seek(byteInicio);
			
			// Esse if é necessário, pois se não for o início do arquivo, descartamos a primeira linha
			// pois ela pode estar cortada por conta da divisão dos bytes
			if (byteInicio != 0) {
				//se não for o byte 0 do arquivo sempre avançamos para o início da proxima linha
				arquivo.readLine();
			}
			
			// Aqui a thread lê linha por linha de acordo com o número de bytes determinado e garante que
			// a última linha seja lida por completo, mesmo se a quantidade de bytes passar.
			while (arquivo.getFilePointer() <= byteFim && (linha = raf.readLine()) != null) {
				
			}
		}
	}
}