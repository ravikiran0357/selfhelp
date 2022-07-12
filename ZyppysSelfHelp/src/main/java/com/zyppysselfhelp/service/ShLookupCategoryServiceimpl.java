package com.zyppysselfhelp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyppysselfhelp.modal.ShLookupCategory;
import com.zyppysselfhelp.repo.ShLookupCategoryRepo;

@Service
public class ShLookupCategoryServiceimpl implements ShLookupCategoryService {

    @Autowired
    ShLookupCategoryRepo ShLookupCategoryRepo;

    @Override
    public ShLookupCategory saveShLookupCategory(ShLookupCategory shlookupCategory) {

        return ShLookupCategoryRepo.save(shlookupCategory);
    }

    @Override
    public ShLookupCategory getShLookupCategoryById(int id) {
        // TODO
        return ShLookupCategoryRepo.findByshLookupCatgId(id);
    }

    @Override
    public List<ShLookupCategory> getAllShLookupCategory() {
        return ShLookupCategoryRepo.findAll();
    }

    @Override
    public List<ShLookupCategory> removeShLookupCategory(int id) {
        ShLookupCategoryRepo.deleteById(id);;
        return getAllShLookupCategory() ;
    }

}
