package terceiraatividade;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		Grafo grafo = new Grafo();
		String txtAlgoritmo = JOptionPane.showInputDialog("Escolha o algoritmo à ser utilizado (1-2):"
															+ "\n1 - Algoritmo de Kruskal"
															+ "\n2 - Algoritmo de de Prim-Jarnik");
		grafo.setandoAlgoritmo(txtAlgoritmo);
		String txtVertices = JOptionPane.showInputDialog("Insira o conjunto de vértices:\nEx.: a b c");
		grafo.coletaVertices(txtVertices);
		String txtArestas = JOptionPane.showInputDialog("Insira o conjunto de arestas:\nEx.: a,b,5 a,c,8");
		grafo.coletaArestas(txtArestas);
		
		//MODELOS PARA TESTE
		grafo.coletaVertices("0 1 2 3 4 5 6 7 8");
		grafo.coletaArestas("0,1,4 0,7,8 1,7,11 1,2,8 7,8,7 7,6,1 8,6,6 8,2,2 2,3,7 2,5,4 6,5,2 3,5,14 3,4,9 5,4,10");
		grafo.coletaArestas("0,1,2 0,3,3 0,4,5 1,2,10 4,2,2 3,5,8 4,5,7 5,7,3 5,6,4 6,8,11 7,2,5 8,2,6");
		
		if(grafo.isKruskal())
			grafo.algoritmoKruskal();
		
		if(grafo.isPrim()) {
			String txtVertInicial = JOptionPane.showInputDialog("Qual é o Vértice Inicial/Origem?");
			grafo.algoritmoPrim(txtVertInicial);
		}
	}

}
