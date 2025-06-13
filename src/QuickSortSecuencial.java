/**
 * Versión secuencial del algoritmo QuickSort en Java.
 * Este código está diseñado para ser comparado con su equivalente concurrente.
 */

public class QuickSortSecuencial {

    // Método para intercambiar dos elementos del array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i]; // Guarda el valor de la posición i
        array[i] = array[j]; // Coloca el valor de j en i
        array[j] = temp; // Restaura el valor de i en j
    }

    // Método que organiza el subarray según un pivote y retorna la posición del pivote
    private static int partition(int[] array, int low, int high, boolean descendente) {
        int pivot = array[high]; // Se toma el último elemento como pivote
        int i = (low - 1); // Inicializa el índice de menor elemento

        for (int j = low; j <= high - 1; j++) {
            // Cambia la comparación según tipo de orden
            if ((descendente && array[j] > pivot) || (!descendente && array[j] < pivot)) {
                i++;
                swap(array, i, j); // Intercambio si corresponde
            }
        }

        swap(array, i + 1, high); // Coloca el pivote en su posición final
        return i + 1; // Retorna el índice del pivote
    }

    // Método principal del QuickSort secuencial
    public static void quickSort(int[] array, int low, int high, boolean descendente) {
        if (low < high) {
            int pi = partition(array, low, high, descendente); // Particiona el array
            quickSort(array, low, pi - 1, descendente); // Ordena lado izquierdo
            quickSort(array, pi + 1, high, descendente); // Ordena lado derecho
        }
    }

    // Método para verificar si el array quedó ordenado
    public static boolean estaOrdenado(int[] array, boolean descendente) {
        for (int i = 1; i < array.length; i++) {
            if ((descendente && array[i - 1] < array[i]) || (!descendente && array[i - 1] > array[i])) {
                return false;
            }
        }
        return true;
    }

    // Método main para probar el algoritmo
    public static void main(String[] args) {
        int[] array = new int[10]; // Tamaño configurable
        boolean ordenDescendente = false; // Cambiar a true para orden descendente o false para ascendente

        // Genera un array aleatorio
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100); // Valores aleatorios entre 0-999
        }

        System.out.println("Iniciando ordenamiento secuencial...");

        // Muestra el array original si es pequeño
        if (array.length <= 50) {
            System.out.println("Array original:");
            for (int num : array) {
                System.out.print(num + " ");
            }
            System.out.println();
        } else {
            System.out.println("Array original omitido por ser demasiado grande.");
        }

        long start = System.currentTimeMillis(); // Tiempo de inicio
        quickSort(array, 0, array.length - 1, ordenDescendente); // Llama al algoritmo de ordenamiento
        long end = System.currentTimeMillis(); // Tiempo de fin

        // Muestra el array ordenado si es pequeño
        if (array.length <= 50) {
            System.out.println("Array ordenado:");
            for (int num : array) {
                System.out.print(num + " ");
            }
            System.out.println();
        } else {
            System.out.println("Array ordenado omitido por ser demasiado grande.");
        }

        // Resultados
        System.out.println("Tiempo de ejecución (secuencial): " + (end - start) + " ms");
        System.out.println("¿El array está ordenado?: " + estaOrdenado(array, ordenDescendente));
        System.out.println("Tamaño del array: " + array.length);
    }
}
