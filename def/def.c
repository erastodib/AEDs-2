int main(){

    Aluno aluno[1024];

    int len = 0;

    while (scanf("%d", &aluno[len].nota)!=EOF){
        scanf("%d %[^\n]", &aluno[len].faltas, aluno[len].nome);
        len++;
    }
