document.addEventListener("DOMContentLoaded", function () {
    fetch("./jugadors.json")
        .then(function (response) {
            if (!response.ok) {
                throw new Error("El archivo JSON no se ha podido cargar");
            }
            return response.json();
        })
        .then(function (jugadores) {
            let contenedor = document.getElementById("plantillaMasculino");

            for (let i = 0; i < jugadores.length; i++) {
                let equipo = jugadores[i];

                let plantilla = document.createElement("div");
                plantilla.className = "equipos";

                let encabezadoDiv = document.createElement("div");
                encabezadoDiv.className = "encabezadoDiv";
                let escudo = document.createElement("img");
                escudo.src = equipo.escut;
                escudo.className = "escudoEquipo";
                encabezadoDiv.appendChild(escudo);

                let nombreEquipo = document.createElement("h2");
                nombreEquipo.textContent = equipo.equip;
                encabezadoDiv.appendChild(nombreEquipo)
                plantilla.appendChild(encabezadoDiv);

                let personasDiv = document.createElement("div");
                personasDiv.className = "personasDiv";

                let infoJugadores = equipo.jugadors;

                for (let j = 0; j < infoJugadores.length; j++) {
                    let jugador = infoJugadores[j];
                    
                    let tarjetaJugador = document.createElement("div");
                    tarjetaJugador.className = "tarjetas";

                    let fotoJugador = document.createElement("img");
                    fotoJugador.src = "./imagenes/images.jpeg";
                    fotoJugador.className = "imagenPersona";
                    tarjetaJugador.appendChild(fotoJugador);

                    let nombreJugador = document.createElement("p");
                    nombreJugador.textContent = "Nombre: " + jugador.nomPersona;
                    tarjetaJugador.appendChild(nombreJugador);

                    let dorsalJugador = document.createElement("p");
                    dorsalJugador.textContent = "Dorsal: " + jugador.dorsal;
                    tarjetaJugador.appendChild(dorsalJugador);

                    let posicionJugador = document.createElement("p");
                    posicionJugador.textContent = "Posición: " + jugador.posicio;
                    tarjetaJugador.appendChild(posicionJugador);

                    let calidadJugador = document.createElement("p");
                    calidadJugador.textContent = "Calidad: " + jugador.qualitat;
                    tarjetaJugador.appendChild(calidadJugador);
                    
                    personasDiv.appendChild(tarjetaJugador);
                    plantilla.appendChild(personasDiv);
                }
                
                let tarjetaEntrenador = document.createElement("div");
                tarjetaEntrenador.className = "tarjetas";

                let infoEntrenador = equipo.entrenador;

                let fotoEntrenador = document.createElement("img");
                fotoEntrenador.src = "./imagenes/entrenadores/" + infoEntrenador.nomPersona + ".png";
                fotoEntrenador.className = "imagenPersona";
                tarjetaEntrenador.appendChild(fotoEntrenador);

                let nombreEntrenador = document.createElement("p");
                nombreEntrenador.textContent = "Nombre: " + infoEntrenador.nomPersona;
                tarjetaEntrenador.appendChild(nombreEntrenador);

                let seleccionadorEntrenador = document.createElement("p");
                
                let seleccionador;
                if (infoEntrenador.esSeleccionador == 0) {
                    seleccionador = "No";
                } else {
                    seleccionador = "Sí";
                }
                seleccionadorEntrenador.textContent = "Seleccionador: " + seleccionador;
                tarjetaEntrenador.appendChild(seleccionadorEntrenador);

                personasDiv.appendChild(tarjetaEntrenador);
                plantilla.appendChild(personasDiv);

                contenedor.appendChild(plantilla);
                contenedor.appendChild(document.createElement("br"));
            }
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
});
