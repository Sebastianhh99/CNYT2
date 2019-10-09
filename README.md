# CNYT

>Sebastian Herrera Hernandez\
>CNYT 2019-2 Grupo 2\
>Proyecto Computacion cuantica
## Funcionalidades

La librería soporta las siguientes operaciones entre números complejos:

### Calculadora de números complejos

*   Suma.
*   Producto.
*   Resta.
*   División.
*   Módulo.
*   Conjugado.
*   Conversión entre representaciones polar y cartesiano.
*   Retornar la fase de un número complejo.

### Calculadora de matrices complejas

*   Suma de vectores complejos.
*   Inverso aditivo de vector complejo.
*   Multiplicación de escalar por vector complejo.
*   Suma de matrices complejas.
*   Inverso aditivo de matriz compleja.
*   Multiplicación de escalar por matriz compleja.
*   Transpuesta de matriz compleja.
*   Conjugada de matriz compleja.
*   Adjunta (daga) de matriz compleja .
*   Producto de matrices complejas.
*   Acción de matriz compleja sobre vector complejo.
*   Producto interno de vectores complejos.
*   Norma de vector complejo.
*   Distancia entre dos vectores complejos.
*   ¿Es la matriz compleja una matriz unitaria?.
*   ¿Es la matriz compleja una matriz hermitiana?.
*   Producto tensorial de matrices complejas.

### El sato de lo clásico a lo cuántico


#### 1. Simulador de sistemas determinísticos, estocásticos y cuánticos; junto con su dinámica. 
* Recibe como parámetros:
  * Tipo de sistema.
  * Matriz M.
  * Vector de estado inicial.
  * N° de clicks de tiempo.

* Retorna:
  * Validez de la matriz, según el parámetro de la dinámica ingresada. (Dinámica clásica ó dinámica doblemente estocástica ó dinámica cuántica).
  * Matriz M^t
  * Vector de estado final.

#### 2. Función que ensambla sistemas y sus dinámicas.

* Recibe como parámetros:
  * Matriz 1.
  * Vector 1.
  * Matriz 2.
  * Vector 2.
  * N° de clicks de tiempo.

* Retorna:
  * Vector de estado final.

#### 3. Función que realiza la simulación del experimento de las rendijas. 

* Recibe como parámetros:
  * Número de rendijas.
  * Número de blancos por pared.
  * Vector de probabilidades.
  * Tipo de sistema al que corresponde el vector de probabilidades (clásico, estocástico o cuántico).

* Retorna:
  * Matriz asociada al sistema (al grafo).
  * Matriz M^2
  * Vector de estado final, y su resultado es mostrado gráficamente en un diagrama de barras 2D.
