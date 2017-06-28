package service;

import java.util.List;
import java.util.Map;

import dao.applyDao.Trainapply;

public interface TrainApplyService {
	//保存一个机构申请
	public boolean saveTrainApply(Map<String, Object> map);
	
	public List search(String managename);
	
	public boolean update(Map<String, Object> map);
	
	public String verify(Trainapply trainapply);
	
	public List<?> findAllTrainApplyLimit(Map<String,Object> params);
	
	public boolean dealTrainApplye(Trainapply trainapply);
}
