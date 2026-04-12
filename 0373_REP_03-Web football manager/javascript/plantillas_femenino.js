document.addEventListener("DOMContentLoaded", function () {
    fetch("./FM_LligaFemenina_2025/jugadores.json")
        .then(function (response) {
            if (!response.ok) {
                throw new Error("El archivo JSON no se ha podido cargar");
            }
            return response.json();
        })
        .then(function (jugadoras) {
            let contenedor = document.getElementById("plantillaFemenino");

            for (let i = 0; i < jugadoras.length; i++) {
                let equipo = jugadoras[i];

                let plantilla = document.createElement("div");
                plantilla.className = "equipos";

                let encabezadoDiv = document.createElement("div");
                encabezadoDiv.className = "encabezadoDiv";
                let escudo = document.createElement("img");
                escudo.src = equipo.escut;
                escudo.className = "escudoEquipoFemenino";
                encabezadoDiv.appendChild(escudo);

                let nombreEquipo = document.createElement("h2");
                nombreEquipo.textContent = equipo.equip;
                encabezadoDiv.appendChild(nombreEquipo)
                plantilla.appendChild(encabezadoDiv);

                let personasDiv = document.createElement("div");
                personasDiv.className = "personasDiv";

                let infoJugadoras = equipo.jugadors;

                for (let j = 0; j < infoJugadoras.length; j++) {
                    let jugadora = infoJugadoras[j];
                    
                    let tarjetaJugador = document.createElement("div");
                    tarjetaJugador.className = "tarjetas";

                    let fotoJugador = document.createElement("img");
                    fotoJugador.src = jugadora.foto;
                    fotoJugador.className = "imagenPersona";
                    tarjetaJugador.appendChild(fotoJugador);

                    let nombreJugador = document.createElement("p");
                    nombreJugador.textContent = "Nombre: " + jugadora.nomPersona;
                    tarjetaJugador.appendChild(nombreJugador);

                    let dorsalJugador = document.createElement("p");
                    dorsalJugador.textContent = "Dorsal: " + jugadora.dorsal;
                    tarjetaJugador.appendChild(dorsalJugador);

                    let posicionJugador = document.createElement("p");
                    posicionJugador.textContent = "Posición: " + jugadora.posicio;
                    tarjetaJugador.appendChild(posicionJugador);

                    let calidadJugador = document.createElement("p");
                    calidadJugador.textContent = "Calidad: " + jugadora.qualitat;
                    tarjetaJugador.appendChild(calidadJugador);
                    
                    personasDiv.appendChild(tarjetaJugador);
                    plantilla.appendChild(personasDiv);
                }
                
                let tarjetaEntrenadora = document.createElement("div");
                tarjetaEntrenadora.className = "tarjetas";

                let infoEntrenador = equipo.entrenador;

                let fotoEntrenadora = document.createElement("img");
                fotoEntrenadora.src = infoEntrenador.foto;
                fotoEntrenadora.className = "imagenPersona";
                tarjetaEntrenadora.appendChild(fotoEntrenadora);

                let nombreEntrenadora = document.createElement("p");
                nombreEntrenadora.textContent = "Nombre: " + infoEntrenador.nomPersona;
                tarjetaEntrenadora.appendChild(nombreEntrenadora);

                let seleccionadorEntrenadora = document.createElement("p");
                
                let seleccionador;
                if (infoEntrenador.esSeleccionador == 0) {
                    seleccionador = "No";
                } else {
                    seleccionador = "Sí";
                }
                seleccionadorEntrenadora.textContent = "Seleccionador: " + seleccionador;
                tarjetaEntrenadora.appendChild(seleccionadorEntrenadora);

                personasDiv.appendChild(tarjetaEntrenadora);
                plantilla.appendChild(personasDiv);

                contenedor.appendChild(plantilla);
                contenedor.appendChild(document.createElement("br"));
            }
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
});
