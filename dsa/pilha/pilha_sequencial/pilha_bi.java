import java.util.Stack;

public class pilha_bi{

	public static void main(String[] args){
		Stack<String> stack = new Stack<String>();

	//	FILA = Estrutura de Dados Linear LIFO (Last-In First-Out)
	//	Armazena objetos da mesma maneira que em uma pilha
	//	push() para adicionar no topo
	//	pop() para remover do topo
		
		stack.push("Minecraft");
		stack.push("Skyrim");
		stack.push("DOOM");
		stack.push("Borderlands");
		stack.push("FFVII");
		//System.out.println(stack.empty());

		String myFavGame = stack.pop();
		System.out.println(myFavGame);
		System.out.println(stack);
	}

}

//		Exemplos práticos de aplicações:
//
//		1. Undo/redo em editores de texto
//		2. Avançar e voltar no histórico do navegador
//		3. Algoritmos de Backtracking (maze, diretórios de arquivo)
//		3. Chamada de funções (call stack)


