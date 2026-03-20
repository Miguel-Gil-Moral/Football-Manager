document.addEventListener("DOMContentLoaded", function () {
    fetch("jugadors.json")
        .then(function (response) {
            if (!response.ok) {
                throw new Error("El archivo JSON no se ha podido cargar");
            }
            return response.json();
        })
        .then(function (jugadores) {
            let contenedor = document.getElementById("jugadoresDiv");

            for (let i = 0; i < jugadores.length; i++) {
                let jugador = jugadores[i];

                let jugadorDiv = document.createElement("div");
                jugadorDiv.className = "jugador";

                let nombreEquipo = document.createElement("h2");
                nombreEquipo.textContent = jugador.equip;
                jugadorDiv.appendChild(nombreEquipo); //PlaceHolder

                contenedor.appendChild(jugadorDiv);
            }
        })
        .catch(function (error) {
            console.error("Esto es un error: ", error);
        });
});
