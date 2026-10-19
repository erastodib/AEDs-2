import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ex1{

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

		//GETTERS
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

	private static void mergeSort(Veiculo[] veiculos){ //A chave de pesquisa é o atributo 'consumo_cidade',
						  //e, em caso de empate, o atributo 'categoria'
		int len = veiculos.length;

		if (len <= 1) return; //caso base
		
		int mid = len / 2;
		Veiculo[] lArr = new Veiculo[mid];
		Veiculo[] rArr = new Veiculo[len - mid];

		int i = 0; //L
		int j = 0; //R
		

		for (; i < len; i++){
			if (i < mid)
				lArr[i] = veiculos[i];
			else{
				rArr[j] = veiculos[i];
				j++;
			}
		}

		mergeSort(lArr);
		mergeSort(rArr);
		merge(lArr, rArr, veiculos);
	}

	private static void merge(Veiculo[] lArr, Veiculo[] rArr, Veiculo[] veiculos){
		
		int lLen = veiculos.length / 2;
		int rLen = veiculos.length - lLen;
		int i = 0, l = 0, r = 0;

		while (l < lLen && r < rLen){		

			float consumoL = lArr[l].getConsumoCidade();
			float consumoR = rArr[r].getConsumoCidade();

			int compCategoria = lArr[l].getCategoria().compareTo(rArr[r].getCategoria());

			if (consumoL < consumoR || (consumoL == consumoR && compCategoria <= 0)){
				veiculos[i] = lArr[l];
				i++;
				l++;
			}
			else {
				veiculos[i] = rArr[r];
				i++;
				r++;
			}
		}

		while (l < lLen){
			veiculos[i] = lArr[l];
			i++;
			l++;
		}

		while (r < rLen){
			veiculos[i] = rArr[r];
			i++;
			r++;
		}
	
	}


	public static void main(String[] args){
		
		LeitorCSV leitor = new LeitorCSV();

		Veiculo[] veiculos = leitor.lerCSV("/tmp/veiculos.csv");

		int n = 0;

		while (veiculos[n] != null)
			n++;

		Scanner scanner = new Scanner(System.in);

		Veiculo[] selecionados = new Veiculo[512];

		int qtd = 0;
		int id;

		do{
			id = scanner.nextInt();

			if (id != -1){

				for(int i = 0; i < n; i++){
					if (veiculos[i].getId() == id){
						selecionados[qtd++] = veiculos[i];
						break;
					}
				}
			}

		} while (id != -1);

		scanner.close();

		Veiculo[] resultado = new Veiculo[qtd];

		for (int i = 0; i < qtd; i++)
			resultado[i] = selecionados[i];

		mergeSort(resultado);

		for (int i = 0; i < qtd; i++)
			System.out.println(resultado[i].format());

	}
}
