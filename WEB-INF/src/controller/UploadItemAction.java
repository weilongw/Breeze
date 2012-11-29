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

import databean.Item;
import databean.User;

public class UploadItemAction extends Action {

	@Override
	public String getName() {
		return "uploadItem.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		User curUser = (User)request.getSession().getAttribute("user");
		Item newItem = (Item)request.getSession().getAttribute("newItem");
		if (curUser == null) {
			errors.add("You are not logged in");
			return "browse.do";
		}
		if (newItem == null) {
			errors.add("You haven't posted an item yet");
			return "post_item.jsp";
		}
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			errors.add("Invalid request type");
			return "upload_image.jsp";
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem)iter.next();
				if (!item.isFormField()) {
					if(item.getFieldName().equals("fname")) {
						String type = item.getContentType();
						if (!type.startsWith("image/")) {
							errors.add("Invalid image file type");
							return "upload_image.jsp";
						}
						String fileName = curUser.getUserName() + newItem.getId() + ".png";
						File uploadedFile = new File("webapps/Breeze/img/item/" + fileName);
						item.write(uploadedFile);
						request.setAttribute("uploadedFile", fileName);
						request.setAttribute("success", "Item image uploaded, Click Finish to confirm");
						return "upload_image.jsp";
					}
				}
			}
		} catch (FileUploadException e) {
			errors.add(e.getMessage());
			return "upload_image.jsp";
		} catch (IOException e) {
			errors.add(e.getMessage());
			return "upload_image.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "upload_image.jsp";
		}
		
		errors.add("no field named 'fname' present");
		return "upload_image.jsp";
		
		
	}

	

}
