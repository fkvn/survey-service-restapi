package survey.model.statistic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import survey.model.response.Answer;
import survey.model.response.RatingAnswer;
import survey.model.survey.Question;
import survey.model.survey.RatingQuestion;
import survey.model.survey.Survey;

@Entity
@DiscriminatorValue("RATING")
public class RatingQRS extends QuestionResultSummary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RatingQRS() {

		// TODO Auto-generated constructor stub
	}

	public RatingQRS(Survey survey, Question question) {

		super(survey, question);
		this.ratingResponses = getRatingResponsesFromQuestion((RatingQuestion) question);
	}

	@OneToMany(cascade = CascadeType.ALL)   // unidirectional
	@JoinTable(name = "ratingResponses", joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "response_group_id"))
	@MapKeyJoinColumn(name = "Integer", nullable = true)
	private Map<Integer, ResponseGroup> ratingResponses;

	public Map<Integer, ResponseGroup> getRatingResponsesFromQuestion(RatingQuestion question) {

		Map<Integer, ResponseGroup> ratingResponses = new HashMap<>();

		for (int i = 0; i < question.getRatingScale(); i++) {
			ResponseGroup responseGroup = new ResponseGroup("ratingOption", String.valueOf(i + 1));
			ratingResponses.put(i + 1, responseGroup);
		}

		List<Answer> answers = question.getAnswers();

		for (int i = 0; i < answers.size(); i++) {
			RatingAnswer ans = (RatingAnswer) answers.get(i);
			ratingResponses.get(ans.getRating()).getResponses().add(ans.getAnswerSection().getResponse());
		}

		return ratingResponses;
	}


	public Map<Integer, ResponseGroup> getRatingResponses() {

		return ratingResponses;
	}

	public void setRatingResponses(Map<Integer, ResponseGroup> ratingResponses) {

		this.ratingResponses = ratingResponses;
	}

	@Override
	public void updateResultSummary() {
		// this.getRatingResponses().clear();
		// this.setRatingResponses(getRatingResponsesFromQuestion((RatingQuestion) this.getQuestion()));

		this.getRatingResponses().forEach((k, v) -> {
			v.getResponses().clear();
		});

		List<Answer> answers = this.getQuestion().getAnswers();

		for (int i = 0; i < answers.size(); i++) {
			RatingAnswer ans = (RatingAnswer) answers.get(i);
			this.ratingResponses.get(ans.getRating()).getResponses()
					.add(ans.getAnswerSection().getResponse());
		}

	}

//	@JsonProperty("avgRating")
//	public Double getavgRating() {
//
//		Double avgRating = 0.0;
//
//		for (Map.Entry<Integer, ResponseGroup> entry : this.getRatingResponses().entrySet()) {
//			avgRating += entry.getKey() * entry.getValue().getResponses().size();
//		}
//
//		avgRating /= this.getTotalResponses();
//
//		return round(avgRating, 2);
//	}
//
//	@JsonProperty("maxRating")
//	public Integer getMaxRating() {
//
//		Map<String, String> maxRating = new HashMap<>();
//
//		Integer maxRate = 0;
//
//		for (Map.Entry<Integer, ResponseGroup> entry : this.getRatingResponses().entrySet()) {
//			if (entry.getKey() > 0 && entry.getKey() > maxRate
//					&& entry.getValue().getResponses().size() > 0) {
//				maxRate = entry.getKey();
//			}
//		}
//
//		maxRating.put("maxRate", maxRate.toString());
//		maxRati
//		
//		maxRating.put(maxRate, this.getRatingResponses().get(maxRate).getResponses().size());
//
//		return maxRating;
//	}
//	
//	@JsonProperty("minRating")
//	public Map<Integer, Integer> getminRating() {
//
//		Map<Integer, Integer> maxRating = new HashMap<>();
//
//		Integer minRate = ((RatingQuestion) this.getQuestion()).getRatingScale();
//
//		for (Map.Entry<Integer, ResponseGroup> entry : this.getRatingResponses().entrySet()) {
//			if (entry.getKey() > 0 && entry.getKey() < minRate
//					&& entry.getValue().getResponses().size() > 0) {
//				minRate = entry.getKey();
//			}
//		}
//
//		maxRating.put(minRate, this.getRatingResponses().get(minRate).getResponses().size());
//
//		return maxRating;
//	}


	private double round(double value, int places) {

		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
