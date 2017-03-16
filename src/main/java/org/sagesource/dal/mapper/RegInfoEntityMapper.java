package org.sagesource.dal.mapper;

import org.sagesource.dal.entity.RegInfoEntity;

public interface RegInfoEntityMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(RegInfoEntity record);

	int insertSelective(RegInfoEntity record);

	RegInfoEntity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RegInfoEntity record);

	int updateByPrimaryKey(RegInfoEntity record);
}