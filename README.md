## Programa de intercambio de datos entre columnas de una tabla

### Descripción
El programa establece una conexion a una base de datos, segun las credenciales parametrizadas en los atributos de la clase. Luego, busca en una tabla parametrizada, el campo parametrizado para dos registros, e intercamia los valores de esos campos para ambos registros. Finalmente, cierra la conexion a la base de datos.

## El código cumple con las siguientes características:

1- Reutilización y modularidad: El código está diseñado para ser reutilizable y modular. Los nombres de la tabla y la columna, así como los IDs de las filas, son parámetros que pueden ser fácilmente modificados para adaptarse a diferentes situaciones.

2- Manejo de errores: Se ha implementado un manejo de errores más robusto utilizando excepciones de SQL. Esto asegura que cualquier error durante la conexión, la ejecución de consultas SQL o el cierre de la conexión sea manejado adecuadamente y proporcione información útil sobre lo que ha salido mal.

3- Claridad y legibilidad: El código está organizado de manera que sea fácil de entender y modificar. Los nombres de métodos y variables son descriptivos, lo que facilita la comprensión de lo que hace cada parte del programa.

4- Seguridad: Se utiliza PreparedStatement en lugar de Statement para protegerse contra ataques de inyección SQL.

## Repositorio

[Repositorio en GitHub](https://github.com/Ernestolop/IntercambioJava)