package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;

import database.BaseHibernateDAO;
import database.HibernateSessionFactory;
import util.ValidateUtil;
/**
 * 
 * 主要是建立一个表格查询的公共方法和生产Id的公共方法
 *
 */
public class BaseDao extends BaseHibernateDAO{
	//查询培训机构，并且分页；公共方法
		public static List findAllTrainLimit(Map<String,Object> params,String tableName){
			try{
				//获取表格所需参数
				Integer curentPage = Integer.parseInt(params.get("curentPage").toString()) ;
				Integer pageNumbers = Integer.parseInt(params.get("pageNumbers").toString());
				String searchValue = (String) params.get("searchValue");
				String searchKey = (String) params.get("searchKey");
				String jumpPage = (String)params.get("jumpPage");
				String initConditon = (String)params.get("initConditon");
				
				String searchCondition = "";
				int limitCondition = 0;
				
				//分页处理
				if(jumpPage != null && ValidateUtil.isNumeric(jumpPage)){
					limitCondition = Integer.parseInt("jumpPage") * pageNumbers;
				}else{
					limitCondition = curentPage * pageNumbers;
				}
				
				//搜索条件
				searchCondition = " and " + searchKey + " = '" + searchValue + "'";
				
				String sql = "select * from ( select rownum as rid,model.* from " + tableName +" model) where 1=1 ";
				
				if(initConditon != null && !"".equals(initConditon.trim())){
					sql +=" and " + initConditon + " ";
				}
				String limitStr = "" ;

				if(limitCondition > 0){
					limitStr += "and rid > " + (limitCondition - pageNumbers)  + " and rid <= "+ limitCondition;
				}
				
				if(searchKey != null && !"".equals(searchKey.trim())){
					sql += searchCondition;
				}
				
				if(limitStr != null && !"".equals(limitStr)){
					sql += limitStr;
				}
				
				sql += " order by rid asc ";
				
				List list = HibernateSessionFactory.getSession().createSQLQuery(sql).list();
				Map<String,Object> map = new HashMap<String, Object>();
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//简单的主键生成
		public static Integer generateEntityId(String tableName,String filedName){
			try {
				//查找最大ID语句
				String sql = "select max(" + filedName + ") from " + tableName;
				SQLQuery sqlQuery = HibernateSessionFactory.getSession().createSQLQuery(sql);
				List res = sqlQuery.list();
				int a = 0;
				if(res.get(0) == null){
					return 1;
				}
				for(int i=0;i < res.size();i++){
					a = Integer.parseInt(res.get(i).toString()) ;
				}
				//返回结果
				return a+1;
			} catch (RuntimeException re) {
				throw re;
			}
		}
		
}
