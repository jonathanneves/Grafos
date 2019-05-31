package trabalho;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		Grafo grafo = new Grafo();

		//Empresas por Bairros
		grafo.coletaVertices("VilaEsperança Humaita Dehon Morrotes Oficinas Centro Recife VilaMoema Revoredo Passagem");
		
		//Distância das ruas
		grafo.coletaArestas("VilaEsperança,Humaita,880 VilaEsperança,Dehon,1540 Humaita,Dehon,1100 "
				+ "Morrotes,Dehon,950 Morrotes,Centro,1720 Dehon,Centro,1000 Morrotes,Oficinas,1600 "
				+ "Oficinas,Centro,1200 Humaita,Centro,1300 Oficinas,Recife,1900 Humaita,Revoredo,1400 "
				+ "Centro,Recife,1200 Recife,VilaMoema,1650 VilaMoema,Revoredo,780 Revoredo,Passagem,1000 "
				+ "VilaMoema,Passagem,1200 Centro,VilaMoema,1800 ");

		
		grafo.algoritmoPrim("VilaMoema");
	}

}

