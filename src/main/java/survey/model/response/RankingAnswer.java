package survey.model.response;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@DiscriminatorValue("RANKING")
public class RankingAnswer extends Answer {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "answer_ranking_selection", joinColumns = @JoinColumn(name = "answer_id"))
	@Column(name = "selection_rank")
	@MapKeyColumn(name = "ranking")
	@Id
	private Map<Integer, String> selectionRanks;

	public Map<Integer, String> getSelectionRanks() {

		return selectionRanks;
	}

	public void setSelectionRanks(Map<Integer, String> selectionRanks) {

		this.selectionRanks = selectionRanks;
	}
}
