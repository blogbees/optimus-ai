package com.optimus.ai.image.webservices;

import java.util.Map;

import org.optimus.ai.image.analyzer.ImageAnalyzer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class ImageWebService {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "image-web-server");
		SpringApplication.run(ImageWebService.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Optimus Image Web Services";
	}

	@RequestMapping("/analyze")
	public Map<String, Object> analyze(MultipartFile imageFile) throws Exception {
		ImageAnalyzer tensorFlowImageAnalyzer = new ImageAnalyzer();
		return tensorFlowImageAnalyzer.analyze(imageFile.getBytes());
	}
}
