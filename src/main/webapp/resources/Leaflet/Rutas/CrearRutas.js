/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var mapa;
var cont = 0;
var L;

function iniciarMapa(latitud, longitud, nombreSucursal) {

    mapa = L.map('mapa').setView([latitud, longitud], 16);
    var controlRuta = null;

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; OpenStreetMap'
    }).addTo(mapa);


    agregarSucursal(latitud, longitud, nombreSucursal);
}

function  trazarCamino(latitudOrigen, longitudOrigen, latitudDestino, LongitudOrigen, color) {

    controlRuta = L.Routing.control({
        waypoints: [
            L.latLng(latitudOrigen, longitudOrigen),
            L.latLng(latitudDestino, LongitudOrigen)
        ],
        lineOptions: {
            styles: [{color: color, weight: 8, opacity: 0.8}]
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
function obtenerColorDePaleta() {
    var colores = [
        '#0066FF',
        '#E53935',
        '#4CAF50',
        '#8E24AA',
        '#FF9800',
        '#00ACC1'
    ];
    var indice = Math.floor(Math.random() * colores.length);

    return colores[indice];
}
function agregarSucursal(latitud, longitud, nombreSucursal) {

    var marker = L.marker([latitud, longitud]).addTo(mapa);
    marker.bindPopup(nombreSucursal, {
        permanent: true,
        direction: 'top',
        offset: [0, -20]
    }).openTooltip();
}
document.addEventListener("DOMContentLoaded", function () {
    var rutaIniciada = document.getElementById("iniciarRuta");

    if (rutaIniciada) {

        var latitudOrigen = document.getElementById("origenLatitud").value;
        var longitudOrigen = document.getElementById("origenLongitud").value;
        var nombreOrigen = document.getElementById("origenNombre").value;

        iniciarMapa(latitudOrigen, longitudOrigen, nombreOrigen);


        var latitudes = document.getElementsByClassName("destinoLatitud");
        var longitudes = document.getElementsByClassName("destinoLongitud");
        var nombres = document.getElementsByClassName("destinoNombre");


        for (var i = 0; i < latitudes.length; i++) {
            var lat = latitudes[i].value;
            var long = longitudes[i].value;
            var nombre = nombres[i].value;

            agregarSucursal(lat, long, nombre);
            trazarCamino(latitudOrigen, longitudOrigen, lat, long, obtenerColorDePaleta());
        }
    }
});