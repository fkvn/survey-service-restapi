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
	public File uploadFile(MultipartFile file, User user) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (!fileName.equals("") && !fileName.equals("..")) {
				File newFile = new File();
				
				newFile.setOwner(user);
				newFile.setName(fileName);
				newFile.setDate(new Date());
				newFile.setType(file.getContentType());
				newFile.setFileData(file.getBytes());
				newFile.setSize(file.getSize());

				newFile = entityManager.merge(newFile);

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/downloadFile/").path(String.valueOf(newFile.getId())).toUriString();

				newFile.setUrl(fileDownloadUri);

				newFile = entityManager.merge(newFile);

				return newFile;

			}
		} catch (Exception ex) {
		}

		return null;
	}

	@Override
	public File getFile(int file_id) {

		return entityManager.find(File.class, file_id);
	}

}
