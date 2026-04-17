document.addEventListener("DOMContentLoaded", function () {
    let rutas = ["./json/clasificacion_masc.json", "./json/clasificacion_fem.json"];

    let boton = document.getElementById("botonClasificacion");
    let h2Clasificacion = document.getElementById("encabezado_clasificacion");
    boton.addEventListener("click", function () {
        if (boton.textContent === "Ver femenino") {
            let divClasificacion = document.getElementById("clasificacionMasculino");

            divClasificacion.id = "clasificacionFemenino";
            boton.textContent = "Ver masculino";
            h2Clasificacion.textContent = "Clasificación Femenino";
            cargarClasificacion(rutas[1]);
        } else {
            let divClasificacion = document.getElementById("clasificacionFemenino");

            divClasificacion.id = "clasificacionMasculino";
            boton.textContent = "Ver femenino";
            h2Clasificacion.textContent = "Clasificación Masculino";
            cargarClasificacion(rutas[0]);
        }
    })
});

function cargarClasificacion(ruta) {
    fetch("./json/clasificacion_masc.json")
        .then(function (response) {
            if(!response.ok) {
                throw new Error("El archivo JSON no se ha podido cargar");
            }
            return response.json();
        })
        .then(function (clasificacion) {
            let contenedor = document.getElementById("clasificacionMasculino");
            let tabla = document.getElementById("tablaClasificacion");
            let posicion = 1;

            for (let i = 0; i < clasificacion.length; i++) {
                let partido = clasificacion[i];

                let clasificacionEquipo = document.createElement("tr");
                let posicionEquipo = document.createElement("td");
                posicionEquipo.textContent = posicion;

                clasificacionEquipo.appendChild(posicionEquipo);

                let nombreEquipo = document.createElement("td");

                let escudoEquipo = document.createElement("td");
                let escudo = document.createElement("img");
                escudo.src = partido.escut;
                if (ruta.includes("masc")) {
                    escudo.className = "escudoEquipo";
                } else {
                    escudo.className = "escudoEquipoFemenino";
                }
                escudoEquipo.appendChild(escudo);
                clasificacionEquipo.appendChild(escudoEquipo);

                let equipo = document.createElement("p");
                equipo.textContent = partido.nom;
                nombreEquipo.appendChild(equipo);

                clasificacionEquipo.appendChild(nombreEquipo);

                let puntos = document.createElement("td");
                puntos.textContent = partido.clasificacio.punts;
                clasificacionEquipo.appendChild(puntos);

                let partidos_jugados = document.createElement("td");
                partidos_jugados.textContent = partido.clasificacio.partits_jugats;
                clasificacionEquipo.appendChild(partidos_jugados);

                let golesFavor = document.createElement("td");
                golesFavor.textContent = partido.clasificacio.gols_favor;
                clasificacionEquipo.appendChild(golesFavor);

                let golesContra = document.createElement("td");
                golesContra.textContent = partido.clasificacio.gols_contra;
                clasificacionEquipo.appendChild(golesContra);

                tabla.appendChild(clasificacionEquipo);
                contenedor.appendChild(tabla);
                posicion++;
            }
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
}