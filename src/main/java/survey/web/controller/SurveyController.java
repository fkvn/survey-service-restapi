package survey.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import survey.exception.AddingQuestionError;
import survey.exception.BadRequest;
import survey.exception.InvalidResponse;
import survey.exception.SurveyNotFoundException;
import survey.exception.UpdatingQuestionError;
import survey.exception.UpdatingSurveyError;
import survey.model.core.File;
import survey.model.core.User;
import survey.model.core.dao.FileDao;
import survey.model.core.dao.UserDao;
import survey.model.response.Answer;
import survey.model.response.AnswerSection;
import survey.model.response.MultipleChoiceAnswer;
import survey.model.response.RankingAnswer;
import survey.model.response.SurveyResponse;
import survey.model.response.dao.AnswerDao;
import survey.model.response.dao.AnswerSectionDao;
import survey.model.response.dao.SurveyResponseDao;
import survey.model.statistic.MultipleChoiceQRS;
import survey.model.statistic.QuestionResultSummary;
import survey.model.statistic.RankingQRS;
import survey.model.statistic.RatingQRS;
import survey.model.statistic.TextQRS;
import survey.model.statistic.dao.QuestionResultSummaryDao;
import survey.model.statistic.dao.ResponseGroupDao;
import survey.model.survey.MultipleChoiceQuestion;
import survey.model.survey.Question;
import survey.model.survey.QuestionSection;
import survey.model.survey.RankingQuestion;
import survey.model.survey.Survey;
import survey.model.survey.SurveyType;
import survey.model.survey.dao.QuestionDao;
import survey.model.survey.dao.QuestionSectionDao;
import survey.model.survey.dao.SurveyDao;
import survey.util.Views;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

	@Autowired
	private SurveyDao surveyDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private QuestionSectionDao questionSectionDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private SurveyResponseDao surveyResponseDao;

	@Autowired
	private AnswerSectionDao answerSectionDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private QuestionResultSummaryDao qResultSummaryDao;

	@Autowired
	private ResponseGroupDao resGroupDao;


	// get sub from access_token
	private String getSub(HttpServletRequest request) throws ParseException {

		if (request.getHeader("Authorization") == null
				|| request.getHeader("Authorization").length() == 0)
			return "";
		String token = request.getHeader("Authorization").split(" ")[1]; // get jwt from header
		String encodedPayload = token.split("\\.")[1]; // get second encoded part in jwt
		Base64 base64Url = new Base64(true);
		String payload = new String(base64Url.decode(encodedPayload));

		JSONParser parser = new JSONParser();
		JSONObject claimsObj = null;
		try {
			claimsObj = (JSONObject) parser.parse(payload);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return claimsObj.get("sub").toString();
	}

	// Survey

	// no need authorization
	@GetMapping("/open")
	@JsonView(Views.Public.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	// public List<Survey> getOpenSurvey(HttpServletRequest request) throws ParseException {
	// System.out.println("open");
	//
	// String sub = getSub(request);
	// if (sub == null || sub.length() == 0)
	// throw new AccessDeniedException("403 returned");
	//
	// System.out.println(sub);
	//
	// return surveyDao.getOpenSurveys();
	// }
	public List<Survey> getOpenSurvey(@ModelAttribute("sub") String sub) throws ParseException {

		System.out.println("open with sub from controller Advice");

//		if (sub == null || sub.length() == 0)
//			throw new AccessDeniedException("403 returned");

		System.out.println(sub);

		return surveyDao.getOpenSurveys();
	}

	@JsonView(Views.Public.class)
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Survey> getSurveys() {

		// has to change later if we want to do authorization
		User user = userDao.getUser(1);

		return surveyDao.getSurveys(user);
	}

	// @GetMapping("/closed")
	// @JsonView(Views.Public.class)
	// @ResponseStatus(HttpStatus.ACCEPTED)
	// public List<Survey> getClosedSurvey() {
	//
	// return surveyDao.getClosedSurveys();
	// }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long addSurvey(@RequestBody Survey survey) {

		//
		System.out.println("here");
		// // has to change later if we want to do authorization
		User user = userDao.getUser(1);

		survey.setAuthor(user);
		survey.setCreatedDate(new Date());

		System.out.println(survey.getName());

		if (survey.getType() == null) {
			survey.setType(SurveyType.ANONYMOUS);
		}


		survey.setQuestionSections(new ArrayList<>());

		survey = surveyDao.saveSurvey(survey);

		return survey.getId();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Internal.class)
	public Survey getSurvey(@PathVariable Long id) {

		Survey survey = surveyDao.getSurvey(id);
		System.out.println(survey == null);

		if (survey == null)
			throw new SurveyNotFoundException();

		return surveyDao.getSurvey(id);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long editSurvey(@PathVariable Long id, @RequestBody Map<String, Object> surveyInfo) {

		Survey survey = surveyDao.getSurvey(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (String key : surveyInfo.keySet()) {
			switch (key) {
				case "name":
					survey.setName((String) surveyInfo.get(key));
					break;
				case "description":
					survey.setDescription((String) surveyInfo.get(key));
					break;
				case "publishDate":
					Date publisedDate = null;
					try {
						publisedDate = formatter.parse((String) surveyInfo.get(key));
					} catch (ParseException e) {
						throw new UpdatingSurveyError(e.getLocalizedMessage());
					}
					survey.setPublishDate(publisedDate);
					break;
				case "closeDate":
					Date closeDate = null;
					try {
						closeDate = formatter.parse((String) surveyInfo.get(key));
					} catch (ParseException e) {
						throw new UpdatingSurveyError(e.getLocalizedMessage());
					}
					survey.setCloseDate(closeDate);
					break;
				case "closed":
					survey.setClosed((boolean) surveyInfo.get(key));
					break;
				default:
			}
		}

		survey = surveyDao.saveSurvey(survey);

		return survey.getId();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSurvey(@PathVariable Long id) {

		Survey survey = surveyDao.getSurvey(id);

		survey.getResponses().forEach(res -> {
			res.getAnswerSections().forEach(ansSection -> {
				ansSection.getAnswers().forEach(ans -> {
					answerDao.removeAnswer(((Answer) ans).getId());
				});
				answerSectionDao.removeAnswerSection(((AnswerSection) ansSection).getId());
			});
			surveyResponseDao.removeResponse(((SurveyResponse) res).getId());
		});

		survey.getQuestionSections().forEach(qSection -> {
			qSection.getQuestions().forEach(question -> {
				question.getAttachments().forEach(file -> {
					fileDao.deleteFile(((File) file).getId(), userDao.getUser(1));
				});
				questionDao.removeQuestion(question.getId());
			});
			questionSectionDao.removeQuestionSection(qSection.getId());;
		});


		surveyDao.removeSurvey(id);
	}

	// Survey > Section

	@GetMapping("/{surveyId}/questionResultSummaries")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public Map<Long, Object> getSurveyQRS(@PathVariable Long surveyId) {

		Survey survey = surveyDao.getSurvey(surveyId);
		Map<Long, Object> surveyQRS = new HashMap<>();

		survey.getQuestionSections().forEach(sec -> {

			surveyQRS.put(sec.getId(), getAllQRSFromSection(sec));

		});


		return surveyQRS;
	}

	// Survey > Section

	@JsonView(Views.Public.class)
	@GetMapping("/{surveyId}/sections")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<QuestionSection> getSections(@PathVariable Long surveyId) {

		return questionSectionDao.getQuestionSections(surveyId);
	}

	@PostMapping("/{surveyId}/sections")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addSection(@PathVariable Long surveyId,
			@RequestBody QuestionSection questionSection) {

		Survey survey = surveyDao.getSurvey(surveyId);

		questionSection.setQuestions(new ArrayList<>());
		questionSection = questionSectionDao.saveQuestionSection(questionSection);

		survey.getQuestionSections().add(questionSection);
		survey = surveyDao.saveSurvey(survey);

		return survey.getQuestionSections().get(survey.getNumOfSections() - 1).getId();
	}

	@GetMapping("/{surveyId}/sections/{sectionId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Internal.class)
	public QuestionSection getSection(@PathVariable Long sectionId) {

		return questionSectionDao.getQuestionSection(sectionId);
	}

	@PatchMapping("/{surveyId}/sections/{sectionId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long editSection(@PathVariable Long sectionId,
			@RequestBody Map<String, Object> questionSectionInfo) {

		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		for (String key : questionSectionInfo.keySet()) {
			switch (key) {

				case "description":
					questionSection.setDescription((String) questionSectionInfo.get(key));
					break;
				default:
			}
		}
		questionSection = questionSectionDao.saveQuestionSection(questionSection);
		return questionSection.getId();
	}

	@PutMapping("/{surveyId}/sections/{sectionId}/index")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateSectionIndex(@PathVariable Long surveyId, @PathVariable Long sectionId,
			@RequestBody Map<String, Object> requestInfo) {

		QuestionSection section = questionSectionDao.getQuestionSection(sectionId);
		int oldIndex = section.getSectionIndex();
		int newIndex = (int) requestInfo.get("index");

		// System.out.println(surveyId);
		// System.out.println(sectionId);
		// System.out.println(oldIndex);
		// System.out.println(newIndex);

		surveyDao.moveSectionInSurvey(surveyId, oldIndex, newIndex);

	}


	@DeleteMapping("/{surveyId}/sections/{sectionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSection(@PathVariable Long surveyId, @PathVariable Long sectionId) {

		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		if (questionSection.getQuestions().size() > 0) {

			questionSection.getQuestions().forEach(question -> {
				if (question != null) {
					question.getAttachments().forEach(file -> {
						fileDao.deleteFile(file.getId(), userDao.getUser(1));
					});
					questionDao.removeQuestion(question.getId());
				}
			});
		}

		questionSectionDao.removeQuestionSection(surveyId, sectionId);
	}

	// Survey > Section > Question

	@GetMapping("/{surveyId}/sections/{sectionId}/questions")
	@JsonView(Views.Public.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Question> getSectionQuestions(@PathVariable Long sectionId) {

		// QuestionSection qSection = ques

		return questionDao.getSectionQuestions(sectionId);
	}

	@GetMapping("/{surveyId}/sections/{sectionId}/questions/{questionId}")
	@JsonView(Views.Internal.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Question getSectionQuestion(@PathVariable Long surveyId, @PathVariable Long sectionId,
			@PathVariable Long questionId) {

		return questionDao.getSectionQuestion(surveyId, sectionId, questionId);
	}

	@PostMapping("/{surveyId}/sections/{sectionId}/questions")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addQuestion(@PathVariable Long surveyId, @PathVariable Long sectionId,
			@RequestPart("question") String strQuestion,
			@RequestPart(value = "files", required = false) MultipartFile[] files) {

		ObjectMapper mapper = new ObjectMapper();
		// System.out.println("before convert");
		// System.out.println(strQuestion.isEmpty());

		try {
			// System.out.println("trying convert");
			Question question = mapper.readValue(strQuestion, Question.class);
			// System.out.println(question.getDecriminatorValue());
			// System.out.println("after convert");
			QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

			// has to change later if we want to do authorization
			User user = userDao.getUser(1);

			// uploading file process
			if (files != null && files.length > 0) {
				try {
					// file validation

					// System.out.println(files.length);
					// System.out.println(files[0].isEmpty());
					// System.out.println(files[1].isEmpty());
					for (int i = 0; i < files.length; i++) {
						if (!files[i].isEmpty()
								&& !files[i].getContentType().split("/")[0].trim().equals("image")) {
							throw new AddingQuestionError(
									"Uploaded files are not supported!!! Only Image file type can be uploaded!");
						}
					}

					// generate question attachment lists (if null)
					if (question.getAttachments() == null) {
						question.setAttachments(new ArrayList<File>());
					}

					// uploading files and add to the question attachment lists
					for (int i = 0; i < files.length; i++) {
						if (!files[i].isEmpty()) {
							File newFile = fileDao.uploadFile(files[i], user);
							// System.out.println(newFile);
							// System.out.println(newFile.getName());

							question.getAttachments().add(newFile);
						}
					}

				} catch (Exception e) {
					throw new AddingQuestionError(e.getLocalizedMessage());
				}
			}

			// System.out.println("here");

			// adding question to database
			question = questionDao.saveQuestion(question);
			// System.out.println(question.getId());

			questionSection.getQuestions().add(question);
			questionSectionDao.saveQuestionSection(questionSection);

			return question.getId();

		} catch (JsonProcessingException e) {
			throw new AddingQuestionError(e.getLocalizedMessage());
		}

	}

	@PutMapping("/{surveyId}/sections/{sectionId}/questions/{questionId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long editQuestion(@PathVariable Long sectionId, @PathVariable Long questionId,
			@RequestPart("question") String strQuestion,
			@RequestPart(value = "files", required = false) MultipartFile[] files) {

		ObjectMapper mapper = new ObjectMapper();
		try {

			Question question = mapper.readValue(strQuestion, Question.class);

			// has to change later if we want to do authorization
			User user = userDao.getUser(1);

			// genrating files
			List<File> newFiles = new ArrayList<File>();
			if (files != null && files.length > 0) {
				try {
					for (int i = 0; i < files.length; i++) {
						if (!files[i].isEmpty()
								&& !files[i].getContentType().split("/")[0].trim().equals("image")) {
							throw new Exception(
									"Uploaded files are not supported!!! Only Image file type can be uploaded!");
						}
					}

					// uploading files and add to the question attachment lists
					for (int i = 0; i < files.length; i++) {
						if (!files[i].isEmpty()) {
							File newFile = fileDao.uploadFile(files[i], user);
							newFiles.add(newFile);
						}
					}

				} catch (Exception e) {
					throw new UpdatingQuestionError(e.getLocalizedMessage());
				}
			}


			Question existedQuestion = questionDao.getQuestion(questionId);
			QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

			Long questionIndex = (long) questionSection.getQuestions().indexOf(existedQuestion);
			existedQuestion =
					questionDao.updateQuestion(sectionId, questionIndex, questionId, question, newFiles);
			return existedQuestion.getId();

		} catch (JsonProcessingException e) {
			throw new UpdatingQuestionError(e.getLocalizedMessage());
		}
	}

	@PutMapping("/{surveyId}/sections/{sectionId}/questions/{questionId}/index")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateQuestionIndex(@PathVariable Long surveyId, @PathVariable Long sectionId,
			@PathVariable Long questionId, @RequestBody Map<String, Object> requestInfo) {

		Question question = questionDao.getQuestion(questionId);
		int oldIndex = question.getQuestionIndex();
		int newIndex = (int) requestInfo.get("index");

		System.out.println(surveyId);
		System.out.println(sectionId);
		System.out.println(oldIndex);
		System.out.println(newIndex);

		questionSectionDao.moveQuestionInSections(surveyId, sectionId, oldIndex, newIndex);

	}


	@DeleteMapping("/{surveyId}/sections/{sectionId}/questions/{questionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteQuestion(@PathVariable Long surveyId, @PathVariable Long sectionId,
			@PathVariable Long questionId) throws Exception {

		Question question = questionDao.getQuestion(questionId);
		QuestionSection questionSection = questionSectionDao.getQuestionSection(sectionId);

		if (question == null || !questionSection.getSurvey().getId().equals(surveyId)) {
			throw new Exception("Unsuccessful delete question!");
		}


		questionSection.getQuestions().remove(question);
		questionSectionDao.saveQuestionSection(questionSection);

		question.getAttachments().forEach(file -> {
			fileDao.deleteFile(((File) file).getId(), userDao.getUser(1));
		});

		questionDao.removeQuestion(questionId);

	}


	// Survey > Section > questionResultSummary

	@GetMapping("/{surveyId}/sections/{sectionId}/questionResultSummaries")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public Map<Long, QuestionResultSummary> getSectionQRS(@PathVariable Long sectionId) {

		QuestionSection section = questionSectionDao.getQuestionSection(sectionId);

		System.out.println("Section: " + section.getId());
		return getAllQRSFromSection(section);
	}


	public Map<Long, QuestionResultSummary> getAllQRSFromSection(QuestionSection section) {

		Map<Long, QuestionResultSummary> qResultSummaries = new HashMap<>();

		List<Question> questions = section.getQuestions();

		questions.forEach(q -> {
			Question question = q;

			System.out.println("isNull: " + question.getId());

			QuestionResultSummary qResultSummary =
					qResultSummaryDao.getQuestionResultSummaryByQuestionId(question.getId());



			if (qResultSummary != null) {
				qResultSummary.updateResultSummary();
			}

			else {

				switch (question.getDecriminatorValue()) {
					case "MULTIPLE_CHOICE": {
						System.out.println("mc: " + question.getId());
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
			}


			if (qResultSummary != null) {
				qResultSummary = qResultSummaryDao.saveQuestionResultSummary(qResultSummary);
			}

			qResultSummaries.put(question.getId(), qResultSummary);


		});
		return qResultSummaries;

	}

	// =================================================================

	// Survey > Response

	@GetMapping("/{surveyId}/responses")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public List<SurveyResponse> getSurveyResponses(@PathVariable Long surveyId) {

		return surveyResponseDao.getSurveyResponses(surveyId);
	}

	@GetMapping("/{surveyId}/allresponses")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public List<SurveyResponse> getSurveyResponsesAll(@PathVariable Long surveyId) {

		return surveyResponseDao.getSurveyResponsesAll(surveyId);
	}

	@PostMapping("/{surveyId}/responses")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addSurveyResponse(@PathVariable Long surveyId, @RequestBody SurveyResponse response) {

		Survey survey = surveyDao.getSurvey(surveyId);

		response.setSurvey(survey);
		response.setDate(new Date());

		// don't have the same section as survey
		if (response.getAnswerSections() == null
				|| response.getAnswerSections().size() != survey.getQuestionSections().size()) {
			throw new InvalidResponse("The total sections were not match!");
		}
		// correct #ofSections
		else {
			for (int sectionIndex = 0; sectionIndex < response.getAnswerSections()
					.size(); sectionIndex++) {

				// not the same order of section Or don't have the same #ofanswer for each section
				if (survey.getQuestionSections().get(sectionIndex).getSectionIndex() != sectionIndex
						|| response.getAnswerSections().get(sectionIndex).getAnswers().size() != survey
								.getQuestionSections().get(sectionIndex).getQuestions().size()) {
					throw new InvalidResponse("Unmatched number of answers!");
				} else {

					// answerSection description
					response.getAnswerSections().get(sectionIndex)
							.setDescription(survey.getQuestionSections().get(sectionIndex).getDescription());

					int answerIndex = 0;
					for (Answer answer : response.getAnswerSections().get(sectionIndex).getAnswers()) {

						Question question =
								survey.getQuestionSections().get(sectionIndex).getQuestions().get(answerIndex);

						// unmatched between answer and question
						if (answerIndex != question.getQuestionIndex()
								|| !answer.getDecriminatorValue().equals(question.getDecriminatorValue())) {
							throw new InvalidResponse("Unmatched answer!");
						} else {
							// type requirements
							switch (answer.getDecriminatorValue()) {
								case "MULTIPLE_CHOICE":
									Set<Integer> answerSelections = ((MultipleChoiceAnswer) answer).getSelections();
									List<String> questionChoice = ((MultipleChoiceQuestion) question).getChoices();
									int minSelections = ((MultipleChoiceQuestion) question).getMinSelections();
									int maxSelections = ((MultipleChoiceQuestion) question).getMaxSelections();

									if (answerSelections == null) {
										answerSelections = new HashSet<Integer>();
									}

									if (answerSelections.size() > maxSelections
											|| answerSelections.size() < minSelections) {
										throw new InvalidResponse("Unmatched number of selections!");
									}

									answerSelections.forEach((selection) -> {
										if (selection > questionChoice.size() - 1) {
											throw new InvalidResponse("Unmatched selections!");
										}
									});

									break;

								case "RANKING":
									if (((RankingAnswer) answer).getSelectionRanks()
											.size() != ((RankingQuestion) question).getRankingChoices().size()) {
										throw new InvalidResponse("Unmatched ranking answer!");
									}
									break;

								default:
									break;

							}


							answer.setDescription(question.getDescription());
							answer.setQuestion(question);
						}
						answerIndex++;
					}
				}
			}
		}

		return surveyResponseDao.saveResponse(response).getId();

	}

	@GetMapping("/{surveyId}/responses/{responseId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Internal.class)
	public SurveyResponse getSurveyResponse(@PathVariable Long responseId) {

		return surveyResponseDao.getResponse(responseId);
	}

	@DeleteMapping("/{surveyId}/responses/{responseId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSurveyResponse(@PathVariable Long surveyId, @PathVariable Long responseId) {

		SurveyResponse response = surveyResponseDao.getResponse(responseId);

		if (!response.getSurvey().getId().equals(surveyId))
			throw new BadRequest("The response doesn't not belong to the given survey!");

		response.setDeleted(true);

		surveyResponseDao.saveResponse(response);
	}

	// =================================================================

	// Survey > Response


}
