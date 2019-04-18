package br.uema.algoritmo.entity;

/**
 * @author Lucas Raphael Fernandes Ferreira - 201504580
 *         lucasraphael.fernandes@gmail.com
 *
 */
public class Produto {

	private int peso;

	private int utilidade;

	private String nome;

	public Produto(int peso, int utilidade, String nome) {
		super();
		this.peso = peso;
		this.utilidade = utilidade;
		this.nome = nome;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getUtilidade() {
		return utilidade;
	}

	public void setUtilidade(int utilidade) {
		this.utilidade = utilidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Produto [ nome= " + nome + ", peso= " + peso + ", utilidade=" + utilidade + "]";
	}

}
