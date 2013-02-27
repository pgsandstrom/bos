(function () {
    "use strict";

    window.onload = function () {

        var entry = document.getElementById('entry');
        entry.addEventListener("submit", validateInput);

        function validateInput(evt) {

            entry.disable = true; // lol

            var content = document.getElementById("content");
            var text = content.value;
            console.log("Content: " + content);
            console.log("text: " + text);
            $.post("api/text", {
                content: text
            }).done(function (data) {
                    console.log("success! Redirecting!");
                    console.log("data.key: " + data.key);
                    window.location = "text/" + data.key;
                }).fail(function (data, textStatus, jqXHR) {
                    console.log("error");
                    console.log("error status: " + data.status);
                    console.log("error responseText: " + data.responseText);
                    console.log("error data: " + data);
                    console.log("error textStatus: " + textStatus);
                    console.log("error jqXHR: " + jqXHR);

                    if (data.status === 400) {
                        alert("omg forbidden text!");
                    } else {
                        alert("yeah something bad happened");
                    }

                }).always(function () {
                    console.log("always");
                });

            evt.preventDefault();
            return false;
        }

        var button = document.getElementById("update");

        button
            .addEventListener(
            "click",
            function () {
                button.disabled = true;

                $.get(
                    "api/text/latest",
                    function (data, status) {
                        console.log("Data: " + data);
                        console.log(data);
                        var latestItems = document
                            .getElementById("latest-items");
                        latestItems.innerHTML = '';

                        // <%- instead of <%= to prevent html-injection
                        var template = _
                            .template('<a href="text/<%- key %>"><p class="latest-entry ellipsize"><%- content %></p></a>');

                        var html = [];
                        for (var i = 0; i < data.length; i++) {
                            var entry = data[i];
                            html += template({
                                key: entry.key,
                                content: entry.content
                            });
                        }

                        console.log(html);
                        latestItems.innerHTML = html;

                        button.disabled = false;
                    });
            });
    };

})();

function test1() {
    return 1;
}