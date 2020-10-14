package com.api.test;

import com.alibaba.fastjson.JSONArray;
import com.model.generate.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/** 
* @Description: 用户接口api
* @Author: endure
* @Date: 2019/12/31 
*/
@RestController
@RequestMapping(value = "test")
public class TestCache {
    private static Logger logger = LoggerFactory.getLogger(TestCache.class);
    /**
     * 注入用户service
     */
    @Resource
    private UserService userService;

    /**
     * @Description: 测试缓存查询
     * @Param:
     * @return:
     * @Author: endure
     * @Date: 2019/12/31
     */
    @PostMapping(value = "testCache")
    public String testCache(){
        List<User> user = userService.getByname("张三");
        logger.info("查询消息"+JSONArray.toJSONString(user)+"222");
        return JSONArray.toJSONString(user);
    }

    public static void main(String args[]) throws Exception {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            /* 读入TXT文件 */
            String pathname = "D:\\22\\pop.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename),"UTF-8"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            int i = 1;
            while (line != null) {
                line = br.readLine(); // 一次读入一行数据
                System.out.println(line);

                // /* 写入Txt文件 */
                File writename = new File("d:\\22\\"+i+".docx"); // 相对路径，如果没有则要建立一个新的output。txt文件
                writename.createNewFile(); // 创建新文件
                BufferedWriter out = new BufferedWriter(new FileWriter(writename));
                out.write(line); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
                out.close(); // 最后记得关闭文件
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
