package com.zyppysselfhelp.controller;

import java.util.Calendar;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyppysselfhelp.bean.ResponseBean;

import com.zyppysselfhelp.modal.Instructor;
import com.zyppysselfhelp.service.InstructorService;

@RestController
@RequestMapping("/api/Instructor")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InstructorController 
{
	protected static final Logger log = LoggerFactory.getLogger(InstructorController.class.getName());

	@Autowired
	private InstructorService instructorService;
	//@RequestMapping(value="/addInstructor",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)

	ObjectMapper objectMapper = new ObjectMapper();
	
	@PostMapping(value = "/addInstructor",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public  ResponseEntity<ResponseBean> addInstructor(@RequestBody Instructor instructor,
			@RequestParam(required = false, value = "file") MultipartFile file)
	{
		log.info("/addInstructor");
		ResponseBean responseBean = new ResponseBean();
		Instructor instructorObj= new Instructor();
		try {
			Long startTime = Calendar.getInstance().getTimeInMillis();
			//instructorObj=objectMapper.readValue(instructor,Instructor.class);
			instructorObj=instructorService.saveInstructor(instructor,file);
			responseBean.setObject(instructorObj);
			Long endTime = Calendar.getInstance().getTimeInMillis();
			responseBean.setResponseTime(endTime - startTime);
			responseBean.setStatusMSG("Instructor has been uploaded successfully!");
			responseBean.setStatusCD(200);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.CREATED);
		}catch (Exception e) {
			log.error("Exception in add : " + e);
			e.printStackTrace();
			responseBean.setStatusCD(500);
			responseBean.setStatusMSG("Internal processing error, Please contact Doctorite");
			responseBean.setObject(null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/getInstructorById/{id}")
	public ResponseEntity<?> getInstructorById(@PathVariable int id)
	{
		ResponseBean responseBean = new ResponseBean();
		log.info("/getInstructorById");
		try {
			Long startTime = Calendar.getInstance().getTimeInMillis();
			Instructor instructor= instructorService.getInstructorById(id);
			if(instructor!=null)
			{
				responseBean.setStatusMSG("InstructorById Sucessfully");
				responseBean.setObject(instructor);
			}
			else {
				responseBean.setStatusMSG("Could not find a activeInstructor");
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
	public ResponseEntity<?> getInstructorList()
	{
		ResponseBean responseBean = new ResponseBean();
		log.info("/listAll");
		try {
			Long startTime = Calendar.getInstance().getTimeInMillis();
			List<Instructor> list = instructorService.getAllInstructor();
			responseBean.setObject(list);
			Long endTime = Calendar.getInstance().getTimeInMillis();
			responseBean.setResponseTime(endTime - startTime);
			responseBean.setStatusCD(200);
			responseBean.setStatusMSG("Instructor listAll Sucessfully");
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception in listAll : " + e);
			responseBean.setStatusCD(500);
			responseBean.setStatusMSG("Internal processing error, Please contact Doctorite");
			responseBean.setObject(null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	

}
