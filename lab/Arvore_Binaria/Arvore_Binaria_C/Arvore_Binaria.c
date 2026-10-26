#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct No {
    int elemento;
    struct No *esq;
    struct No *dir;
} No;

No* criarNo(int x) {
    No *novo = (No*) malloc(sizeof(No));
    novo->elemento = x;
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}

// INSERÇÃO
No* inserir(int x, No *i) {
    if (i == NULL) {
        i = criarNo(x);
    } else if (x < i->elemento) {
        i->esq = inserir(x, i->esq);
    } else if (x > i->elemento) {
        i->dir = inserir(x, i->dir);
    }
    return i;
}

// PESQUISA
bool pesquisarRecursivo(int x, No *i) {
    if (i == NULL) {
        return false;
    }

    printf("%d ", i->elemento);

    if (x == i->elemento) {
        return true;
    } else if (x < i->elemento) {
        return pesquisarRecursivo(x, i->esq);
    } else {
        return pesquisarRecursivo(x, i->dir);
    }
}

void pesquisar(int x, No *raiz) {
    bool resp = pesquisarRecursivo(x, raiz);
    if (resp) {
        printf("S\n");
    } else {
        printf("N\n");
    }
}

// TRAVERSALS
void caminharPreRecursivo(No *i) {
    if (i != NULL) {
        printf("%d ", i->elemento);
        caminharPreRecursivo(i->esq);
        caminharPreRecursivo(i->dir);
    }
}

void caminharPre(No *raiz) {
    if (raiz == NULL) {
        printf("V\n");
    } else {
        caminharPreRecursivo(raiz);
        printf("\n");
    }
}

void caminharPosRecursivo(No *i) {
    if (i != NULL) {
        caminharPosRecursivo(i->esq);
        caminharPosRecursivo(i->dir);
        printf("%d ", i->elemento);
    }
}

void caminharPos(No *raiz) {
    if (raiz == NULL) {
        printf("V\n");
    } else {
        caminharPosRecursivo(raiz);
        printf("\n");
    }
}

void caminharEmRecursivo(No *i) {
    if (i != NULL) {
        caminharEmRecursivo(i->esq);
        printf("%d ", i->elemento);
        caminharEmRecursivo(i->dir);
    }
}

void caminharEm(No *raiz) {
    if (raiz == NULL) {
        printf("V\n");
    } else {
        caminharEmRecursivo(raiz);
        printf("\n");
    }
}

// Memory Cleanup
void destruirArvore(No *i) {
    if (i != NULL) {
        destruirArvore(i->esq);
        destruirArvore(i->dir);
        free(i);
    }
}

int main() {
    No *raiz = NULL;
    char comando[10];

    while (scanf("%s", comando) != EOF) {
        if (strcmp(comando, "I") == 0) {
            int x;
            scanf("%d", &x);
            raiz = inserir(x, raiz);
        } else if (strcmp(comando, "P") == 0) {
            int y;
            scanf("%d", &y);
            pesquisar(y, raiz);
        } else if (strcmp(comando, "PRE") == 0) {
            caminharPre(raiz);
        } else if (strcmp(comando, "POS") == 0) {
            caminharPos(raiz);
        } else if (strcmp(comando, "EM") == 0) {
            caminharEm(raiz);
        }
    }

    destruirArvore(raiz);
    return 0;
}
