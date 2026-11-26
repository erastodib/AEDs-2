import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BST{

	private static class Data{
		private int ano;
		private int mes;
		private int dia;

		public void parseData(String dataStr){
			if (dataStr != null && !dataStr.isEmpty()){
				String[] partes = dataStr.split("-");

				if (partes.length == 3){
					this.ano = Integer.parseInt(partes[0]);
					this.mes = Integer.parseInt(partes[1]);
					this.dia = Integer.parseInt(partes[2]);
				}
			}
		}

		public String format(){
			return String.format("%02d/%02d/%04d", dia, mes, ano);
		}
	}

	private static class Veiculo {

		private int id;
		private String marca;
		private String modelo;
		private int ano;
		private String categoria;
		private String combustivel;
		private int cilindros;
		private float cilindrada;
		private String transmissao;
		private String tracao;
		private float consumoCidade;
		private float consumoEstrada;
		private float co2;
		private boolean turbo;
		private Data dataRegistro;

		// GETTERS
		public int getId(){return this.id;}
		public String getMarca(){return this.marca;}
		public String getModelo(){return this.modelo;}
		public int getAno(){return this.ano;}
		public String getCategoria(){return this.categoria;}
		public String getCombustivel(){return this.combustivel;}
		public int getCilindros(){return this.cilindros;}
		public float getCilindrada(){return this.cilindrada;}
		public String getTransmissao(){return this.transmissao;}
		public String getTracao(){return this.tracao;}
		public float getConsumoCidade(){return this.consumoCidade;}
		public float getConsumoEstrada(){return this.consumoEstrada;}
		public float getCo2(){return this.co2;}
		public boolean getTurbo(){return this.turbo;}
		public Data getDataRegistro(){return this.dataRegistro;}

		public void parseVeiculo(String strVeiculo){
			int i = 0;

			String[] veiculo = strVeiculo.split(",");

			this.id = Integer.parseInt(veiculo[i++]);
			this.marca = veiculo[i++];
			this.modelo = veiculo[i++];
			this.ano = Integer.parseInt(veiculo[i++]);
			this.categoria = veiculo[i++];
		        this.combustivel = veiculo[i++];
		        this.cilindros = Integer.parseInt(veiculo[i++]);
		        this.cilindrada = Float.parseFloat(veiculo[i++]);
			this.transmissao = veiculo[i++];
		        this.tracao = veiculo[i++];
		        this.consumoCidade = Float.parseFloat(veiculo[i++]);
		        this.consumoEstrada = Float.parseFloat(veiculo[i++]);
		        this.co2 = Float.parseFloat(veiculo[i++]);
		        this.turbo = Boolean.parseBoolean(veiculo[i++]);
		
		        this.dataRegistro = new Data();
		        this.dataRegistro.parseData(veiculo[i]);
		}

		public String format(){
			return String.format(
				"[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]",
		                id,
		                marca,
		                modelo,
		                ano,
		                categoria,
		                combustivel.replace(";", ","),
		                cilindros,
		                cilindrada,
		                transmissao,
		                tracao,
		                consumoCidade,
		                consumoEstrada,
		                co2,
		                turbo,
		                dataRegistro.format()
		            );
		}
	}

	private static class LeitorCSV{
		Veiculo[] lerCSV(String path){
			Veiculo[] array = new Veiculo[512];
			int i = 0;

			try{
				
				Scanner file = new Scanner(new File(path));

				file.nextLine();

				while (file.hasNextLine()){
					String linha = file.nextLine();
					Veiculo v = new Veiculo();
					v.parseVeiculo(linha);

					array[i++] = v;
				}

				file.close();

				return array;
			} catch (FileNotFoundException e){
				throw new RuntimeException(e);
			}
		}
	}

	// Estrutura e construtor da classe No
	private static class No {
		Veiculo veiculo;
		No esq, dir;

		No(Veiculo veiculo){this.veiculo = veiculo;}
	}

	// Declaracao da raiz e construtor da BST
	

	private No raiz;
	BST(){raiz = null;}

	// INSERIR
	public void inserir(Veiculo x){raiz = inserir(x, raiz);}

	private No inserir(Veiculo x, No n){
		if (n == null)
			return new No(x);

		int tmp = comparar(x.modelo, n.veiculo.modelo);
		if (tmp < 0)
			n.esq = inserir(x, n.esq);
		else if (tmp > 0)
			n.esq = inserir(x, n.dir);
		
		// A lógica ignora elementos duplicados, como pedido no enunciado

		return n;
	}

	// PESQUISAR (ITERATIVO)	
	public boolean pesquisar(Veiculo x){
		No n = raiz;

		while(n != null){
			int tmp = comparar(x.modelo, n.veiculo.modelo);

			if (tmp == 0)
				return true;
			else if (tmp < 0)
				n = n.esq;
			else 
				n = n.dir;
		}
	}


	// Método auxiliar que compara duas Strings lexicograficamente
	// Retorna um inteiro:
	// NEGATIVO	-> se A < B
	// POSITIVO 	-> se A > B
	// 0  		-> se A == B
	int comparar(String a, String b){
		return a.compareTo(b);
	}

	public void emOrdem(){emOrdem(raiz);}

	private void emOrdem(No n){
		if (n != null){
			emOrdem(n.esq);
			System.out.println(n.veiculo.modelo);
			emOrdem(n.dir);
		}
	}

