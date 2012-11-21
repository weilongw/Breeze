// We create a request object when the page loads, 
// so that all our javascript methods can use it.
var request = createRequest();


//Method for creating the request object independent of the browser.
function createRequest() {
	var request = null;
	try {
	 	request = new XMLHttpRequest();
	} catch (trymicrosoft) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
				
		} catch (othermicrosoft) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failed) {
				request = null;
			}
		}
	}
	
	if (request == null) {
		alert("Error creating request object!");
	} else {
		return request;
	}
}

function search_movie(choice) {
	if (request.readyState != 0) return;
	var movie_name = document.getElementById(choice + "-ajax-movie").value;
	var movie_year = document.getElementById(choice + "-ajax-movie-year").value;
	var url="http://www.omdbapi.com/?t=" + movie_name + "&y="+ movie_year + "&r=XML";
	request.onreadystatechange = update;
	request.open("GET", url, true);
	request.send();
	document.getElementById("movie").innerHTML="<img src=\"img/pending.gif\">";
}

function load_movie(movieid) {
	if (request.readyState != 0) return;
	if (movieid== "") {
		document.getElementById("legend_title").innerHTML = "No movie info present";
		return;
	}
	var url = "http://www.omdbapi.com/?i="+movieid+"&r=XML";
	request.onreadystatechange = update_movie;
	request.open("GET", url, true);
	request.send();
}

function update_movie() {
	if (request.readyState != 4) return;
	if (request.status != 200) {
		alert("request status is " + request.status);
		return;
	}

	var xmlDoc = request.responseXML;
	var root = xmlDoc.getElementsByTagName("root")[0].attributes.getNamedItem("response").nodeValue;
	if (root == "False") {
		request = createRequest();
		document.getElementById("legend_title").innerHTML = "Service unavaliable";
		return;
	}
	var attributes = xmlDoc.getElementsByTagName("movie")[0].attributes;
	for (var i = 0; i < attributes.length - 1; i++) {
		if (attributes[i].nodeName=="poster")
			continue;
		document.getElementById(attributes[i].nodeName).innerHTML="<td><strong>" +
			attributes[i].nodeName +": </strong></td><td>" + attributes[i].nodeValue +"</td>";
	}
	document.getElementById("legend_title").innerHTML=attributes.getNamedItem("title").nodeValue;
	document.getElementById("movie_poster").src=attributes.getNamedItem("poster").nodeValue;
	request = createRequest();
}

function update() {
	if (request.readyState != 4) return;
	if (request.status != 200) {
		alert("request status is " + request.status);
		return;
	}
	clearResults();
	var xmlDoc = request.responseXML;
	var root = xmlDoc.getElementsByTagName("root")[0].attributes.getNamedItem("response").nodeValue;
	if (root == "False") {
		request = createRequest();
		document.getElementById("movie").innerHTML="<h4>Sorry, no movie was found</h4>";	
		return;
	}

	var movieDiv = document.getElementById("movie");
	var movieHead = document.createElement("h4");
	var movieHeadText = document.createTextNode("Movie");
	movieDiv.appendChild(movieHead);
	movieHead.appendChild(movieHeadText);
	var line = document.createElement("hr");
	movieDiv.appendChild(line);
	var attributes = xmlDoc.getElementsByTagName("movie")[0].attributes;
	var movieTitle = document.createElement("h4");
	var movieTitleText = document.createTextNode("Title");
	movieDiv.appendChild(movieTitle);
	movieTitle.appendChild(movieTitleText);
	var xmlTitle = document.createTextNode(attributes[0].nodeValue);
	var titlep = document.createElement("p");
	var titlepstrong = document.createElement("strong");
	movieDiv.appendChild(titlep);
	titlep.appendChild(titlepstrong);	
	titlepstrong.appendChild(xmlTitle);
	var movieImg = document.createElement("img");
	movieImg.src = attributes.getNamedItem("poster").nodeValue;
	movieDiv.appendChild(movieImg);
	document.getElementById("p-movie-name").value=attributes[attributes.length - 1].nodeValue;
	document.getElementById("r-movie-name").value=attributes[attributes.length - 1].nodeValue;
	request = createRequest();
}

function clearResults() {
	var results = document.getElementById("movie");
	while(results.hasChildNodes()) {
		results.removeChild(results.firstChild);
	}
	document.getElementById("p-movie-name").value="";
	document.getElementById("r-movie-name").value="";
}


function enable_text(text, isChecked) {
	document.getElementById(text).disabled = !isChecked;
}

function show_msg(which, msg_id, date) {
    document.getElementById("user" + which).innerHTML = document.getElementById(msg_id + "user").value;
    document.getElementById("title"+ which).innerHTML = document.getElementById(msg_id + "title").value;
    document.getElementById("content" + which).innerHTML = document.getElementById(msg_id + "content").value;
    document.getElementById("date" + which).innerHTML = date;
    if (which='1') {
    	document.getElementById("reply-btn").innerHTML="<a href=\"redirectSend.do?receiver=" + document.getElementById(msg_id + "user").value 
    													+ "&title=Reply:" + document.getElementById(msg_id + "title").value +"\">reply</a>";
    }
}

function show_xchg(text) {
	document.getElementById("xchg").innerHTML = "<strong>Item Exchange Description</strong> \
												<br/><p>" + text + "</p>";
}

function waiting(hint) {
	document.getElementById(hint).innerHTML="<img src=\"img/waiting.gif\">";
}

function validate_userName() {
	var userName = document.getElementById("userName").value;

	if(userName.trim().length==0) {
		document.getElementById("userNameHint").innerHTML="user name cannot be blank";
	}
	else{
		document.getElementById("userNameHint").innerHTML="<img src=\"img/correct.gif\" style=\"max-width:20px\">";
	}
	
}

function validate_blank(text) {
	var field = document.getElementById(text).value;
	if (field.trim().length == 0) {
		document.getElementById(text + "Hint").innerHTML=text + " cannot be blank";
	}
	else {
		document.getElementById(text + "Hint").innerHTML="<img src=\"img/correct.gif\" style=\"max-width:20px\">";
	}
}

function validate_same(text) {
	var email1 = document.getElementById(text).value;
	var email2 = document.getElementById(text + "Confirm").value;
	if (email1==email2 && email1=="") {
		document.getElementById(text + "ConfirmHint").innerHTML=text + " cannot be blank";
	}
	else if (email1==email2){
		document.getElementById(text + "ConfirmHint").innerHTML="<img src=\"img/correct.gif\" style=\"max-width:20px\">";
	}
	else {
		document.getElementById(text + "ConfirmHint").innerHTML="Inconsistent " + text;
	}
}