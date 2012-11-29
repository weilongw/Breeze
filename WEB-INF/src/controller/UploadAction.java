package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import databean.User;

public class UploadAction extends Action {

	
	@Override
	public String getName() {
		return "upload.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		User curUser = (User)request.getSession().getAttribute("user");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			errors.add("Invalid request type");
			return "showProfile.do";
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			
			while(iter.hasNext()) {
				FileItem item = (FileItem)iter.next();
				if (item.isFormField()) {
				}
				else {
					
					if (item.getFieldName().equals("fname")) {
						String type = item.getContentType();
						if (!type.startsWith("image/")) {
							errors.add("Invalid image file type");
							return "showProfile.do";
						}
						String fname = item.getName();
						int rand = fname.hashCode();
						String userName = curUser.getUserName();
						String fileName = userName + rand + fname;
						File uploadedFile = new File("webapps/Breeze/img/user/" + fileName);
						
						item.write(uploadedFile);
						request.setAttribute("uploadedFile", fileName);
						request.setAttribute("success", "Upload success, click Confirm to accept the change");
						return "showProfile.do";
					}
					
				}
			}
		} catch (FileUploadException e) {
			errors.add(e.getMessage());
			return "showProfile.do";
		} catch (IOException e) {
			errors.add(e.getMessage());
			return "showProfile.do";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "showProfile.do";
		}
		errors.add("no field named 'fname' present");
		return "showProfile.do";
	}
	
	

	

}
