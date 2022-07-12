package com.zyppysselfhelp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zyppysselfhelp.modal.ShLookupCategory;

public interface ShLookupCategoryRepo extends JpaRepository<ShLookupCategory,Integer> {



	ShLookupCategory findByshLookupCatgId(int id);
}
