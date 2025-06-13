/*
 * Trabajo Práctico - Programación Concurrente
 * Versión concurrente del algoritmo QuickSort en Java utilizando hilos.
 */

public class QuickSortConcurrente extends Thread {

	private int[] array; // Array que se va a ordenar
	private int low; // Índice inferior del subarray actual
	private int high; // Índice superior del subarray actual
	private int profundidad; // Nivel de profundidad permitido para crear nuevos hilos
	private boolean descendente; // Bandera para ordenar de forma descendente o ascendente

	// Constructor que inicializa las variables del hilo
	public QuickSortConcurrente(int[] array, int low, int high, int profundidad, boolean descendente) {
		this.array = array; // Asigna el array al hilo actual
		this.low = low; // Asigna el índice inferior
		this.high = high; // Asigna el índice superior
		this.profundidad = profundidad; // Asigna la profundidad
		this.descendente = descendente; // Define el sentido del ordenamiento
	}

	// Método que intercambia dos elementos del array
	private void swap(int[] array, int i, int j) {
		int temp = array[i]; // Guarda el valor de la posición i
		array[i] = array[j]; // Asigna el valor de j a i
		array[j] = temp; // Asigna el valor original de i a j
	}

	// Método que particiona el array según el pivote
	private int partition(int[] array, int low, int high) {
		int pivot = array[high]; // Usa el último elemento como pivote
		int i = (low - 1); // Inicializa el índice menor
		for (int j = low; j <= high - 1; j++) {
			// Aplica condición según tipo de ordenamiento
			if ((descendente && array[j] > pivot) || (!descendente && array[j] < pivot)) {
				i++;
				swap(array, i, j); // Intercambia si corresponde
			}
		}
		swap(array, i + 1, high); // Coloca el pivote en posición final
		return (i + 1); // Devuelve el índice del pivote
	}

	// Método que se ejecuta cuando se inicia el hilo
	@Override
	public void run() {
		if (low < high) { // Verifica si hay más de un elemento
			int pi = partition(array, low, high); // Particiona el array
			if (profundidad > 0) { // Si hay profundidad, crea nuevos hilos
				QuickSortConcurrente hiloIzq = new QuickSortConcurrente(array, low, pi - 1, profundidad - 1,
						descendente);
				QuickSortConcurrente hiloDer = new QuickSortConcurrente(array, pi + 1, high, profundidad - 1,
						descendente);
				hiloIzq.start(); // Inicia hilo izquierdo
				hiloDer.start(); // Inicia hilo derecho
				try {
					hiloIzq.join(); // Espera que termine hilo izquierdo
					hiloDer.join(); // Espera que termine hilo derecho
				} catch (InterruptedException e) {
					e.printStackTrace(); // Maneja errores de sincronización
				}
			} else {
				// Si no hay profundidad, ejecuta recursivo secuencial
				quickSort(array, low, pi - 1);
				quickSort(array, pi + 1, high);
			}
		}
	}

	// Método quicksort secuencial usado cuando no se crean hilos
	private void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pi = partition(array, low, high); // Particiona el array
			quickSort(array, low, pi - 1); // Ordena lado izquierdo
			quickSort(array, pi + 1, high); // Ordena lado derecho
		}
	}

	// Método para verificar si el array está ordenado correctamente
	public static boolean estaOrdenado(int[] array, boolean descendente) {
		for (int i = 1; i < array.length; i++) {
			if ((descendente && array[i - 1] < array[i]) || (!descendente && array[i - 1] > array[i])) {
				return false; // Retorna false si encuentra desorden
			}
		}
		return true; // Retorna true si está ordenado correctamente
	}

	public static void main(String[] args) {
		int[] array; // Declaración del array
		int tamaño; // Variable para almacenar su tamaño
		boolean imprimirArray = true; // Controla si se muestra el array
		boolean esAleatorio = true; // Indica que el array es aleatorio
		boolean ordenDescendente = false; // Cambiar a false para orden ascendente o true para orden descendente

		// Genera un array aleatorio de tamaño modificable con números entre 0 y 100
		tamaño = 100;
		array = new int[tamaño];
		for (int i = 0; i < tamaño; i++) {
			array[i] = (int) (Math.random() * 100);
		}

		System.out.println("Iniciando ordenamiento concurrente...");

		// Muestra el array original si tiene 100 elementos o menos
		if ((esAleatorio && array.length <= 50) || (!esAleatorio && imprimirArray)) {
			System.out.println("Array original:");
			for (int num : array) {
				System.out.print(num + " ");
			}
			System.out.println();
		} else {
			System.out.println("Array original omitido por ser demasiado grande.");
		}

		// Registra el tiempo de inicio del ordenamiento
		long start = System.currentTimeMillis();

		// Crea y ejecuta el hilo principal para ordenar el array
		QuickSortConcurrente quickSort = new QuickSortConcurrente(array, 0, tamaño - 1, 4, ordenDescendente);
		quickSort.start();
		try {
			quickSort.join(); // Espera que termine el hilo principal
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Registra el tiempo final del ordenamiento
		long end = System.currentTimeMillis();

		// Muestra el array ordenado si es suficientemente pequeño
		if (array.length <= 50) {
			System.out.println("Array ordenado:");
			for (int num : array) {
				System.out.print(num + " ");
			}
			System.out.println();
		} else {
			System.out.println("Array ordenado omitido por ser demasiado grande.");
		}

		// Imprime métricas finales
		System.out.println("Tiempo de ejecución (concurrente): " + (end - start) + " ms");
		System.out.println("¿El array está ordenado?: " + estaOrdenado(array, ordenDescendente));
		System.out.println("Tamaño del array: " + array.length);
	}
}
