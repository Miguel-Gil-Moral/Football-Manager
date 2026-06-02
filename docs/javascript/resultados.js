document.addEventListener("DOMContentLoaded", function () {
    const boton = document.getElementById("botonResultados");
    let headerDiv = document.getElementById("encabezado_resultados");

    pillarResultados("./json/FM_partits_masc.json");

    boton.addEventListener("click", function () {
        if (boton.textContent == "Ver resultados femenino") {
            boton.textContent = "Ver resultados masculino";
            headerDiv.textContent = "Resultados femenino";
            pillarResultados("./json/FM_partits_fem.json")
        } else {
            boton.textContent = "Ver resultados femenino";
            headerDiv.textContent = "Resultados masculino";
            pillarResultados("./json/FM_partits_masc.json");
        }
    });

    function pillarResultados(rutaArchivo) {
        fetch(rutaArchivo)
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("El archivo JSON no se ha podido cargar");
                }
                return response.json();
            })
            .then(function (resultados) {
                let contenedor = document.getElementById("partidos");
                contenedor.replaceChildren();

                for (let i = 0; i < resultados.length; i++) {
                    let resultadoEquipos = resultados[i];

                    let enfrentamiento = document.createElement("div");
                    enfrentamiento.className = "enfrentamiento";

                    let horario = document.createElement("div");
                    horario.className = "horario";

                    let fecha = document.createElement("p");
                    let fechaPartido = resultadoEquipos.data;
                    fecha.textContent = fechaPartido.substring(0, 10);
                    fecha.className = "fechaResultados";
                    horario.appendChild(fecha);

                    let hora = document.createElement("p");
                    hora.textContent = fechaPartido.slice(12)
                    horario.appendChild(hora);

                    enfrentamiento.appendChild(horario);

                    let resultadoPartido = document.createElement("div");
                    resultadoPartido.className = "resultadoPartido";

                    //Equipo Local

                    let equipoLocal = document.createElement("div");
                    equipoLocal.className = "equipoResultado";

                    let escudoLocal = document.createElement("img");
                    escudoLocal.src = resultadoEquipos.equip_local.escut;
                    escudoLocal.alt = "Escudo" + resultadoEquipos.equip_local.nom;

                    let nombreLocal = document.createElement("h3");
                    nombreLocal.textContent = resultadoEquipos.equip_local.nom;

                    equipoLocal.appendChild(escudoLocal);
                    equipoLocal.appendChild(nombreLocal);

                    //Equipo Visitante

                    let equipoVisitante = document.createElement("div");
                    equipoVisitante.className = "equipoResultado";

                    let escudoVisitante = document.createElement("img");
                    escudoVisitante.src = resultadoEquipos.equip_visitant.escut;
                    escudoVisitante.alt = "Escudo" + resultadoEquipos.equip_visitant.nom;

                    let nombreVisitante = document.createElement("h3");
                    nombreVisitante.textContent = resultadoEquipos.equip_visitant.nom;

                    equipoVisitante.appendChild(nombreVisitante);
                    equipoVisitante.appendChild(escudoVisitante);

                    //Puntos

                    let puntosResultado = document.createElement("p");
                    puntosResultado.textContent = resultadoEquipos.resultat;
                    puntosResultado.className = "puntosResultado";

                    //Append

                    resultadoPartido.appendChild(equipoLocal);
                    resultadoPartido.appendChild(puntosResultado);
                    resultadoPartido.appendChild(equipoVisitante);

                    enfrentamiento.appendChild(resultadoPartido);
                    contenedor.appendChild(enfrentamiento);
                }
            })
            .catch(function (error) {
                console.error("Esto es un error: ", error);
            });
    }
});