package com.zyppysselfhelp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyppysselfhelp.modal.ShLookup;

import com.zyppysselfhelp.repo.ShLookupRepo;

@Service
public class ShLookupServiceimpl implements ShLookupService
{

	@Autowired
	ShLookupRepo shlookupRepo;
	@Override
	public ShLookup saveShLookup(ShLookup shlookup) throws IOException {
		// TODO Auto-generated method stub
		return shlookupRepo.save(shlookup);
	}

	@Override
	public ShLookup getShLookupById(int id) {
		// TODO Auto-generated method stub
		return shlookupRepo.findByshLookupId(id);
	}

	@Override
	public List<ShLookup> getAllShLookup() {
		// TODO Auto-generated method stub
		return shlookupRepo.findAll();
	}

	@Override
	public List<ShLookup> removeShLookup(int id) {
		shlookupRepo.deleteById(id);;
	return getAllShLookup() ;
	}
	

}
