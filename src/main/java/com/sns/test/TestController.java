package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@ResponseBody
    @GetMapping("/test1")
    public String helloWorld() {
        return "Hello world!";
    }
	
	@GetMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String,Object> result = new HashMap<>();
		
		result.put("키1", 808);
		result.put("키2", 913);
		result.put("키3", 115);
		
		return result;
	}
	
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
//	@GetMapping("/test4")
//	@ResponseBody
//	public List<Map<String,Object>> test4() {
//		
//		List<Map<String,Object>> result = postDAO.selectPostTest();
//		
//		return result;
//	}
	
	
	
}	