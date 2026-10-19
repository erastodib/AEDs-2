#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <strings.h>

#define DEFAULT 128

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

    sprintf(
        buffer,
        "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]",
        v.id,
        v.marca,
        v.modelo,
        v.ano,
        v.categoria,
        combustivel,
        v.cilindros,
        v.cilindrada,
        v.transmissao,
        v.tracao,
        v.consumoCidade,
        v.consumoEstrada,
        v.co2,
        v.turbo ? "true" : "false",
        data
    );
}

typedef struct Celula{
    Veiculo elemento;
    struct Celula *prox;
} Celula;

Celula *novaCelula(Veiculo v){
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = v;
    nova->prox = NULL;
    return nova;
}

typedef struct{
    Celula *primeiro;
    Celula *ultimo;
} Lista;

void start(Lista *l){
    l->primeiro = novaCelula((Veiculo){0});
    l->ultimo = l->primeiro;
}

void inserirFim(Lista *l, Veiculo v){
    l->ultimo->prox = novaCelula(v);
    l->ultimo = l->ultimo->prox;
}

void mostrar(Lista *l){
    for (Celula *i = l->primeiro->prox; i != NULL; i = i->prox){
        char buffer[1024];
        formatVeiculo(i->elemento, buffer);
        printf("%s\n", buffer);
    }
}

void liberarLista(Lista *l){
    Celula *i = l->primeiro;
    while (i != NULL){
        Celula *tmp = i;
        i = i->prox;
        free(tmp);
    }
}

void selectionSortFlexivel(Lista *l){
    for (Celula *i = l->primeiro->prox; i != NULL && i->prox != NULL; i = i->prox){
        Celula *min = i;

        for (Celula *j = i->prox; j != NULL; j = j->prox){
            if (strcasecmp(j->elemento.modelo, min->elemento.modelo) < 0){
                min = j;
            }
        }

        if (min != i){
            Veiculo temp = i->elemento;
            i->elemento = min->elemento;
            min->elemento = temp;
        }
    }
}

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

            array = (Veiculo *)realloc(
                array,
                cap * sizeof(Veiculo));
        }

        Veiculo *v = parseVeiculo(linha);

        array[*n] = *v;
        (*n)++;

        free(v);
    }

    fclose(arquivo);

    return array;
}

int main(){

    int n;
    int id;

    Veiculo *veiculos = lerCsv("/tmp/veiculos.csv", &n);

    Lista lista;
    start(&lista);

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

    selectionSortFlexivel(&lista);
    mostrar(&lista);

    liberarLista(&lista);
    free(veiculos);

    return 0;
}
