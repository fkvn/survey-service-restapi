package survey.model.core.dao;

import org.springframework.web.multipart.MultipartFile;

import survey.model.core.File;
import survey.model.core.User;

public interface FileDao {
	File uploadFile(MultipartFile file, String ownerId);

	File getFile(Long fileId);
	
	void deleteFile(Long fileId);
}
