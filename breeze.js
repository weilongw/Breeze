function enable_text(text, isChecked) {
	document.getElementById(text).disabled = !isChecked;
}

function show_msg(which, user, title, content, date) {
    document.getElementById("user" + which).innerHTML = user;
    document.getElementById("title"+ which).innerHTML = title;
    document.getElementById("content" + which).innerHTML = content;
    document.getElementById("date" + which).innerHTML = date;
}

function show_xchg(text) {
	document.getElementById("xchg").innerHTML = "<strong>Item Exchange Description</strong> \
												<br/><p>" + text + "</p>";
}
