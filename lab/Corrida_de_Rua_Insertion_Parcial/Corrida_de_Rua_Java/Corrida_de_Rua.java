import java.util.Scanner;

public class Corrida_de_Rua {

    static class Tempo {
        int hora;
        int min;
        int seg;

        public Tempo(int hora, int min, int seg) {
            this.hora = hora;
            this.min = min;
            this.seg = seg;
        }

        public int sTotal() {
            return hora * 3600 + min * 60 + seg;
        }
    }

    static class Corredor {
        String nome;
        Tempo tempo;

        public Corredor(String nome, Tempo tempo) {
            this.nome = nome;
            this.tempo = tempo;
        }
    }

    public static void insertionSortParc(Corredor[] arr, int n, int k) {
        for (int i = 1; i < n; i++) {
            Corredor tmp = arr[i];
            int t = tmp.tempo.sTotal();
            int j;

            if (i <= k) {
                j = i - 1;
            } else {
                j = k - 1;
            }

            while (j >= 0 && (arr[j].tempo.sTotal() > t)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            Corredor[] arr = new Corredor[n];

            for (int i = 0; i < n; i++) {
                String nome = scanner.next();
                int hora = scanner.nextInt();
                int min = scanner.nextInt();
                int seg = scanner.nextInt();

                arr[i] = new Corredor(nome, new Tempo(hora, min, seg));
            }

            insertionSortParc(arr, n, k);

            for (int i = 0; i < k; i++) {
                System.out.printf("%s %d %d %d%n",
                    arr[i].nome, arr[i].tempo.hora, arr[i].tempo.min, arr[i].tempo.seg);
            }
        }

        scanner.close();
    }
}
