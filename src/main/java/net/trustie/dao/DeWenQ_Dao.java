package net.trustie.dao;

import net.trustie.model.DeWenQ_Model;

import org.apache.ibatis.annotations.Insert;

public interface DeWenQ_Dao {
	
	@Insert("")
	public int add(DeWenQ_Model deWenQ_Model);

}
