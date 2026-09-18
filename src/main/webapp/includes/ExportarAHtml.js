/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


   function descargar( contendio,  inputHide,  formExportar) {
                    var textoTabla = document.getElementById(contendio).innerHTML;         
                    document.getElementById(inputHide).value = textoTabla;    
                    document.getElementById(formExportar).submit();
                }
                