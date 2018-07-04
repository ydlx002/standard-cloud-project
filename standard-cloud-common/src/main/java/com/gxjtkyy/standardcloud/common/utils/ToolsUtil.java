package com.gxjtkyy.standardcloud.common.utils;

import com.gxjtkyy.standardcloud.common.domain.info.UploadInfo;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ToolsUtil {


	/**
	 * @author Bobby
	 * 上载文件，返回文件路径 /images/xxx/xx/xx.xls
	 * @param request
	 * @param name :productName 或者 categoryName 或者（图片）productId
	 * @param fileType :文件类型 file 的name属性 :image excel
	 * @return
	 */
    public static UploadInfo uploadFile(HttpServletRequest request, String name, String fileType, String basePath) throws BaseException {
    	Map<String,String> map = new HashMap<String, String>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
        //构建保存的目录 
        String logPathDir = "";
        String rootPath = basePath;
      //得到保存目录的真实路径
        logPathDir = rootPath+File.separator+fileType;
        //根据真实路径创建目录
        File logSaveFile = new File(logPathDir);  
        if(!logSaveFile.exists())  
            logSaveFile.mkdirs();        
        //页面控件的文件流
        MultipartFile multipartFile = multipartRequest.getFile(fileType);//file 的name属性
        //获取原文件名称 IMG0001.jpg
        String fileName = multipartFile.getOriginalFilename();
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String logFileName =  name+suffix;//构建文件名称  
        //拼成完整的文件保存路径加文件
        String filePath = logPathDir + File.separator + logFileName; 
        File file = new File(filePath);
		UploadInfo upload = new UploadInfo();
		upload.setOriginalName(fileName);
		upload.setExt(fileType);
		upload.setFileName(logFileName);
		upload.setSavePath(filePath);
        try {  
            multipartFile.transferTo(file);  
        } catch (Exception e) {
			log.error("文件上传失败",e);
        	throw new BaseException("文件上传失败");
        }
        return upload;
    } 
    
	/**
	 * @author Bobby
	 * 文件下载 主要方法
	 * @param request
	 * @param response
	 * @param urlPath 文件的路径全名
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String urlPath)
			throws Exception {
//		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		// 获取项目根目录
//		String ctxPath = request.getSession().getServletContext().getRealPath("");
		// 获取下载文件路径
//		String downLoadPath = ctxPath + "/uploadFile/" + storeName;
		try{
			// 获取文件的长度
			long fileLength = new File(urlPath).length();
			int n=urlPath.lastIndexOf("/");
	        String fileName=urlPath.substring(n+1,urlPath.length());
			// 设置文件输出类型
			response.setContentType("application/octet-stream");// x-msdownload; octet-stream;
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(urlPath));
			// 输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	
	}
}
