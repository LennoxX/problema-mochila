package br.uema.algoritmo.util;

/**
 * @author Lucas Raphael Fernandes Ferreira - 201504580
 *         lucasraphael.fernandes@gmail.com
 *
 */

import java.util.Random;

import br.uema.algoritmo.entity.Bolsa;
import br.uema.algoritmo.entity.Produto;

public class Util {

	public static Produto[] gerarProdutos() {
		Produto[] items = new Produto[5];
		items[0] = new Produto(12, 4, "Produto 1");
		items[1] = new Produto(1, 2, "Produto 2");
		items[2] = new Produto(4, 10, "Produto 3");
		items[3] = new Produto(1, 1, "Produto 4");
		items[4] = new Produto(2, 2, "Produto 5");
		return items;
	}

	public static int[] combinacaoAleatoria() {
		Random random = new Random();
		int[] combinacao = new int[5];
		for (int i = 0; i < combinacao.length; i++) {
			combinacao[i] = random.nextInt(2);
		}
		imprimeCombinacao(combinacao);
		return combinacao;
	}

	public static void imprimeCombinacao(int[] combinacao) {
		String combinacaoText;
		combinacaoText = "Combinação:  ";
		for (int i = 0; i < combinacao.length; i++) {
			combinacaoText = combinacaoText + combinacao[i] + " ";
		}
		System.out.println(combinacaoText);
	}


	
	public static Bolsa getItens(int[] combinacao, Produto[] items) {
		Bolsa bolsa = new Bolsa();
		for (int i = 0; i < items.length; i++) {
			if (combinacao[i] == 1) {
				bolsa.getProdutos().add(items[i]);
			}
		}
		return bolsa;
	}
}
