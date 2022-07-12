package com.zyppysselfhelp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zyppysselfhelp.modal.Instructor;

public interface InstructorRepo extends JpaRepository<Instructor,Integer>
{
	Instructor findByInstructorId(int id);

}
