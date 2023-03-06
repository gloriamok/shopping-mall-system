package example.spmvc.finalexam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserInfoValidator implements Validator {
	// 이메일 형식 정규 표현식 문자열
	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-zZa-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern; // 정규 표현식 패턴 검사결과 저장 변수

	// 생성자에서 이메일 형식을 미리 검증
	public UserInfoValidator() {
		pattern = Pattern.compile(emailRegExp);
	}

	// 스프링 MVC가 자동으로 검증 기능을 수행하기 위해 필수로 구현
	@Override
	public boolean supports(Class<?> clazz) {
		// 반환값: <커맨드 객체 클래스명>.class.isAssignableForm(class)
		return UserInfo.class.isAssignableFrom(clazz);
	}

	// 검증 기능 구현부. 검사 대상 객체의 특정 프로퍼티나 상태가 올바른지 검사하고
	// 올바르지 않다면 Errors의 rejectValue() 메서드를 사용하여 에러 코드 저장
	@Override
	public void validate(Object target, Errors errors) {
		// target으로 넘어오는 객체는 커맨드 객체 (여기서는 userInfo 객체)
		UserInfo userInfo = (UserInfo) target;
		// 이메일 형식 검사
		if (userInfo.getEmail() == null || userInfo.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			Matcher matcher = pattern.matcher(userInfo.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		// 전화번호 형식 검사
		if (userInfo.getPhoneNum() == null || userInfo.getPhoneNum().trim().isEmpty()) {
			errors.rejectValue("phoneNum", "required");
		} else if (!userInfo.getPhoneNum().matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) {
			// 번호 형식 : 첫번째 번호는 010,011,016~019만 가능, 중간 번호는 digit 3~4개, 마지막 번호는 digit 4개만 가능
			errors.rejectValue("phoneNum", "bad");
		}
		// 생일 형식 검사
		if (userInfo.getBirthday() == null)
			errors.rejectValue("birthday", "required");

		// 이름은 null값 및 빈 문자열, 공백 문자만으로 된 문자열은 허용하지 않음
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
	}
}
