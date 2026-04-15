document.addEventListener("DOMContentLoaded", function () {
    fetch("./json/clasificacion_masc.json")
        .then(function (response) {
            if(!response.ok) {
                throw new Error("El archivo JSON no se ha podido cargar");
            }
            return response.json();
        })
        .then(function (clasificacion) {
            let contenedor = document.getElementById("clasificacionMasculino");
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
    let boton = document.getElementsByTagName("button");
    boton.addEventListener("click", function () {
        if (boton.textContent === "Ver femenino") {
            let divClasificacion = document.getElementById("clasificacionMasculino");

            divClasificacion.id.toogle("clasificacionFemenino");
            boton.textContent = "Ver masculino";
        } else {
            let divClasificacion = document.getElementById("clasificacionFemenino");

            divClasificacion.id.toogle("clasificacionMasculino")
            boton.textContent = "Ver femenino";
        }
    })
});
/*document.addEventListener("DOMContentLoaded", function () {
    fetch("./json/FM_partits_masc.json")
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

                let clasificacionEquipoLocal = document.createElement("div");
                clasificacionEquipoLocal.className = "clasificacionEquipos";
                let clasificacionEquipoVisitante = document.createElement("div");
                clasificacionEquipoVisitante.className = "clasificacionEquipos";
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

                //Equipo Visitante
                let escudoEquipoVisitante = document.createElement("img");
                escudoEquipoVisitante.src = partido.equip_visitant.escut;
                escudoEquipoVisitante.className = "escudoEquipo";
                nombreEquipoVisitante.appendChild(escudoEquipoVisitante);

                let equipoVisitante = document.createElement("p");
                equipoVisitante.textContent = partido.equip_visitant.nom;
                nombreEquipoVisitante.appendChild(equipoVisitante);

                //Puntos
                let puntosLocalDiv = document.createElement("div");
                puntosLocalDiv.className = "puntosDiv";
                let puntosVisitanteDiv = document.createElement("div");
                puntosVisitanteDiv.className = "puntosDiv";

                let resultados = partido.resultat;

                let golesLocal = document.createElement("p");
                let golesVisitante = document.createElement("p");

                golesLocal.textContent = parseInt(resultados[0]) + " Goles a favor";
                golesVisitante.textContent = parseInt(resultados[2]) + " Goles a favor";

                let puntosLocal = document.createElement("p");
                let puntosVisitante = document.createElement("p");

                puntosLocal.textContent = 0 + " puntos";
                puntosVisitante.textContent = 0 + " puntos";

                if (golesLocal.textContent > golesVisitante.textContent) {
                    puntosLocal.textContent = 3 + " puntos";
                } else if (golesLocal.textContent < golesVisitante.textContent) {
                    puntosVisitante.textContent = 3 + " puntos";
                } else {
                    puntosLocal.textContent = 1 + " puntos";
                    puntosVisitante.textContent = 1 + " puntos";
                }

                let golesLocalContra = document.createElement("p");
                let golesVisitanteContra = document.createElement("p");
                golesLocalContra.textContent = golesVisitante.textContent[0] + " Goles en contra";
                golesVisitanteContra.textContent = golesLocal.textContent[0] + " Goles en contra";

                puntosLocalDiv.appendChild(puntosLocal);
                puntosLocalDiv.appendChild(golesLocal);
                puntosLocalDiv.appendChild(golesLocalContra);
                puntosVisitanteDiv.appendChild(puntosVisitante);
                puntosVisitanteDiv.appendChild(golesVisitante);
                puntosVisitanteDiv.appendChild(golesVisitanteContra);

                //Añadir Local
                clasificacionEquipoLocal.appendChild(nombreEquipoLocal);
                clasificacionEquipoLocal.appendChild(puntosLocalDiv);
                contenedor.appendChild(clasificacionEquipoLocal);

                //Añadir Visitante
                clasificacionEquipoVisitante.appendChild(nombreEquipoVisitante);
                clasificacionEquipoVisitante.appendChild(puntosVisitanteDiv);
                contenedor.appendChild(clasificacionEquipoVisitante);
            }
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
})*/