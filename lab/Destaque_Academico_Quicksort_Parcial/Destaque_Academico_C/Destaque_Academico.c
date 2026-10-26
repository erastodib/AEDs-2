#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int nota;
    int faltas;
    char nome[51];
} Aluno;

int comparar(const Aluno *a, const Aluno *b) {
    if (a->nota != b->nota) {
        return b->nota - a->nota; // Decrescente por nota
    }
    if (a->faltas != b->faltas) {
        return a->faltas - b->faltas; // Crescente por faltas
    }
    return strcmp(a->nome, b->nome); // Ordem alfabética
}

void trocar(Aluno *a, Aluno *b) {
    Aluno temp = *a;
    *a = *b;
    *b = temp;
}

int particionar(Aluno arr[], int low, int high) {
    Aluno pivo = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (comparar(&arr[j], &pivo) <= 0) {
            i++;
            trocar(&arr[i], &arr[j]);
        }
    }
    trocar(&arr[i + 1], &arr[high]);
    return i + 1;
}

void quicksort_parcial(Aluno arr[], int low, int high, int k) {
    if (low < high) {
        int pi = particionar(arr, low, high);

        quicksort_parcial(arr, low, pi - 1, k);

        if (pi < k - 1) {
            quicksort_parcial(arr, pi + 1, high, k);
        }
    }
}

int main() {
    int n, k;

    while (scanf("%d %d", &n, &k) == 2) {
        Aluno *alunos = (Aluno *)malloc(n * sizeof(Aluno));

        for (int i = 0; i < n; i++) {
            scanf("%d %d ", &alunos[i].nota, &alunos[i].faltas);

            if (fgets(alunos[i].nome, sizeof(alunos[i].nome), stdin) != NULL) {
                size_t len = strlen(alunos[i].nome);
                // Remove caracteres de nova linha (\n) e retorno de carro (\r) no final
                while (len > 0 && (alunos[i].nome[len - 1] == '\n' || alunos[i].nome[len - 1] == '\r')) {
                    alunos[i].nome[--len] = '\0';
                }
            }
        }

        quicksort_parcial(alunos, 0, n - 1, k);

        for (int i = 0; i < k; i++) {
            printf("%d %d %s\n", alunos[i].nota, alunos[i].faltas, alunos[i].nome);
        }

        free(alunos);
    }

    return 0;
}
