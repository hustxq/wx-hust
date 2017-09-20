package cn.hustxq.hust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class HustApplication {
	@RequestMapping("/")
	@ResponseBody
	public String greeting() {
		return "Hello World!";
	}

	@RequestMapping("/json")
	public Map json(){
		Map map = new HashMap<>();
		map.put("name","xq");
		return map;
	}

	public static void main(String[] args) {
		SpringApplication.run(HustApplication.class, args);
	}
}
