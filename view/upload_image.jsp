<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp" />
<div style="padding-left:50px;">
    <ul class="breadcrumb" style="padding-left:100">
    <li><a href="home.do">Home</a> <span class="divider">/</span></li>
    <li><a href="showProfile.do">Personal Account</a> <span class="divider">/</span></li>
    <li><a href="postItem.do">New Item</a> <span class="divider">/</span></li>
    <li class="active"><a href="uploadImage.do">Upload Image</a> <span class="divider">/</span></li>
    </ul>
    </div>
	<div class="container-fluid">
		<div class="row-fluid">
			
			<div class="span8" style="padding-left:80px">
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
                                    <form method="post" enctype="multipart/form-data" action="upload?config=WEB-INF/upconf.txt&name=${sessionScope.user.userName}${sessionScope.newItem.id}.png" onsubmit="return validateForm()" >
                                    <td> 
                                        <input style="height:30px" id="uploadFname" type="file" size=20 name="fname">
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
                                            <!--input type="hidden" name="id" value="${sessionScope.newItem.id}"-->
                                            <input type="hidden" name="imgName" value="${uploadedFile}">
                                            <input class="btn" type="submit" value="Finish">
                                        </form>
                                    </td>
                                    <td style="padding:50px">
                                        <form action="uploadImage.do" method="post">
                                            <!--input type="hidden" name="id" value="${sessionScope.newItem.id}"-->
                                            <input type="hidden" name="imgName" value=
                                            "default.jpg">
                                            <input class="btn" type="submit" value="Skip >>">
                                        </form>
                                    </td>
                                </tr>
                                </table>
                            </div>
                            <div class="span4">
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