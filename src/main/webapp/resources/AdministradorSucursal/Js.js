/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var click = true;
var click2=true;

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
       if(click2){
        document.getElementById("crearChofer").setAttribute('style','display: none'); 
         document.getElementById("buscarChofer").setAttribute('style','display: none'); 
    }else{
        document.getElementById("crearChofer").setAttribute('style','display: block'); 
         document.getElementById("buscarChofer").setAttribute('style','display: block'); 
    }
    click2 = !click2;
    
    

    
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
     
     administrarChoferes();
});