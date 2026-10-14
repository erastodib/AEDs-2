
//	AKA:
//	ArrayList (Java)
//	Vector (C++)
//	Array (JavaScript)
//	List (Python)

//	Vantagens:
//	1. Acesso aleatório aos elementos O(1)
//	2. Boa localidade de referência e utilização eficiente do cache de dados
//	3. Fácil inserção/remoção no final do array
//
//	Desvantagens:
//	1. Gasta mais memória que uma Lista Encadeada
//	2. Deslocamento de elementos consume mais tempo O(n)
//	3. Expandir/Encolher o array consume mais tempo O(n)
//
import java.util.ArrayList;

public class array_dinamico{

	public static void main(String[] args){
		
		//ArrayList<String> arrayList = new ArrayList<String>();
		DynamicArray dynamicArray = new DynamicArray(5);

		dynamicArray.add("A");
		dynamicArray.add("B");
		dynamicArray.add("C");
		dynamicArray.add("D");
		dynamicArray.add("E");
		dynamicArray.add("F");

		dynamicArray.delete("A");
		dynamicArray.delete("B");
		dynamicArray.delete("C");

//		dynamicArray.insert(0, "X");
//		dynamicArray.delete("A");
//		System.out.println(dynamicArray.search("C"));

		System.out.println(dynamicArray);
		System.out.println("Size: " + dynamicArray.cap);
		System.out.println("Empty: " + dynamicArray.isEmpty());
		
	}

	public static class DynamicArray{

		int size;
		int cap = 10;
		Object[] array;

		public DynamicArray(){
			this.array = new Object[cap];
		}
		
		public DynamicArray(int cap){
			this.cap = cap;
			this.array = new Object[cap];

		}

		public void add(Object data){
			if (size >= cap)
				grow();
			array[size] = data;
			size++;
		}

		public void insert(int index, Object data){
			if (size >= cap)
				grow();
			for (int i = size; i > index; i--)
				array[i] = array[i-1];
			array[index] = data;
			size++;
		}

		public void delete(Object data){
			for (int i = 0; i < size; i++){
				if(array[i] == data){
					for (int j = 0; j < (size - i - 1); j++)
						array[i+j] = array[i+j+1];
					array[size - 1] = null;
					size--;
					if(size <= (int)(cap/3))
						shrink();
					break;
				}

			}
		}

		public int search(Object data){
			for (int i = 0; i < size; i++)
				if (array[i] == data)
					return i;
			return -1;
		}

		private void grow(){
			int newCap = (int)(cap * 2);
			Object[] newArray = new Object[newCap];
			for (int i = 0; i < size; i++)	
				newArray[i] = array[i];
			cap = newCap;
			array = newArray;
		}

		private void shrink(){
			int newCap = (int)(cap/2);
			Object[] newArray = new Object[newCap];
			for (int i = 0; i < size; i++)
				newArray[i] = array[i];
			cap = newCap;
			array = newArray;
		}

		public boolean isEmpty(){
			return size == 0;
		}

		public String toString(){
			String str = "";
			
			//for (int i = 0; i < size; i++) //Only allocated elements
			for( int i = 0; i < cap; i++)	 //Static array (full capacity)
				str += array[i] + ", ";
			if (str != "" )
				str = "[" + (str.substring(0, str.length()-2) + "]");
			else
				str = "[]";
			return str;
		}		
	}

}
