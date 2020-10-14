package com.api.test;

import java.io.*;

/**
 * @description: testio
 * @author: endure
 * @create: 2020-06-01 10:00
 **/
public class TestIO {
    public static void main(String[] args) throws UnsupportedEncodingException {
        testWriter();
    }

    public void testStream(){
        try {
            File file = new File("d:/test.rar");
            file.createNewFile();
            FileInputStream inputStream = new FileInputStream("d:/消息.rar");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] readContent = new byte[1024];
            int hasContent = 0;
            while ((hasContent = bufferedInputStream.read(readContent)) != -1){
                bufferedOutputStream.write(readContent,0,hasContent);
            }
            bufferedOutputStream.flush();
            System.out.println("读取成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static void testWriter(){
        try{
            File readFile = new File("d:/11/22");
            if(!readFile.exists()){
                readFile.mkdirs();
            }
            File readFile2 = new File(readFile, "/1.txt");
            readFile2.createNewFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile2));
            String line = "";
            int id = 1;
            while ((line = bufferedReader.readLine()) != null) {
                File writeFile = new File(readFile,id+".docx");
                writeFile.createNewFile();
                Writer writer = new FileWriter(writeFile);
                writer.write(line);
                writer.flush();
                System.out.println(line);
                id++;
            }

        }   catch (Exception e){

        }
    }
}
