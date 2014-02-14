
/**
 * Source: http://stackoverflow.com/questions/18082/validate-numbers-in-javascript-isnumeric
 */
function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}


function selectItem(field) {
	val = field.text;
	name = field.parentElement.parentElement.id.replace('_list', '');
	$('#brands_button').html(val + ' <span class="caret"></span>');
	if(val === 'New Brand') {
	  $('input[name='+name+']').val('');
	  $('input[name='+name+']').removeAttr('readonly');
	}
	else {
		$('input[name='+name+']').val(val);
		$('input[name='+name+']').attr('readonly', 'readonly');
	}
}

function selectItemDropdown(field) {
  var name = field.parentElement.parentElement.id.replace('_choices', '');
  $('#'+name+'_button').html(field.text+' <span class="caret"></span>');
  $('input[name='+name+']').attr('value', field.attr('value').replace('id_', ''));
  console.log(name);
}



