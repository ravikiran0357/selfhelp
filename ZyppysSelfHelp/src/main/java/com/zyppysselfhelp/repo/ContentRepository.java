package com.zyppysselfhelp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zyppysselfhelp.modal.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

	Content findByContentId(int id);

}
