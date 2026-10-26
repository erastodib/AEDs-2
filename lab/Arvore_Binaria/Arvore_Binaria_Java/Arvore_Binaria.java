import java.util.Scanner;

class No {
    public int elemento;
    public No esq, dir;

    public No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
}

class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    // INSERÇÃO
    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        }
        return i;
    }

    // PESQUISA
    public boolean pesquisar(int x) {
        boolean resp = pesquisar(x, raiz);
        if (resp) {
            System.out.println("S");
        } else {
            System.out.println("N");
        }
        return resp;
    }

    private boolean pesquisar(int x, No i) {
        if (i == null) {
            return false;
        }

        System.out.print(i.elemento + " ");

        if (x == i.elemento) {
            return true;
        } else if (x < i.elemento) {
            return pesquisar(x, i.esq);
        } else {
            return pesquisar(x, i.dir);
        }
    }

    public void caminharPre() {
        if (raiz == null) {
            System.out.println("V");
        } else {
            caminharPre(raiz);
            System.out.println();
        }
    }

    private void caminharPre(No i) {
        if (i != null) {
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharPos() {
        if (raiz == null) {
            System.out.println("V");
        } else {
            caminharPos(raiz);
            System.out.println();
        }
    }

    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    public void caminharEm() {
        if (raiz == null) {
            System.out.println("V");
        } else {
            caminharEm(raiz);
            System.out.println();
        }
    }

    private void caminharEm(No i) {
        if (i != null) {
            caminharEm(i.esq);
            System.out.print(i.elemento + " ");
            caminharEm(i.dir);
        }
    }
}

public class Arvore_Binaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();

        while (scanner.hasNext()) {
            String comando = scanner.next();

            if (comando.equals("I")) {
                int x = scanner.nextInt();
                arvore.inserir(x);
            } else if (comando.equals("P")) {
                int y = scanner.nextInt();
                arvore.pesquisar(y);
            } else if (comando.equals("PRE")) {
                arvore.caminharPre();
            } else if (comando.equals("POS")) {
                arvore.caminharPos();
            } else if (comando.equals("EM")) {
                arvore.caminharEm();
            }
        }

        scanner.close();
    }
}
