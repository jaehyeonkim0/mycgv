$(document).ready(function() {
    $(".movieList").click(function(){

        $(".movieList").removeClass("selected");

        // Add 'selected' class to the clicked item
        $(this).addClass("selected");

        // Reset the color for all nameLink items
        $(".nameLink").css("color", "black");

        // Set the color to white for the nameLink inside the clicked item
        $(this).find(".nameLink").css("color", "white");
    })
})