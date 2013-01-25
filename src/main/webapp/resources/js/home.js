(function() {
    "use strict";

    window.onload = function() {

        var entry = document.getElementById('entry');
        entry.addEventListener("submit", validateInput);

        function validateInput() {
            var elem = document.getElementById('content');
            if (elem.value.length === 0) {
                alert("hej");
                elem.focus(); // set the focus to this input
                return false;
            }
            return true;
        }
    };

})();

function test1() {
    return 1;
}