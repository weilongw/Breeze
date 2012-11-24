<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="#">Home</a> <span class="divider">/</span></li>
    <li><a href="register.do">Register</a> <span class="divider">/</span></li>
    
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
			</div>
			<div class="span8" style="padding:20px">
				<h4>It's a trap!</h4>
				<jsp:include page="error.jsp" />
				<section>
				<form action="register.do" method="post" class="form-horizontal">
					<div class="control-group">
						<label class="control-label">Username</label>
						<div class="controls">
							<input class="span4" type="text" id="userName" name="userName" value="${form.userName}" onfocus="waiting('userNameHint');" onblur="validate_userName();">
							&nbsp;<span class="text-error" id="userNameHint"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Email</label>
						<div class="controls">
							<input class="span4" type="text" id="email" name="email" value="${form.email}" onfocus="waiting('emailHint');" onblur="validate_blank('email');">&nbsp;<span class="text-error" id="emailHint"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Confirm Email</label>
						<div class="controls">
							<input class="span4" type="text" id="emailConfirm" name="emailConfirm" value="${form.emailConfirm}" onfocus="waiting('emailConfirmHint');" onblur="validate_same('email');">&nbsp;<span class="text-error" id="emailConfirmHint"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Address</label>
						<div class="controls">
							<textarea class="span6" id="address" rows="4" name="address" onfocus="waiting('addressHint');" onblur="validate_blank('address');">${form.address}</textarea>&nbsp;<span class="text-error" id="addressHint"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Password</label>
						<div class="controls">
							<input class="span4" type="password" id="password" name="password" onfocus="waiting('passwordHint');" onblur="validate_blank('password');">&nbsp;<span class="text-error" id="passwordHint"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Confirm Password</label>
						<div class="controls">
							<input class="span4" type="password" id="passwordConfirm" name="passwordConfirm" onfocus="waiting('passwordConfirmHint');" onblur="validate_same('password');">&nbsp;<span class="text-error" id="passwordConfirmHint"></span>
						</div>
					</div>
					<div class="control-group">
    					<div class="controls">  								
    					<input type="submit" value="Sign up" class="btn">
    				</div>		
				</form>
				</section>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>