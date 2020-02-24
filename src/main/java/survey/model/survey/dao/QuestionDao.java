package survey.model.survey.dao;

import java.util.List;

import survey.model.survey.Question;

public interface QuestionDao {

	public List<Question> getAllQuestions();

	public List<Question> getSectionQuestions(Long sectionId);

	public Question getQuestion(Long id);

	public Question getSectionQuestion(Long sectionId, Long questionId);

	public Question saveQuestion(Question question);
	
	public Question updateQuestion(Long sectionId, Long index, Long questionId, Question question);

	public Question isExist(String questionType, String questionDescription);

	public void removeQuestion(long id);
}
