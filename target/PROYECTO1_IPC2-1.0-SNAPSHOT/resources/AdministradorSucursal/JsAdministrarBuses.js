/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */






function cambiarItem() {
    var select = document.getElementById("selectItem");
    var labelAEditar = document.getElementById("itemEditar");
    var estado = document.getElementById("estadoItem");
    var input = document.getElementById("inputNuevoItem");
    if (select && labelAEditar && estado && input) {
        var textoSeleccionado = select.options[select.selectedIndex].text;
        labelAEditar.innerText = "Nuevo valor para " + textoSeleccionado + ":";
             estado.setAttribute('style', 'display: none');
            input.setAttribute('style', 'display: none');
        if (textoSeleccionado === "Estado") {

            estado.setAttribute('style', 'display: block');
          
        } else {
       
            input.setAttribute('style', 'display: block');


        }
    }


}


function  cambiarItemChofer() {
    var select = document.getElementById("selectItem");
    var labelAEditar = document.getElementById("itemEditar");
    var estado = document.getElementById("estadoItem");
    var input = document.getElementById("inputNuevoItem");
    var fecha = document.getElementById("fechaVencimientoItem");
    var tipoLicencia = document.getElementById("tipoLicenciaItem");
    if (select && labelAEditar && estado && input && fecha && tipoLicencia) {
        var textoSeleccionado = select.options[select.selectedIndex].text;
        labelAEditar.innerText = "Nuevo valor para " + textoSeleccionado + ":";
        estado.setAttribute('style', 'display: none');
        input.setAttribute('style', 'display: none');
        fecha.setAttribute('style', 'display: none');
        tipoLicencia.setAttribute('style', 'display: none');
        if (textoSeleccionado === "Estado") {

            estado.setAttribute('style', 'display: block');


        } else if (textoSeleccionado === "tipo de licencia") {

            tipoLicencia.setAttribute('style', 'display: block');
        } else if (textoSeleccionado === "fecha vencimiento licencia") {

            fecha.setAttribute('style', 'display: block');


        } else {

            input.setAttribute('style', 'display: bolck');


        }


    }



}


document.addEventListener("DOMContentLoaded", function () {
    cambiarItem();
    cambiarItemChofer();
});
