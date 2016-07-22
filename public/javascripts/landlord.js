$(document).ready(function () { 
   $('#editresidence').dropdown();
   
 $('#deleteresidence').dropdown(); 
 
 
   $('.ui.form').form({ 
     fields: { 
    	 deleteresidence: { 
         identifier: 'eircode', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Please Select from the dropdown residence to delete', 
         },], 
       }, 
 

       editresidence: { 
         identifier: 'editresidence', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Select residence to edit ', 
         },], 
       }, 
     }, 
   }); 
 });