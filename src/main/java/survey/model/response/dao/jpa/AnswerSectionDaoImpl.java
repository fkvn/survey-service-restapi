package survey.model.response.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.response.AnswerSection;
import survey.model.response.dao.AnswerSectionDao;

@Repository
public class AnswerSectionDaoImpl implements AnswerSectionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AnswerSection> getAnswerSections(Long responseId) {

		return entityManager
				.createQuery("from AnswerSection where response.id = :responseId", AnswerSection.class)
				.setParameter("responseId", responseId).getResultList();
	}

	@Override
	public AnswerSection getAnswerSection(Long id) {

		return entityManager.find(AnswerSection.class, id);
	}

	@Override
	@Transactional
	public AnswerSection saveAnswerSection(AnswerSection answerSection) {

		return entityManager.merge(answerSection);
	}

	@Override
	@Transactional
	public void removeAnswerSection(Long id) {

		entityManager.remove(entityManager.find(AnswerSection.class, id));

	}

}
