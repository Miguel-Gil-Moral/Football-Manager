document.addEventListener("DOMContentLoaded", function () {
    fetch("jugadors.json")
        .then(function (response) {
            if (!response.ok) {
                throw new Error("El archivo JSON no se ha podido cargar");
            }
            return response.json();
        })
        .then(function (jugadores) {
            let contenedor = document.getElementById("plantillaDiv");

            for (let i = 0; i < jugadores.length; i++) {
                let equipo = jugadores[i];

                let plantillaDiv = document.createElement("div");
                plantillaDiv.className = "equipos";

                let nombreEquipo = document.createElement("h2");
                nombreEquipo.textContent = equipo.equip;
                plantillaDiv.appendChild(nombreEquipo); //PlaceHolder

                let infoJugadores = equipo.jugadors;

                let jugadoresDiv = document.createElement("div");
                jugadoresDiv.className = "jugadoresDiv";

                for (let j = 0; j < infoJugadores.length; j++) {
                    let jugador = infoJugadores[j];

                    let tarjetaJugador = document.createElement("div");
                    tarjetaJugador.className = "tarjetaJugador"

                    let fotoJugador = document.createElement("img");
                    fotoJugador.src = "./imagenes/images.jpeg"
                    tarjetaJugador.appendChild(fotoJugador);

                    let nombreJugador = document.createElement("p");
                    nombreJugador.textContent = "Nombre: " + jugador.nomPersona;
                    tarjetaJugador.appendChild(nombreJugador);

                    let dorsalJugador = document.createElement("p");
                    dorsalJugador.textContent = "Dorsal: " + jugador.dorsal;
                    tarjetaJugador.appendChild(dorsalJugador);

                    let posicionJugador = document.createElement("p");
                    posicionJugador.textContent = "Posición: " + jugador.posicio;
                    jugadoresDiv.appendChild(tarjetaJugador);

                    let calidadJugador = document.createElement("p");
                    
                }
                plantillaDiv.appendChild(jugadoresDiv);
                contenedor.appendChild(plantillaDiv);
                contenedor.appendChild(document.createElement("br"));
            }
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
});
