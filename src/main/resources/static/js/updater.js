$(document).ready(function() {
    updateRate();
    updateSlider();
    setInterval(updateRate, 1000);
});

function updateRate() {
    $.get("/consuming-rate", function(data) {
        if (data != null) {
            document.getElementById("consuming_rate").innerHTML = data;
        }
    });
}

function updateSlider(){
    var slider = document.getElementById("throttle_setting");
    $.get("/get-throttle", function(data){
        if (data != null){
            slider.value = data;
        }
    });
}