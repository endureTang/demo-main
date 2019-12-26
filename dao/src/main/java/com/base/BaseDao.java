/*
 * COPYRIGHT. ShenZhen GreatVision Network Technology Co., Ltd. 2015.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording, 
 * or otherwise, without the prior written permission of ShenZhen GreatVision Network Technology Co., Ltd.
 *
 * Amendment History:
 * 
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * Apr 17, 2015    YangShengJun         Create the class
 */
package com.base;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface BaseDao {

	public int save(String str, Object obj) throws Exception;
	
	public <T> int batchSave(String str, List<T> objs) throws Exception;
	
	public int update(String str, Object obj) throws Exception;
	
	public <T> void batchUpdate(String str, List<T> objs) throws Exception;
	
	public int delete(String str, Object obj) throws Exception;
	
	public <T> int batchDelete(String str, List<T> objs) throws Exception;

	public <T> T findForObject(String str, Object obj) throws Exception;

	public <E> List<E> findForList(String str, Object obj) throws Exception;

	public <K, V> Map<K, V> findForMap(String str, Object obj, String key, String value) throws Exception;

	public <T> T getMapper(Class cls) throws Exception;
}
