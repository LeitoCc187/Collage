// Smooth scrolling to anchor links
$(document).on('click', 'a[href^="#"]', function (e) {
    // Prevent default behavior of anchor links
    e.preventDefault();

    // Animate the scroll to the target element
    $('html, body').animate({
        scrollTop: $($.attr(this, 'href')).offset().top // Get the vertical position of the target
    }, 900); // Duration of animation in milliseconds
});

// Variable to store IP text for copying
let t;

// Wait until the document is fully loaded
$(document).ready(() => {
    // Store the content of the element with class 'ip' in variable 't'
    t = $(".ip").html();
});

// Click-to-copy functionality for the IP address
$(document).on("click", ".ip", () => {
    // Create a temporary <textarea> element to hold the text
    let copy = document.createElement("textarea");
    copy.style.position = "absolute"; // Position it off-screen
    copy.style.left = "-99999px"; // Place it far left
    copy.style.top = "0"; // Position it at the top
    copy.setAttribute("id", "ta"); // Assign an ID for later removal
    document.body.appendChild(copy); // Add the textarea to the document
    copy.textContent = t; // Set the text content to the IP address
    copy.select(); // Select the content for copying
    document.execCommand("copy"); // Execute the copy command

    // Temporarily update the element to show "IP copied!"
    $(".ip").html("<span class='extrapad'>IP copied!</span>");
    setTimeout(() => {
        // Revert to the original IP address after 1 second
        $(".ip").html(t);
        var copy = document.getElementById("ta"); // Find the textarea
        copy.parentNode.removeChild(copy); // Remove it from the DOM
    }, 1000); // 1-second delay
});

// Fetching and displaying the player count
$(document).ready(() => {
    // Get the IP and port from HTML attributes of the element with class 'sip'
    const ip = $(".sip").attr("data-ip");
    const port = $(".sip").attr("data-port");

    // Initial fetch to get the server status and player count
    $.get(`https://mcapi.us/server/status?ip=${ip}&port=${port}`, (result) => {
        if (result.online) {
            // If the server is online, display the current player count
            $(".sip").html(result.players.now);
        } else {
            // If the server is offline, show an error message
            $(".playercount").html("Server isn't online!");
        }
    });

    // Set an interval to refresh the player count every 500 milliseconds
    setInterval(() => {
        $.get(`https://mcapi.us/server/status?ip=${ip}&port=${port}`, (result) => {
            if (result.online) {
                // Update the player count if the server is online
                $(".sip").html(result.players.now);
            } else {
                // Update the error message if the server is offline
                $(".playercount").html("Server isn't online!");
            }
        });
    }, 500); // Refresh interval in milliseconds
});
