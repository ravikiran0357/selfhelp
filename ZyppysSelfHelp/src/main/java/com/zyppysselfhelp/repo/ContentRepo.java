package com.zyppysselfhelp.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zyppysselfhelp.modal.Content;

@Repository
public interface ContentRepo extends JpaRepository<Content, Integer> {

	Content findByContentId(int id);
	
	@Query("SELECT p FROM Content p WHERE p.hashtags=?1")
	List<Content> search(String searchData);
	
	
	@Query("SELECT p FROM Content p WHERE p.hashtags=?1")
	Page<Content> findByName(String searchBy, Pageable pageable);


	
//	@Override
//	@Query(nativeQuery = true ,value = "Select * from content c where c.deleted=false")
//	public List<Content> findAll();

//	Look up deleted entities
//	@Query("select c from Content e where c.delete=true")
//	public List<Content> recycleBin(); 

	//Soft delete.
//	@Query("update Content c set c.delete=true where c.contentId=?1")
//	@Modifying
//	public void inactive(String id); 
//	
//	@Query("update Content c set c.delete=false where c.contentId=?1")
//	@Modifying
//	public void active(String id); 
	
	

}
