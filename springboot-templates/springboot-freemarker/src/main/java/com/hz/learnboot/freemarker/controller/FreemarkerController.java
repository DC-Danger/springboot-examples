package com.hz.learnboot.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FreemarkerController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/toUpload")
	public String toUpload(ModelMap model) {
		return "upload";
	}

	@RequestMapping("/toFormData")
	public String formData(ModelMap model) {
		return "formdata";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[] files, String name,HttpServletRequest request) {
		if(files != null){
			for(MultipartFile file : files){
				System.out.println(file.getOriginalFilename());
			}
		}
		System.out.println(request.getParameter("name"));
		return name;
	}

	@RequestMapping("/formData")
	@ResponseBody
	public String formData(@RequestParam("file") MultipartFile[] files, String name,HttpServletRequest request) {
		if(files != null){
			for(MultipartFile file : files){
				System.out.println(file.getOriginalFilename());
			}
		}
		System.out.println(request.getParameter("name"));
		return name;
	}
}
