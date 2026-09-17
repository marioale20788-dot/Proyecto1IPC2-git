/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function trazarCamino(mapa, latitudOrigen, longitudOrigen, latitudDestino, longitudDestino, color) {
    L.Routing.control({
        waypoints: [
            L.latLng(latitudOrigen, longitudOrigen),
            L.latLng(latitudDestino, longitudDestino) // Corregido: antes decía LongitudOrigen
        ],
        lineOptions: {
            styles: [{ color: color, weight: 8, opacity: 0.8 }]
        },
        createMarker: function () {
            return null;
        },
        addWaypoints: false,
        draggableWaypoints: false,
        fitSelectedRoutes: true,
        show: false
    }).addTo(mapa); 
}

document.addEventListener("DOMContentLoaded", function () {


    var listaIdViaje = document.getElementsByClassName("idViaje");
    var listaOrigenLatitud = document.getElementsByClassName("origenLatitud");
    var listaOrigenLongitud = document.getElementsByClassName("origenLongitud");
    var listaOrigenNombre = document.getElementsByClassName("origenNombre");
    
    var listaDestinoLatitud = document.getElementsByClassName("destinoLatitud");
    var listaDestinoLongitud = document.getElementsByClassName("destinoLongitud");
    var listaDestinoNombre = document.getElementsByClassName("destinoNombre"); // Corregido

    for (var i = 0; i < listaIdViaje.length; i++) {
        var id = listaIdViaje[i].value;
        var latitudOrigen = parseFloat(listaOrigenLatitud[i].value);
        var longitudOrigen = parseFloat(listaOrigenLongitud[i].value);
        var nombreOrigen = listaOrigenNombre[i].value;

        var latitudDestino = parseFloat(listaDestinoLatitud[i].value);
        var longitudDestino = parseFloat(listaDestinoLongitud[i].value);
        var nombreDestino = listaDestinoNombre[i].value;


        var mapa = L.map("mapa" + id).setView([latitudOrigen, longitudOrigen], 8);
        
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19
        }).addTo(mapa);

        L.marker([latitudOrigen, longitudOrigen]).addTo(mapa).bindPopup("Origen: " + nombreOrigen);
        
     
        L.marker([latitudDestino, longitudDestino]).addTo(mapa)
            .bindPopup("Destino: " + nombreDestino)
            .bindTooltip("Destino: " + nombreDestino, {
                permanent: true,
                direction: 'top',
                offset: [0, -20]
            }).openTooltip();

      
        trazarCamino(mapa, latitudOrigen, longitudOrigen, latitudDestino, longitudDestino, "blue");

        
    }
});

                                        