/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */






function cambiarItem() {
    var select = document.getElementById("selectItem");
    var labelAEditar = document.getElementById("itemEditar");
    var estado = document.getElementById("estadoItem");
    var input = document.getElementById("inputNuevoItem");

    if (select && labelAEditar && estado) {
        var textoSeleccionado = select.options[select.selectedIndex].text;
        labelAEditar.innerText = "Nuevo valor para " + textoSeleccionado + ":";
        if (textoSeleccionado === "Estado") {

            estado.setAttribute('style', 'display: block');
            input.setAttribute('style', 'display: none');
        } else {
            estado.setAttribute('style', 'display: none');
            input.setAttribute('style', 'display: block');

        }
    }
}

document.addEventListener("DOMContentLoaded", function () {
    cambiarItem();
});
