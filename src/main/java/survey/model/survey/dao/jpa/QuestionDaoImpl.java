package survey.model.survey.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import survey.model.core.File;
import survey.model.survey.Question;
import survey.model.survey.dao.QuestionDao;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Question> getAllQuestions() {

		return entityManager.createQuery("from Question", Question.class).getResultList();
	}


	@Override
	public List<Question> getSectionQuestions(Long sectionId) {

		return entityManager
				.createQuery("from Question where question_section_id =:sectionId", Question.class)
				.setParameter("sectionId", sectionId).getResultList();
	}

	@Override
	public Question getQuestion(Long id) {

		return entityManager.find(Question.class, id);
	}


	@Override
	public Question getSectionQuestion(Long surveyId, Long sectionId, Long questionId) {


		Question question = entityManager.find(Question.class, questionId);

		if (question.getQuestionSection().getId().equals(sectionId)
				&& question.getQuestionSection().getSurvey().getId().equals(surveyId)) {

			return entityManager.find(Question.class, questionId);
		}

		return null;
	}

	@Override
	@Transactional
	public Question saveQuestion(Question question) {

		return entityManager.merge(question);
	}

	@Override
	@Transactional
	public void removeQuestion(long id) {

		Question question = entityManager.find(Question.class, id);
		entityManager.remove(question);
	}

	@Override
	public Question isExist(String questionType, String questionDescription) {

		try {
			return entityManager
					.createQuery(
							"from Question where question_type = :questionType and description = :description",
							Question.class)
					.setParameter("description", questionDescription)
					.setParameter("questionType", questionType).getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}


	@Override
	@Transactional
	public Question updateQuestion(Long sectionId, Long index, Long questionId, Question question,
			List<File> files) {

		try {

			Question existedQuestion = entityManager.find(Question.class, questionId);

			existedQuestion.getAttachments().forEach(file -> {
				entityManager.remove(entityManager.find(File.class, ((File) file).getId()));
			});

			existedQuestion.setAttachments(new ArrayList<>());

			if (existedQuestion.getDecriminatorValue() != question.getDecriminatorValue()) {

				entityManager.remove(existedQuestion);

				question.setAttachments(files);

				existedQuestion = entityManager.merge(question);

				entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0;").executeUpdate();

				entityManager.createNativeQuery(
						"update question set id = :newId, question_section_id = :sectionId, question_index = :index where id = :oldId")
						.setParameter("newId", questionId).setParameter("sectionId", sectionId)
						.setParameter("index", index).setParameter("oldId", existedQuestion.getId())
						.executeUpdate();

				entityManager
						.createNativeQuery(
								"update question_attachments set question_id = :newIq where question_id = :oldId")
						.setParameter("newIq", questionId).setParameter("oldId", existedQuestion.getId())
						.executeUpdate();

				if (question.getDecriminatorValue().equals("MULTIPLE_CHOICE"))
					entityManager
							.createNativeQuery(
									"update question_choices set question_id = :newId where question_id = :oldId")
							.setParameter("newId", questionId).setParameter("oldId", existedQuestion.getId())
							.executeUpdate();

				if (question.getDecriminatorValue().equals("RANKING")) {
					entityManager.createNativeQuery(
							"update question_ranking_choices set question_id = :newId where question_id = :oldId")
							.setParameter("newId", questionId).setParameter("oldId", existedQuestion.getId())
							.executeUpdate();
				}

				entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1;").executeUpdate();

				return entityManager.find(Question.class, questionId);

			} else {
				existedQuestion.updateQuestion(question, files);
				return entityManager.merge(existedQuestion);
			}
		} catch (Exception e) {
			throw e;
		}
	}


}
