$(document).ready(function() {
    // Get the modal
    var modal = document.getElementById("modal_filtera");
    var filter = document.getElementById("filter");
    var span = document.getElementsByClassName("close")[0];

    filter.onclick = function() {
    	modal.style.display = "block";		// prikazujem filter
    }
    span.onclick = function() {
    	modal.style.display = "none";		// sakrij filter kad klikne na x
    }
    window.onclick = function(event) {
	    if (event.target == modal) {		// sakrij filter kad klikne van filtera
	        modal.style.display = "none";
	    }
    }

	$("form#filter_karte").submit(function(event) {
		event.preventDefault();			// ovde cemo dobavljati karte za prikaz
		alert("top");
	});
});