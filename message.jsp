<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					<li><a href="postItem.do"><i class="icon-chevron-right"></i>Post</a></li>
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Items</a></li>
					<li class="active"><a href="message.jsp"><i class="icon-chevron-right"></i>My Messages</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
    				<li class="active"><a href="#tab1" data-toggle="tab">Inbox</a></li>
    				<li><a href="#tab2" data-toggle="tab">Sent</a></li>
    				<li><a href="#tab3" data-toggle="tab">Compose</a></li>
    				</ul>
    				<div class="tab-content">
    					<div class="tab-pane active" id="tab1">
    						<table class="table table-hover">
    							<thead>
    								<tr>
    									<th>From</th>
    									<th class="pull-right">When</th>
    								</tr>
    							</thead>
    							<tbody>
    								<tr class="info">
    									<td>foo</td>
    									<td>10/31/2012</td>
    								</tr>
    								<tr class="info">
    									<td>foo</td>
    									<td>10/31/2012</td>
    								</tr>

    								<tr>
    									<td>admin</td>
    									<td>10/31/2012</td>
    								</tr>
    								<tr>
    									<td>foo</td>
    									<td>10/31/2012</td>
    								</tr>
    							</tbody>
    						</table>
    					</div>
    					<div class="tab-pane" id="tab2">
   							<table class="table table-hover">
    							<thead>
    								<tr>
    									<th>To</th>
    									<th>When</th>
    								</tr>
    							</thead>
    							<tbody>
    								<tr>
    									<td>foo</td>
    									<td>10/31/2012</td>
    								</tr>
    								<tr>
    									<td>foo</td>
    									<td>10/31/2012</td>
    								</tr>
    								<tr>
    									<td>admin</td>
    									<td>10/31/2012</td>
    								</tr>
    								<tr>
    									<td>foo</td>
    									<td>10/31/2012</td>
    								</tr>
    							</tbody>
    						</table>
    					</div>
    					<div class="tab-pane" id="tab3">
   							<form action="#" method="post" class="form-horizontal">
   								<div class="control-group">
   									<label class="control-label">To (username)</label>
    								<div class="controls">  								
    								<input type="text" class="span4" name="receiver">
    								</div>		
    							</div>
    							<div class="control-group">
   									<label class="control-label">Content</label>
    								<div class="controls">  								
    								<textarea class="span8" rows="4" name="content"></textarea>
    								</div>		
    							</div>
    							<div class="control-group">
    								<div class="controls">
    								<input type="hidden" name="sender" value="">
    								<input type="submit" value="Send" class="btn">
    								</div>		
    							</div>
   							</form>
    					</div>
    				</div>
    			</div>
			</div>
		</div>
	</div>
</body>
</html>