package cn.hustxq.hust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@ComponentScan(basePackages={"cn.hustxq.hust"})
// mapper 接口类扫描包配置
@MapperScan("cn.hustxq.hust.dao")
public class HustApplication extends SpringBootServletInitializer {
	/*@RequestMapping("/")
	@ResponseBody
	public String greeting() {
		return "Hello World!";
	}

	@RequestMapping("/json")
	public Map json(){
		Map map = new HashMap<>();
		map.put("name","xq");
		return map;
	}*/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HustApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(HustApplication.class, args);
	}
}
