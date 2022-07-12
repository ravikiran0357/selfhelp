package com.zyppysselfhelp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

public interface S3BucketStorageService {

	String uploadFile(MultipartFile file) throws AmazonServiceException, SdkClientException, IOException;

	byte[] downloadFile(String fileName);

	String deleteFile(String fileName);

}
