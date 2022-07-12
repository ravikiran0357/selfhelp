package com.zyppysselfhelp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class S3BucketStorageServiceImpl implements S3BucketStorageService {

	private static final Logger LOG = LoggerFactory.getLogger(S3BucketStorageServiceImpl.class);

	@Value("${bucketName}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3;

	@Autowired
	private AmazonS3Client awsS3Client;

//	public S3Service(AmazonS3 s3) {
//	this.s3 = s3;
//}
	@Override
	public String uploadFile(MultipartFile file) {

		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		//String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String key = System.currentTimeMillis() + "." + file.getOriginalFilename();

		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentLength(file.getSize());
		metaData.setContentType(file.getContentType());

		try {
			awsS3Client.putObject(bucketName, key, file.getInputStream(), metaData);
			awsS3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
			return awsS3Client.getResourceUrl(bucketName, key);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"An exception occured while uploading the file");
		}

	}

//	@Override
//	public String uploadFile(MultipartFile file) throws AmazonServiceException, SdkkeyClientException, IOException {
//		// File fileObj = convertMultiPartFileToFile(file);
//		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//		ObjectMetadata metadata = new ObjectMetadata();
//		metadata.setContentLength(file.getSize());
//		awsS3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
//		// s3Client.putObject(new PutObjectRequest(bucketName,
//		// fileName,fileObj,metadata));
//		// s3Client.setObjectAcl(bucketName,fileName,CannedAccessControlList.PublicRead);
//		// awsS3Client.setObjectAcl(bucketName, fileName,
//		// CannedAccessControlList.PublicRead);
//		// URL url = s3Client.getUrl(bucketName, fileName);
//
//		String url = awsS3Client.getResourceUrl(bucketName, fileName);
//		System.out.println(url.toString());
//
//		// fileObj.delete();
//		return "File uploaded : " + fileName + "-" + url.toString();
//	}

//	@Override
//	public String uploadFile(MultipartFile file) {
//		File fileObj = convertMultiPartFileToFile(file);
//		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//		
//		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
//		
//		s3Client.setObjectAcl(bucketName, fileName,CannedAccessControlList.PublicRead);
//		
//		URL url=s3Client.getUrl(bucketName, fileName);
//		System.out.println(url.toString());
//		// s3Client.getResourceUrl(bucketName, fileName);
//		fileObj.delete();
//		return "File uploaded : " + fileName;
//	}

	@Override
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = awsS3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteFile(String fileName) {
		awsS3Client.deleteObject(bucketName, fileName);
		return fileName + " removed ...";
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			LOG.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}
}