package survey.model.response.dao.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.response.SurveyResponse;
import survey.model.response.dao.SurveyResponseDao;
import survey.model.survey.Question;

@Repository
public class SurveyResponseDaoImpl implements SurveyResponseDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SurveyResponse> getSurveyResponsesAll(Long surveyId) {

		return entityManager
				.createQuery("from SurveyResponse where survey.id =:surveyId", SurveyResponse.class)
				.setParameter("surveyId", surveyId).getResultList();
	}

	@Override
	public List<SurveyResponse> getSurveyResponses(Long surveyId) {

		return entityManager.createQuery(
				"select res from SurveyResponse res where survey.id =:surveyId and isDeleted = false",
				SurveyResponse.class).setParameter("surveyId", surveyId).getResultList();

		// return null;
	}


	@Override
	public SurveyResponse getResponse(Long id) {

		return entityManager.find(SurveyResponse.class, id);
	}

	@Override
	@Transactional
	public SurveyResponse saveResponse(SurveyResponse response) {

		return entityManager.merge(response);
	}

	@Override
	@Transactional
	public void removeResponse(Long id) {

		SurveyResponse surveyResponse = entityManager.find(SurveyResponse.class, id);
		entityManager.remove(surveyResponse);
	}

	@Override
	public List<SurveyResponse> getAllResponses() {

		List<SurveyResponse> responses = new ArrayList<>();

		responses = entityManager
				.createQuery("from SurveyResponse where isDeleted = false", SurveyResponse.class)
				.getResultList();

		return responses;
	}

	@Override
	public List<SurveyResponse> getSurveyResponsesByQuestionId(Long questionId) {

		List<SurveyResponse> responses = new ArrayList<>();
		responses = entityManager.createQuery(
				"select res from SurveyResponse res, Question q where q.id = :questionId and res.survey.id = q.questionSection.survey.id and res.isDeleted = false",
				SurveyResponse.class).setParameter("questionId", questionId).getResultList();

		return responses;
	}

	@Override
	public List<SurveyResponse> getSurveyResponsesByQuestionDesc(String desc) {

		List<SurveyResponse> responses = new ArrayList<>();
		responses = entityManager.createQuery(
				"select res from SurveyResponse res, Question q where q.description = :desc and res.survey.id = q.questionSection.survey.id and res.isDeleted = false",
				SurveyResponse.class).setParameter("desc", desc).getResultList();

		return responses;
	}

	@Override
	public List<SurveyResponse> getSurveyResponsesByQuestionAnswer(Long questionId, String answer) {

		Question question = entityManager.find(Question.class, questionId);
		List<SurveyResponse> responses = new ArrayList<>();

		switch (question.getDecriminatorValue()) {
			case "MULTIPLE_CHOICE": {
				Integer ansSelectionIndex = Integer.valueOf(answer);
				responses = entityManager.createQuery(
						"select distinct res from SurveyResponse res, Question q , MultipleChoiceAnswer ans join ans.selections selection "
								+ "where " + "q.id = :questionId and " + "ans.question.id = :questionId and  "
								+ "selection = :selectionIndex and "
								+ "res.id = ans.answerSection.response.id and res.isDeleted = false",
						SurveyResponse.class).setParameter("questionId", question.getId())
						.setParameter("selectionIndex", ansSelectionIndex).getResultList();
			}
				break;
			case "RANKING": {
				String[] rankInfo = answer.split(";");
				Integer rank = Integer.valueOf(rankInfo[0]);
				Integer rankChoiceIndex = Integer.valueOf(rankInfo[1]);

				System.out.println(rank);
				System.out.println(rankChoiceIndex);

				responses = entityManager.createQuery(
						"select distinct res from SurveyResponse res, Question q , RankingAnswer ans join ans.selectionRanks selRank "
								+ "where " + "q.id = :questionId and " + "ans.question.id = :questionId and  "
								+ "key(selRank) = :rank and selRank = :rankChoiceIndex and "
								+ "res.id = ans.answerSection.response.id and res.isDeleted = false",
						SurveyResponse.class).setParameter("questionId", question.getId())
						.setParameter("rank", rank).setParameter("rankChoiceIndex", rankChoiceIndex)
						.getResultList();
			}
				break;
			case "RATING": {
				Integer rating = Integer.valueOf(answer);
				responses = entityManager
						.createQuery(
								"select distinct res from SurveyResponse res, RatingQuestion q, RatingAnswer ans "
										+ "where " + "q.id = :questionId and " + "ans.question.id = :questionId and "
										+ "ans.rating = :rating and "
										+ "ans.rating > 0 and ans.rating <= q.ratingScale and "
										+ "res.id = ans.answerSection.response.id and res.isDeleted = false",
								SurveyResponse.class)
						.setParameter("questionId", question.getId()).setParameter("rating", rating)
						.getResultList();
			}
				break;
			case "TEXT": {
				if (answer.trim().equals("answered")) {
					responses = entityManager
							.createQuery(
									"select distinct res from SurveyResponse res, Question q, TextAnswer ans "
											+ "where " + "q.id = :questionId and " + "ans.question.id = :questionId and "
											+ "ans.text is not null and ans.text <> '' and "
											+ "res.id = ans.answerSection.response.id and res.isDeleted = false",
									SurveyResponse.class)
							.setParameter("questionId", question.getId()).getResultList();
				} else if (answer.trim().equals("skipped")) {
					responses = entityManager
							.createQuery(
									"select distinct res from SurveyResponse res, Question q, TextAnswer ans "
											+ "where " + "q.id = :questionId and " + "ans.question.id = :questionId and "
											+ "ans.text is not null and ans.text = '' and "
											+ "res.id = ans.answerSection.response.id and res.isDeleted = false",
									SurveyResponse.class)
							.setParameter("questionId", question.getId()).getResultList();
				}
			}
				break;
			default:
				break;
		}

		return responses;
	}

	@Override
	public List<SurveyResponse> getSurveyResponsesByQuestionAnswer(String questionDesc,
			String answer) {

		List<Question> questions =
				entityManager.createQuery("from Question where desc = :questionDesc", Question.class)
						.setParameter("questionDesc", questionDesc).getResultList();

		List<SurveyResponse> responses = new ArrayList<>();

		for (Question question : questions) {
			responses.addAll(getSurveyResponsesByQuestionAnswer(question.getId(), answer));
		}

		return responses;
	}

	@Override
	public List<SurveyResponse> getSurveyResponsesByYear(Long surveyId, Integer year) {

		List<SurveyResponse> responses = new ArrayList<>();

		responses =
				entityManager
						.createQuery("from SurveyResponse " + "where " + "year(date) = :year and "
								+ "survey.id = :surveyId", SurveyResponse.class)
						.setParameter("year", year).setParameter("surveyId", surveyId)
						.getResultList();

		return responses;
	}

	@Override
	public Map<Integer, List<SurveyResponse>> getSurveyResponsesByYears(Long surveyId) {

		Map<Integer, List<SurveyResponse>> responseByYears = new HashMap<>();

		List<Integer> years = entityManager
				.createQuery("select distinct year(rec.date) "
						+ "FROM SurveyResponse rec where rec.survey.id = :surveyId", Integer.class)
				.setParameter("surveyId", surveyId).getResultList();

		for(Integer year : years) {
			responseByYears.put(year, getSurveyResponsesByYear(surveyId, year));
		}
		
		// TODO Auto-generated method stub
		return responseByYears;
	}

	@Override
	public Map<Integer, List<SurveyResponse>> getResponseGroupByYearsOfSurvey(Long surveyId) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getResponseYears(Long surveyId) {
		List<Integer> years = new ArrayList<>();
		years = entityManager
				.createQuery("select distinct year(rec.date) "
						+ "FROM SurveyResponse rec where rec.survey.id = :surveyId", Integer.class)
				.setParameter("surveyId", surveyId).getResultList();
		// TODO Auto-generated method stub
		return years;
	}

}
