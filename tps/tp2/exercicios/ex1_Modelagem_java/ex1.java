public class ex1 {

	static class Data {
		private int ano;
		private int mes;
		private int dia;

		//Método que lê a data do CSV, preenche um novo indice do vetor partes sempre que encontra um "-". 
		public void ler(String dataStr){
			if (dataStr != null && !dataStr.isEmpty()){
				String[] partes = dataStr.split("-");

				if (partes.length == 3){
					this.ano = Integer.parseInt(partes[0]);
					this.mes = Integer.parseInt(partes[1]);
					this.dia = Integer.parseInt(partes[2]);
				}
			}
		}
	}

	static class Veiculo {

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
		private float consumo_cidade;
		private float consumo_estrada;
		private int co2;
		private boolean turbo;
		private Data data_registro;	
		

		public void parseVeiculo(string StrVeiculo){
			
		}
	
	}

	static class LeitorCSV{
	
		Veiculo[] lerCSV(String path){
			Veiculo array[] = new Veiculo[500];

			return array;
		}

	}

	public static void main(String[] args){

	}
}
