package br.uema.algoritmo.main;

/**
 * @author Lucas Raphael Fernandes Ferreira - 201504580
 * lucasraphael.fernandes@gmail.com
 *
 **/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.uema.algoritmo.entity.Bolsa;
import br.uema.algoritmo.entity.Produto;
import br.uema.algoritmo.util.Util;

public class Main {

	// CONSTANTES
	public static final int PESO_MAXIMO = 15;
	public static final int QUANTIDADE_COMBINACOES = 10;

	static Produto[] items;
	static int[][] combinacoes = new int[QUANTIDADE_COMBINACOES][5];

	// ONDE SERÁ ARMAZENADA A POSSIBILIDADE DE BOLSA
	static Bolsa possibilidade;

	// ONDE SERÃO ARMAZENADAS TODAS AS POSSIBILIDADES GERADAS
	static HashMap<Integer, Bolsa> possibilidades = new HashMap<>();

	// LISTA DE BOLSAS UTILIZADA PARA ORDENAR A HASH
	static List<Bolsa> listBolsas = new ArrayList<>();

	static int[][] filhosUmEDois = new int[4][5];
	static int[][] filhosTresEQuatro = new int[4][5];

	public static void main(String[] args) {

		start();

	}

	private static void start() {

		iniciaItems();

		analisaItens();

		ordenaItens();

		realizaCruzamento();

		int[] filhoUm = aplicarMutacao(filhosUmEDois[0]);
		int[] filhoDois = aplicarMutacao(filhosUmEDois[1]);
		int[] filhoTres = aplicarMutacao(filhosTresEQuatro[0]);
		int[] filhoQuatro = aplicarMutacao(filhosTresEQuatro[1]);

		exibirResultados(filhoUm, filhoDois, filhoTres, filhoQuatro);
	}

	private static void exibirResultados(int[] filhoUm, int[] filhoDois, int[] filhoTres, int[] filhoQuatro) {
		List<Bolsa> temp = new ArrayList<Bolsa>();
		List<Bolsa> resultado = new ArrayList<Bolsa>();
		temp.add(Util.getItens(filhoUm, items));
		temp.add(Util.getItens(filhoDois, items));
		temp.add(Util.getItens(filhoTres, items));
		temp.add(Util.getItens(filhoQuatro, items));

		for (Bolsa b : temp) {
			if (b.calculaPeso() <= PESO_MAXIMO) {
				resultado.add(b);
			}
		}

		Collections.sort(resultado, new Comparator<Bolsa>() {
			@Override
			public int compare(Bolsa b1, Bolsa b2) {
				if (b1.calculaUtilidade() > b2.calculaUtilidade()) {
					return -1;
				} else if (b1.calculaUtilidade() < b2.calculaUtilidade()) {
					return 1;
				}
				return 0;
			}
		});

		resultado.get(0).display();
		int[] showResult = bolsaToArray(resultado.get(0));
		for (int i = 0; i < showResult.length; i++) {
			System.out.print(showResult[i] + " ");
		}

	}

	private static int[] aplicarMutacao(int[] filho) {
		// CHANCE DE MUTACAO DE 10%
		// MUTACAO EM UM GENE ALEATÓRIO

		Random mutacao = new Random();
		int[] filhoTemp = filho;
		if (mutacao.nextInt(100) < 10) {
			int gene = mutacao.nextInt(5);
			System.out.println("Mutação no Gene: " + gene);
			if (filhoTemp[gene] == 0) {
				filhoTemp[gene] = 1;
			} else {
				filhoTemp[gene] = 0;
			}
			System.out.print("Nova Combinação: ");
			Util.imprimeCombinacao(filhoTemp);
		}

		return filhoTemp;

	}

	private static void realizaCruzamento() {
		// UTILIZANDO UMA SUBLISTA COM 4 COMBINACOES
		// O CRUZAMENTO SERÁ FEITO APENAS NOS 4 MELHORES CENÁRIOS

		List<Bolsa> tempList = listBolsas.subList(0, 4);
		int[][] selecionados = new int[4][5];
		for (int i = 0; i < 4; i++) {
			selecionados[i] = bolsaToArray(tempList.get(i));
		}
		System.out.println();
		System.out.println("EXIBINDO CROMOSSOMOS ESCOLHIDOS PARA O CRUZAMENTO: ");
		for (Bolsa b : tempList) {
			int[] temp = bolsaToArray(b);
			for (int i = 0; i < 5; i++) {
				System.out.print(temp[i] + " ");
			}
			System.out.println();
		}
		int[] individuo1 = selecionados[0];
		int[] individuo2 = selecionados[1];
		int[] individuo3 = selecionados[2];
		int[] individuo4 = selecionados[3];

		filhosUmEDois = cross(individuo1, individuo2);
		filhosTresEQuatro = cross(individuo3, individuo4);

		System.out.println("EXIBINDO RESULTADOS DO CRUZAMENTO: ");

		System.out.println("Filho 1: ");
		Util.imprimeCombinacao(filhosUmEDois[0]);
		System.out.println();

		System.out.println("Filho 2: ");
		Util.imprimeCombinacao(filhosUmEDois[1]);
		System.out.println();

		System.out.println("Filho 3: ");
		Util.imprimeCombinacao(filhosTresEQuatro[0]);
		System.out.println();

		System.out.println("Filho 4: ");
		Util.imprimeCombinacao(filhosTresEQuatro[1]);
		System.out.println();

	}

	private static int[][] cross(int[] individuo1, int[] individuo2) {
		int[][] resultado = new int[2][5];
		for (int j = 0; j < 2; j++) {
			resultado[0][j] = individuo1[j];
			resultado[1][j] = individuo2[j];
		}
		for (int j = 2; j < 5; j++) {
			resultado[0][j] = individuo2[j];
			resultado[1][j] = individuo1[j];
		}

		return resultado;
	}

	public static int[] bolsaToArray(Bolsa bolsa) {
		int[] array = { 0, 0, 0, 0, 0 };
		for (Produto p : bolsa.getProdutos()) {
			if (p.getNome().contains("1")) {
				array[0] = 1;
			}
			if (p.getNome().contains("2")) {
				array[1] = 1;
			}
			if (p.getNome().contains("3")) {
				array[2] = 1;
			}
			if (p.getNome().contains("4")) {
				array[3] = 1;
			}
			if (p.getNome().contains("5")) {
				array[4] = 1;
			}
		}
		return array;
	}

	private static void ordenaItens() {

		// SET DE KEYS USADAS NA HASH, UTILIZADA PARA ITERAR PELA HASH
		Set<Integer> keys = possibilidades.keySet();
		keys = possibilidades.keySet();

		// UTILIZANDO ELITISMO PARA ORDENAR AS POSSIBILIDADES RESTANTES DE ACORDO COM A
		// UTILIDADE
		keys = possibilidades.keySet();
		for (Integer key : keys) {
			listBolsas.add(possibilidades.get(key));
		}
		Collections.sort(listBolsas, new Comparator<Bolsa>() {
			@Override
			public int compare(Bolsa b1, Bolsa b2) {
				if (b1.calculaUtilidade() > b2.calculaUtilidade()) {
					return -1;
				} else if (b1.calculaUtilidade() < b2.calculaUtilidade()) {
					return 1;
				}
				return 0;
			}
		});

	}

	private static void analisaItens() {
		// LISTA PARA SALVAR OS INDEXES QUE DEVEM SER REMOVIDOS DA HASH
		List<Integer> remover = new ArrayList<Integer>();

		// SET DE KEYS USADAS NA HASH, UTILIZADA PARA ITERAR PELA HASH
		Set<Integer> keys = possibilidades.keySet();

		// VERIFICANDO QUAIS POSSIBILIDADES ESTÃO ACIMA DO PESO MAXIMO
		for (Integer key : keys) {
			if (possibilidades.get(key).calculaPeso() > PESO_MAXIMO) {
				System.out.println("REMOVENDO POSSIBILIDADE NA POSIÇÃO: " + (key + 1));
				remover.add(key);
			}
		}

		for (Integer i : remover) {
			possibilidades.remove(i);
		}

		if (possibilidades.size() <= 4) {
			System.out.println("População restante menor do que 4. Reiniciando o processo!");
			start();
		}
	}

	private static void iniciaItems() {
		// INICIA UM VETOR DE PRODUTOS
		items = Util.gerarProdutos();

		System.out.println("IMPRIMINDO POPULAÇÃO INICIAL DE CROMOSSOMOS: ");

		// GERA 10 COMBINACOES ALEATORIAS DE PRODUTOS
		for (int i = 0; i < QUANTIDADE_COMBINACOES; i++) {
			combinacoes[i] = Util.combinacaoAleatoria();
			possibilidade = Util.getItens(combinacoes[i], items);
			possibilidades.put(i, possibilidade);
		}

		System.out.println();
	}

}
