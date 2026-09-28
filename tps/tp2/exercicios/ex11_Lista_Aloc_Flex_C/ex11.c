#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define DEFAULT 128
#define TAM 5

// DATA ===================================================================================

typedef struct{

    int dia;
    int mes;
    int ano;

} Data;

Data parseData(char *str){

    Data data;
    char *parte;

    parte = strtok(str, "-");
    data.ano = atoi(parte);

    parte = strtok(NULL, "-");
    data.mes = atoi(parte);

    parte = strtok(NULL, "-");
    data.dia = atoi(parte);

    return data;
}

void formatData(Data d, char *buffer){

    sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
}

// VEICULO ===================================================================================

typedef struct{

    int id;
    char marca[DEFAULT];
    char modelo[DEFAULT];
    int ano;
    char categoria[DEFAULT];
    char combustivel[DEFAULT];
    int cilindros;
    double cilindrada;
    char transmissao[DEFAULT];
    char tracao[DEFAULT];
    double consumoCidade;
    double consumoEstrada;
    double co2;
    bool turbo;
    Data dataRegistro;

} Veiculo;

Veiculo *parseVeiculo(char *str){

    Veiculo *v = (Veiculo *)malloc(sizeof(Veiculo));

    char *parte;

    parte = strtok(str, ",");
    v->id = atoi(parte);

    parte = strtok(NULL, ",");
    strcpy(v->marca, parte);

    parte = strtok(NULL, ",");
    strcpy(v->modelo, parte);

    parte = strtok(NULL, ",");
    v->ano = atoi(parte);

    parte = strtok(NULL, ",");
    strcpy(v->categoria, parte);

    parte = strtok(NULL, ",");
    strcpy(v->combustivel, parte);

    parte = strtok(NULL, ",");
    v->cilindros = atoi(parte);

    parte = strtok(NULL, ",");
    v->cilindrada = atof(parte);

    parte = strtok(NULL, ",");
    strcpy(v->transmissao, parte);

    parte = strtok(NULL, ",");
    strcpy(v->tracao, parte);

    parte = strtok(NULL, ",");
    v->consumoCidade = atof(parte);

    parte = strtok(NULL, ",");
    v->consumoEstrada = atof(parte);

    parte = strtok(NULL, ",");
    v->co2 = atof(parte);

    parte = strtok(NULL, ",");
    v->turbo = strcmp(parte, "true") == 0;

    parte = strtok(NULL, ",");
    v->dataRegistro = parseData(parte);

    return v;
}

void formatVeiculo(Veiculo v, char *buffer){

    char data[20];
    char combustivel[DEFAULT];

    formatData(v.dataRegistro, data);

    strcpy(combustivel, v.combustivel);

    for (int i = 0; combustivel[i] != '\0'; i++){

        if (combustivel[i] == ';')
            combustivel[i] = ',';
    }

    sprintf(buffer, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]", v.id, v.marca, v.modelo, v.ano, v.categoria, combustivel, v.cilindros, v.cilindrada, v.transmissao, v.tracao, v.consumoCidade, v.consumoEstrada, v.co2, v.turbo ? "true" : "false", data);
}

// CSV ===================================================================================

Veiculo *lerCsv(char *caminhoArquivo, int *n){

    FILE *arquivo;
    Veiculo *array;

    char linha[1024];

    int cap = 10;

    arquivo = fopen(caminhoArquivo, "r");

    if (arquivo == NULL){

        *n = 0;
        return NULL;
    }

    array = (Veiculo *)malloc(cap * sizeof(Veiculo));

    fgets(linha, sizeof(linha), arquivo);
    *n = 0;

    while (fgets(linha, sizeof(linha), arquivo) != NULL){

        if (*n == cap){

            cap *= 2;

            array = (Veiculo *)realloc(array, cap * sizeof(Veiculo));
        }

        Veiculo *v = parseVeiculo(linha);
        array[*n] = *v;
        (*n)++;

        free(v);
    }

    fclose(arquivo);

    return array;
}

// LISTA SIMPLESMENTE ENCADEADA ===================================================================================

typedef struct Celula{

    Veiculo elemento;
    struct Celula *prox;

} Celula;


typedef struct{

    Celula *primeiro;
    Celula *ultimo;
    int tam;

} Lista;


void inicializarLista(Lista *lista){

    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tam = 0;
}


void inserirInicio(Lista *lista, Veiculo veiculo){

    Celula *nova;

    nova = (Celula *)malloc(sizeof(Celula));

    nova->elemento = veiculo;
    nova->prox = lista->primeiro;

    lista->primeiro = nova;

    if (lista->tam == 0)
        lista->ultimo = nova;

    lista->tam++;
}


void inserirFim(Lista *lista, Veiculo veiculo){

    Celula *nova;

    nova = (Celula *)malloc(sizeof(Celula));

    nova->elemento = veiculo;
    nova->prox = NULL;

    if (lista->tam == 0){

        lista->primeiro = nova;
        lista->ultimo = nova;

    } else {

        lista->ultimo->prox = nova;
        lista->ultimo = nova;
    }

    lista->tam++;
}


void inserir(Lista *lista, Veiculo veiculo, int posicao){

    if (posicao == 0)
        inserirInicio(lista, veiculo);

    else if (posicao == lista->tam)
        inserirFim(lista, veiculo);

    else{
        Celula *anterior = lista->primeiro;

        for (int i = 0; i < posicao - 1; i++)
            anterior = anterior->prox;

        Celula *nova;

        nova = (Celula *)malloc(sizeof(Celula));

        nova->elemento = veiculo;
        nova->prox = anterior->prox;
        anterior->prox = nova;

        lista->tam++;
    }
}


Veiculo removerInicio(Lista *lista){

    Celula *tmp;
    Veiculo removido;

    tmp = lista->primeiro;
    removido = tmp->elemento;

    lista->primeiro = tmp->prox;

    if (lista->tam == 1)
        lista->ultimo = NULL;

    free(tmp);

    lista->tam--;

    return removido;
}


Veiculo removerFim(Lista *lista){

    if (lista->tam == 1)
        return removerInicio(lista);

    Celula *anterior = lista->primeiro;

    while (anterior->prox != lista->ultimo)
        anterior = anterior->prox;

    Veiculo removido = lista->ultimo->elemento;

    free(lista->ultimo);

    lista->ultimo = anterior;
    lista->ultimo->prox = NULL;

    lista->tam--;

    return removido;
}


Veiculo remover(Lista *lista, int posicao){

    if (posicao == 0)
        return removerInicio(lista);

    if (posicao == lista->tam - 1)
        return removerFim(lista);

    Celula *anterior = lista->primeiro;

    for (int i = 0; i < posicao - 1; i++)
        anterior = anterior->prox;

    Celula *tmp = anterior->prox;

    Veiculo removido = tmp->elemento;

    anterior->prox = tmp->prox;

    free(tmp);

    lista->tam--;

    return removido;
}


void mostrar(Lista *lista){

    Celula *atual = lista->primeiro;

    int posicao = 0;

    while (atual != NULL){

        char buffer[1024];

        formatVeiculo(atual->elemento, buffer);

        printf("%s\n", buffer);

        atual = atual->prox;
        posicao++;
    }
}

// MAIN ===================================================================================

int main(){

    int n;
    int id;

    Veiculo *veiculos;

    veiculos = lerCsv("/tmp/veiculos.csv", &n);

    Lista lista;

    inicializarLista(&lista);

    do{

        scanf("%d", &id);

        if (id != -1){

            for (int i = 0; i < n; i++){

                if (veiculos[i].id == id){

                    inserirFim(&lista, veiculos[i]);

                    break;
                }
            }
        }

    } while (id != -1);

    int quantidade;

    scanf("%d", &quantidade);

    for (int i = 0; i < quantidade; i++){

        char comando[3];

        scanf("%s", comando);

        if (strcmp(comando, "II") == 0){

            scanf("%d", &id);

            for (int j = 0; j < n; j++){

                if (veiculos[j].id == id){

                    inserirInicio(&lista, veiculos[j]);

                    break;
                }
            }
        }

        else if (strcmp(comando, "IF") == 0){

            scanf("%d", &id);

            for (int j = 0; j < n; j++){

                if (veiculos[j].id == id){

                    inserirFim(&lista, veiculos[j]);

                    break;
                }
            }
        }

        else if (strcmp(comando, "I*") == 0){

            int posicao;

            scanf("%d %d", &posicao, &id);

            for (int j = 0; j < n; j++){

                if (veiculos[j].id == id){

                    inserir(&lista, veiculos[j], posicao);

                    break;
                }
            }
        }

        else if (strcmp(comando, "RI") == 0){

            Veiculo removido;

            removido = removerInicio(&lista);

            printf("(R)%s %s\n", removido.marca, removido.modelo);
        }

        else if (strcmp(comando, "RF") == 0){

            Veiculo removido;

            removido = removerFim(&lista);

            printf("(R)%s %s\n", removido.marca, removido.modelo);
        }

        else if (strcmp(comando, "R*") == 0){

            int posicao;

            scanf("%d", &posicao);

            Veiculo removido;

            removido = remover(&lista, posicao);

            printf("(R)%s %s\n", removido.marca, removido.modelo);
        }
    }

    mostrar(&lista);

    while (lista.primeiro != NULL)
        removerInicio(&lista);

    free(veiculos);

    return 0;
}
