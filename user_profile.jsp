<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li class="active"><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					<li><a href="postItem.do"><i class="icon-chevron-right"></i>Post</a></li>
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Items</a></li>
					<li><a href="showMessage.do"><i class="icon-chevron-right"></i>My Messages</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
    				<li class="active"><a href="#tab1" data-toggle="tab">Personal Info</a></li>
    				</ul>
    				<div class="tab-content">
    					<div class="tab-pane active" id="tab1">
                            <div class="span7" style="border-right:1px solid #CCCCCC">
    						<h4> Update your personal information </h4>
    						    <form action="updateProfile.do" method="post" class="form-horizontal">
    						    <div class="control-group">
    								<label class="control-label" for="inputUsername">Username</label>
   									<div class="controls">
    									<span class="input-xlarge uneditable-input">${sessionScope.user.userName}</span>
    								</div>
    							</div>
    							<div class="control-group">
    								<label class="control-label" for="inputEmail">Email</label>
   									<div class="controls">
    									<input class="span9" type="text" id="inputEmail" name="email" value="${sessionScope.user.email}">
    								</div>
    							</div>
                                <div class="control-group">
                                    <label class="control-label" for="inputEmail">Confirm Email</label>
                                    <div class="controls">
                                        <input class="span9" type="text" id="inputEmail" name="confirmEmail">
                                    </div>
                                </div>
    							<div class="control-group">
    								<label class="control-label" for="inputAddress">Address</label>
   									<div class="controls">
    									<textarea class="span10" rows="5" cols="50" name="address">${sessionScope.user.address}</textarea>
    								</div>
    							</div>
   								<div class="control-group">
    								<label class="control-label" for="inputPassword">New Password</label>
    								<div class="controls">
    									<input class="span9" type="password" name="password" id="inputPassword">
    								</div>
    							</div>
    							<div class="control-group">
    								<label class="control-label" for="inputPassword">Confirm Password</label>
    								<div class="controls">
    									<input class="span9" type="password" name="confirmPwd" id="inputPassword">
    								</div>
    							</div>
    							<div class="control-group">
    								<div class="controls">  								
    								<input type="submit" value="Update" class="btn">
    								</div>		
    							</div>
    						</form>
                            </div>
                            <div class="span4" style="padding-left: 20px;">
                                <section>
                                <h4>Update your personal icon!</h4>
                                <c:set var="rand"><%= java.lang.Math.random() %></c:set>
                                <form method="post" enctype="multipart/form-data" action="upload?config=WEB-INF/up-user.txt&name=${sessionScope.user.userName}${rand}.png" onsubmit="return validateForm()">
                                    <input style="height:30px" id="uploadFname" type="file" size=20 name="fname">
                                    <p></p>
                                    <table><tr>
                                    <td><input class="btn" type="Submit" value="Upload"></td>
                                </form>
                                <form action="updatePhoto.do" method="post">
                                            <input type="hidden" name="userPhoto" value="${uploadedFile}">
                                            <td style="padding-left:30px"><input class="btn" type="submit" value="Confirm"></td></tr>
                                        </table>
                                        </form>
                                <p>&nbsp;</p>
                                <div>
                                    <img src="img/user/${sessionScope.user.userPhoto}" style="max-width:80px;max-height:80px;">
                                    <i class="icon-arrow-right"></i>
                                    <img src="img/user/${uploadedFile}" style="max-width:80px;max-height:80px;" alt="preview">
                                </div>
                                <p>&nbsp;</p>
                            </section>
                   
                            <section  style="border-top:1px solid #CCCCCC">

                                <h4>Current credit:</h4>
                                <p>${sessionScope.user.credit}</p>
                            </section>
                                
                            </div>
                            
    					</div>   					
    				</div>
    			</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>