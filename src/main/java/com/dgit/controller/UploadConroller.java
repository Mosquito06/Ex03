package com.dgit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.util.MediaUtils;

@Controller
public class UploadConroller {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadConroller.class);
	private String innerUploadPath = "resources/upload";
	
	@Resource(name = "uploadPath") // bean에 등록된 특정 아이디를 가져올 때 사용하는 태그
	private String outUploadPath;
	
	@RequestMapping(value="/innerUpload", method=RequestMethod.GET)
	public String innerUploadTest(){
		
		return "innerUploadForm";		
	}
	
	@RequestMapping(value="/innerUpload", method = RequestMethod.POST)
	public String innerUploadResult(String test, MultipartFile file, HttpServletRequest request, Model model){
		logger.info("innerUploadResult()");
		logger.info("test : " + test);
		logger.info("file : " + file.getOriginalFilename());
		
		// 경로를 알기 위해 request를 매개변수로 받아옴
		String root_path = request.getSession().getServletContext().getRealPath("/");
		
		File dirPath = new File(root_path + "/" + innerUploadPath);		
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		
		UUID uid = UUID.randomUUID(); // 중복방지를 위하여 랜던값 생성
		String saveName = uid.toString() + "-" + file.getOriginalFilename();
		
		// 해당 경로에 파일 그릇을 생성(안드로이드와 동일)
		File target = new File(root_path + "/" + innerUploadPath, saveName);
		try {
			// 파일 그릇에 넘어온 파일을 복사
			FileCopyUtils.copy(file.getBytes(), target);
		
			model.addAttribute("test", test);
			model.addAttribute("filename", innerUploadPath + "/" + saveName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "innerUploadResult";
	}
	
	@RequestMapping(value="innerMultiUpload", method=RequestMethod.GET)
	public String innerMultiUpload(){
		
		
		return "innerMultiUploadForm";
	}
	
	@RequestMapping(value="innerMultiUpload", method=RequestMethod.POST)
	public String innerMultiUploadResult(String test, List<MultipartFile> files, HttpServletRequest request, Model model){
		logger.info("innerMultiUploadResult()");
		logger.info("test : " + test);
		List<String> fileNames = new ArrayList<>();
		for(MultipartFile f : files){
			logger.info("file : " + f.getOriginalFilename());
		}
		
		String root_Path = request.getSession().getServletContext().getRealPath("/");
		File dir = new File(root_Path + "/" + innerUploadPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		for(MultipartFile f : files){
			UUID uid = UUID.randomUUID();
			String saveName = uid.toString() + "_" + f.getOriginalFilename();
			
			File target = new File(root_Path + "/" + innerUploadPath, saveName);
			try {
				FileCopyUtils.copy(f.getBytes(), target);
				fileNames.add(innerUploadPath + "/" + saveName);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		model.addAttribute("test", test);
		model.addAttribute("filename", fileNames);
		
		
		
		return "innerMultiUploadResult";
	}
	
	@RequestMapping(value="/outUpload", method = RequestMethod.GET)
	public String outUploadForm(){
		logger.info("outUploadForm()");
		logger.info("outUploadPath : " + outUploadPath);
		
		return "outUploadForm";
	}
	
	@RequestMapping(value="/outUpload", method = RequestMethod.POST)
	public String outUploadResult(String test, MultipartFile file, Model model){
		logger.info("outUploadForm()");
		logger.info("test : " + test);
		logger.info("file name : " + file.getOriginalFilename());
		logger.info("file size : " + file.getSize());
		logger.info("file contentType : " + file.getContentType());
		logger.info("outUploadPath : " + outUploadPath);
		
		File dirPath = new File(outUploadPath);		
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		
		UUID id = UUID.randomUUID();
		String savedName = id.toString() + "_" + file.getOriginalFilename();
		File target = new File(outUploadPath, savedName);
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			model.addAttribute("test", test);
			model.addAttribute("filename", outUploadPath + "/" + savedName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "outUploadResult"; 
		// 서버 밖의 외부 저장소에 저장하기 때문에 서버에 저장하고 불러오는 방식과는 다른 방식을 사용해야 함.
	}
	
	// img 태그에서  서버 밖의 이미지 데이터를 ajax 방식으로 가져와서 출력하도록 처리
	
	@RequestMapping(value="/uploadDrag", method= RequestMethod.GET)
	public String uploadDragFrom(){
		logger.info("uploadDrag from get");
		
		return "uploadDragForm";
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadDrag", method= RequestMethod.POST)
	public ResponseEntity<List<String>> uploadDragResult(String test, List<MultipartFile> files){
		logger.info("uploadDragResult post");
		ResponseEntity<List<String>> entity = null;
		logger.info("innerMultiUploadResult()");
		logger.info("test : " + test);
		List<String> list = new ArrayList<>();
		
		try{
			for(MultipartFile f : files){
				logger.info("file : " + f.getOriginalFilename()); 
				
				File dir = new File(outUploadPath);
				if(!dir.exists()){
					dir.mkdirs();
				}
				
				UUID uid = UUID.randomUUID();
				String saveName = uid.toString() + "_" + f.getOriginalFilename();
				File imgFile = new File(outUploadPath, saveName);
				
				FileCopyUtils.copy(f.getBytes(), imgFile);
				list.add(outUploadPath + "/" + saveName);
				
			}
			entity = new ResponseEntity<List<String>>(list, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<List<String>>(list, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="displayFile", method = RequestMethod.GET) 
	public ResponseEntity<byte[]> displayFile(String filename){
		ResponseEntity<byte[]> entity = null;
		logger.info("filename : " + filename);
		FileInputStream in = null;
		
		try{
			String formatName = filename.substring(filename.lastIndexOf(".") + 1); // 파일형식 반환(예: jpg, png)
			
			// jsp file 내 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			// contentType을 지정하기 위해 MediaUtils을 만들고 Type을 지정해줌
			// MediUtils 클래스를 만들지 않아도 되지만, 작업의 편의를 위해 별도의 util 패키지내 클래스로 작성
						
			MediaType type = MediaUtils.getMeditType(formatName); 
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			
			in = new FileInputStream(filename);
																		// contentType = "text/MediaType.IMAGE_JPEG"으로 설정한 header를 함께 넘김
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
												// fileInputStream으로 읽은 byte배열을 넘김
		
		
		}catch(Exception e){
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}	
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteFile", method = RequestMethod.GET)
	public ResponseEntity<String> deleteFile(String filename){
		ResponseEntity<String> entity = null;
		logger.info("deleteFile()");
		logger.info("filename : " + filename);
		try{
		
			System.gc(); // 가비지 컬렉터 호출
			File del = new File(filename);
			if(del.exists()){
				del.delete();
			}
			entity = new ResponseEntity<String>("success", HttpStatus.OK);			
		}catch(Exception e){
			entity = new ResponseEntity<String>("success", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
