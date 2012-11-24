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

function join(commName){
	if (request.readyState != 0) return;
		var url = "joinCommunity.do?name=" + commName;
		request.onreadystatechange = decideJoin;
		request.open("GET", url, true);
		request.send();
}

function decideJoin(){
	if (request.readyState != 4) return;
	
	if (request.status != 200) {
		alert("Error, request status is "+request.status);
		return;
	}

	var xmlDoc  = request.responseXML;
	var errors = xmlDoc.getElementsByTagName("error");
	var success = xmlDoc.getElementsByTagName("success");
	var success_msg = success[0].childNodes[0].nodeValue;
	var choice = xmlDoc.getElementsByTagName("choice")[0].childNodes[0].nodeValue;
	var comm = xmlDoc.getElementsByTagName("comm")[0].childNodes[0].nodeValue;

	var joinDivElMsg = document.getElementById("decide_join_message");
	var joinDivEl = document.getElementById("decide_join");

	while (joinDivElMsg.hasChildNodes()) {
		joinDivElMsg.removeChild(joinDivElMsg.firstChild);
	}

/*	for (var i=0; i<errors.length; i++) {
		var error_msg = errors[i].getElementsByTagName("error")[0].nodeValue;
		
		var errEl = xmlDoc.createTextNode(error_msg);
		suggestDivElMsg.appendChild(errEl);
	}*/

/*<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">x</button>
		<strong>Success!</strong>
			${success}<br/>
	</div>*/
	/*button = document.createElement("button");
	button.type="button";
	button.class="close";
	var msg_btn_data_dismiss = document.createAttribute("data-dismiss");
	msg_btn_data_dismiss.value = "alert";
	button.setAttributeNode(msg_btn_data_dismiss);
	
	button.innerHTML="X"; */
	if (errors.length != 0) {
		var error_div = document.createElement("div");
		
		var div_class_error =document.createAttribute("class");
		div_class_error.value="alert alert-error";
		error_div.setAttributeNode(div_class_error);

		var button = document.createElement("button");
		var btn_class= document.createAttribute("class");
		btn_class.value="close";
		button.setAttributeNode(btn_class);
		var btn_data = document.createAttribute("data-dismiss");
		btn_data.value="alert";
		button.setAttributeNode(btn_data);
		button.innerHTML="x";

		var strong = document.createElement("strong");
		strong.innerHTML="Error!<br/>";

		var new_line = document.createElement("br");

		error_div.appendChild(button);
		error_div.appendChild(strong);

		for (var i=0; i<errors.length; i++) {
			var error_msg = errors[i].getElementsByTagName("error")[0].nodeValue;
		
			var errEl = document.createTextNode(error_msg);
			error_div.appendChild(errEl);
			error_div.appendChild(new_line);
		}
		joinDivElMsg.appendChild(error_div);
	}

	if (success_msg != null) {
		var success_div = document.createElement("div");
		
		var div_class=document.createAttribute("class");
		div_class.value="alert alert-success";
		success_div.setAttributeNode(div_class);

		var button = document.createElement("button");
		var btn_class= document.createAttribute("class");
		btn_class.value="close";
		button.setAttributeNode(btn_class);
		var btn_data = document.createAttribute("data-dismiss");
		btn_data.value="alert";
		button.setAttributeNode(btn_data);
		button.innerHTML="x";

		var strong = document.createElement("strong");
		strong.innerHTML="Success!&nbsp;&nbsp;&nbsp;"

		var text = document.createTextNode(success_msg);

		var new_line = document.createElement("br");

	//joinDivElMsg.appendChild(button);
		success_div.appendChild(button);
		success_div.appendChild(strong);
		success_div.appendChild(text);
		success_div.appendChild(new_line);
		joinDivElMsg.appendChild(success_div);
	}
	
/*	var msg_div = xmlDoc.createElement("div");
	var msg_div_class = document.createAttribute("class");
	msg_div_class.value = "alert alert-success";
	msg_div.setAttributeNode(msg_div_class);

	var msg_btn = xmlDoc.createElement("button");

	var msg_btn_type = document.createAttribute("type");
	msg_btn_type.value = "button";
	msg_btn.setAttributeNode(msg_btn_type);

	var msg_btn_class = document.createAttribute("class");
	msg_btn_class.value = "close";
	msg_btn.setAttributeNode(msg_btn_class);

	var msg_btn_data_dismiss = document.createAttribute("data-dismiss");
	msg_btn_data_dismiss.value = "alert";
	msg_btn.setAttributeNode(msg_btn_data_dismiss);

	var close_txt = xmlDoc.createTextNode("x");

	msg_btn.appendChild(close_txt);

	var sucTextEl = xmlDoc.createTextNode("success");
	var strongEl = xmlDoc.createElement("strong");
	strongEl.appendChild(sucTextEl);
	var msgTextEl = xmlDoc.createTextNode(success_msg);

	var br = xmlDoc.createElement("br");

	msg_div.appendChild(msg_btn);
	msg_div.appendChild(strongEl);
	msg_div.appendChild(msgTextEl);
	msg_div.appendChild(br);

	joinDivElMsg.appendChild(msg_div); */

	while (joinDivEl.hasChildNodes()) {
		joinDivEl.removeChild(joinDivEl.firstChild);
	}

	var joinTextEl = document.createTextNode(choice);

	var anchorEl = document.createElement("a");
		anchorEl.appendChild(joinTextEl);


	var style_join = document.createAttribute('style');
	style_join.value = "float:right; margin-top:5px; margin-right:8px";
	anchorEl.setAttributeNode(style_join);

	var onclick_attr = document.createAttribute('onclick');

	if(choice == "Join!")
	onclick_attr.value = "join(\'" + comm + "\')";
		else if(choice == "Unjoin!")
	onclick_attr.value = "unjoin(\'" + comm + "\')";


	anchorEl.setAttributeNode(onclick_attr);

	joinDivEl.appendChild(anchorEl);

	request = createRequest();


}


function unjoin(commName){
	if (request.readyState != 0) return;
	var url = "unjoinCommunity.do?name=" + commName;
	request.onreadystatechange = decideJoin;
	request.open("GET", url, true);
	request.send();
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

function validateForm() {
	var fname=document.getElementById("uploadFname").value;
	if (fname==null || fname == "" ) {
		alert("You haven't uploaded an image yet");
		return false;
	}
	var ext = fname.split('.').pop();
	if (ext != "png" && ext != "jpg" && ext !="gif") {
		alert("Invalid file extension");
		return false;
	}
	return true;
}

function smartReply(user) {
	var textarea = document.getElementById("content");
	textarea.value = "";
	textarea.value = "Reply " + user + " :";
	textarea.focus();
}