import java.util.Scanner;

public class premiacao_oscar {

    static class Filme {
        int premios;
        int ano;
        String titulo;
    }

    // Returns negative if 'a' has higher priority than 'b'
    public static int comparar(Filme a, Filme b) {
        if (a.premios != b.premios) {
            return b.premios - a.premios; // Decreasing by awards
        }
        if (a.ano != b.ano) {
            return a.ano - b.ano; // Increasing by year
        }
        return a.titulo.compareTo(b.titulo); // Alphabetical by title
    }

    public static void trocar(Filme[] arr, int i, int j) {
        Filme temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapify(Filme[] arr, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && comparar(arr[esquerda], arr[maior]) < 0) {
            maior = esquerda;
        }

        if (direita < n && comparar(arr[direita], arr[maior]) < 0) {
            maior = direita;
        }

        if (maior != i) {
            trocar(arr, i, maior);
            heapify(arr, n, maior);
        }
    }

    public static void heapsortParcial(Filme[] arr, int n, int k) {
        // Build Max-Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract top K elements one by one
        for (int i = n - 1; i >= n - k; i--) {
            trocar(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            Filme[] filmes = new Filme[n];

            for (int i = 0; i < n; i++) {
                filmes[i] = new Filme();
                filmes[i].premios = scanner.nextInt();
                filmes[i].ano = scanner.nextInt();
                // Read the space after the integer before getting the line
                filmes[i].titulo = scanner.nextLine().trim();
            }

            heapsortParcial(filmes, n, k);

            for (int i = n - 1; i >= n - k; i--) {
                System.out.println(filmes[i].premios + " " + filmes[i].ano + " " + filmes[i].titulo);
            }
        }

        scanner.close();
    }
}
