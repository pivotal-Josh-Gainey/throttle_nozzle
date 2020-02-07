$(document).ready(function() { setInterval(updateRate, 1000); });

function updateRate() {
    $.get("/consuming-rate", function(data) {
        if (data != null) {
            document.getElementById("consuming_rate").innerHTML = data;
        }
    });
}