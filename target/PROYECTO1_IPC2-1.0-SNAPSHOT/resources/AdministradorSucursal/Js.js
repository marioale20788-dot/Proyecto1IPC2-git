/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var click = true;
var click2=true;
var click3=true;
var click4=true;

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
    
    if(click3){
               document.getElementById("buscarRuta").setAttribute('style','display: none'); 
        document.getElementById("crearRuta").setAttribute('style','display: none'); 
    
       
    }else{
         document.getElementById("buscarRuta").setAttribute('style','display: block'); 
        document.getElementById("crearRuta").setAttribute('style','display: block'); 
          
 
    }
    click3 = !click3;
    
    
    
    
}
function administrarViajes(){
    
    if(click4){
               document.getElementById("crearViaje").setAttribute('style','display: none'); 
        document.getElementById("verViaje").setAttribute('style','display: none'); 
         document.getElementById("viajePrivado").setAttribute('style','display: none'); 
    
       
    }else{
         document.getElementById("crearViaje").setAttribute('style','display: block'); 
        document.getElementById("verViaje").setAttribute('style','display: block'); 
            document.getElementById("viajePrivado").setAttribute('style','display: block'); 
 
    }
    click4 = !click4;
    
    
    
    
    
    
}
function adminsitrarTaller(){
    
}
function adminsitrarReportes(){
    
} 
document.addEventListener("DOMContentLoaded", function() {
    adminsitrarBuses();
     administrarRutas();
     administrarChoferes();
     
     administrarViajes();
});