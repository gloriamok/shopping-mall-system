package example.spmvc.finalexam;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//label.properties 파일 적용을 위해 MessageSourcer 빈 설정을 포함한 AppConfig 클래스 작성
@Configuration
@EnableWebMvc // Bean Validation으로 커맨드 객체 검증하기
public class AppConfig implements WebMvcConfigurer {
	// 빈 이름은 무조건 messageSource 여야 함 !!
	@Bean
	public MessageSource messageSource() {
		// 메시지 파일로 프로퍼티 형식 사용을 위한 MessageSource 구현체 클래스
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("label"); // src/main/resources 폴더에 label.properties가 있기 때문
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}