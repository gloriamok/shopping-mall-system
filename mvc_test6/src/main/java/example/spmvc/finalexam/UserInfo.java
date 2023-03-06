package example.spmvc.finalexam;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class UserInfo { // 회원정보
	// 아이디, 암호, 이름, 생년월일, 이메일, 주소, 전화번호
	@NotEmpty // Bean Validation으로 커맨드 객체 검증하기
	private String userid;

	@NotEmpty
	private String pwd;

	private Boolean rememberid;

	@NotEmpty
	private String name;

	@DateTimeFormat(pattern = "yyyyMMdd")
	private LocalDate birthday; // 시,분까지 필요하면 LocalDateTime 써야함
	// @NotEmpty는 LocalDate 타입을 지원하지 않기 때문에 birthday 입력값이 null일 때 에러가 나지 않게
	// HomeController에서 값
	// 써줘야함

	@NotBlank
	@Email
	private String email;

	private String address;

	@Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
	private String phoneNum;

	public String getUserid() {
		return userid;
	}

	public String getPwd() {
		return pwd;
	}

	public Boolean getRememberid() {
		return rememberid;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setRememberid(Boolean rememberid) {
		this.rememberid = rememberid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
