<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li class="active"><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					<li><a href="post_item.jsp"><i class="icon-chevron-right"></i>Post</a></li>
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Items</a></li>
					<li><a href="message.jsp"><i class="icon-chevron-right"></i>My Messages</a></li>
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
    									<input class="span4" type="text" id="inputEmail" name="email" value="${sessionScope.user.email}">
    								</div>
    							</div>
                                <div class="control-group">
                                    <label class="control-label" for="inputEmail">Confirm Email</label>
                                    <div class="controls">
                                        <input class="span4" type="text" id="inputEmail" name="confirmEmail">
                                    </div>
                                </div>
    							<div class="control-group">
    								<label class="control-label" for="inputAddress">Address</label>
   									<div class="controls">
    									<textarea id="inputAddress" rows="5" cols="50" name="address">${sessionScope.user.address}</textarea>
    								</div>
    							</div>
   								<div class="control-group">
    								<label class="control-label" for="inputPassword">New Password</label>
    								<div class="controls">
    									<input class="span4" type="password" name="password" id="inputPassword">
    								</div>
    							</div>
    							<div class="control-group">
    								<label class="control-label" for="inputPassword">Confirm Password</label>
    								<div class="controls">
    									<input class="span4" type="password" name="confirmPwd" id="inputPassword">
    								</div>
    							</div>
    							<div class="control-group">
    								<div class="controls">  								
    								<input type="submit" value="Update" class="btn">
    								</div>		
    							</div>
    						</form>
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