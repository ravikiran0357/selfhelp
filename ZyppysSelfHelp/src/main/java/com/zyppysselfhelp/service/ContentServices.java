package com.zyppysselfhelp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zyppysselfhelp.bean.ContentRequest;
import com.zyppysselfhelp.modal.Content;

public interface ContentServices {

	public Content saveContent(ContentRequest contentRequest,MultipartFile file) throws IOException;


	//public Content saveContent(MultipartFile file) throws IOException;

	public Content getContentById(int id);

	public List<Content> getAllContent();

	public List<Content> removeContent(int id);
	
	public List<Content> listAll(String searchData);
}
