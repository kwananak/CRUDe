//initial get request for the existing oil batches
function homecall() {
	let httpRequest = new XMLHttpRequest();
	httpRequest.open("GET", 'http://localhost:8080/get');
	httpRequest.send();
	httpRequest.onload = function() {
		const oillist = JSON.parse(httpRequest.responseText);
		getTable(oillist);
	}
}

//sends a request to create an oil batch and receives the updated oil list
function pumpcall() {
	document.getElementById("pumpimage").src="/images/pumpanim.gif";	
	
	let httpRequest = new XMLHttpRequest();
	httpRequest.open("POST", 'http://localhost:8080/pumpy');
	httpRequest.send();
	httpRequest.onload = function() {
		const oillist = JSON.parse(httpRequest.responseText);
		getTable(oillist);
	}
}

//sends a request to refine a specific oil batch and receives the updated oil list
function boilcall(id) {
	document.getElementById("boilimage").src="/images/boilanim.gif";
	
	let httpRequest = new XMLHttpRequest();
	httpRequest.open("POST", 'http://localhost:8080/boily/' + id);
	httpRequest.send();
	httpRequest.onload = function() {
		const oillist = JSON.parse(httpRequest.responseText);
		getTable(oillist);
	}
}

//sends a request to delete a specific oil batch and receives the updated oil list
function spillcall(id) {
	document.getElementById("spillimage").src="/images/spillanim.gif";	
	
	let httpRequest = new XMLHttpRequest();
	httpRequest.open("POST", 'http://localhost:8080/spilly/' + id);
	httpRequest.send();
	httpRequest.onload = function() {
		const oillist = JSON.parse(httpRequest.responseText);
		getTable(oillist);
	}
}

//creates and updates the table with the received list
function getTable(oillist) {	
	let text = "<thead><tr><td></td><td>BatchID</td><td width='80px'>Type</td><td>Quantity</td><td></td></tr></thead><tbody>";
	for (let oil in oillist) {
		text += "<tr>";
		if (oillist[oil].type !== "gasoline") {
			text += "<td><button id='boilbutton' onclick='boilcall(" + oillist[oil].batchId + ")'>Boil!</button></td>";
		} else {
			text += "<td></td>";
		}
		text += "<td id='inborders'>" + oillist[oil].batchId + "</td><td id='inborders'>" + oillist[oil].type + "</td><td id='inborders'>" + oillist[oil].quantity + "</td>";
		text += "<td><button id='spillbutton' onclick='spillcall(" + oillist[oil].batchId + ")'>Spill!</button></td></tr>";
	}
	text += "</tbody>";
	document.getElementById("oiltable").innerHTML = text;
}