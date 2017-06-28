package service;

import java.util.*;

import dao.trainDao.Train;

public interface TrainService {

	
	public List<?> findAllTrainLimit(Map<String,Object> params);
	
	public Train search(String trainid);
	
	public Train update(Train train);
	
	public int delete(String trainid);
	
	public List findAll();
	
}
