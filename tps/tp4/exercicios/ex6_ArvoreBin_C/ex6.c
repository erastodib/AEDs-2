#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <strings.h>
#define DEFAULT 1024

// CLASSE DATA ===================================================================================

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

// CLASSE VEICULO ===================================================================================

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

	int ordemSelecao;

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
//CSV

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

//IMPLEMENTAÇÃO BST

//Struct e criação da árvore
typedef struct No {

	Veiculo veiculo;
	struct No *esq;
	struct No *dir;

} No;

// Protótipos das funções
No* newNo(Veiculo veiculo);
No* inserir(Veiculo x, No* n);
bool pesquisar(char* modelo, No* n);
void emOrdem(No* n);

//-------------------------------------------------------------------------------------------------
//MAIN

int main(){

        int n;
        int id;

        Veiculo *veiculos = lerCsv("/tmp/veiculos.csv", &n);
	No* raiz = NULL; //Criar árvore vazia
	

	// Popular árvore de acordo com a entrada de pub.in
        do{
                scanf("%d", &id);

                if(id != -1){
                        for (int i = 0; i < n; i++){
                                if (veiculos[i].id  == id){
                                        raiz = inserir(veiculos[i], raiz);
                                        break;
                                }
                        }
                }

        } while (id != -1);

	getchar(); //limpar buffer
	
	// Pesquisar na árvore de acordo com a entrada de pub.in
	
	char modelo[64];
	bool tmp;

	while (scanf(" %[^\n]", modelo) == 1 && strcmp(modelo, "FIM") != 0)
		tmp = pesquisar(modelo, raiz);	 

	// Imprime os itens da árvore por caminhamento em ordem
	emOrdem(raiz);

        free(veiculos);
}


//-------------------------------------------------------------------------------------------------

No* newNo(Veiculo veiculo){
	No *novoNo = (No*)malloc(sizeof(No));
	novoNo->veiculo = veiculo;
	novoNo->esq = novoNo->dir = NULL;

	return novoNo;
}

// INSERIR retornando ponteiro atualizado
No* inserir(Veiculo x, No* n){
	if (n == NULL) return newNo(x);
	
	int comp = strcmp(x.modelo, n->veiculo.modelo);
	
	/*strcmp compara duas Strings lexicograficamente e retorna um inteiro:
	 * NEGATIVO	-> se A < B
	 * POSITIVO	-> se A > B
	 * 0		-> se A == B
	 */
	if (comp < 0)
		n->esq = inserir(x, n->esq);
	else if (comp > 0)
		n->dir = inserir(x, n->dir);

	// Como pedido no enunciado, a lógica de inserção ignora elementos duplicados
	
	return n;
}

bool pesquisar(char* modelo, No* n){

	printf("raiz ");

	while (n != NULL){
		int comp = strcmp(modelo, n->veiculo.modelo);
		
		if (comp == 0){
			printf("SIM\n");
			return true;
		} else if (comp < 0){
			n = n->esq;
			printf("esq ");
		} else {
			n = n->dir;
			printf("dir ");
		}
	
	}

	printf("NAO\n");
	return false;
}

// CAMINHAMENTO EM ORDEM
void emOrdem(No* n){
	if (n != NULL){
		char buffer[DEFAULT];

		emOrdem(n->esq);

		formatVeiculo(n->veiculo, buffer);
		printf("%s\n", buffer);

		emOrdem(n->dir);
	}
}
