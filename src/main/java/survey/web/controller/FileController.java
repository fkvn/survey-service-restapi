package survey.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import survey.exception.AddingQuestionError;
import survey.model.core.File;
import survey.model.core.User;
import survey.model.core.dao.FileDao;
import survey.model.core.dao.UserDao;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private FileDao fileDao;

	@GetMapping("/{file_id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Resource> downloadFile(@PathVariable Long file_id) {

		// has to change later if we want to do authorization
		// User user = userDao.getUser(1);

		// Load file from database
		File file = fileDao.getFile(file_id);
		
//		if (!file.getOwnerId().equals(sub))
//			throw new AccessDeniedException("403 returned");

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(new ByteArrayResource(file.getFileData()));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> uploadFile(@ModelAttribute("sub") String sub,
			@RequestParam(value = "file", required = true) MultipartFile file) {

		try {
			
			 File newFile = fileDao.uploadFile(file, sub);
			 Map<String, String> jsonFile = new HashMap<>();
			 jsonFile.put("url", newFile.getUrl());
			
			 return jsonFile;

		} catch (Exception e) {
			System.out.println("uploading error");
			throw new AddingQuestionError(e.getLocalizedMessage());
		}
	}
	
	@DeleteMapping("/{file_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeFile(@ModelAttribute("sub") String sub, @PathVariable Long file_id) {

		File file = fileDao.getFile(file_id);
		
		if (!file.getOwnerId().equals(sub))
			throw new AccessDeniedException("403 returned");
		
		fileDao.deleteFile(file_id);
	}
}
