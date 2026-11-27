public class MatrizDeLista{

    CelulaMat inicio;


    //Pre-condição: célula (i,j) deve existir
    public boolean pesquisar(int i, int j, int elemento)  throws Exception {
        if(i < 0 || i >= linhas || j < 0 || j>= colunas){
            throw new Exception("Indices de célula inválidos!!");
        }
        
        //Ir até a célula (i,j)
        CelulaMat celulaMat = inicio;
        for (int ii = 0; ii < i; ii++){
            celulaMat = celulaMat.inf;
        }
        for (int jj = 0; jj < j; jj++){
            celulaMat = celulaMat.dir;
        }

        //Pesquisar na lista correspondente à celula (i,j)
        boolean resp = pesquisarLista(celulaMat, elemento);
    }

    private boolean pesquisarLista(CelulaMat celulaMat, int elemento){
        boolean resp = false;
        for(CelulaLista i = celulaMat.primeiro.prox; i != null && !resp; i = i.prox){
            if(i.elemento == elemento){
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisar(int elemento){
        boolean resp = false;
        CelulaMat i;

        for (i = inicio; i != null && !resp; i = i.inf){
            for(CelulaMat j = i; j != null && !resp; j = j.dir){
                //Visitando a celula j
                resp = pesquisarLista(j, elemento);

            }
        }
        return resp;
    }
}


class CelulaMat{
    CelulaMat esq, dir, inf, sup;
    CelulaLista primeiro, ultimo;
}

class CelulaLista{
    int elemento;
    CelulaLista prox;
}
