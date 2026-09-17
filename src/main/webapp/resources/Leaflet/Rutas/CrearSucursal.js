/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
var marker;
var mapa; 

document.addEventListener("DOMContentLoaded", function () {

    
    var Guatemala = L.latLngBounds(
        L.latLng(13.73, -92.24),
        L.latLng(17.82, -88.22)
    );


    mapa = L.map('mapa', {
        maxBounds: Guatemala,
        maxBoundsViscosity: 1.0,
        minZoom: 9,           
        maxZoom: 18
        
      
    }).setView([14.8555, -91.5363], 13);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19

    }).addTo(mapa);

 
    mapa.on('click', function (clickMapa) {
        var latitud = clickMapa.latlng.lat;
        var longitud = clickMapa.latlng.lng;

        if (marker) {
            marker.setLatLng(clickMapa.latlng);
        } else {
            marker = L.marker(clickMapa.latlng).addTo(mapa);
        }

        document.getElementById('latitud').value = latitud.toFixed(8);
        document.getElementById('longitud').value = longitud.toFixed(8);
    });

});