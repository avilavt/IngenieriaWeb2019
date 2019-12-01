
Cliente Rest que contiene:

* Entidades de la base de datos:
  * Comentario
  * Tablon 
  * Usuario
  
* Clase A1ClientRestUsuario.java, con los métodos:
  * Consultas:
    * **read()**: que imprime por pantalla todos los usuarios y devuelve el *id* del último usuario.
    * **findById(String idUsuario)**: que devuelve un ***Usuario*** con el id que se pasa por parámetro.
    * **findByRole(String role)**: que devuelve una lista de ***Usuarios*** que tienen asignado el rol pasado por parámetro.
    * **findByName(String patron)**: que devuelve una lista de ***Usuarios*** cuyo nombre contiene el patrón pasado por parámetro.
    * **lastId()**: que devuelve el *id* del último usuario.
  * ***create(String newName, String newEmail, String newRole)***: que permite la creación de un nuevo usuario, con nombre *newName*, correo electrónico *newEmail* y rol "newRole*.
  * ***update(String idUsuario, String newName)***: modificar el nombre del **Usuario** con *id* igual a *idUsuario* con el nombre *newName*.
  * ***delete(String idUsuario)***: borra el **Usuario** cuya *id* es igual a *idUsuario*.
