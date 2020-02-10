var slider = document.getElementById("throttle_setting");

slider.oninput = function() {
    $.get("/slider-set-throttle", {throttleSetting: slider.value});
};