package PrimeiraAtividade;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		Grafo grafo = new Grafo();
		String txtDirecionado = JOptionPane.showInputDialog("O grafo é direcionável?(S/N)");
		grafo.setDirecionado(txtDirecionado);
		String txtValoravel = JOptionPane.showInputDialog("O grafo é valorável?(S/N)");
		grafo.setValoravel(txtValoravel);
		String txtVertices = JOptionPane.showInputDialog("Insira o conjunto de vértices:\nEx.: a b c");
		grafo.coletaVertices(txtVertices);
		String txtArestas = JOptionPane.showInputDialog("Insira o conjunto de arestas:\nEx.: a,b ou a,b,5");
		grafo.coletaArestas(txtArestas);
		
		JOptionPane.showMessageDialog(null, grafo.listaArestas());
		JOptionPane.showMessageDialog(null, grafo.matrizAdjacencia());
		JOptionPane.showMessageDialog(null, grafo.listaAdjacencia());
		JOptionPane.showMessageDialog(null, grafo.matrizIncidencia());
		
		System.out.println(grafo.arestas.get(0).origem.getNome()+"-"+grafo.arestas.get(0).destino.getNome());
	}

}
