package trabalho;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Grafo {
			
	ArrayList<Vertice> vertices = new ArrayList<>();
	
	ArrayList <Aresta> arestas = new ArrayList<>();
	
	public void algoritmoPrim(String vertInicial) {
		// Lista que armazena os valores dos vértices respectivamente
		int[] listaValor = new int[vertices.size()];
		
		String texto = "Árvore Geradora Mínima (Prim-Jarnik)\n";
		
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
			//Organizando resultados
			int custo = 0;
			for (int c = 0; c < listaValor.length; c++) {
				custo += listaValor[c];
			}
			custo -= origem;
			texto += "Rede Provedora: "+vertices.get(origem).getNome()+"\n";
			JOptionPane.showMessageDialog(null, texto);
			texto = "";
			texto += "Distância total: "+(float)custo/1000+"Km\n";
			texto += " \n";
			texto += "Bairros | Distância\n";
			for (int c = 0; c < listaPai.length; c++) {
				if(!vertices.get(c).getNome().equals(vertices.get(listaPai[c]).getNome()))
					texto +=  vertices.get(c).getNome()+ " - " +vertices.get(listaPai[c]).getNome()+ " = "+(float)listaValor[c]/1000+"Km\n";
			}
			JOptionPane.showMessageDialog(null, texto);
			
			//Calculando orçamento total de fibra optica
			BigDecimal formatar = new BigDecimal((((float)custo/1000)*7400)/3);
			NumberFormat nf = NumberFormat.getCurrencyInstance();  
			String valor = nf.format (formatar);
			
			String texto2 = "------------------ORÇAMENTO------------------\n";
			texto2 += "Preço do cabo de Fibra óptica 3KM: R$7.400 \n";
			texto2 += "Aproximadamente "+(float)custo/1000+"Km de fibra óptica \n";
			texto2 += "Orçamento Final: "+valor;
			JOptionPane.showMessageDialog(null, texto2);
			
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

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

}