package service.TrainServiceBean;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.TrainService;
import dao.applyDao.TrainapplyDAO;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;
import database.HibernateSessionFactory;

public class TrainServiceBean implements TrainService{
	private TrainDAO trainDAO = new TrainDAO();
	private TrainapplyDAO trainapplyDAO = new TrainapplyDAO();

	public List findAllTrainLimit(Map<String, Object> params) {
		return trainDAO.findAllTrainLimit(params);
	}
	
	public Train search(String trainid){
		Train t =  trainDAO.findById(Integer.parseInt(trainid));
		return t;
	}
	
	
	public Train update(Train train){
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		TrainDAO traindao = new TrainDAO();
		traindao.save(train);
		
		transaction.commit();
		session.close();
		return train;
	}
	
	public int delete(String trainid){
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		
		String sql = "delete Train as t where t." + trainid +"=?";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.executeUpdate();
		transaction.commit();
		session.close();
		return 1;
	}

	@Override
	public List findAll() {
		try {
			return this.trainDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
