package br.uema.algoritmo.entity;

/**
 * @author Lucas Raphael Fernandes Ferreira - 201504580
 *         lucasraphael.fernandes@gmail.com
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Bolsa {

	private List<Produto> produtos;

	private int pesoAtual;

	private int utilidadeAtual;

	private int pesoMaximo;

	public Bolsa() {
		this.pesoAtual = 0;
		this.utilidadeAtual = 0;
		this.produtos = new ArrayList<Produto>();
	}

	public int getPesoAtual() {
		return pesoAtual;
	}

	public void setPesoAtual(int pesoAtual) {
		this.pesoAtual = pesoAtual;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(int pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}

	public int getUtilidadeAtual() {
		return utilidadeAtual;
	}

	public void setUtilidadeAtual(int utilidadeAtual) {
		this.utilidadeAtual = utilidadeAtual;
	}

	public void display() {
		String itens = "";
		this.pesoAtual = this.calculaPeso();
		this.utilidadeAtual = this.calculaUtilidade();
		System.out.println("Produtos: " + this.produtos.size());
		for (Produto p : this.produtos) {
			
			itens += p.getNome() + " ";
		}
		System.out.println("Peso Atual: " + this.pesoAtual);
		System.out.println("Utilidade: " + this.utilidadeAtual);
		System.out.println("Itens escolhidos: " + itens);

	}

	public int calculaPeso() {
		this.pesoAtual = 0;
		for (Produto p : this.getProdutos()) {
			this.pesoAtual += p.getPeso();
		}
		return this.pesoAtual;
	}

	public int calculaUtilidade() {
		this.utilidadeAtual = 0;
		for (Produto p : this.getProdutos()) {
			this.utilidadeAtual += p.getUtilidade();
		}
		return this.utilidadeAtual;
	}

}
