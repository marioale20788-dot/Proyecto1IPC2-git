/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


var elementosSeleccionados = [];

function seleccionarAsiento(boton, numero) {

    if (boton.disabled) {
        return;

    }
    var yaDioClick = document.getElementById("yaDioClick" + numero);
    var botonesSeleccionadosAcumulador = document.getElementById("asientosSeleccionados");
    var precio = document.getElementById("precioTotalBoletos");
    var precioIndiviual = document.getElementById("precioIndividual");
    if (yaDioClick.value === "true") {

        yaDioClick.value = "false";

        var posicionNumero = elementosSeleccionados.indexOf(numero);
        if (posicionNumero !== -1) {
            elementosSeleccionados.splice(posicionNumero, 1);

        }
        boton.classList.remove('btn-warning');
        boton.classList.add('btn-success');


    } else {
        yaDioClick.value = "true";
        elementosSeleccionados.push(numero);
        boton.classList.remove('btn-success');
        boton.classList.add('btn-warning');

    }
    var total = elementosSeleccionados.length * precioIndiviual.value;
    precio.innerHTML = 'PRECIO TOTAL: Q'+total;
    botonesSeleccionadosAcumulador.value = elementosSeleccionados.join(',');



} 