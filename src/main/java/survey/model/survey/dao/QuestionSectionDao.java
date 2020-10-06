package survey.model.survey.dao;

import java.util.List;

import survey.model.survey.Question;
import survey.model.survey.QuestionSection;

public interface QuestionSectionDao {

	public List<QuestionSection> getQuestionSections(Long surveyId);

	public QuestionSection getQuestionSection(Long id);

	public QuestionSection saveQuestionSection(QuestionSection questionSection);
	
	public QuestionSection moveQuestionInSections(Long surveyId, Long sectionId, int oldIndex, int newIndex);

	public void removeQuestionSection(Long surveyId, Long id);

	public void removeQuestionSection(Long id);
}
