package com.hz.learnboot.poi.controller;

import com.hz.learnboot.poi.convert.EntityToMapConvert;
import com.hz.learnboot.poi.model.WordParam;
import com.hz.learnboot.poi.utils.FileUtil;
import com.hz.learnboot.poi.utils.PoiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

@Controller
public class PoiController extends BaseController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(PoiController.class);

	@Value("${zyd.springboot.param.company}")
	private String company;

	private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<Long>();

	@GetMapping("/")
	public ModelAndView index(Model model) {
		try {
			// properties文件中的中文 默认是以ISO-8859-1的字符编码读取
			// 要想获取真实数据，需要重新编码
			model.addAttribute("company", new String(company.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ModelAndView("index");
	}

	@PostMapping("/build")
	public ResponseEntity<byte[]> build(WordParam wordParam) throws IOException {
		THREAD_LOCAL.set(System.currentTimeMillis());
		LOGGER.info("准备生成绩效考核文件...");
		Map<String, Object> map = EntityToMapConvert.toMap(wordParam);
		for (Entry<String, Object> set : map.entrySet()) {
			String value = set.getValue().toString().replaceAll("\\r", "").replaceAll("\\n", "\r");
			map.put(set.getKey(), value);
			LOGGER.info(set.getKey() + "=[" + value + "]");
		}
		String fileName = PoiUtil.generateFileName(wordParam);
		String expFile = "/opt/tmp/" + fileName;
		File file = null;
		try {
			LOGGER.info("开始生成绩效考核文件...");
			// String tmpFile = "classpath:conf/template.doc";
			// LOGGER.info("模板地址：{}", tmpFile);

			// URL
			// fileURL=this.getClass().getClassLoader().getResource("conf/template.doc");
			// System.out.println("==========" + fileURL.getFile());

			// PoiUtil.build(ResourceUtils.getFile(tmpFile), map, expFile);

			InputStream inputStream = PoiController.class.getClassLoader().getResourceAsStream("conf/template.doc");
			PoiUtil.build(inputStream, map, expFile);
			LOGGER.info("绩效考核文件已生成[" + expFile + "]...");
			file = new File(expFile);
			return FileUtil.download(fileName, file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file.exists()) {
				LOGGER.info("清除临时文件" + expFile);
				file.delete();
			}
		}
		LOGGER.info("发生异常");
		return null;
	}
}
