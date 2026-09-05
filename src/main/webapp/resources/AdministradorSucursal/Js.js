/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function adminsitrarBuses(){
    fetch('../AdministradorSucursal/AdministradorSucursalVista.jsp')  
        .then(response => response.text())
        .then(html => {
            document.getElementById('requestResultado').innerHTML = html;
        });
}
function administrarChoferes(){
    
}
function administrarRutas(){
    
}
function adminsitrarViajes(){
    
}
function adminsitrarTaller(){
    
}
function adminsitrarReportes(){
    
} 