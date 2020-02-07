var slider = document.getElementById("throttle_setting");



slider.oninput = function() {
    $.get("/set-throttle", {throttleSetting: slider.value});
};