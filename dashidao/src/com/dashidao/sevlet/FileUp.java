package com.dashidao.sevlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.foundation.domain.query.IntegralLogQueryObject;
import com.dashidao.foundation.service.IIntegralLogService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService; 


/**
 * 文件上传
 */ 
public class FileUp extends HttpServlet  {
	 
	   public FileUp() {
		           super();
		           // TODO Auto-generated constructor stub
		       }
	public  void  doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 Map<String, Object>obj=new HashMap<String, Object>();
		 
			try {
				response.setContentType("text/html; charset=utf-8");
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				//int  imgSize=Integer.parseInt(request.getParameter("imgSize"));
				String  imgName=request.getParameter("imgName");
				String  imgBase64=request.getParameter("imgBase64");
				System.out.println(imgBase64);
				System.out.println(request.getParameter("data"));
				if (imgBase64!=null) {
					imgBase64=imgBase64.substring(imgBase64.indexOf(",")+1); 
					byte[]files=new BASE64Decoder().decodeBuffer(imgBase64);
					 String imgpath="";
						File file=new File(imgpath);
						if(!file.exists()){
							file.mkdir();  
						}
						String path=getDateXml(new Date())+"-"+imgName;
						String savepath=imgpath+"/"+path; 
						File  createFile=new File(savepath); 
						OutputStream  fileWrite=new FileOutputStream(createFile);  
						fileWrite.write(files); 
					    
					    fileWrite.flush();
					    fileWrite.close();
					   
					    obj.put("path", path);
					    obj.put("ret", true);
					    obj.put("state", 0);
					 
				}
			
					
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				 obj.put("ret", false);
				 obj.put("state", 1);
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 obj.put("ret", false);
				 obj.put("state", 1);
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				 obj.put("ret", false);
				 obj.put("state", 1);
				e.printStackTrace();
			}
			
		 
		
	    String json = JSONObject.fromObject(obj).toString();
		response.getWriter().print(json); 	   
		
		
	}
	public  void  doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 Map<String, Object>obj=new HashMap<String, Object>();
		 
			try { 
				response.setContentType("text/html; charset=utf-8");
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				
				// 指定允许其他域名访问 
				response.setHeader("Access-Control-Allow-Origin", "*");
				
				//int  imgSize=Integer.parseInt(request.getParameter("imgSize"));
				String  imgName=request.getParameter("imgName");
				System.out.println(imgName);
				String  imgBase64=request.getParameter("imgBase64"); 
				if (imgBase64!=null) {
					obj.put("obj", 1);
					imgBase64=imgBase64.substring(imgBase64.indexOf(",")+1); 
					byte[]files=new BASE64Decoder().decodeBuffer(imgBase64);
					//图片保存路径
					 String imgpath=this.getServletContext().getResource("/").getPath()+"/upload/img/";
						File file=new File(imgpath);
						if(!file.exists()){
							file.mkdir();  
						}
						String path=getDateXml(new Date())+"-"+imgName;
						String savepath=imgpath+"/"+path; 
						File  createFile=new File(savepath); 
						OutputStream  fileWrite=new FileOutputStream(createFile);  
						fileWrite.write(files); 
					    
					    fileWrite.flush();
					    fileWrite.close();
					    response.getWriter().print(path); 	
					    obj.put("path", path);
					    obj.put("ret", true);
					    obj.put("state", 0);
					    obj.put("savepath", savepath);
					 
				}
			
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 obj.put("ret", false);
				 obj.put("state", 1);
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				 obj.put("ret", false);
				 obj.put("state", 1);
				e.printStackTrace();
			}
			
		 
			
	    //String json = JSONObject.fromObject(obj).toString();
			   
		
		
	}
	public static String getDateXml(Date date){
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss");
		return dateformat1.format(date);
	}
	

     
	
}
