/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function cargarDatosModal(idViaje, iniciarFinalizarViaje) {
    document.getElementById('iniciarViajeIdViaje').value = idViaje;
    if (iniciarFinalizarViaje === 'iniciarViaje') {
        document.getElementById('accionViaje').value = 'iniciar';
        
        
        document.getElementById('seccionIniciar').setAttribute('style', 'display: flex');
        document.getElementById('seccionFinalizar').setAttribute('style', 'display: none');

    }

    else if (iniciarFinalizarViaje === 'finalizarViaje') {
          document.getElementById('accionViaje').value = 'finalizar';
        document.getElementById('seccionFinalizar').setAttribute('style', 'display: flex');
        document.getElementById('seccionIniciar').setAttribute('style', 'display: none');

    }




}