package survey.model.response.dao;

import java.util.List;

import survey.model.response.AnswerSection;

public interface AnswerSectionDao {

	List<AnswerSection> getAnswerSections(Long responseId);

	AnswerSection getAnswerSection(Long id);
	
	AnswerSection saveAnswerSection(AnswerSection answerSection);
	
	void removeAnswerSection(Long id);
}
