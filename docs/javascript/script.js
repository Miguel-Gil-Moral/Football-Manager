document.addEventListener("DOMContentLoaded", function () {
    const equipos = ["Seleccione un equipo", "FC Barcelona", "Real Madrid", "Atletico de Madrid", "Sevilla FC", 
        "Real Sociedad", "Real Betis", "Athletic Club", "Villarreal CF", "Valencia CF", "RC Celta de Vigo", 
        "RCD Mallorca", "Girona FC", "CA Osasuna", "Getafe CF", "UD Las Palmas", "RCD Espanyol", 
        "CD Leganés", "Real Valladolid CF", "Rayo Vallecano", "Deportivo Alavés"];
    const valueEquipos = ["defecto", "barcelona", "real-madrid", "atletico-madrid", "sevilla", "real-sociedad", "betis", 
        "athletic", "villarreal", "valencia", "celta", "mallorca", "girona", "osasuna", "getafe", "las-palmas", 
        "espanyol", "leganes", "valladolid", "rayo", "alaves"];

    let listaEquipos = document.getElementById("slctEquipos");
    
    for (let i = 0; i < equipos.length; i++) {
        let opcion = document.createElement("option");
        opcion.value = valueEquipos[i];
        opcion.textContent = equipos[i];
        if (i == 0) {
            opcion.selected;
        }

        listaEquipos.appendChild(opcion);
    }

    let jugadorRadio = document.getElementById("jugador");
    let entrenadorRadio = document.getElementById("entrenador");

    jugadorRadio.addEventListener("change", function () {
        let contenedorJugador = document.getElementById("inputs_jugador");

        let label = document.createElement("label");
        label.textContent = "Posición";
        label.htmlFor = "slctPosicion";
        contenedorJugador.appendChild(label);

        let select = document.createElement("select");
        select.id = "slctPosicion";
        
        const opciones = ["Seleccione una posición", "Portero", "Defensa", "Mediocampista", "Delantero"];
        const opcionesValue = ["defecto", "portero", "defensa", "mediocampista", "delantero"];

        for (let i = 0; i < opciones.length; i++) {
            let option = document.createElement("option");
            option.value = opcionesValue[i];
            option.textContent = opciones[i];
            if (i == 0) {
                option.selected;
            }

            select.appendChild(option);
        }

        contenedorJugador.appendChild(select);
    });

    entrenadorRadio.addEventListener("change", function () {
        let contenedorJugador = document.getElementById("inputs_jugador");
        contenedorJugador.replaceChildren();
    });
});