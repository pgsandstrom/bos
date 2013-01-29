(function() {
    "use strict";

    window.onload = function() {

        var entry = document.getElementById('entry');
        entry.addEventListener("submit", validateInput);

        function validateInput(evt) {
            var elem = document.getElementById('content');
            if (elem.value.length === 0) {
                alert("no empty content yo");
                elem.focus(); // set the focus to this input
                evt.preventDefault();
                return false;
            }
            return true;
        }

        var button = document.getElementById("update");

        button.addEventListener("click", function() {
            // TODO fixa url...
            $.get("/bos/api/text/latest", function(data, status) {
                // alert("Data: " + data + "\nStatus: " + status);
                console.log("Data: " + data);
                console.log(data);
                var latestItems = document.getElementById("latest-items");
                latestItems.innerHTML = '';
                // var ul = document.createElement('ul');
                for ( var i = 0; i < data.length; i++) {
                    var entry = data[i];
                    // var li = document.createElement('li');
                    // console.log("entry.content: " + entry.content);
                    // li.innerHTML = '<a href="text/' + entry.key
                    // + '"><p class="latest-entry ellipsize">'
                    // + entry.content + '</p></a>';
                    // ul.appendChild(li);

                    // TODO testa underscore.js istället

                    var link = document.createElement('a');
                    link.href = 'text/' + entry.key;

                    var entryText = document.createElement('p');
//                    entryText.class = "latest-entry ellipsize";
                    $(entryText).attr('class', 'latest-entry ellipsize');
                    entryText.textContent = entry.content;

                    link.appendChild(entryText);
                    latestItems.appendChild(link);

                }


//                latestItems.appendChild(ul);

                // JSON.parse(data).reduce(function(list, item) {
                // var li = document.createElement('li');
                // li.textContent = item.content;
                // list.appendChild(li);
                // return list;
                // }, document.createElement('ul'));

            });
        });
    };

})();

function test1() {
    return 1;
}