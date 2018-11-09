package SegundaAtividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	
	boolean direcionado;
	boolean valoravel = true;
	
	String VertInicial;
	
	ArrayList<Vertice> vertices = new ArrayList<>();
	
	ArrayList <Aresta> arestas = new ArrayList<>();
	
	public String algoritmoDijkstra(String vertInicial) {
		
		//Declarando variáveis iniciais;
		String texto="";
		String [] vetor = new String [vertices.size()+1];
		int [] dist = new int [vertices.size()];
		String [] path = new String [vertices.size()];
		ArrayList<Integer> tamanhoFila = new ArrayList<>();
		
		//Definindo valor default para todos os vertices
		vetor[0] = "Vért | Dist | Path";	
		for(int c=0; c<vertices.size(); c++) {
			path[c] = null;
			tamanhoFila.add(c);
			if(vertices.get(c).getNome().equals(vertInicial)) {
				dist[c] = 0;
			} else
				dist[c] = 999999;	
		}	
		
		//Calculando a menor distância e o seu caminho
		while (!tamanhoFila.isEmpty()) {
			tamanhoFila.remove(tamanhoFila.size()-1);
			for(Aresta a:arestas) {
				if(dist[a.destino.getId()] > dist[a.origem.getId()] + a.getValor()) {
					
					dist[a.destino.getId()] = dist[a.origem.getId()] + a.getValor();								
					path[a.destino.getId()] = a.origem.getNome();
				}else if(!isDirecionado()) {
					if(dist[a.origem.getId()] > dist[a.destino.getId()] + a.getValor()) {	 
								
						dist[a.origem.getId()] = dist[a.destino.getId()] + a.getValor();							
						path[a.origem.getId()] = a.destino.getNome();								
					}
				}				
			}
		}
		
		//Montando tabela
		texto += vetor[0]+"\n";
		for(int c=0; c<vertices.size(); c++) {
			texto += " "+vertices.get(c).getNome()+"   | "+dist[c]+"   | "+path[c]+"\n"; 
		}
		return texto;
	}
	
	public void coletaVertices(String txtVertices){
		String nome="";
		int id = 0;
		for(int c=0; c<txtVertices.length(); c++) {
			if(txtVertices.charAt(c) == ' ') {
				if(!nome.equals("")) {
					vertices.add(new Vertice(nome, id));
					nome = "";
					id++;
				}
			}else {
				nome += txtVertices.charAt(c);
			}
		}
		if(!nome.equals("")) {
			vertices.add(new Vertice(nome, id));
		}
	}
	
	public void coletaArestas(String txtArestas){
		String texto="", nome="";
		int valor=0;
		txtArestas += " ";
		for(int c=0; c<txtArestas.length(); c++) {
			if(txtArestas.charAt(c) == ' ') {
				if(!texto.equals("")) {
					Vertice origem = null, destino = null;
					System.out.println(texto);
					texto += ",";
					System.out.println(texto);
					int operador = 0;
					for(int c1=0; c1<texto.length(); c1++) {
						if(texto.charAt(c1) == ',') {
							for(Vertice v:vertices) {
								if(v.getNome().equals(nome)) {
									if(operador==0) {
										origem = v;
										operador++;
										nome = "";
									}else {
										if(operador==1) {
											destino = v;
											if(isValoravel()) {
												operador=2;
											}else {
												operador=0;
											}
											nome = "";
										}
									}
								}
							}
							if(isValoravel() && operador==2 && !nome.equals("")) {
									valor = Integer.parseInt(nome);											
									operador = 0;
									nome = "";
							}
						}else {
							nome += texto.charAt(c1);
						}
					}
					if(valoravel) {
						arestas.add(new Aresta(origem, destino, valor));
					}else {
						arestas.add(new Aresta(origem, destino));
					}
					texto = "";
				}else {
					System.out.println("----------------------");
				}
			}else {
				texto += txtArestas.charAt(c);
				//System.out.println(texto);
			}
			
		}
	}

	public boolean isDirecionado() {
		return direcionado;
	}

	public void setDirecionado(String txtDirecionado) {
		if(txtDirecionado.trim().toUpperCase().equals("S")) {
			this.direcionado = true;
		}else if(txtDirecionado.trim().toUpperCase().equals("N")){
			this.direcionado = false;
		}else {
			System.out.print("Entrada inválida. 'S ou N'");
			System.exit(0);
		}
	}
	
	public String getVertInicial() {
		return VertInicial;
	}
	
	public void setVertInicial(String txtVertInicial) {
		if(!txtVertInicial.equals("")) {
			this.VertInicial = txtVertInicial;
		}else {
			System.out.println("Entrada inválida");
			System.exit(0);
		}
	}
	
	public boolean isValoravel() {
		return valoravel;
	}

	public void setValoravel(String txtValoravel) {
			this.valoravel = true;
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}
	
}