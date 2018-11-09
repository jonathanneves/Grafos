package SegundaAtividade;

public class Aresta {
	
	int valor;
	Vertice origem, destino;
	
	//Construtor para grafo não valoravel
	public Aresta(Vertice origem, Vertice destino) {
		this.origem = origem;
		this.destino = destino;
	}
	
	//Contrutor para grafo valoravel
	public Aresta(Vertice origem, Vertice destino, int valor) {
		this.origem = origem;
		this.destino = destino;
		this.setValor(valor);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
}
