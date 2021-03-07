package survey.model.statistic.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import survey.model.statistic.ResponseGroup;
import survey.model.statistic.dao.ResponseGroupDao;

@Repository
public class ResponseGroupDaoImpl implements ResponseGroupDao {

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public ResponseGroup getResponseGroup(String groupedBy, String groupedValue) {

		return entityManager
				.createQuery("from ResponseGroup where groupBy = :groupBy and groupedValue = :groupedValue",
						ResponseGroup.class)
				.setParameter("groupBy", groupedBy.trim())
				.setParameter("groupedValue", groupedValue.trim())
				.getSingleResult();
	}

//	@Override
//	public ResponseGroup getResponseGroup(Long surveyId, String groupedBy, String groupedValue) {
//
//		return entityManager
//				.createQuery("from ResponseGroup where groupBy = :groupBy and groupedValue = :groupedValue and ",
//						ResponseGroup.class)
//				.setParameter("groupBy", groupedBy.trim())
//				.setParameter("groupedValue", groupedValue.trim())
//				.getSingleResult();
//	}

	
	@Override
	public ResponseGroup getResponseGroup(Long resGroupId) {

		return entityManager.createQuery("from ResponseGroup where id = :id", ResponseGroup.class)
				.setParameter("id", resGroupId).getSingleResult();
	}

	@Override
	@Transactional
	public void removeResponseGroup(Long rgId) {

		entityManager.remove(entityManager.find(ResponseGroup.class, rgId));

	}

	@Override
	@Transactional
	public void removeResponseGroup(ResponseGroup responseGroup) {

		entityManager.remove(responseGroup);

	}


	@Override
	@Transactional
	public ResponseGroup saveResponseGroup(ResponseGroup responseGroup) {

		return entityManager.merge(responseGroup);
	}

	@Override
	public List<ResponseGroup> getResponseGroupsBySurvey(Long surveyId) {
		List<ResponseGroup> resGroups = new ArrayList<>();
		
		resGroups = entityManager.createQuery(
				"select distinct resGroup from ResponseGroup resGroup join resGroup.responses res "
				+ "where res.survey.id = :surveyId and "
				+ "resGroup.groupBy like :groupBy",
				ResponseGroup.class)
				.setParameter("surveyId", surveyId)
				.setParameter("groupBy", "surveyId%")
				.getResultList();
		
		return resGroups;
	}





}
