package survey.model.survey.dao;

import java.util.List;

import survey.model.survey.QuestionSection;

public interface QuestionSectionDao {

	public List<QuestionSection> getQuestionSection(Long surveyId);

	public QuestionSection getQuestionSection(long id);

	public QuestionSection saveQuestionSection(QuestionSection questionSection);

	public void removeQuestionSection(long id);
}
