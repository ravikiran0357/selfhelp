package com.zyppysselfhelp.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zyppysselfhelp.modal.Instructor;

public interface InstructorService 
{

	public Instructor saveInstructor(Instructor instructor, MultipartFile mFile) throws IOException;


	//public Content saveContent(MultipartFile file) throws IOException;

	public Instructor getInstructorById(int id);

	public List<Instructor> getAllInstructor();

	public List<Instructor> removeInstructor(int id);

}
