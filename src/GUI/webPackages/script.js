window.onload = function() {
    // RSS feed URL
    var feedURL = "https://rss.app/feeds/tFa9ENK7OESzo9UR.xml";

    // Fetch the RSS feed using AJAX
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Parse the XML response
            var xmlDoc = xhr.responseXML;
            var items = xmlDoc.getElementsByTagName("item");
            var html = "";
            for (var i = 0; i < items.length; i++) {
                // Get the title, link, description, and image (if available) of each item
                var title = items[i].getElementsByTagName("title")[0].childNodes[0].nodeValue;
                var link = items[i].getElementsByTagName("link")[0].childNodes[0].nodeValue;
                var description = items[i].getElementsByTagName("description")[0].childNodes[0].nodeValue;
                var image = "";
                if (items[i].getElementsByTagName("enclosure").length > 0) {
                    image = items[i].getElementsByTagName("enclosure")[0].getAttribute("url");
                }
                // Build the HTML for the item
                html += "<li><a href='" + link + "'><h2>" + title + "</h2></a>";
                if (image != "") {
                    html += "<img src='" + image + "' alt='" + title + "'>";
                }
                html += "<p>" + description + "</p></li>";
            }
            // Add the HTML to the feed element
            document.getElementById("feed").innerHTML = html;
        }
    };
    xhr.open("GET", feedURL, true);
    xhr.send();
};
