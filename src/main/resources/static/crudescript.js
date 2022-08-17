
//main function called to send requests, always updates the oil table
function sendcall(callmethod, endofcall) {
	let httpRequest = new XMLHttpRequest();
	httpRequest.open(callmethod, "http://192.168.1.195:8080/" + endofcall);
	httpRequest.send();
	httpRequest.onload = function() {
		const oillist = JSON.parse(httpRequest.responseText);
		getTable(oillist);
	}
}

//initial get request for the existing oil batches
function homecall() {
	sendcall("GET", "get");
}

//sends a request to create an oil batch
function pumpcall() {
	document.getElementById("pumpimage").src="/images/pumpanim.gif";	
	sendcall("POST", "pumpy");
}

//sends a request to refine a specific oil batch
function boilcall(id) {
	document.getElementById("boilimage").src="/images/boilanim.gif";
	sendcall("POST", "boily/" + id);
}

//sends a request to delete a specific oil batch
function spillcall(id) {
	document.getElementById("spillimage").src="/images/spillanim.gif";
	sendcall("POST", "spilly/" + id);
}

//creates and updates the table with the received list
function getTable(oillist) {	
	let text = "<thead><tr><td></td><td>BatchID</td><td id='typerow'>Type</td><td>Quantity</td><td></td></tr></thead><tbody>";
	for (let oil in oillist) {
		text += "<tr>";
		if (oillist[oil].type !== "GASOLINE") {
			text += "<td><button id='boilbutton' onclick='boilcall(" + oillist[oil].batchId + ")'>Boil!</button></td>";
		} else {
			text += "<td></td>";
		}
		text += "<td id='inborders'>" + oillist[oil].batchId + "</td><td id='inborders'>" + oillist[oil].type.toLowerCase() + "</td><td id='inborders'>" + oillist[oil].quantity + "</td>";
		text += "<td><button id='spillbutton' onclick='spillcall(" + oillist[oil].batchId + ")'>Spill!</button></td></tr>";
	}
	text += "</tbody>";
	document.getElementById("oiltable").innerHTML = text;
}