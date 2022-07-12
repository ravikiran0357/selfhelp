package com.zyppysselfhelp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zyppysselfhelp.modal.ShLookup;
import com.zyppysselfhelp.modal.ShLookupCategory;

public interface ShLookupRepo extends JpaRepository<ShLookup,Integer>{
	ShLookup findByshLookupId(int id);
}
