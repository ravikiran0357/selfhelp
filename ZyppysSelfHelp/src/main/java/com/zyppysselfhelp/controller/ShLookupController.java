package com.zyppysselfhelp.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyppysselfhelp.bean.ResponseBean;
import com.zyppysselfhelp.modal.Instructor;
import com.zyppysselfhelp.modal.ShLookup;
import com.zyppysselfhelp.modal.ShLookupCategory;
import com.zyppysselfhelp.repo.ShLookupRepo;
import com.zyppysselfhelp.service.ShLookupService;

@RestController

@RequestMapping("/api/shlookup")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShLookupController 
{
	
	protected static final Logger log = LoggerFactory.getLogger(ShLookupCategoryController.class.getName());

	@Autowired
	ShLookupRepo shlookupRepo;
	
	@Autowired
	ShLookupService  shLookupService;
	
	@PostMapping("/addShLookup")
	public ResponseEntity<ResponseBean> addShLookupCategory(@RequestBody ShLookup shlookup)
	{
		log.info("/addShLookup");
	    ResponseBean responseBean = new ResponseBean();
        ShLookup shLookup2= new ShLookup();
        try {
            Long startTime = Calendar.getInstance().getTimeInMillis();

            shLookup2 = shLookupService.saveShLookup(shlookup);
            //System.out.println(shlookupCategory.getCatgName()+shlookupCategory.getCatgDesc()+shlookupCategory.getStatus());
            responseBean.setObject(shLookup2);
            Long endTime = Calendar.getInstance().getTimeInMillis();
            responseBean.setResponseTime(endTime - startTime);
            responseBean.setStatusMSG("ShLookup has been uploaded successfully!");
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
	//lookup list by category
	@GetMapping("/getShLookUpById")
	public ResponseEntity<?> getInstructorById(@PathVariable int id)
	{
		ResponseBean responseBean = new ResponseBean();
		log.info("/getShLookUpById");
		try {
			Long startTime = Calendar.getInstance().getTimeInMillis();
			ShLookup shLookup= shLookupService.getShLookupById(id);
			if(shLookup!=null)
			{
				responseBean.setStatusMSG("ShLookUpById Sucessfully");
				responseBean.setObject(shLookup);
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
	
	

}
