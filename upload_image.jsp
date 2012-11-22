<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="showProfile.do"><i class="icon-chevron-right"></i>User Info</a></li>
					<li class="active"><a href="postItem.do"><i class="icon-chevron-right"></i>Post</a></li>
					<li><a href="showMyItems.do"><i class="icon-chevron-right"></i>My Items</a></li>
					<li><a href="message.jsp"><i class="icon-chevron-right"></i>My Messages</a></li>
				</ul>
			</div>
			<div class="span8" style="padding:20px">
                <jsp:include page="error.jsp" />
                <jsp:include page="success.jsp" />
                <jsp:include page="upload_msg.jsp" />
				<div class="tabbable"> <!-- Only required for left/right tabs -->
    				<ul class="nav nav-tabs">
    				<li class="disabled"><a href="#">Post</a></li>
    				<li class="disabled"><a href="#">Request</a></li>   
                    <li class="active"><a href="#tab3" data-toggle="tab">Upload</a></li>
    				</ul>
    				<div class="tab-content">
                        <div class="tab-pane active" id="tab3">
                            <div class="span8">
                                <h4>You can also upload an image of the product</h4>
                                <table>
                                <tr>
                                    <form method="post" enctype="multipart/form-data" action="upload?config=WEB-INF/upconf.txt&name=${sessionScope.user.userName}${sessionScope.newItem.id}.png">
                                    <td> 
                                        <input style="height:30px" type="file" size=20 name="fname">
                                    </td>
                                </tr>
                                <tr>
                                    <td> 
                                        <input class="btn" type="Submit" value="Upload">
                                    </td>
                                    </form>
                                </tr>
                                
                                </table>
                                <table>
                                <tr>
                                    <td>
                                        <form action="uploadImage.do" method="post">
                                            <input type="hidden" name="id" value="${sessionScope.newItem.id}">
                                            <input type="hidden" name="imgName" value="${uploadedFile}">
                                            <input class="btn" type="submit" value="Finish">
                                        </form>
                                    </td>
                                    <td style="padding:50px">
                                        <form action="uploadImage.do" method="post">
                                            <input type="hidden" name="id" value="${sessionScope.newItem.id}">
                                            <input type="hidden" name="imgName" value=
                                            "default.png">
                                            <input class="btn" type="submit" value="Skip >>">
                                        </form>
                                    </td>
                                </tr>
                                </table>
                            </div>
                            <div class="span3">
                                <img src="img/item/${uploadedFile}" style="max-width:250px; max-height:250px; float:left; padding-top:100px" alt="preview">
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