package com.endure.service;

import com.endure.dao.CaseDao;
import com.model.mogodb.UserMongoData;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * MongoDB业务层
 */
@Service("pushCaseService")
public class PushCaseService {
	@Resource
	private CaseDao caseDao;
	
	public void saveBatch(List<UserMongoData> data) {
		caseDao.saveAll(data);
	}

	/**
	 * 根据名称获取
	 * @param name
	 * @return
	 */
	public List<UserMongoData> queryList(String name) {
		//自定义查询条件
		ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact());
		UserMongoData caseMongoData = new UserMongoData();
        caseMongoData.setName(name);
        Example<UserMongoData> example = Example.of(caseMongoData, matcher);
        return caseDao.findAll(example);
	}
}
