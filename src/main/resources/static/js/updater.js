$(document).ready(function() {
    updateRate();
    updateTotalConsumed();
    updateSlider();
    setInterval(updateUI, 1000);
});
function updateUI() {
    updateRate();
    updateTotalConsumed();
}

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

var updateTotalConsumed = function () {
    $.get("/total-consumed", function(data) {
        if (data != null) {
            document.getElementById("total_consumed").innerHTML = "Total Consumed on this Connection: " + data;
        }
    });
};