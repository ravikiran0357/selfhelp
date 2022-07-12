package com.zyppysselfhelp.service;

import java.io.IOException;
import java.util.List;

import com.zyppysselfhelp.modal.ShLookup;


public interface ShLookupService 
{
	 public ShLookup saveShLookup(ShLookup shlookup) throws IOException;

	    // public Content saveContent(MultipartFile file) throws IOException;

	    public ShLookup getShLookupById(int id);

	    public List<ShLookup> getAllShLookup();

	    public List<ShLookup> removeShLookup(int id);

}
