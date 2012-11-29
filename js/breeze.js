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
	document.getElementById("pending").innerHTML = "<img src=\"img/pending.gif\">";
	request.onreadystatechange = update_movie;
	request.open("GET", url, true);
	request.send();
}

function update_movie() {
	if (request.readyState != 4) return;
	if (request.status != 200) {
		//alert("request status is " + request.status);
		return;
	}

	var xmlDoc = request.responseXML;
	var root = xmlDoc.getElementsByTagName("root")[0].attributes.getNamedItem("response").nodeValue;
	document.getElementById("pending").innerHTML = "";
	if (root == "False") {
		request = createRequest();
		var errorMsg = xmlDoc.getElementsByTagName("error")[0].childNodes[0].nodeValue;
		document.getElementById("legend_title").innerHTML = errorMsg;
		
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
	//document.getElementById("movie_poster").src=attributes.getNamedItem("poster").nodeValue;
	//var img_src = document.createAttribute("src");
	//img_src.value ="img/poster/" + attributes[attributes.length - 1].nodeValue + ".jpg";
	//document.getElementById("movie_poster").setAttributeNode(img_src);
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
		var errorMsg = xmlDoc.getElementsByTagName("error")[0].childNodes[0].nodeValue;
		document.getElementById("movie").innerHTML="<h4>" + errorMsg + "</h4>";	
		return;
	}
	var attributes = xmlDoc.getElementsByTagName("movie")[0].attributes;

	request = createRequest();
	if (request.readyState != 0) return;
	
	//var url = "download.do?url=" + attributes.getNamedItem("poster").nodeValue + "&id=" + attributes[attributes.length - 1].nodeValue;
	var downurl = "download.do";
	//alert(downurl);
	request.onreadystatechange = download_movie;
	request.open("POST", downurl, true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send("url="+attributes.getNamedItem("poster").nodeValue
				  +"&id=" + attributes[attributes.length - 1].nodeValue );

	var movieDiv = document.getElementById("movie");
	var movieHead = document.createElement("h4");
	var movieHeadText = document.createTextNode("Movie");
	movieDiv.appendChild(movieHead);
	movieHead.appendChild(movieHeadText);
	var line = document.createElement("hr");
	movieDiv.appendChild(line);
	
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
	
	document.getElementById("p-movie-name").value=attributes[attributes.length - 1].nodeValue;
	document.getElementById("r-movie-name").value=attributes[attributes.length - 1].nodeValue;

	

	
}

function download_movie() {
	if (request.readyState != 4) return;
	if (request.status != 200) {
		alert("request status is " + request.status);
		return;
	}
	var xmlDoc = request.responseXML;
	var root = xmlDoc.getElementsByTagName("root")[0].attributes.getNamedItem("result").nodeValue;
	if (root == "False") {
		request = createRequest();
		
		return;
	}
	var movieDiv = document.getElementById("movie");
	var msg = xmlDoc.getElementsByTagName("message")[0].childNodes[0].nodeValue;
	var movieImg = document.createElement("img");
	//movieImg.src = attributes.getNamedItem("poster").nodeValue;
	movieImg.src = "img/poster/" + msg + ".jpg";
	movieDiv.appendChild(movieImg);
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

function show_msg(which, msg_id, date, hasRead) {
	
    document.getElementById("user" + which).innerHTML = document.getElementById(msg_id + "user").value;

    if (document.getElementById(msg_id + "user").value == "Admin") {
    	document.getElementById("title" + which).innerHTML="";
	    var title = document.createTextNode(document.getElementById(msg_id + "title").value);
	    document.getElementById("title" + which).appendChild(title);
    	//document.getElementById("title" + which).innerHTML = document.getElementById(msg_id + "title").value;
    	document.getElementById("content" + which).innerHTML = document.getElementById(msg_id + "content").value;
    }
    else {
	    document.getElementById("title" + which).innerHTML="";
	    var title = document.createTextNode(document.getElementById(msg_id + "title").value);
	    document.getElementById("title" + which).appendChild(title);

	    document.getElementById("content" + which).innerHTML="";
	    var content = document.createTextNode(document.getElementById(msg_id + "content").value);
	    document.getElementById("content" + which).appendChild(content);
    }

    document.getElementById("date" + which).innerHTML = date;

    if (which =='1') {
    	//alert(document.getElementById(msg_id + "title").value);
    	if (document.getElementById(msg_id + "user").value == "Admin") {
    		document.getElementById("reply-btn").innerHTML="reply";
    	}
    	else{
    		//var title = document.createTextNode(document.getElementById(msg_id + "title").value);
    		document.getElementById("reply-btn").innerHTML="<a href=\"redirectSend.do?receiver=" + document.getElementById(msg_id + "user").value 
    													+ "\">reply</a>";
    	}
    	
    }

    if (hasRead == '0') {

		if (request.readyState != 0) return;
		//var url = "read.do?msgId=" + msg_id;
		var readurl = "read.do"
		request.onreadystatechange = markAsRead;
		request.open("POST", readurl, true);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.send("msgId=" + msg_id);
    }
}

function markAsRead() {
	if (request.readyState != 4) return;

	if (request.status != 200) {
		alert("Error, request status is " + request.status);
		return;
	}
	var xmlDoc = request.responseXML;
	var root = xmlDoc.getElementsByTagName("root")[0].attributes.getNamedItem("result").nodeValue;
	if (root == "False") {
		request = createRequest();
		var msg = xmlDoc.getElementsByTagName("message")[0].childNodes[0].nodeValue;
		//alert(msg);
		return;
	}
	var msg = xmlDoc.getElementsByTagName("message")[0].childNodes[0].nodeValue;
	var msgID = xmlDoc.getElementsByTagName("messageID")[0].childNodes[0].nodeValue;
	var msgDate = xmlDoc.getElementsByTagName("messageDate")[0].childNodes[0].nodeValue;

	
	var row = document.getElementById("tr" + msgID);
	var attr = document.createAttribute("onclick");
	attr.value="show_msg('1','"+ msgID +"', '" + msgDate +"', '1');";
	row.setAttributeNode(attr);
	row.style.fontWeight="normal";

	var hiuser = document.getElementById("headCount");

	hiuser.innerHTML=msg;

	request = createRequest();
}

function show_xchg() {
	document.getElementById("xchg_title").innerHTML="Item Exchange Detail:";
	var xchg = document.createTextNode(document.getElementById("xchgMsg").value);
	document.getElementById("xchg").innerHTML = "";
	document.getElementById("xchg").appendChild(xchg);
}

function waiting(hint) {
	document.getElementById(hint).innerHTML="<img src=\"img/waiting.gif\">";
}

function validate_userName() {
	var userName = document.getElementById("userName").value;

	if (request.readyState != 0) return;
	var url = "check.do?value=" + escape(userName) +"&field=1";
	request.onreadystatechange = updateUserHint;
	request.open("GET", url, true);
	request.send();
	
}

function updateUserHint() {
	if (request.readyState != 4) return;

	if (request.status != 200) {
		alert("Error, request status is " + request.status);
		return;
	}
	var xmlDoc = request.responseXML;
	var root = xmlDoc.getElementsByTagName("root")[0].attributes.getNamedItem("result").nodeValue;
	if (root == "True") {
		request = createRequest();
		document.getElementById("userNameHint").innerHTML="<img src=\"img/correct.gif\" style=\"max-width:20px\">";
		return;
	}
	
	var msg = xmlDoc.getElementsByTagName("message")[0].childNodes[0].nodeValue;
	
	document.getElementById("userNameHint").innerHTML=msg;
	request = createRequest();
}

function validate_commName() {
	var commName = document.getElementById("commName").value;

	if (request.readyState != 0) return;
	var url = "check.do?value=" + escape(commName) +"&field=2";
	request.onreadystatechange = updateCommNameHint;
	request.open("GET", url, true);
	request.send();
	
}

function updateCommNameHint() {
	if (request.readyState != 4) return;

	if (request.status != 200) {
		alert("Error, request status is " + request.status);
		return;
	}
	var xmlDoc = request.responseXML;
	var root = xmlDoc.getElementsByTagName("root")[0].attributes.getNamedItem("result").nodeValue;
	if (root == "True") {
		request = createRequest();
		document.getElementById("commNameHint").innerHTML="<img src=\"img/correct.gif\" style=\"max-width:20px\">";
		return;
	}
	
	var msg = xmlDoc.getElementsByTagName("message")[0].childNodes[0].nodeValue;
	
	document.getElementById("commNameHint").innerHTML=msg;
	request = createRequest();
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

function validate_email() {
	var email = document.getElementById("email").value;
	var re = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	if (email == "") {
		document.getElementById("emailHint").innerHTML="email cannot be blank";
	} else if (!re.test(email)) {
		document.getElementById("emailHint").innerHTML="Invalid email address";
	} else {
		document.getElementById("emailHint").innerHTML="<img src=\"img/correct.gif\" style=\"max-width:20px\">";
	}
}

function validateForm() {
	var fname=document.getElementById("uploadFname").value;
	if (fname==null || fname == "" ) {
		alert("You haven't uploaded an image yet");
		return false;
	}
	var ext = fname.split('.').pop().toLowerCase();
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