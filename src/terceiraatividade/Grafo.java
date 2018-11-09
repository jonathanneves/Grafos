package terceiraatividade;

import java.util.ArrayList;


import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;


public class Grafo {
	
	boolean kruskal = false;
	boolean prim = false;
		
	ArrayList<Vertice> vertices = new ArrayList<>();
	
	ArrayList <Aresta> arestas = new ArrayList<>();
	
	public void algoritmoKruskal() {
		//Ordenando em ordem crescente
		Collections.sort(arestas, Comparator.comparing(Aresta::getValor));
		
		//Declarando variaveis padrões
		int custo=0; 
		int index=0;
		String texto = "Árvore Geradora Mínima - Kruskal\n";
		ArrayList <Aresta> arestasSubgrafo = new ArrayList<>();
		ArrayList <String> subconjuntos = new ArrayList<>();
		
		//Declarando todos os vertices na Lista
		for(int i=0; i<vertices.size(); i++)
			subconjuntos.add(vertices.get(i).getNome());

		//Enquanto lista de aresta não for = lista de vertices - 1, continua
		while(arestasSubgrafo.size() < vertices.size()-1){
			if(!arestas.get(index).destino.getNome().equals(arestas.get(index).origem.getNome())) {
				
					//Definir a posição atual da aresta para buscar na lista de Vertice
					int v1 = buscar(subconjuntos, arestas.get(index).origem.getId(), index);
					int v2 = buscar(subconjuntos, arestas.get(index).destino.getId(), index);
					
					if(!(subconjuntos.get(v1).equals("") && subconjuntos.get(v2).equals(""))){
						if (!subconjuntos.get(v1).contains(arestas.get(index).destino.getNome()) || !subconjuntos.get(v1).contains(arestas.get(index).origem.getNome())) {
							
							//Incrementar custo e adicionar aresta selecionada,
							custo = custo + arestas.get(index).getValor();
							arestasSubgrafo.add(arestas.get(index));

							//Atualizar a lista de vertices para fazer o comparativo
							subconjuntos = atualizarSubconjunto(subconjuntos, v1, v2, index);
						}
					}
				//Proximo da Lista
				index++;
				}
		}
		
		texto += "Custo total: "+custo+"\n";
		texto += "Arestas | Custo\n";
		for(Aresta  a : arestasSubgrafo)
			texto += "  "+a.origem.getNome()+" - "+a.destino.getNome()+"      |     "+a.getValor()+"\n";
		JOptionPane.showMessageDialog(null, texto);
	}

	public int buscar(ArrayList<String> subconjuntos, int v, int index) {
		if(subconjuntos.get(v).equals("")) {
			for(int x=0; x<vertices.size(); x++) {
				if(subconjuntos.get(x).contains(arestas.get(index).origem.getNome())) {
					v = x;
					break;
				}
			}
		}	
		return v;
	}
	
	public ArrayList<String> atualizarSubconjunto(ArrayList<String> subconjuntos, int v1, int v2,int index) {
		if((!subconjuntos.get(v1).contains(arestas.get(index).origem.getNome()))
				|| !subconjuntos.get(v1).contains(arestas.get(index).destino.getNome())){
				subconjuntos.set(v1, subconjuntos.get(v1)+subconjuntos.get(v2));
				subconjuntos.set(v2, "");
			}
		if((!subconjuntos.get(v2).contains(arestas.get(index).origem.getNome()))
				|| !subconjuntos.get(v2).contains(arestas.get(index).destino.getNome())){
				subconjuntos.set(v2, subconjuntos.get(v1)+subconjuntos.get(v2));
				subconjuntos.set(v1, "");
			}
		return subconjuntos;
	}
	
	public void algoritmoPrim(String vertInicial) {
		// Lista que armazena os valores dos vértices respectivamente
		int[] listaValor = new int[vertices.size()];
		
		String texto = "Árvore Geradora Mínima - Prim-Jarnik\n";
		
		//var origem escolhida
		int origem = 0;
		for (Vertice v : vertices) {
			if (vertInicial.equals(v.getNome())) {
				origem = v.getId();
				System.out.println(origem);
			}
		}
		
		// Arvore onde amazenamos os id que já estão verificados
		int[] arvore = new int[vertices.size()];
		// Lista de pai dos vértices
		int[] listaPai = new int[vertices.size()];
	
		arvore = iniciaConjunto(origem, arvore);
		listaValor = iniciaConjunto(origem, listaValor);
		for (int c = 0; c < listaPai.length; c++) {
			listaPai[c] = -1;
		}
		listaPai[origem] = origem;
		
		int contArvore = 1;
	
		while (contArvore <= vertices.size()-1) {
			int menorValor = 999999999, menorId = 0;
			for (int i = 0; i < vertices.size(); i++) {
				if (listaPai[i] != -1) {
					for (int j = 0; j < arestas.size(); j++) {
						if (arestas.get(j).getDestino().getId() == vertices.get(i).getId() && listaPai[arestas.get(j).getOrigem().getId()] == -1
								|| arestas.get(j).getOrigem().getId() == vertices.get(i).getId() && listaPai[arestas.get(j).getDestino().getId()] == -1) {
							if (arestas.get(j).getValor() < menorValor) {
								menorValor = arestas.get(j).getValor();
								menorId = j;
							}
						}
					}
				}
			}
			if (listaPai[arestas.get(menorId).getDestino().getId()] == -1) {
				listaPai[arestas.get(menorId).getDestino().getId()] = arestas.get(menorId).getOrigem().getId();
				listaValor[arestas.get(menorId).getDestino().getId()] = menorValor;
				arvore[contArvore] = arestas.get(menorId).getDestino().getId();
				contArvore++;
			} else {
				if (listaPai[arestas.get(menorId).getOrigem().getId()] == -1) {
					listaPai[arestas.get(menorId).getOrigem().getId()] = arestas.get(menorId).getDestino().getId();
					listaValor[arestas.get(menorId).getOrigem().getId()] = menorValor;
					arvore[contArvore] = arestas.get(menorId).getOrigem().getId();
					contArvore++;
				}
			}
		}
		try {
			int custo = 0;
			for (int c = 0; c < listaValor.length; c++) {
				custo += listaValor[c];
			}
			custo -= origem;
			texto += "Origem: "+origem+" - Custo total: "+custo+"\n";
			texto += "Arestas | Custo\n";
			for (int c = 0; c < listaPai.length; c++) {
				if(!vertices.get(c).getNome().equals(vertices.get(listaPai[c]).getNome()))
					texto +=  "  "+vertices.get(listaPai[c]).getNome() + " - " + vertices.get(c).getNome() + "      |     "+listaValor[c]+"\n";
			}
			JOptionPane.showMessageDialog(null, texto);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//Inicia os conjuntos com valores infinitos e corrige origem
	public int[] iniciaConjunto(int origem, int[] conjunto) {
		for(int c = 0; c < vertices.size(); c++) {
			//Define os valores infinitos			
			conjunto[c] = 999999999;
		}
		//corrige origem
		conjunto [origem] = origem;
		return conjunto;
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
											operador=2;
											nome = "";
										}
									}
								}
							}
							if(operador==2 && !nome.equals("")) {
									valor = Integer.parseInt(nome);											
									operador = 0;
									nome = "";
							}
						}else {
							nome += texto.charAt(c1);
						}
					}
					arestas.add(new Aresta(origem, destino, valor));
					texto = "";
				}else {
					System.out.println("----------------------");
				}
			}else {
				texto += txtArestas.charAt(c);
			}
			
		}
	}

	
	public void  setandoAlgoritmo(String txtAlgoritmo) {
		if(txtAlgoritmo.trim().toUpperCase().equals("1")) {
			this.setKruskal(true);
		}else if(txtAlgoritmo.trim().toUpperCase().equals("2")){
			this.setPrim(true);
		}else {
			System.out.print("Entrada inválida. '1 ou 2'");
			System.exit(0);
		}
	}
	
	public boolean isPrim() {
		return prim;
	}

	public void setPrim(boolean prim) {
		this.prim = prim;
	}

	public boolean isKruskal() {
		return kruskal;
	}

	public void setKruskal(boolean kruskal) {
			this.kruskal = kruskal;
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

}