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
import com.zyppysselfhelp.modal.ShLookupCategory;
import com.zyppysselfhelp.repo.ShLookupCategoryRepo;
import com.zyppysselfhelp.repo.ShLookupRepo;
import com.zyppysselfhelp.service.ShLookupCategoryService;

@RestController

@RequestMapping("/api/shlookupcategory")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShLookupCategoryController {

    @Autowired
    ShLookupCategoryService shLookupCategoryService;

    protected static final Logger log = LoggerFactory.getLogger(ShLookupCategoryController.class.getName());

    @Autowired
    private ShLookupCategoryRepo shlookupCategoryRepo;

    @Autowired
    private ShLookupRepo shLookupRepo;

    @PostMapping("/addShLookupCategory")
    public ResponseEntity<ResponseBean> addShLookupCategory(@RequestBody ShLookupCategory shlookupCategory) {
        log.info("/addShLookupCategory");
        ResponseBean responseBean = new ResponseBean();
        ShLookupCategory shLookupCategory2 = new ShLookupCategory();
        try {
            Long startTime = Calendar.getInstance().getTimeInMillis();

            shLookupCategory2 = shLookupCategoryService.saveShLookupCategory(shlookupCategory);
            //System.out.println(shlookupCategory.getCatgName()+shlookupCategory.getCatgDesc()+shlookupCategory.getStatus());
            responseBean.setObject(shLookupCategory2);
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
    @GetMapping("/getshLookupCategoryId/{id}")
    public ResponseEntity<ResponseBean> getshLookupCategoryId(@PathVariable int id ) {
        log.info("/shLookupCategory/{id}");
        ResponseBean responseBean = new ResponseBean();

        try {
            Long startTime = Calendar.getInstance().getTimeInMillis();
            ShLookupCategory shLookupCategory=shLookupCategoryService.getShLookupCategoryById(id);
            if(shLookupCategory!=null)
            {
                responseBean.setStatusMSG("ShLookupCategoryId Sucessfully");
                responseBean.setObject(shLookupCategory);
            } else {
                responseBean.setStatusMSG("Could not find a activeCategory");
            }    
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
    
    }