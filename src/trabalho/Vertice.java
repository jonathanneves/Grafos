package trabalho;

public class Vertice {

	String nome;
	int id;
	
	public Vertice(String nome, int id) {
		this.setNome(nome);
		this.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
