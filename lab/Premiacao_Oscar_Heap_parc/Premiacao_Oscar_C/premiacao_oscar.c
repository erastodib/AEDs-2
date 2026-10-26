#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int premios;
    int ano;
    char titulo[101];
} Filme;

int comparar(const Filme *a, const Filme *b) {
    if (a->premios != b->premios) {
        return b->premios - a->premios; // Decrescente por prêmios
    }
    if (a->ano != b->ano) {
        return a->ano - b->ano; // Crescente por ano (mais antigo primeiro)
    }
    return strcmp(a->titulo, b->titulo); // Ordem alfabética
}

void trocar(Filme *a, Filme *b) {
    Filme temp = *a;
    *a = *b;
    *b = temp;
}

/*
 * Restaura a propriedade de Max-Heap para a subárvore enraizada no índice i.
 * O heap considera a ordem definida pela função comparar().
 */
void heapify(Filme arr[], int n, int i) {
    int maior = i;
    int esquerda = 2 * i + 1;
    int direita = 2 * i + 2;

    // Se o filho da esquerda for "maior" (melhor classificado) que a raiz
    if (esquerda < n && comparar(&arr[esquerda], &arr[maior]) < 0) {
        maior = esquerda;
    }

    // Se o filho da direita for "maior" (melhor classificado) que o maior até agora
    if (direita < n && comparar(&arr[direita], &arr[maior]) < 0) {
        maior = direita;
    }

    // Se a raiz não for o maior elemento
    if (maior != i) {
        trocar(&arr[i], &arr[maior]);
        heapify(arr, n, maior);
    }
}

void heapsort_parcial(Filme arr[], int n, int k) {
    // Construção do Max-Heap
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    // Extrai os K maiores elementos um por um
    for (int i = n - 1; i >= n - k; i--) {
        // Move a raiz atual (maior elemento) para o final
        trocar(&arr[0], &arr[i]);

        // Restaura o heap na subárvore reduzida
        heapify(arr, i, 0);
    }
}

int main() {
    int n, k;

    while (scanf("%d %d", &n, &k) == 2) {
        Filme *filmes = (Filme *)malloc(n * sizeof(Filme));

        for (int i = 0; i < n; i++) {
            scanf("%d %d ", &filmes[i].premios, &filmes[i].ano);

            if (fgets(filmes[i].titulo, sizeof(filmes[i].titulo), stdin) != NULL) {
                size_t len = strlen(filmes[i].titulo);
                while (len > 0 && (filmes[i].titulo[len - 1] == '\n' || filmes[i].titulo[len - 1] == '\r')) {
                    filmes[i].titulo[--len] = '\0';
                }
            }
        }

        // Executa a ordenação parcial apenas para os K primeiros
        heapsort_parcial(filmes, n, k);

        for (int i = n - 1; i >= n - k; i--) {
            printf("%d %d %s\n", filmes[i].premios, filmes[i].ano, filmes[i].titulo);
        }

        free(filmes);
    }

    return 0;
}
