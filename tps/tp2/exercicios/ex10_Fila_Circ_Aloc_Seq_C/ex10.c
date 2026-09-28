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

    sprintf(buffer, "%02d/%02d/%04d",
            d.dia,
            d.mes,
            d.ano);
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

            array = (Veiculo *)realloc(
                array,
                cap * sizeof(Veiculo)
            );
        }

        Veiculo *v = parseVeiculo(linha);
        array[*n] = *v;
        (*n)++;

        free(v);
    }

    fclose(arquivo);

    return array;
}

// FILA CIRCULAR ===================================================================================

typedef struct{

    Veiculo array[TAM];

    int inicio;
    int fim;
    int tam;

} Fila;


void inicializarFila(Fila *fila){
    fila->inicio = 0;
    fila->fim = 0;
    fila->tam = 0;
}


bool cheia(Fila *fila){
    return fila->tam == TAM;
}


bool vazia(Fila *fila){
    return fila->tam == 0;
}

void inserir(Fila *fila, Veiculo veiculo){

    if (cheia(fila)){

        Veiculo removido;

        removido = fila->array[fila->inicio];

        printf("(R)%s %s\n",removido.marca,removido.modelo);

        fila->inicio = (fila->inicio + 1) % TAM;
        fila->tam--;
    }

    fila->array[fila->fim] = veiculo;
    fila->fim = (fila->fim + 1) % TAM;
    fila->tam++;
}


Veiculo remover(Fila *fila){

    Veiculo removido;
    removido = fila->array[fila->inicio];
    fila->inicio = (fila->inicio + 1) % TAM;
    fila->tam--;

    return removido;
}


void mostrar(Fila *fila){

    for (int i = 0; i < fila->tam; i++){

        int posicao;

        posicao = (fila->inicio + i) % TAM;

        char buffer[1024];

        formatVeiculo(fila->array[posicao],buffer);

        printf("%s\n", buffer);
    }
}

// MAIN ===================================================================================

int main(){

    int n;
    int id;

    Veiculo *veiculos;
    veiculos = lerCsv("/tmp/veiculos.csv",&n);

    Fila fila;
    inicializarFila(&fila);

    do{

        scanf("%d", &id);

        if (id != -1){

            for (int i = 0; i < n; i++){

                if (veiculos[i].id == id){
                    inserir(&fila,veiculos[i]);
                    break;
                }
            }
        }

    } while (id != -1);

    int quantidade;

    scanf("%d", &quantidade);

    for (int i = 0; i < quantidade; i++){
        char comando;
        scanf(" %c", &comando);

        if (comando == 'I'){
            scanf("%d", &id);

            for (int j = 0; j < n; j++){
                if (veiculos[j].id == id){
                    inserir(&fila,veiculos[j]);
                    break;
                }
            }
        }


        else if (comando == 'R'){
            Veiculo removido;
            removido = remover(&fila);

            printf("(R)%s %s\n",removido.marca,removido.modelo);
        }
    }

    mostrar(&fila);

    free(veiculos);

    return 0;
}
