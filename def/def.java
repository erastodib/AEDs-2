import java.util.Scanner;

public class Main {

    static class Aluno {
        int nota;
        int faltas;
        String nome;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Aluno[] aluno = new Aluno[1024];
        int len = 0;

        while (scanner.hasNextInt()) {
            aluno[len] = new Aluno();
            aluno[len].nota = scanner.nextInt();
            aluno[len].faltas = scanner.nextInt();

            // Read line rest including spaces (replaces %[^\n])
            aluno[len].nome = scanner.nextLine().trim();

            len++;
        }

        scanner.close();
    }
}
