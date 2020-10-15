var ans;
$(document).ready(function(){
	$('#dp_1').datepicker();
    $('#dp_2').datepicker();
    
    $( "form" ).load(function() {
    	$('form > input,textarea').each(function() {
            if ($(this).val() != '') {
            	$('#SubBtn').removeAttr('disabled');
            }
        });
    });
    
    $('#messageError').css('color',"#fff").css('line-height',"40px").css('background-color', '#f44242').css('height', '40px').css('display', "block");
    
    $('#citySelector').change(function(){
		$('#AreaSelector').html("");
    	var cityId = $('#citySelector :selected').val();
    	$.post("/getAreaFromCity",{ id: cityId },function(data,status){    		
    		
    		var areaDataJson = JSON.parse(data);
			for (var i = 0; i < areaDataJson.length; i++) {
				$('#AreaSelector').append("<option value='"+areaDataJson[i].areaId+"'>"+areaDataJson[i].areaName+"</option>");
			}
    	}); 
    });
    
	$('#input-1, #input-2, #input-3, #input-4, #input-5, #input-6, #input-7, #dp_1, #dp_2').bind('keyup', function() {
		
		$('#input-1, #input-2, #input-3, #input-4, #input-5, #input-6, #input-7, #dp_1, #dp_2').each(function() { 			
			btnValidate();
	    });
	});
	
	function btnValidate(){
	    if($('#input-1').val().length >=3 && $('#input-2').val().length>= 3 && $('#input-4').val().length == 10 && (validateEmail($('#input-3').val()) == true) && (validatePass($('#input-5').val()) == true) && ($('#input-5').val() == $('#input-6').val()) && ($('#input-7').val().length >= 10 && $('#input-7').val().length <= 500) ){
	    	if($('#dp_1').val() >= 9 && $('#dp_2').val() >= 9){
	    		$('#SubBtn').attr('disabled','disabled');
	    	}else{
	    		$('#dp_1, #dp_2').bind('change', function() {
		    		$('#dp_1, #dp_2').each(function() { 			
		    			DateValidate();
		    	    });
		    	});
	    	}
	    }else{
	    	$('#SubBtn').attr('disabled','disabled');
	    }
	}
	
	function DateValidate(){
	    if( validateDate($('#dp_1').val()) == true && validateDate($('#dp_2').val()) == true ){
	    	$('#SubBtn').removeAttr('disabled');
	    }else{
	    	$('#SubBtn').attr('disabled','disabled');
	    }
	}
	
	function validateDate(sDate){
		if(sDate.length >= 9){
			return true;
		}else{
			return false;
		}
	}
	
	function validateCheck(sDate){
		if(sDate.length >= 9){
			return true;
		}else{
			return false;
		}
	}
	
	function validateEmail(sEmail){
		var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		if(filter.test(sEmail)){
			return true;
		}else{
			return false;
		}
	}
	
	function validatePass(sPass){
		var temp = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])");
		if(temp.test(sPass) && sPass.length >= 8 && sPass.length <= 20){
			return true;
		}else{
			return false;
		}
	}
    
    $.validator.addMethod('size',function(value, element){
    	return value.length >=3 && value.length <=20;
    },"Area Name Must Between 3 to 20 Charactor.");
    
    $.validator.addMethod('maxSize',function(value, element){
    	return value.length >=3 && value.length <=500;
    },"Area Name Must Between 10 to 500 Charactor.");
    
    $.validator.addMethod('phoneNumber',function(value, element){
    	return value.length == 10;
    },"Phone Number Must Be 10 Digit Number.");
    
    $.validator.addMethod("pwdStrength", function(value,element) {
    	var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])");
    	   return strongRegex.test(value)
			&& value.length >= 8 && value.length <= 20//it must between 8 to 20 char
   	},"Strong Password Should Contains 1-9 | A-Z | a-z | Symbols");
    
    $('#StaffValidation').validate({
    	rules:{
    		staffFirstName: {
    			required: true
    		},
    		staffLastName: {
    			required: true
    		},
    		staffPhoneNumber: {
    		    required: true,
    		    number: true,
    			phoneNumber: true
    		},
    		"loginVo.password": {
    			required: true,
    			pwdStrength: true
    		},
    		"confirm-password": {
    			required: true,
    			equalTo: "#input-5"
    		},
    		staffDateOfBirth: {
    			required: true
    		},
    		"loginVo.username": {
    			required: true,
    			email: true,
//    			check: true
    		},
    		"staffCity.cityId": {
    			required: true
    		},
    		"staffArea.areaId": {
    			required: true
    		},
    		"staffPost.postId": {
    			required: true
    		},
    		staffAddress: {
    			required: true,
    			maxSize: true
    		},
    		staffPostValidity: {
    			required: true
    		}
    	}
    });
    
    $('#input-3').on('keyup',function(){
    	var val = $('#input-3').val();
    	
    	$.post("/admin/checkUserExists",{ value: val },function(data,status){    		
    		ans = JSON.parse(data).username;
    		if(ans == "true"){
    			$('#custErr').css('display','inline-block');
    		}else{
    			$('#custErr').css('display','none');
    		}
    	});
    });    
    
//    $.validator.addMethod("check", function(val,element) {
//    	$.post("/admin/checkUserExists",{ value: val },function(data,status){    		
//    		ans = JSON.parse(data).username;
//    		console.log(ans);
//    		return ans == "true";
//    	}); 
//   	},"user allready exists.");
    
});