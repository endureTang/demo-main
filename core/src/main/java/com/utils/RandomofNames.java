package com.utils;

import java.util.Random;

/**
 * @Description: 随机生成中文名称
 * @Param:
 * @return:
 * @Author: endure
 * @Date: 2020/1/7
 */
public class RandomofNames {
	private final static String firstName =    "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许"
			+ "何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章"
			+ "云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳"
			+"酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常"
			+ "乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹"
			+ "姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞"
			+ "熊纪舒屈项祝董粱杜阮蓝闵席季麻强贾路娄危"
			+ "江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍"
			+ "虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓"
			+ "郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁 ";
	private final  static String[] lastName = {"小","明","丽","红","强","小丽","炎","燕","晓燕","洋","阳","庄","小庄","晓阳","小洋","山","进","明"
			,"小文","文","文丽","晓歌","小歌","歌","晓波","小波","小亮","晓亮","亮","品华","霞","红霞","晓炎","晓强","德华","华","城","诚","小明"
	};

	/**
	 * @Description: 生成中文名
	 * @Param:
	 * @return:
	 * @Author: endure
	 * @Date: 2020/1/7
	 */
	public static String generateName(){
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		char xing = firstName.charAt(random.nextInt(firstName.length()-1));
		firstName.valueOf(xing);
		sb.append(xing);//姓

		sb.append(lastName[random.nextInt(lastName.length -1)]);
		return sb.toString();
	}
}