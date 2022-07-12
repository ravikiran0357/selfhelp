package com.zyppysselfhelp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zyppysselfhelp.modal.Content;
import com.zyppysselfhelp.modal.ContentLocation;
import com.zyppysselfhelp.modal.Instructor;
import com.zyppysselfhelp.repo.InstructorRepo;
@Service
public class InstructorServiceImpl implements InstructorService 
{
	@Autowired
	private InstructorRepo instructorRepo;
	@Autowired
	private S3BucketStorageService s3BucketStorageService;

	@Override
	public Instructor saveInstructor(Instructor instructor, MultipartFile mFile) throws IOException {
		String extension = "";
		String fileType = "";
		String fileName = "";
		Instructor instructor1;
		Tika tika;
		File file;
		String mimeType = "";
		int i;
		String url = "";
		
		file = convertMultiPartFileToFile(mFile);
		tika = new Tika();
		mimeType = tika.detect(file);
        url = s3BucketStorageService.uploadFile(mFile);
		fileName = file.getName();
		i = fileName.lastIndexOf('.');
		if (i >= 0) {
			extension = fileName.substring(i + 1);
		}
		System.out.println(extension);
		int j = mimeType.lastIndexOf('/');
		if (j >= 0) {
			fileType = mimeType.substring(0, j);
		}
		System.out.println(fileType);
		instructor.setProfilePic(url);
	    instructor1= instructorRepo.save(instructor);
		System.out.println(url);
		
		
		// return "Content save successfully with Contentid -" + Content.getBookId();
		return instructor1;
	}

	@Override
	public Instructor getInstructorById(int id) {
	
		return instructorRepo.findByInstructorId(id);
	}

	@Override
	public List<Instructor> getAllInstructor() {
		
		return instructorRepo.findAll();
	}

	@Override
	public List<Instructor> removeInstructor(int id) {
		instructorRepo.deleteById(id);
		return getAllInstructor() ;
	}
	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			// LOG.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}

}
