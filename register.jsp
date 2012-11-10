<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
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
							<input class="span4" type="text" name="userName" value="${form.userName}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Email</label>
						<div class="controls">
							<input class="span4" type="text" name="email" value="${form.email}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Confirm Email</label>
						<div class="controls">
							<input class="span4" type="text" name="emailConfirm" value="${form.emailConfirm}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Address</label>
						<div class="controls">
							<textarea class="span6" rows="4" name="address">${form.address}</textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Password</label>
						<div class="controls">
							<input class="span4" type="password" name="password">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Confirm Password</label>
						<div class="controls">
							<input class="span4" type="password" name="passwordConfirm">
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