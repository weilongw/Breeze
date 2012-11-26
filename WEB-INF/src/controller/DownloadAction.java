package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.mybeans.forms.FormBeanFactory;

import formbeans.DownloadForm;

public class DownloadAction extends Action {

	private FormBeanFactory<DownloadForm> formBeanFactory = FormBeanFactory.getInstance(DownloadForm.class, "<>\"");
	@Override
	public String getName() {
		return "download.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		DownloadForm form = formBeanFactory.create(request);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("result", "False");
		request.setAttribute("msgs", errors);
		if (!form.isPresent()) return "ajaxCheck.jsp";
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) return "ajaxCheck.jsp";
		
		File f = new File("./webapps/Breeze/img/poster/" + form.getId() + ".jpg");
		if (f.exists()) {
			System.out.println("here");
			errors.add(form.getId());
			request.setAttribute("result", "True");
			return "ajaxCheck.jsp";
		}
		
		BufferedImage image =null;
        try{
 
            URL url =new URL(form.getUrl());
         
            image = ImageIO.read(url);
 
            // for jpg
            ImageIO.write(image, "jpg",new File("./webapps/Breeze/img/poster/" + form.getId() + ".jpg"));
 
        }catch(IOException e){
            errors.add(e.getMessage());
            return "ajaxCheck.jsp";
        }
        request.setAttribute("result", "True");
        errors.add(form.getId());
        return "ajaxCheck.jsp";
	}

	

}
