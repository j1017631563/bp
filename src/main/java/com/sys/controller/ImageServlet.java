/**  
 * Project Name:lenovo_czsys  
 * File Name:ImageServlet.java  
 * Package Name:com.sys.controller  
 * Date:2016-4-21下午2:09:11  
 * Copyright (c) 2016 
 *  
*/  
  
package com.sys.controller;  

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @ClassName: ImageServlet 
 * @Description: TODO 
 * @author lm
 * @date 2016-4-21 下午2:09:11 
 * 
 * 
 */
@SuppressWarnings("all")
public class ImageServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int width = 79;
		int height = 32;
		
		BufferedImage img = new BufferedImage(width, height,BufferedImage.SCALE_SMOOTH);  
        // 得到该图片的绘图对象   
		 
        Graphics g = img.getGraphics();  
        Random r = new Random();  
        //填充整个图片的颜色    
        Color color = new Color(  
                (new Double(Math.random() * 128)).intValue() + 128,  
                (new Double(Math.random() * 128)).intValue() + 128,  
                (new Double(Math.random() * 128)).intValue() + 128);   
        g.setColor(color); 
        
        //画边框
        g.fillRect(0, 0, width, height);  
        //干扰线
        Color c = new Color(  
                (new Double(Math.random() * 128)).intValue() + 128,  
                (new Double(Math.random() * 128)).intValue() + 128,  
                (new Double(Math.random() * 128)).intValue() + 128);
        g.setColor(c);
        for (int i = 0; i < 13; i++) {  
	        int x = r.nextInt(width);  
	        int y = r.nextInt(height);  
	        int xl = r.nextInt(12);  
	        int yl = r.nextInt(12);  
	        g.drawLine(x, y, x + xl, y + yl);
        }
        
        // 向图片中输出数字和字母    
        StringBuffer sb = new StringBuffer();  
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
        int index, len = ch.length;  
        for (int i = 0; i < 4; i++) {  
            index = r.nextInt(len);  
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  
            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, height-5));  
            // 输出的  字体和大小                      
            g.drawString("" + ch[index], (i * 16) + 3, height-4);  
            //写什么数字，在图片 的什么位置画    
            sb.append(ch[index]);  
        }
        
        request.getSession().setAttribute("code", sb.toString());
        
        ImageIO.write(img, "png", response.getOutputStream());
        
	}
}
  
