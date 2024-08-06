package com.personal.ecommercesys.controller;

import com.personal.ecommercesys.controller.aboutProduct.productCenterController;
import org.junit.Test;
import org.springframework.http.HttpRequest;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.Properties;


public class test {
    @Test
    public void testGetCropsInfo() throws IOException {

        File file = new File("src/main/resources/public/cropsText/" + "Blys12320210109175419.txt");
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer detailBuffer = new StringBuffer();
        char[] cbuf = new char[1024];
        int readLength = -1;
        try{
            while ((readLength = reader.read(cbuf))!=-1){
                detailBuffer.append(new String(cbuf,0,readLength));
            }
        }catch (IOException e){
//            return "topPublic";
        }
        System.out.println(detailBuffer.toString());
    }
}
