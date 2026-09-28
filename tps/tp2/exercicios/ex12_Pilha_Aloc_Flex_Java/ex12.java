import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ex12 {

    static class Data {

        private int ano;
        private int mes;
        private int dia;

        public void parseData(String dataStr) {

            if (dataStr != null && !dataStr.isEmpty()) {

                String[] partes = dataStr.split("-");

                if (partes.length == 3) {

                    this.ano = Integer.parseInt(partes[0]);
                    this.mes = Integer.parseInt(partes[1]);
                    this.dia = Integer.parseInt(partes[2]);
                }
            }
        }

        public String format() {
            return String.format("%02d/%02d/%04d", dia, mes, ano);
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
        private float consumoCidade;
        private float consumoEstrada;
        private float co2;
        private boolean turbo;
        private Data dataRegistro;

        public int getId() {
            return this.id;
        }

        public String getMarca() {
            return this.marca;
        }

        public String getModelo() {
            return this.modelo;
        }

        public int getAno() {
            return this.ano;
        }

        public String getCategoria() {
            return this.categoria;
        }

        public String getCombustivel() {
            return this.combustivel;
        }

        public int getCilindros() {
            return this.cilindros;
        }

        public float getCilindrada() {
            return this.cilindrada;
        }

        public String getTransmissao() {
            return this.transmissao;
        }

        public String getTracao() {
            return this.tracao;
        }

        public float getConsumoCidade() {
            return this.consumoCidade;
        }

        public float getConsumoEstrada() {
            return this.consumoEstrada;
        }

        public float getCo2() {
            return this.co2;
        }

        public boolean getTurbo() {
            return this.turbo;
        }

        public Data getDataRegistro() {
            return this.dataRegistro;
        }

        public void parseVeiculo(String strVeiculo) {

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

        public String format() {

            return String.format("[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]", id, marca, modelo, ano, categoria, combustivel.replace(";", ","), cilindros, cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2, turbo, dataRegistro.format());
        }
    }

    static class LeitorCSV {

        Veiculo[] lerCSV(String path) {

            Veiculo[] array = new Veiculo[512];
            int i = 0;

            try {
                Scanner file = new Scanner(new File(path));

                file.nextLine();

                while (file.hasNextLine()) {

                    String linha = file.nextLine();

                    Veiculo v = new Veiculo();
                    v.parseVeiculo(linha);

                    array[i++] = v;
                }

                file.close();

                return array;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // PILHA SIMPLESMENTE ENCADEADA ===================================================================================

    static class Celula {

        Veiculo elemento;
        Celula prox;

        Celula(Veiculo elemento) {
            this.elemento = elemento;
            this.prox = null;
        }
    }

    static class Pilha {

        Celula topo;
        int tamanho;

        Pilha() {
            this.topo = null;
            this.tamanho = 0;
        }

        void inserir(Veiculo veiculo) {

            Celula nova = new Celula(veiculo);

            nova.prox = topo;
            topo = nova;

            tamanho++;
        }

        Veiculo remover() {

            if (topo == null)
                throw new RuntimeException("Pilha vazia");

            Veiculo removido = topo.elemento;
            topo = topo.prox;
            tamanho--;

            return removido;
        }

        void mostrar() {

            Celula atual = topo;
            int posicao = 0;

            while (atual != null) {

                System.out.println(atual.elemento.format());

                atual = atual.prox;
                posicao++;
            }
        }
    }

    // MAIN ===================================================================================

    public static void main(String[] args) {

        LeitorCSV leitor = new LeitorCSV();
        Veiculo[] veiculos = leitor.lerCSV("/tmp/veiculos.csv");
        int n = 0;

        while (veiculos[n] != null)
            n++;

        Pilha pilha = new Pilha();
        Scanner scanner = new Scanner(System.in);

        int id;

        do {
            id = scanner.nextInt();

            if (id != -1) {

                for (int i = 0; i < n; i++) {

                    if (veiculos[i].getId() == id) {

                        pilha.inserir(veiculos[i]);

                        break;
                    }
                }
            }

        } while (id != -1);

        int quantidade = scanner.nextInt();
        for (int i = 0; i < quantidade; i++) {
            String comando = scanner.next();

            if (comando.equals("I")) {
                id = scanner.nextInt();

                for (int j = 0; j < n; j++) {
                    if (veiculos[j].getId() == id) {
                        pilha.inserir(veiculos[j]);
                        break;
                    }
                }

            } else if (comando.equals("R")) {
                Veiculo removido = pilha.remover();
                System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());
            }
        }

        scanner.close();
        pilha.mostrar();
    }
}
