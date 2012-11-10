function enable_text(text, isChecked) {
	//document.post_form.credit.disabled = isChecked;
	//document.write(text);
	document.getElementById(text).disabled = !isChecked;
}