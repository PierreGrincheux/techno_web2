$(document).ready(function(){
	$("#button_modify").click(function(){
        $$("#div1").toggle();
    });
    
    $("#button2").click(function(){
        $("#div1").text("changed some text!");
    });
    $("#button3").click(function(){
        $("#div1").append("added some text afer <div>");
    });
    
    $("#button4").click(function(){
        $("title").text("changed some title!");
    });
    
    $("#button5").click(function(){
    	$("#div1").toggle();
    });
    
    
});


