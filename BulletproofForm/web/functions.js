function verificaStr(obj){
    var text = obj.value;
    var i = text.length-1
    
    if (i >= 0 && !isNaN(text.charAt(i))){
        alert("No puedes introducir números");
        obj.value = text.substr(0,i)
    }else{
        obj.value = text.toUpperCase();
    }
}

function validateForm(){
    var email = document.forms["myForm"]["mail"].value;
    var fname = document.forms["myForm"]["fname"].value;
    var lname = document.forms["myForm"]["lname"].value;
    var gender = document.forms["myForm"]["gender"];
    var reg = document.forms["myForm"]["ddlRegion"].value;
    var vac = document.forms["myForm"]["vaccines"].value;
    var com = document.forms["myForm"]["comment"].value;
    var flag = true;
    
    if(email == null || email == "" ){
        flag = false;
    }
    
    if (fname == null || fname == "" ){
        flag = false;
    }
    
    if(lname == null || lname == ""){
        flag = false;
    }
    
    if(com == null || com == ""){
        flag = false;
    }
    
    if(!(gender[0].checked || gender[1].checked)){
        flag = false;
    }
    
    if(flag){
        var index = email.indexOf("@");
        
        if (index < 0){
            alert("Email invalido");
            return false;
        }
        
        index = email.indexOf(".");
        
        if(index < 0){
            alert("Email invalido");
            return false;
        }
        
        return true;
    }else{
        alert("Debes llenar todos los campos de forma correcta.")
        return false;
    }
    
}


