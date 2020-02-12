package survey.model.survey.dao;

import java.util.List;

import survey.model.survey.QuestionSection;

public interface QuestionSectionDao {

	public List<QuestionSection> getQuestionSections(Long surveyId);

	public QuestionSection getQuestionSection(Long id);

	public QuestionSection saveQuestionSection(QuestionSection questionSection);

	public void removeQuestionSection(Long surveyId, Long id);
}
