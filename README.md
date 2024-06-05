# POO - Examen Final

El Instituto Tecnológico de Morelia se encuentra en búsqueda de un nuevo sistema el cual pueda llevar toda la gestión de la escuela, lo cual incluye gestionar alumnos, maestros, administrativos, grupos, etc.

Debido a esto, el Instituto ha tomado la decisión de realizar una licitación. Una licitación un proceso formal donde una organización (el comprador) emite una solicitud para que varios proveedores (los competidores) presenten propuestas para desarrollar un software o realizar un proyecto específico.

Por esto, tu y tu equipo de desarrolladores han tomado la decisión de participar en este proceso de desarrollo para el nuevo sistema ***“Mindbox”*** que usará el Instituto Tecnológico de Morelia.

Los requerimientos que se ocupan para desarrollar este nuevo proyecto se describen a continuación, por lo que tu y tu equipo tendrán que analizar, comprender, realizar el diagrama **UML** correspondiente y desarrollar este nuevo producto. El fecha límite para la entrega de este producto es el día 2 ***de junio del 2024***, por lo que deberá estar listo para esa fecha ***sin errores o fallas***.

# Requerimientos principales  🏁

- El Instituto ocupa que al momento de iniciarse el programa por ***primera vez,*** es decir, ***cuando no se ha ejecutado ninguna vez,*** tenga cargadas las 3 **carreras** que se describen posteriormente. Es importante mencionar que a partir de la segunda vez que se ejecute el programa, ya debe de tener cargados los cambios que se pudieran haber hecho anteriormente.
- Toda la información debe de guardarse en archivos **JSON** para que se tenga siempre un registro de la información.
- Deben de existir ya inicialmente 1 coordinador por carrera cuando el sistema inicia.
- El Instituto necesita que el sistema no tenga fallas por lo que ha solicitado que se usen validaciones y manejo de excepciones.

# Carrera 📒

El sistema contará con 3 carreras.

- Ingeniería en Sistemas Computaciones
- Ingeniería en Materiales
- Ingeniería Electrónica

Las abreviaciones de las carreras son las siguientes:

- Sistemas - ISC
- Materiales - IMAT
- Electrónica - ELC

Los atributos que tiene una carrera son los siguientes

| Id | Nombre carrera | Cantidad de grupos |
| --- | --- | --- |
| Cantidad de alumnos | Cantidad de materias | Fecha de creación |
| Coordinador |  |  |

# Semestre 📕

Se van a contemplar 3 semestres únicamente, el sistema debe de contar con una opción para que el coordinador avance un grupo de semestre, al momento de que avanza de semestre se le asignarán las nuevas materias del siguiente semestre a todo el grupo.

**Ejemplo:**

1. El grupo A de 1 semestre de la carrera de Sistemas tiene 3 materias; Matemáticas 1, Programación 1 y Cálculo 1, cada alumno del grupo tiene su calificación en las respectivas materias, si el coordinador avanza el semestre de ese grupo, ese grupo pasará al semestre 2 y tendrá ahora las materias de Matemáticas 2, Programación 2 y Cálculo 2, donde ahora no tendrán calificaciones registradas.
2. Si un grupo se encuentra en el 3 semestre, y avanza de semestre, pasará a la sección de ***graduados.***

**Considerar lo siguiente:**

1. Si el sistema detecta que un alumno reprobó alguna de sus materias, dejarlo en el semestre y grupo que está y removerle sus calificaciones anteriores.
2. Si algún alumno no tiene todas sus calificaciones registradas no se podrá avanzar de semestre.

Los atributos del semestre serán los siguientes:

| Id | Número de semestre | Carrera |
| --- | --- | --- |
| Grupos |  |  |

# Graduados 🎓

Será un apartado que tendrá el registro de los alumnos que ya se graduaron. Se almacenarán los siguientes datos:

| Fecha graduación | Carrera | Alumnos (Incluir también el promedio con el que se graduaron) |
| --- | --- | --- |
| Generación de graduación. Ejemplo: Ene - Jun 2024, Ago - Dic 2024, etc. |  |  |

# Grupo 📗

Un grupo será la entidad que contendrá a los alumnos, es importante señalar que un grupo debe de tener mínimo 3 estudiantes y un máximo de 20 estudiantes. Debe de existir un grupo por carrera, siempre se comenzará con el grupo ***A***, y máximo 2 grupos, que sería el ***B***.

Un grupo debe de contener lo siguiente:

| Carrera al que pertenece | Alumnos | Tipo de grupo; A o B |
| --- | --- | --- |
| Id | Materias que tiene | Semestre |

# Materia 📙

Las materias van a depender del semestre y de la carrera. Cada materia debe de contener lo siguiente:

| Id | Carrera a la que pertenece | Grupo | Profesor que la imparte |
| --- | --- | --- | --- |

Esta información se describe a continuación:

| Semestre | Carrera | Materia 1 | Materia 2 | Materia 3 |
| --- | --- | --- | --- | --- |
| 1 | Sistemas | Programación 1 | Probabilidad 1 | Cálculo 1 |
| 2 | Sistemas | Programación 2 | Probabilidad 2 | Cálculo 2 |
| 3 | Sistemas | Programación 3 | Probabilidad 2 | Cálculo 2 |

| Semestre | Carrera | Materia 1 | Materia 2 | Materia 3 |
| --- | --- | --- | --- | --- |
| 1 | Materiales | Estadística 1 | Contabilidad 1 | Cálculo 1 |
| 2 | Materiales | Estadística 2 | Contabilidad 2 | Cálculo 2 |
| 3 | Materiales | Estadística 3 | Contabilidad 3 | Cálculo 2 |

| Semestre | Carrera | Materia 1 | Materia 2 | Materia 3 |
| --- | --- | --- | --- | --- |
| 1 | Electrónica | Redes 1 | Circuitos 1 | Cálculo 1 |
| 2 | Electrónica | Redes 2 | Circuitos 2 | Cálculo 2 |
| 3 | Electrónica | Redes 3 | Circuitos 3 | Cálculo 2 |

# Calificaciones 📖

Las calificaciones podrán ser registradas únicamente por el profesor que imparte la materia o el coordinador, y podrá seleccionar al alumno e ingresarle su calificación, esta puede ser aprobatoria (arriba de 70) o reprobatoria.

Cuando un profesor o coordinador desee consultar las calificaciones, podrá filtrar los alumnos, aprobados, reprobados o todos. (Por grupo, materia y semestre)

Además, los alumnos podrán ver toda la información de sus calificaciones, separadas por semestre y materia.

Una vez que el semestre se ha avanzado, las calificaciones no podrán modificarse.

# Historial 📋

Se ocupa que los alumnos puedan visualizar su información anterior aún cuando cambien de semestre, podrán ver todo el registro desde el semestre 1 hasta que se gradúen, esto incluye lo siguiente:

- Materias cursadas por semestre
- Calificaciones de sus materias
- Profesores que les impartieron las materias
- Promedio por semestre

Está información ***también podrá ser consultada por el coordinador de la carrera***.

# Roles ✅

El sistema va a contar con los 3 roles siguientes.

- Alumno
- Trabajador
    - Profesor
    - Coordinador de carrera (puede ser también profesor)

# Alumno 👨🏽‍🎓

Un alumno podrá ser dado de alta únicamente por el coordinador de carrera, todos los alumnos al momento de realizar su registro, tendrán que comenzar en el semestre 1 de su carrera y pertenecer a un grupo.

Cuando se registre un alumno se le generará su número de control con el siguiente formato:

- l - {Primera letra de su nombre} - {Primero dos dígitos del año} - {Abreviación carrera} - {indice}
- Ejemplo:
    - **lE24ISC0**

Este rol podrá ver todo lo que un ***“alumno”*** puede hacer en un sistema de gestión escolar.

La información requerida es la siguiente:

| Nombre | Apellidos | Año de nacimiento |
| --- | --- | --- |
| Ciudad | Estado | CURP (Crear un algoritmo para generarlo automáticamente de acuerdo a la información del alumno) |
| Dirección | Fecha de registro | Carrera |
| Semestre | Grupo | Promedio |
| Número de control |  |  |

# Profesor 👨🏽‍🏫

El profesor podrá ser dado de alta únicamente por cualquier coordinador y podrá impartir más de 1 materia en cualquier carrera.

Este rol únicamente podrá ver su información, actualizarla, ver sus grupos, asignar calificaciones, modificarlas, etc.

Cuando se registre un alumno se le generará su número de control con el siguiente formato:

- M - {Primera letra de su nombre} - {Primero dos dígitos del año} - {Abreviación carrera} - {indice}
- Ejemplo:
    - **ME24ISC0**

La información requerida es la siguiente:

| Nombre | Apellidos | Año de nacimiento |
| --- | --- | --- |
| Ciudad | Estado | CURP (Crear un algoritmo para generarlo automáticamente de acuerdo a la información del profesor) |
| Dirección | Fecha de registro | RFC (Crear un algoritmo para generarlo automáticamente de acuerdo a la información del profesor) |
| Materias que imparte | Sueldo | Número de control |

# Coordinador 🧑🏽‍🏫

El coordinador deberá ya de existir cuando se cargue el sistema, el podrá también impartir 1 o más clases en cualquier carrera.

Este rol podrá hacer todo únicamente en la carrera en la cual esté adscrito, de igual forma, será el único que podrá avanzar el semestre.

Cuando se registre un alumno se le generará su número de control con el siguiente formato:

- C - {Primera letra de su nombre} - {Primero dos dígitos del año} - {Abreviación carrera} - {indice}
- Ejemplo:
    - **CE24ISC0**

La información requerida es la siguiente:

| Nombre | Apellidos | Año de nacimiento |
| --- | --- | --- |
| Ciudad | Estado | CURP (Crear un algoritmo para generarlo automáticamente de acuerdo a la información del profesor) |
| Dirección | Fecha de registro | RFC (Crear un algoritmo para generarlo automáticamente de acuerdo a la información del profesor) |
| Materias que imparte | Sueldo |  |

# Notas 📒

- ***Equipos de 5 personas.***
- Cada usuario debe contar con su propio **usuario y contraseña**.
- Cada usuario tendrá la opción de poder visualizar, modificar, eliminar, crear, de acuerdo al rol que tienen y lo que pueden hacer.
- Realizar todas las validaciones correspondientes, ej. no ID’s duplicados, que existan objetos antes de crear relaciones, que si se eliminan objetos no rompan otras relaciones, etc.
- Usar try catch para **manejo de excepciones.**
- **Guardar todo en JSON** **y leerlo de ahí**. Se revisará el JSON y se verá si hace ***match*** con lo que ocurre en el sistema. De igual forma, si se cierra el programa y se abre de nuevo, que tenga la información del JSON cargada.
- Subir el código a una rama llamada **examen/unidad4**
- En los **README** de los proyectos poner la descripción del examen y los integrantes.
- Subir los integrantes en el excel **“EquiposExamen4”** que se encuentra en el grupo de Teams.
- Realizar los **CRUD** correspondientes
