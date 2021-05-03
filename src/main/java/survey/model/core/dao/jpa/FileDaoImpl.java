package survey.model.core.dao.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import survey.model.core.File;
import survey.model.core.User;
import survey.model.core.dao.FileDao;

@Repository
public class FileDaoImpl implements FileDao {
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	@Transactional
	public File uploadFile(MultipartFile file, String ownerId) {
		System.out.println("uploading files");
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (!fileName.equals("") && !fileName.equals("..")) {
				File newFile = new File();
				
				newFile.setOwnerId(ownerId);
				newFile.setName(fileName);
				newFile.setDate(new Date());
				newFile.setType(file.getContentType());
				newFile.setFileData(file.getBytes());
				newFile.setSize(file.getSize());

				newFile = entityManager.merge(newFile);

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/files/").path(String.valueOf(newFile.getId())).toUriString();

				newFile.setUrl(fileDownloadUri);

				newFile = entityManager.merge(newFile);

				return newFile;

			}
		} catch (Exception ex) {
		}

		return null;
	}

	@Override
	public File getFile(Long fileId) {

		return entityManager.find(File.class, fileId);
	}

	@Override
	@Transactional
	public void deleteFile(Long fileId) {
		entityManager.remove(entityManager.find(File.class, fileId));
	}

}
