package com.zyppysselfhelp.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyppysselfhelp.bean.ContentRequest;
import com.zyppysselfhelp.bean.ResponseBean;
import com.zyppysselfhelp.modal.Content;
import com.zyppysselfhelp.repo.ContentRepo;
import com.zyppysselfhelp.service.ContentServices;

@RestController
@RequestMapping("/api/content")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContentController {

	protected static final Logger log = LoggerFactory.getLogger(ContentController.class.getName());

	@Autowired
	private ContentServices service;
	@Autowired
	private ContentRepo contentRepo;

	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<ResponseBean> addContent(
			@RequestParam(value = "contentRequest", required = false) String contentRequest,
			@RequestParam(required = true, value = "file") MultipartFile file) {
		log.info("/add");
		ResponseBean responseBean = new ResponseBean();
		Content content = new Content();
		try {

			Long startTime = Calendar.getInstance().getTimeInMillis();
			// ContentRequest contentdetails = objectMapper.readValue(contentRequest,
			// ContentRequest.class);
			ContentRequest contentdetails = new ContentRequest();
			contentdetails.setContentSource("s3");
			contentdetails.setContentFormatType("Video");
			contentdetails.setContentLookupCategory("LookupCategory");
			contentdetails.setHashtags("Yoga");
			contentdetails.setInstructor("1");
			contentdetails.setIsPremium("1");
			contentdetails.setLanguage("1");
			contentdetails.setTitle("Title");
			contentdetails.setLink("link");
			content = service.saveContent(contentdetails, file);
			responseBean.setObject(content);
			Long endTime = Calendar.getInstance().getTimeInMillis();
			responseBean.setResponseTime(endTime - startTime);
			responseBean.setStatusMSG("Content has been uploaded successfully!");
			responseBean.setStatusCD(200);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Exception in add : " + e);
			e.printStackTrace();
			responseBean.setStatusCD(500);
			responseBean.setStatusMSG("Internal processing error, Please contact Doctorite");
			responseBean.setObject(null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getContentById/{id}")
	public ResponseEntity<?> getContentById(@PathVariable int id) {
		ResponseBean responseBean = new ResponseBean();
		log.info("/getContentById");
		try {
			Long startTime = Calendar.getInstance().getTimeInMillis();
			Content content = service.getContentById(id);

			if (content != null) {
				responseBean.setStatusMSG("ContentById Sucessfully");
				responseBean.setObject(content);
			} else {
				responseBean.setStatusMSG("Could not find a activeContent");
			}
			Long endTime = Calendar.getInstance().getTimeInMillis();
			responseBean.setResponseTime(endTime - startTime);
			responseBean.setStatusCD(200);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception in SelfHelp : " + e);
			responseBean.setStatusCD(500);
			responseBean.setStatusMSG("Internal processing error, Please contact Doctorite");
			responseBean.setObject(null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/listAll")
	public ResponseEntity<?> getSelfHelpList() {
		ResponseBean responseBean = new ResponseBean();
		log.info("/listAll");
		try {
			Long startTime = Calendar.getInstance().getTimeInMillis();
			List<Content> list = service.getAllContent();
			responseBean.setObject(list);
			Long endTime = Calendar.getInstance().getTimeInMillis();
			responseBean.setResponseTime(endTime - startTime);
			responseBean.setStatusCD(200);
			responseBean.setStatusMSG("Content listAll Sucessfully");
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception in listAll : " + e);
			responseBean.setStatusCD(500);
			responseBean.setStatusMSG("Internal processing error, Please contact Doctorite");
			responseBean.setObject(null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/action/{id}/{operation}")
	public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String operation) {

		ResponseBean responseBean = new ResponseBean();
		log.info("/delete");
		Content newSelfHelp = null;
		try {
			Long startTime = Calendar.getInstance().getTimeInMillis();
			Optional<Content> contentOptional = contentRepo.findById(Integer.parseInt(id));
			if (contentOptional.isPresent()) {

				newSelfHelp = contentOptional.get();
				if (operation.equals("inactive")) {
					newSelfHelp.setDeleted(true);
//					contentRepo.inactive(id);
					responseBean.setStatusMSG("Content inactived Successfully");
				} else if (operation.equals("active")) {
//					contentRepo.active(id);
					newSelfHelp.setDeleted(false);
					responseBean.setStatusMSG("Content actived Successfully");
				}
			} else {
				System.out.printf("Data Not Found");
				responseBean.setStatusCD(204);
				responseBean.setStatusMSG("No Record Found");
				responseBean.setObject(null);
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.NO_CONTENT);
			}
			// newSelfHelp = contentRepo.save(newSelfHelp);
			responseBean.setObject(newSelfHelp);
			Long endTime = Calendar.getInstance().getTimeInMillis();
			responseBean.setResponseTime(endTime - startTime);
			responseBean.setStatusCD(200);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception in delete : " + e);
			responseBean.setStatusCD(500);
			responseBean.setStatusMSG("Internal processing error, Please contact Doctorite");
			responseBean.setObject(null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	

}
