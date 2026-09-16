/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function cambiarItemViaje() {
    var select = document.getElementById("selectItem");
    var labelAEditar = document.getElementById("itemEditar");
    var text = document.getElementById("inputText");
    var buses = document.getElementById("Buses");
    var choferes = document.getElementById("choferes");
    var fechaHora = document.getElementById("fechaHora");
    var rutas = document.getElementById("rutas");

  
    if (select && labelAEditar && text && buses && choferes && fechaHora) {
        var textoSeleccionado = select.options[select.selectedIndex].text;
        labelAEditar.innerText = "Nuevo valor para " + textoSeleccionado + ":";

        
        text.setAttribute('style', 'display: none');
        buses.setAttribute('style', 'display: none');
        choferes.setAttribute('style', 'display: none');
        fechaHora.setAttribute('style', 'display: none');
        if (rutas) {
            rutas.setAttribute('style', 'display: none');
        }

        text.disabled = true;
        buses.disabled = true;
        choferes.disabled = true;
        fechaHora.disabled = true;
        if (rutas) {
            rutas.disabled = true;
        }

        if (textoSeleccionado === "bus encargado") {
            buses.setAttribute('style', 'display: block');
            buses.disabled = false;

        } else if (textoSeleccionado === "chofer encargado") {
            choferes.setAttribute('style', 'display: block');
            choferes.disabled = false;

        } else if (textoSeleccionado === "Precio boleto") {
            text.setAttribute('style', 'display: block');
            text.disabled = false;

        } else if (textoSeleccionado === "Ruta") {
            if (rutas) {
                rutas.setAttribute('style', 'display: block');
                rutas.disabled = false;
            }

        } else {
            fechaHora.setAttribute('style', 'display: block');
            fechaHora.disabled = false;
        }
    }
}
document.addEventListener("DOMContentLoaded", function () {
    cambiarItemViaje();
});
