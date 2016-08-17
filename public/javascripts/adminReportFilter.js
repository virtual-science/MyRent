$(document).ready(function () { 
  
	
	 $('#rentStatus').dropdown();
	$('.ui.dropdown.residenceType').dropdown(); 
   	$('.ui.dropdown.byRent').dropdown();
   	
 
 
   $('.ui.form').form({ 
     fields: { 
    	 byRent: { 
         identifier: 'byRentsort', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Please Select from the dropdown residence to filter', 
         },], 
       }, 
 

       residenceType: { 
         identifier: 'bytype', 
         rules: [{ 
           type: 'empty', 
           prompt: 'Select residence to filter ', 
         },], 
       }, 
     }, 
   }); 
 });




function updateTenantDropdown($residenceTypeVacant, $residenceTypeRented) {
  let $obj = $('.res.residenceType');
  // Remove the selected residence from dropdown
  for (let i = 0; i < $obj.length; i += 1) {
    if($obj[i].getAttribute('data-value').localeCompare($residenceTypeRented) == 0) {
      $obj[i].remove();
      $('#eircode_residence').dropdown('');
      break;
    }
  }
  
  // Add the new rental eircode to dropdown
  let newMenuItem = dropdownDiv($residenceTypeVacant);
  $('.menu.tenant').append(newMenuItem);
}

function dropdownDiv(name) {
  return '<div class="item eircode"' + ' ' + 'data-value="' + name + '">' + name + '</div>';
}
//}); 


