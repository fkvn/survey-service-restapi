package survey.model.statistic.dao.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.response.RatingAnswer;
import survey.model.response.SurveyResponse;
import survey.model.statistic.MultipleChoiceQRS;
import survey.model.statistic.QuestionResultSummary;
import survey.model.statistic.RankingQRS;
import survey.model.statistic.RatingQRS;
import survey.model.statistic.ResponseGroup;
import survey.model.statistic.TextQRS;
import survey.model.statistic.dao.QuestionResultSummaryDao;
import survey.model.survey.Question;
import survey.model.survey.QuestionSection;

@Repository
public class QuestionResultSummaryDaoImpl implements QuestionResultSummaryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public QuestionResultSummary getQuestionResultSummary(Long id) {

		QuestionResultSummary qResultSummary = entityManager
				.createQuery("from QuestionResultSummary where id = :id", QuestionResultSummary.class)
				.setParameter("id", id).getSingleResult();

		return qResultSummary;
	}

	@Override
	@Transactional
	public QuestionResultSummary saveQuestionResultSummary(QuestionResultSummary qResultSummary) {

		return entityManager.merge(qResultSummary);
	}

	@Override
	@Transactional
	public void removeQuestionResultSummary(Long id) {

		entityManager.remove(entityManager.find(QuestionResultSummary.class, id));

	}

	@Override
	public QuestionResultSummary getQuestionResultSummaryByQuestionId(Long id) {

		QuestionResultSummary qResultSummary = null;

		try {
			qResultSummary =
					entityManager.createQuery("from QuestionResultSummary where question.id = :id",
							QuestionResultSummary.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
		} ;


		return qResultSummary;
	}

	//


	@Override
	public List<QuestionResultSummary> getAllQRSFromSurvey(long surveyId) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<QuestionResultSummary> getAllQRSFromSection(Long sectionId) {

		List<QuestionResultSummary> qResultSummaries = new ArrayList<>();

		QuestionSection section = entityManager.find(QuestionSection.class, sectionId);

		List<Question> questions = section.getQuestions();

		questions.forEach(q -> {
			Question question = q;

			QuestionResultSummary qResultSummary = null;

			try {
				qResultSummary =
						entityManager
								.createQuery("from QuestionResultSummary where question.id = :id",
										QuestionResultSummary.class)
								.setParameter("id", question.getId()).getSingleResult();
			} catch (Exception e) {
			} ;


			if (qResultSummary != null) {
				System.out.println(question.getId());
				System.out.println("existed");
				entityManager.remove(qResultSummary);
			}

			switch (question.getDecriminatorValue()) {
				case "MULTIPLE_CHOICE": {
					qResultSummary =
							new MultipleChoiceQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				case "RANKING": {
					qResultSummary = new RankingQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				case "RATING": {
					qResultSummary = new RatingQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				case "TEXT": {
					qResultSummary = new TextQRS(question.getQuestionSection().getSurvey(), question);
				}
					break;

				default:
					break;
			}

			if (qResultSummary != null) {
				qResultSummaries.add(entityManager.merge(qResultSummary));
			}

		});

		return qResultSummaries;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> getRatingQuestionChartInfo(Long questionId) {

		Question question = entityManager.find(Question.class, questionId);

		Map<Object, Object> chartInfo = new HashMap<>();

		List<Integer> years = new ArrayList<>();
		years = entityManager
				.createQuery("select distinct year(rec.date) "
						+ "FROM SurveyResponse rec, Question q where q.id = :qId "
						+ "and q.questionSection.survey.id = rec.survey.id", Integer.class)
				.setParameter("qId", questionId).getResultList();

		chartInfo.put("categories", years);

		Map<String, Object> series = new HashMap<>();
		series.put("name", "ANONYMOUS");
		series.put("data", new ArrayList<>());

		for (int i = 0; i < years.size(); i++) {

			List<SurveyResponse> responses = new ArrayList<>();

			responses =
					entityManager
							.createQuery("from SurveyResponse " + "where " + "year(date) = :year and "
									+ "survey.id = :survId", SurveyResponse.class)
							.setParameter("year", years.get(i))
							.setParameter("survId", question.getQuestionSection().getSurvey().getId())
							.getResultList();

			double avg = 0.0;

			for (int j = 0; j < responses.size(); j++) {
				RatingAnswer ans = null;

				ans = entityManager.createQuery(
						"select ans from RatingAnswer ans, SurveyResponse rec where " + "rec.id = :recId and "
								+ "ans.answerSection.response.id = :recId and " + "ans.question.id = :qId",
						RatingAnswer.class).setParameter("recId", responses.get(j).getId())
						.setParameter("qId", questionId).getSingleResult();

				if (ans.getDecriminatorValue().equals("RATING")) {
					avg += ans.getRating();
				}


			}

			avg /= responses.size();

			((ArrayList<Double>) series.get("data")).add(avg);

		}

		chartInfo.put("series", series);

		return chartInfo;
	}



	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> getRatingQuesChartInfoForResponseGroups(Long questionId,
			List<ResponseGroup> resGroups) {

		Map<Object, Object> chartInfo = new HashMap<>();

		Question question = entityManager.find(Question.class, questionId);

		System.out.println(question.getDecriminatorValue());
		
		switch (question.getDecriminatorValue()) {
			case "RATING": {
				List<Integer> years = entityManager
						.createQuery("select distinct year(rec.date) "
								+ "FROM SurveyResponse rec where rec.survey.id = :surveyId", Integer.class)
						.setParameter("surveyId", question.getQuestionSection().getSurvey().getId())
						.getResultList();
				
				Collections.sort(years);

				chartInfo.put("categories", years);


				List<Map<String, Object>> series = new ArrayList<>();


				resGroups.forEach((resGroup) -> {


					Map<String, Object> serie = new HashMap<>();
					serie.put("data", new ArrayList<>());

					// serie name
					serie.put("name", resGroup.getName());
//					switch (resGroup.getGroupBy()) {
//						case "surveyId;questionId;mulChoiceSelectionIndex": {
//							if (resGroup.getGroupedValue().split(";").length == 3) {
//								MultipleChoiceQuestion q = entityManager.find(MultipleChoiceQuestion.class,
//										Long.valueOf(resGroup.getGroupedValue().split(";")[1]));
//
//								String serieName =
//										q.getChoices().get(Integer.valueOf(resGroup.getGroupedValue().split(";")[2]));
//								serie.put("name", serieName);
//							}
//						}
//							break;
//						default:
//							break;
//					}

					// getting data
					List<Long> responseIDs = new ArrayList<>();
					resGroup.getResponses().forEach((SurveyResponse res) -> {
						responseIDs.add(res.getId());
					});

					years.forEach(year -> {
						
						List<SurveyResponse> responses = entityManager
								.createQuery("from SurveyResponse " + "where " + "year(date) = :year and "
										+ "id in (:recIds)", SurveyResponse.class)
								.setParameter("year", year).setParameter("recIds", responseIDs).getResultList();

						double avg = 0.0;

						for (int j = 0; j < responses.size(); j++) {
							RatingAnswer ans = null;

							ans = entityManager
									.createQuery("select ans from RatingAnswer ans, SurveyResponse rec where "
											+ "rec.id = :recId and " + "ans.answerSection.response.id = :recId and "
											+ "ans.question.id = :qId", RatingAnswer.class)
									.setParameter("recId", responses.get(j).getId()).setParameter("qId", questionId)
									.getSingleResult();

							if (ans.getDecriminatorValue().equals("RATING")) {
								avg += ans.getRating();
							}


						}

						avg /= responses.size();
						((ArrayList<Double>) serie.get("data")).add(avg);

					});

					series.add(serie);
				});

				chartInfo.put("series", series);
			}
				break;
			default:
				break;
		}

		return chartInfo;
	}



}
