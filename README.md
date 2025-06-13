# QuickSort Concurrente vs Secuencial

Este proyecto implementa y compara dos versiones del algoritmo de ordenamiento **QuickSort**: una versión **secuencial** y una versión **concurrente utilizando hilos (`Thread`)** en Java.

## 📌 Objetivo

Comparar el rendimiento entre ambas versiones y entender cómo la concurrencia puede mejorar (o no) el tiempo de ejecución en diferentes escenarios, dependiendo del tamaño del array y la profundidad de concurrencia permitida.

## 🧩 Contenido

- `QuickSortSecuencial.java`: Implementación secuencial del algoritmo QuickSort.
- `QuickSortConcurrente.java`: Implementación concurrente del mismo algoritmo utilizando hilos.

## 🧠 Descripción

Ambas implementaciones:
- Generan un array de números aleatorios.
- Lo ordenan en **forma ascendente o descendente** según una bandera booleana (`ordenDescendente`).
- Miden el tiempo de ejecución en milisegundos.
- Verifican si el array quedó correctamente ordenado.
- Imprimen los datos si el array tiene 50 elementos o menos.

## 🧪 Cómo ejecutar

Compilá los archivos en terminal:

```bash
javac QuickSortSecuencial.java
javac QuickSortConcurrente.java
