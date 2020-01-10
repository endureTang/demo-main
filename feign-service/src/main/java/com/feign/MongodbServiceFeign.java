package com.feign;

import com.model.mogodb.UserMongoData;
import com.model.responseDto.ResponseMsgDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="mongoDB",configuration=FeignConfig.class)
public interface MongodbServiceFeign {

	/**
	 * 批量保存推送催收案件
	 * @param caseMongoDatas
	 * @return
	 */
	@PostMapping(value="/userReceive/saveBatch")
	ResponseMsgDto saveBatch(@RequestBody List<UserMongoData> caseMongoDatas);

	/**
	 * 获取当天推送未处理的催收案件列表
	 * @param name 姓名
	 * @return
	 */
	@PostMapping(value="/userReceive/selectList")
	List<UserMongoData> queryCaseList(String name);
}
