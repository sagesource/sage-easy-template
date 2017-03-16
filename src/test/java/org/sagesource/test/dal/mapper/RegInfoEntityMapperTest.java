package org.sagesource.test.dal.mapper;

import org.junit.Test;
import org.sagesource.dal.mapper.RegInfoEntityMapper;
import org.sagesource.test.base.BaseTransTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p></p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/16
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class RegInfoEntityMapperTest extends BaseTransTest {

	@Autowired
	private RegInfoEntityMapper regInfoEntityMapper;

	@Test
	public void selectByPrimaryKey_test() {
		regInfoEntityMapper.selectByPrimaryKey(1);
	}

}
