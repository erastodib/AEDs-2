import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data() {}

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public static Data parseData(String str) {
        String[] partes = str.split("-");
        int ano = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);
        return new Data(dia, mes, ano);
    }

    public String format() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}

class Veiculo {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String categoria;
    private String combustivel;
    private int cilindros;
    private double cilindrada;
    private String transmissao;
    private String tracao;
    private double consumoCidade;
    private double consumoEstrada;
    private double co2;
    private boolean turbo;
    private Data dataRegistro;
    private int ordemSelecao;

    public Veiculo() {}

    public int getId() { return id; }
    public String getMarca() { return marca; }
    public double getConsumoEstrada() { return consumoEstrada; }
    public int getOrdemSelecao() { return ordemSelecao; }
    public void setOrdemSelecao(int ordemSelecao) { this.ordemSelecao = ordemSelecao; }

    public static Veiculo parseVeiculo(String linha) {
        String[] partes = linha.split(",");
        Veiculo v = new Veiculo();
        v.id = Integer.parseInt(partes[0]);
        v.marca = partes[1];
        v.modelo = partes[2];
        v.ano = Integer.parseInt(partes[3]);
        v.categoria = partes[4];
        v.combustivel = partes[5];
        v.cilindros = Integer.parseInt(partes[6]);
        v.cilindrada = Double.parseDouble(partes[7]);
        v.transmissao = partes[8];
        v.tracao = partes[9];
        v.consumoCidade = Double.parseDouble(partes[10]);
        v.consumoEstrada = Double.parseDouble(partes[11]);
        v.co2 = Double.parseDouble(partes[12]);
        v.turbo = partes[13].equalsIgnoreCase("true");
        v.dataRegistro = Data.parseData(partes[14]);
        return v;
    }

    public String format() {
        String combFormatted = combustivel.replace(';', ',');
        return String.format("[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]",
                id, marca, modelo, ano, categoria, combFormatted, cilindros, cilindrada,
                transmissao, tracao, consumoCidade, consumoEstrada, co2, turbo, dataRegistro.format());
    }
}

class CelulaDupla {
    public Veiculo elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    public CelulaDupla() {
        this(null);
    }

    public CelulaDupla(Veiculo elemento) {
        this.elemento = elemento;
        this.ant = null;
        this.prox = null;
    }
}

class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserirFim(Veiculo v) {
        ultimo.prox = new CelulaDupla(v);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void mostrar() {
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento.format());
        }
    }

    public void quicksort() {
        if (primeiro.prox != null && primeiro.prox != ultimo) {
            quicksort(primeiro.prox, ultimo);
        }
    }

    private void quicksort(CelulaDupla esq, CelulaDupla dir) {
        if (dir != null && esq != dir && esq != dir.prox) {
            CelulaDupla pivoPos = particao(esq, dir);

            if (pivoPos != null && pivoPos != esq) {
                quicksort(esq, pivoPos.ant);
            }
            if (pivoPos != null && pivoPos != dir) {
                quicksort(pivoPos.prox, dir);
            }
        }
    }

    private CelulaDupla particao(CelulaDupla esq, CelulaDupla dir) {
        Veiculo pivo = dir.elemento;
        CelulaDupla i = esq.ant;

        for (CelulaDupla j = esq; j != dir; j = j.prox) {
            if (compara(j.elemento, pivo) < 0) {
                i = (i == null) ? esq : i.prox;
                swap(i, j);
            }
        }

        i = (i == null) ? esq : i.prox;
        swap(i, dir);
        return i;
    }

    private int compara(Veiculo a, Veiculo b) {
        if (a.getConsumoEstrada() < b.getConsumoEstrada()) {
            return -1;
        } else if (a.getConsumoEstrada() > b.getConsumoEstrada()) {
            return 1;
        } else {
            int cmpMarca = a.getMarca().compareTo(b.getMarca());
            if (cmpMarca != 0) {
                return cmpMarca;
            } else {
                return Integer.compare(a.getOrdemSelecao(), b.getOrdemSelecao());
            }
        }
    }

    private void swap(CelulaDupla i, CelulaDupla j) {
        Veiculo temp = i.elemento;
        i.elemento = j.elemento;
        j.elemento = temp;
    }
}

public class ex5 {
    public static Veiculo[] lerCsv(String caminhoArquivo) {
        Veiculo[] array = new Veiculo[10];
        int n = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (n == array.length) {
                    Veiculo[] novoArray = new Veiculo[array.length * 2];
                    for (int i = 0; i < array.length; i++) {
                        novoArray[i] = array[i];
                    }
                    array = novoArray;
                }
                array[n++] = Veiculo.parseVeiculo(linha);
            }
        } catch (IOException e) {
            return new Veiculo[0];
        }

        Veiculo[] resultado = new Veiculo[n];
        for (int i = 0; i < n; i++) {
            resultado[i] = array[i];
        }
        return resultado;
    }

    public static void main(String[] args) {
        Veiculo[] veiculos = lerCsv("/tmp/veiculos.csv");
        ListaDupla lista = new ListaDupla();
        Scanner scanner = new Scanner(System.in);

        int qtd = 0;
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();

            while (id != -1) {
                for (int i = 0; i < veiculos.length; i++) {
                    if (veiculos[i].getId() == id) {
                        Veiculo v = veiculos[i];
                        v.setOrdemSelecao(qtd++);
                        lista.inserirFim(v);
                        break;
                    }
                }
                if (scanner.hasNextInt()) {
                    id = scanner.nextInt();
                } else {
                    break;
                }
            }
        }

        lista.quicksort();
        lista.mostrar();
        scanner.close();
    }
}
