package SegundaAtividade;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		Grafo grafo = new Grafo();
		String txtDirecionado = JOptionPane.showInputDialog("O grafo � orientado? (S/N)");
		grafo.setDirecionado(txtDirecionado);
		String txtVertices = JOptionPane.showInputDialog("Insira o conjunto de v�rtices:\nEx.: a b c");
		grafo.coletaVertices(txtVertices);
		String txtArestas = JOptionPane.showInputDialog("Insira o conjunto de arestas:\nEx.: a,b,5 a,c,8");
		grafo.coletaArestas(txtArestas);
		String txtVertInicial = JOptionPane.showInputDialog("Qual � o V�rtice Inicial/Origem?");
		grafo.setVertInicial(txtVertInicial);
		
		JOptionPane.showMessageDialog(null, grafo.algoritmoDijkstra(grafo.getVertInicial()));
	}

}
