<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<!-- ⚠️ Només s'ha de realitzar el disseny del formulari. NO enviarem cap informació a la base de dades! ⚠️

El formulari que preparerem per poder donar d'alta jugadors i entrenadors a la base de dades ha de contenir la següent informació:

Nom, alçada, pes i data de naixement del jugador.

Fotografia.

S'ha de poder indicar si es tracta d'un  jugador o d'un entrenador.

Només en el cas dels jugadors, s'ha de poder indicar de quina posició o posicions juga (defensa, davanter, migcampista, porter). Aquesta opció no ha d'aparèixer si seleccionem que es tracta d'un entrenador.

Assignarem el jugador / entrenador a un equip a través d'un desplegable. Aquest desplegable s'haurà d'omplir a través de JavaScript. Creeu una llista amb tots els equips i el codi necessari per crear totes les opcions del desplegable a partir d'aquesta llista.

🔎 Trieu el tipus de camp més adient per a cada cas i afegiu les restriccions adequades a través de l'HTML. -->
    <form action="">
        <label for="txtNombre">Nombre</label>
        <input type="text" name="txtNombre" id="txtNombre">
        <br>
        <label for="numAltura">Altura</label>
        <input type="number" name="numAltura" id="numAltura">
        <br>
        <label for="numPeso">Peso</label>
        <input type="number" name ="numPeso" id="numPeso">
        <br>
        <label for="fechaNacimiento">Fecha Nacimiento</label>
        <input type="date" name="fechaNacimiento" id="fechaNacimiento">
        <br>
</body>
</html>