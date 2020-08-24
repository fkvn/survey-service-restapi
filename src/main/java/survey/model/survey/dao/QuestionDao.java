package survey.model.survey.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import survey.model.core.File;
import survey.model.survey.Question;
// import survey.model.survey.dao.jpa.File;

public interface QuestionDao {

	public List<Question> getAllQuestions();

	public List<Question> getSectionQuestions(Long sectionId);

	public Question getQuestion(Long id);

	public Question getSectionQuestion(Long surveyId, Long sectionId, Long questionId);

	public Question saveQuestion(Question question);

	public Question updateQuestion(Long sectionId, Long index, Long questionId, Question question,
			List<File> files);

	public Question isExist(String questionType, String questionDescription);

	public void removeQuestion(long id);
}
