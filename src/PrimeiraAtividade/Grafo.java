package PrimeiraAtividade;

import java.util.ArrayList;

public class Grafo {
	
	boolean direcionado, valoravel;
	
	ArrayList<Vertice> vertices = new ArrayList<>();
	
	ArrayList <Aresta> arestas = new ArrayList<>();
	
	public String listaArestas() {
		String texto="";
		for(Aresta a:arestas) {
			if(this.isValoravel()) {
				texto += a.origem.getNome()+"-"+a.destino.getNome()+"-"+a.getValor()+"\n";
			}else {
				texto += a.origem.getNome()+"-"+a.destino.getNome()+"\n";
			}
		}
		return texto;
	}
	
	public String matrizAdjacencia() {
		String [] [] matriz = new String [vertices.size()+1][vertices.size()+1];
		if(isDirecionado()) {
			for(Aresta a:arestas) {
				if(isValoravel()) {
					matriz [a.origem.getId()+1] [a.destino.getId()+1] = a.getValor()+"";
				}else {
					matriz [a.origem.getId()+1] [a.destino.getId()+1] = "1";
				}
			}
		}else {
			for(Aresta a:arestas) {
				if(isValoravel()) {
					matriz [a.origem.getId()+1] [a.destino.getId()+1] = a.getValor()+"";
					matriz [a.destino.getId()+1] [a.origem.getId()+1] = a.getValor()+"";
				}else {
					matriz [a.origem.getId()+1] [a.destino.getId()+1] = "1";
					matriz [a.destino.getId()+1] [a.origem.getId()+1] = "1";
				}
			}
		}
		for(int c=1; c<=vertices.size(); c++) {
			matriz [0] [c] = vertices.get(c-1).getNome();
			matriz [c] [0] = vertices.get(c-1).getNome();
		}
		
		String texto="";
		for(int i=0; i<=vertices.size(); i++) {
			for(int j=0; j<=vertices.size(); j++) {
				if(matriz [i][j]==null) {
					if(isValoravel()) 
						matriz[i][j] = "∞";
					else
						matriz[i][j] = "0";
				}
				if(i==0 && j==0) {
					matriz[i][j] = " ";
				}
				texto += " "+matriz [i] [j]+" ";
			}
			texto += "\n";
		}
		return texto;
	}
	
	public String listaAdjacencia() {
		String texto="";
		String [] vetor = new String [vertices.size()+1];
		for(int c=0; c<vertices.size(); c++) {
			vetor[c] = vertices.get(c).getNome()+" - "; 
		}
		if(isDirecionado()) {
			for(Aresta a:arestas) {
				vetor[a.origem.getId()] += a.destino.getNome()+"  ";
			}
		}else {
			for(Aresta a:arestas) {
				if(!a.destino.getNome().equals(a.origem.getNome())) {
					vetor[a.origem.getId()] += a.destino.getNome()+"  ";
					vetor[a.destino.getId()] += a.origem.getNome()+"  ";
				}else {
					vetor[a.origem.getId()] += a.destino.getNome()+"  ";
				}
			}
		}
		for(int c=0; c<vertices.size(); c++) {
			texto += vetor[c]+"\n";
		}
		return texto;
	}
	
	public String matrizIncidencia() {
		String texto="";
		String [] [] matriz = new String [vertices.size()+1][arestas.size()+1];
		for(int c=0; c<arestas.size(); c++) {
			if(isDirecionado()) {
					matriz [0] [c+1] = "a"+c;
					if(isValoravel()) {
						matriz [arestas.get(c).destino.getId()+1] [c+1] = "-"+arestas.get(c).getValor();
						matriz [arestas.get(c).origem.getId()+1] [c+1] = " "+arestas.get(c).getValor();
					}else {
						matriz [arestas.get(c).destino.getId()+1] [c+1] = "-1";
						matriz [arestas.get(c).origem.getId()+1] [c+1] = " 1";
					}
			}else {
				matriz [0] [c+1] = "a"+c;
				if(isValoravel()) {
					matriz [arestas.get(c).destino.getId()+1] [c+1] = " "+arestas.get(c).getValor();
					matriz [arestas.get(c).origem.getId()+1] [c+1] = " "+arestas.get(c).getValor();
				}else {
					matriz [arestas.get(c).destino.getId()+1] [c+1] = " 1";
					matriz [arestas.get(c).origem.getId()+1] [c+1] = " 1";
				}		
			}
		}
		for(int c=1; c<=vertices.size(); c++) {
			matriz [c] [0] = vertices.get(c-1).getNome();
		}
		for(int i=0; i<=vertices.size(); i++) {
			for(int j=0; j<=arestas.size(); j++) {
				if(matriz [i][j]==null) {
					if(isValoravel())
						matriz[i][j] = " ∞";
					else
						matriz[i][j] = " 0";
				}
				if(i==0 && j==0) {
					matriz[i][j] = " ";
				}
				texto += matriz [i] [j]+" ";
			}
			texto += "\n";
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
					System.out.println("Texto vazio");
				}
			}else {
				texto += txtArestas.charAt(c);
				System.out.println(texto);
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

	public boolean isValoravel() {
		return valoravel;
	}

	public void setValoravel(String txtValoravel) {
		if(txtValoravel.trim().toUpperCase().equals("S")) {
			this.valoravel = true;
		}else if(txtValoravel.trim().toUpperCase().equals("N")){
			this.valoravel = false;
		}else {
			System.out.print("Entrada inválida. 'S ou N'");
			System.exit(0);
		}
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}
	
}