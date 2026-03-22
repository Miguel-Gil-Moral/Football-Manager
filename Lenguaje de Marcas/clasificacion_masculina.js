document.addEventListener("DOMContentLoaded", function () {
    fetch("./FM_partits_masc.json")
        .then(function (response) {
            if(!response.ok) {
                throw new Error("El archivo JSON no se ha podido cargar");
            }
            return response.json();
        })
        .then(function (clasificacion) {
            let contenedor = document.getElementById("clasificacionMasculino");

            for (let i = 0; i < clasificacion.length; i++) {
                let partido = clasificacion[i];

                let clasificacionEquipo = document.createElement("div");
                clasificacionEquipo.className = "clasificacionEquipos";
                let nombreEquipoLocal = document.createElement("div");
                nombreEquipoLocal.className = "nombreEquipo";
                let nombreEquipoVisitante = document.createElement("div");
                nombreEquipoVisitante.className = "nombreEquipo";

                //Equipo Local
                let escudoEquipoLocal = document.createElement("img");
                escudoEquipoLocal.src = partido.equip_local.escut;
                escudoEquipoLocal.className = "escudoEquipo";
                nombreEquipoLocal.appendChild(escudoEquipoLocal);

                let equipoLocal = document.createElement("p");
                equipoLocal.textContent = partido.equip_local.nom;
                nombreEquipoLocal.appendChild(equipoLocal);

                clasificacionEquipo.appendChild(nombreEquipoLocal);
                contenedor.appendChild(clasificacionEquipo);

                //Equipo Visitante
                let escudoEquipoVisitante = document.createElement("img");
                escudoEquipoVisitante.src = partido.equip_visitant.escut;
                escudoEquipoVisitante.className = "escudoEquipo";
                nombreEquipoVisitante.appendChild(escudoEquipoVisitante);

                let equipoVisitante = document.createElement("p");
                equipoVisitante.textContent = partido.equip_visitant.nom;
                nombreEquipoVisitante.appendChild(equipoVisitante);

                clasificacionEquipo.appendChild(nombreEquipoVisitante);
                contenedor.appendChild(clasificacionEquipo);

                //Puntos
                /*
                let resultados = clasificacion.resultat;

                let golesLocal = parseInt(resultados[0]);
                let golesVisitante = parseInt(resultados[2]);

                let puntosLocal = 0;
                let puntosVisitante = 0;

                if (golesLocal > golesVisitante) {
                    puntosLocal = 3;
                } else if (golesLocal < golesVisitante) {
                    puntosVisitante = 3;
                } else {
                    puntosLocal = 1;
                    puntosVisitante = 1;
                }
                     */
            }
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
})