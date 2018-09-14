package PrimeiraAtividade;

public class Aresta {
	
	String valor;
	Vertice origem, destino;
	
	//Construtor para grafo não valoravel
	public Aresta(Vertice origem, Vertice destino) {
		this.origem = origem;
		this.destino = destino;
	}
	
	//Contrutor para grafo valoravel
	public Aresta(Vertice origem, Vertice destino, String valor) {
		this.origem = origem;
		this.destino = destino;
		this.setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
