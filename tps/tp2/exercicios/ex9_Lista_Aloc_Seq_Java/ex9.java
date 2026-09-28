import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ex9 {

    // DATA ===================================================================================

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
            return String.format("%02d/%02d/%04d",dia,mes,ano
            );
        }
    }

    // VEICULO ===================================================================================

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

        // GETTERS

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

    // LEITOR CSV ===================================================================================

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

    // LISTA ===================================================================================

    static class Lista {

        private Veiculo[] array;
        private int n;

        public Lista() {
            this(512);
        }

        public Lista(int tamanho) {

            array = new Veiculo[tamanho];
            n = 0;
        }

        public void inserirInicio(Veiculo veiculo) throws Exception {

            if (n >= array.length)
                throw new Exception("Erro ao inserir!");

            for (int i = n; i > 0; i--)
                array[i] = array[i - 1];

            array[0] = veiculo;
            n++;
        }

        public void inserir(Veiculo veiculo, int posicao) throws Exception {

            if (n >= array.length || posicao < 0 || posicao > n)
                throw new Exception("Erro ao inserir!");

            for (int i = n; i > posicao; i--)
                array[i] = array[i - 1];

            array[posicao] = veiculo;
            n++;
        }

        public void inserirFim(Veiculo veiculo) throws Exception {

            if (n >= array.length)
                throw new Exception("Erro ao inserir!");

            array[n] = veiculo;
            n++;
        }

        public Veiculo removerInicio() throws Exception {

            if (n == 0)
                throw new Exception("Erro ao remover!");

            Veiculo removido = array[0];

            for (int i = 0; i < n - 1; i++)
                array[i] = array[i + 1];

            n--;
            array[n] = null;

            return removido;
        }

        public Veiculo remover(int posicao) throws Exception {

            if (n == 0 || posicao < 0 || posicao >= n)
                throw new Exception("Erro ao remover!");

            Veiculo removido = array[posicao];

            for (int i = posicao; i < n - 1; i++)
                array[i] = array[i + 1];

            n--;
            array[n] = null;

            return removido;
        }

        public Veiculo removerFim() throws Exception {

            if (n == 0)
                throw new Exception("Erro ao remover!");

            Veiculo removido = array[n - 1];
            n--;
            array[n] = null;

            return removido;
        }

        public void mostrar() {

            for (int i = 0; i < n; i++)
                System.out.println(array[i].format());
        }
    }

    // MAIN ===================================================================================

    public static void main(String[] args) throws Exception {

        LeitorCSV leitor = new LeitorCSV();
        Veiculo[] veiculos = leitor.lerCSV("/tmp/veiculos.csv");
        int n = 0;

        while (veiculos[n] != null)
            n++;

        Lista lista = new Lista();
        Scanner scanner = new Scanner(System.in);

        int id;

        do {

            id = scanner.nextInt();

            if (id != -1) {

                for (int i = 0; i < n; i++) {

                    if (veiculos[i].getId() == id) {

                        lista.inserirFim(veiculos[i]);

                        break;
                    }
                }
            }

        } while (id != -1);

        int quantidade = scanner.nextInt();

        for (int i = 0; i < quantidade; i++) {
            String comando = scanner.next();

            if (comando.equals("II")) {
                id = scanner.nextInt();

                for (int j = 0; j < n; j++) {
                    if (veiculos[j].getId() == id) {
                        lista.inserirInicio(veiculos[j]);
                        break;
                    }
                }

            } else if (comando.equals("IF")) {
                id = scanner.nextInt();

                for (int j = 0; j < n; j++) {
                    if (veiculos[j].getId() == id) {
                        lista.inserirFim(veiculos[j]);
                        break;
                    }
                }

            } else if (comando.equals("I*")) {
                int posicao = scanner.nextInt();
                id = scanner.nextInt();

                for (int j = 0; j < n; j++) {
                    if (veiculos[j].getId() == id) {
                        lista.inserir(veiculos[j], posicao);
                        break;
                    }
                }

            } else if (comando.equals("RI")) {
                Veiculo removido = lista.removerInicio();

                System.out.println("(R)" + removido.getMarca() + " " +removido.getModelo());

            } else if (comando.equals("R*")) {
                int posicao = scanner.nextInt();
                Veiculo removido = lista.remover(posicao);

                System.out.println("(R)" +removido.getMarca() + " " +removido.getModelo());

            } else if (comando.equals("RF")) {
                Veiculo removido = lista.removerFim();

                System.out.println("(R)" + removido.getMarca() + " " + removido.getModelo());
            }
        }

        scanner.close();

        lista.mostrar();
    }
}
