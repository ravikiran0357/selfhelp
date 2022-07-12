package com.zyppysselfhelp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zyppysselfhelp.bean.ContentRequest;
import com.zyppysselfhelp.modal.Content;
import com.zyppysselfhelp.modal.ContentLocation;
import com.zyppysselfhelp.repo.ContentLocationRepo;
import com.zyppysselfhelp.repo.ContentRepo;

@Service
public class ContentServicesImpl implements ContentServices {

	@Autowired
	private ContentRepo repo;
	@Autowired
	private ContentLocationRepo contentLocationRepo;

	@Autowired
	private S3BucketStorageService s3BucketStorageService;

	@Override
	public Content saveContent(ContentRequest contentRequest, MultipartFile mFile) throws IOException {
		String extension = "";
		String fileType = "";
		Content content;
		String fileName = "";
		Tika tika;
		File file;
		String mimeType = "";
		int i;
		String url = "";
		
		file = convertMultiPartFileToFile(mFile);
		tika = new Tika();
		mimeType = tika.detect(file);
       
		if(contentRequest.getContentSource()=="s3") {
			url = s3BucketStorageService.uploadFile(mFile);
		}else {
			url=contentRequest.getLink();
		}

		// set content object
		content = new Content();

		fileName = file.getName();
		i = fileName.lastIndexOf('.');
		if (i >= 0) {
			extension = fileName.substring(i + 1);
		}
		System.out.println(extension);
		int j = mimeType.lastIndexOf('/');
		if (j >= 0) {
			fileType = mimeType.substring(0, j);
		}
		System.out.println(fileType);
		content.setContentFormatExtension(extension);
		content.setContentFormatType(fileType);
		content.setLanguageId(Integer.parseInt(contentRequest.getLanguage()));
		content.setContentSource(contentRequest.getContentSource());
		content.setHashtags(contentRequest.getHashtags());
		content.setIsPremium(Byte.parseByte(contentRequest.getIsPremium()));
		content.setDeleted(false);
		content = repo.save(content);
		ContentLocation cl = new ContentLocation();
		System.out.println(url);
		cl.setActualContentLocation(url);
		cl.setContentQualityType(0);
		cl.setContent(content);
		//content.setContentLocations(Arrays.asList(cl));
		int id=contentLocationRepo.save(cl).getContentLocationId();
		System.out.println(id);
		
		// return "Content save successfully with Contentid -" + Content.getBookId();
		return content;
	}

	@Override
	public List<Content> getAllContent() {
		return repo.findAll();
	}

	@Override
	public List<Content> removeContent(int id) {
		repo.deleteById(id);
		return getAllContent();
	}

	@Override
	public Content getContentById(int id) {
		return repo.findByContentId(id);
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			// LOG.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}

	@Override
	public List<Content> listAll(String searchData) {
		if (searchData != null) {
            return repo.search(searchData);
        }
        return repo.findAll();
	}

}
