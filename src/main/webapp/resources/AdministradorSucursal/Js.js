/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var click = true;

function adminsitrarBuses(){
    if(click){
        document.getElementById("crearBusForm").setAttribute('style','display: none'); 
         document.getElementById("editarBus").setAttribute('style','display: none'); 
    }else{
        document.getElementById("crearBusForm").setAttribute('style','display: block'); 
         document.getElementById("editarBus").setAttribute('style','display: block'); 
    }
    click = !click;
    
  
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
document.addEventListener("DOMContentLoaded", function() {
    adminsitrarBuses();
     
});