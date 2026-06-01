document.addEventListener("DOMContentLoaded", function () {
    const boton = document.getElementById('botonClasificacion');
    extraerTablaMasculina();

    boton.addEventListener("click", function () {
        if (boton.textContent == "Ver femenino") {
            boton.textContent = "Ver masculino";
            extraerTablaFemenina();
        } else {
            boton.textContent = "Ver femenino";
            extraerTablaMasculina();
        }
    });

    function extraerTablaMasculina() {
        fetch("./json/clasificacion_masc.json")
            .then(function (response) {
                if(!response.ok) {
                    throw new Error("El archivo JSON no se ha podido cargar");
                }
                return response.json();
            })
            .then(function (clasificacion) {
                const headerDiv = document.getElementById("encabezado_clasificacion");
                headerDiv.textContent = "Clasificación Masculino";

                let tabla = document.getElementById("tablaClasificacion");
                tabla.replaceChildren();
                headerTabla(tabla)
                
                let posicion = 1;

                for (let i = 0; i < clasificacion.length; i++) {
                    let fila = document.createElement("tr");
                    let resultadoPartidos = clasificacion[i];

                    let posicionEquipo = document.createElement("td");
                    posicionEquipo.textContent = posicion + " º";
                    fila.appendChild(posicionEquipo);

                    let escudoEquipo = document.createElement("td");
                    let escudo = document.createElement("img");
                    escudo.src = resultadoPartidos.escut;
                    escudo.alt = "Escudo " + resultadoPartidos.nom;
                    escudo.classList.add("imagenClasificacion");
                    escudoEquipo.appendChild(escudo);
                    fila.appendChild(escudoEquipo);

                    let nombreEquipo = document.createElement("td");
                    nombreEquipo.textContent = resultadoPartidos.nom;
                    fila.appendChild(nombreEquipo);

                    let puntos = document.createElement("td");
                    puntos.textContent = resultadoPartidos.clasificacio.punts;
                    fila.appendChild(puntos);

                    let partidos_jugados = document.createElement("td");
                    partidos_jugados.textContent = resultadoPartidos.clasificacio.partits_jugats;
                    fila.appendChild(partidos_jugados);

                    let golesFavor = document.createElement("td");
                    golesFavor.textContent = resultadoPartidos.clasificacio.gols_favor;
                    fila.appendChild(golesFavor);

                    let golesContra = document.createElement("td");
                    golesContra.textContent = resultadoPartidos.clasificacio.gols_contra;
                    fila.appendChild(golesContra);

                    posicion++;
                    tabla.appendChild(fila);
                }
            })
            .catch(function (error) {
                console.error("Esto es un error: ", error);
            });
    }

    function headerTabla(tabla) {
        const fila = document.createElement('tr');

        let posicionHeader = document.createElement('th');
        posicionHeader.textContent = "Posición";
        fila.appendChild(posicionHeader);

        let escudoHeader = document.createElement('th');
        escudoHeader.textContent = "Escudo";
        fila.appendChild(escudoHeader);

        let nombreHeader = document.createElement('th');
        nombreHeader.textContent = "Nombre Equipo";
        fila.appendChild(nombreHeader);

        let puntosHeader = document.createElement('th');
        puntosHeader.textContent = "Puntos";
        fila.appendChild(puntosHeader);

        let partidosJugadosHeader = document.createElement('th');
        partidosJugadosHeader.textContent = "Partidos Jugados";
        fila.appendChild(partidosJugadosHeader);

        let golesFavorHeader = document.createElement('th');
        golesFavorHeader.textContent = "Goles a favor";
        fila.appendChild(golesFavorHeader);

        let golesContraHeader = document.createElement('th');
        golesContraHeader.textContent = "Goles en contra";
        golesContraHeader.classList.add("ultimaCelda");
        fila.appendChild(golesContraHeader);

        tabla.appendChild(fila);
    }

    function extraerTablaFemenina() {
        fetch("./json/clasificacion_fem.json")
            .then(function (response) {
                if(!response.ok) {
                    throw new Error("El archivo JSON no se ha podido cargar");
                }
                return response.json();
            })
            .then(function (clasificacion) {
                const headerDiv = document.getElementById("encabezado_clasificacion");
                headerDiv.textContent = "Clasificación Femenino";

                let tabla = document.getElementById("tablaClasificacion");
                tabla.replaceChildren();
                headerTabla(tabla)
                
                let posicion = 1;

                for (let i = 0; i < clasificacion.length; i++) {
                    let fila = document.createElement("tr");
                    let resultadoPartidos = clasificacion[i];

                    let posicionEquipo = document.createElement("td");
                    posicionEquipo.textContent = posicion + " º";
                    fila.appendChild(posicionEquipo);

                    let escudoEquipo = document.createElement("td");
                    let escudo = document.createElement("img");
                    escudo.src = resultadoPartidos.escut;
                    escudo.alt = "Escudo " + resultadoPartidos.nom;
                    escudo.classList.add("imagenClasificacion");
                    escudoEquipo.appendChild(escudo);
                    fila.appendChild(escudoEquipo);

                    let nombreEquipo = document.createElement("td");
                    nombreEquipo.textContent = resultadoPartidos.nom;
                    fila.appendChild(nombreEquipo);

                    let puntos = document.createElement("td");
                    puntos.textContent = resultadoPartidos.clasificacio.punts;
                    fila.appendChild(puntos);

                    let partidos_jugados = document.createElement("td");
                    partidos_jugados.textContent = resultadoPartidos.clasificacio.partits_jugats;
                    fila.appendChild(partidos_jugados);

                    let golesFavor = document.createElement("td");
                    golesFavor.textContent = resultadoPartidos.clasificacio.gols_favor;
                    fila.appendChild(golesFavor);

                    let golesContra = document.createElement("td");
                    golesContra.textContent = resultadoPartidos.clasificacio.gols_contra;
                    fila.appendChild(golesContra);

                    posicion++;
                    tabla.appendChild(fila);
                }
            })
            .catch(function (error) {
                console.error("Esto es un error: ", error);
            });
    }
});