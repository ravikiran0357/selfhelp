package com.zyppysselfhelp.service;

import java.io.IOException;
import java.util.List;

import com.zyppysselfhelp.modal.ShLookupCategory;

public interface ShLookupCategoryService {
	  public ShLookupCategory saveShLookupCategory(ShLookupCategory shlookupCategory) throws IOException;

	    // public Content saveContent(MultipartFile file) throws IOException;

	    public ShLookupCategory getShLookupCategoryById(int id);

	    public List<ShLookupCategory> getAllShLookupCategory();

	    public List<ShLookupCategory> removeShLookupCategory(int id);

}
