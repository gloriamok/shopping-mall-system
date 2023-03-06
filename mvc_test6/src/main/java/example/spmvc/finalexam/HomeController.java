package example.spmvc.finalexam;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private ArrayList<UserInfo> userList;
	private UserInfo user_info;

	private ArrayList<ProductInfo> productList;
	private ArrayList<OrderHistory> orderList;

	private int orderCnt = 10100;

	public HomeController() {
		userList = new ArrayList<UserInfo>();
		user_info = new UserInfo();

		productList = new ArrayList<ProductInfo>();
		productList.add(new ProductInfo(1, "베이지 니트 가디건", 25900, "F", "어디에나 어울리는, 언제나 걸치기 좋은 베이지색 니트 가디건"));
		productList.add(new ProductInfo(2, "데님 진", 58000, "F", "스트레치 원단으로 제작되어 착용감이 편안한 데님 진"));
		productList.add(new ProductInfo(3, "검정 슬랙스", 33000, "F", "하이웨스트 디자인으로 길어보이면서 베이직한 컬러감으로 어디에나 매치 가능한 슬랙스"));
		productList.add(new ProductInfo(4, "발목 양말 3세트 (하양,회색,검정)", 1900, "F", "편안하고 안정적인 착용감"));
		productList.add(new ProductInfo(5, "검정 백팩", 99000, "F", "다양하고 편리한 수납공간을 자랑하는, 대학생과 직장인의 필수품"));

		orderList = new ArrayList<OrderHistory>();
	}

	/* 홈화면 */
	@GetMapping("/")
	public String home(UserInfo userInfo, ProductInfo productInfo, Model model, HttpSession session) {
		if (session != null) {
			model.addAttribute("authInfo", (UserInfo) session.getAttribute("authInfo"));
		}
		return "home";
	}

	/* 회원가입 */
	@GetMapping("/signup")
	public String signup(UserInfo userInfo, Model model, HttpSession session) {
		if (session != null) {
			model.addAttribute("authInfo", (UserInfo) session.getAttribute("authInfo"));
		}
		return "signup";
	}

	/* 회원가입결과 */
	@PostMapping("/submit")
	public String signupResult(@Valid UserInfo userInfo, Errors errors) { // Bean Validation으로 커맨드 객체 검증하기
		for (UserInfo item : userList) {
			if (item.getUserid().equals(userInfo.getUserid())) {
				errors.rejectValue("userid", "duplicated");
			}
			if (item.getEmail().equals(userInfo.getEmail())) {
				errors.rejectValue("email", "duplicated");
			}
			if (item.getPhoneNum().equals(userInfo.getPhoneNum())) {
				errors.rejectValue("phoneNum", "duplicated");
			}
		}
		if (userInfo.getBirthday() == null)
			errors.rejectValue("birthday", "required");
		if (errors.hasErrors()) {
			return "signup";
		}
		userList.add(userInfo);

		return "signup_result";
	}

	/* 리다이렉트 처리 */
	@GetMapping("/submit")
	public String signupRedirect() {
		return "redirect:/";
	}

	/* 로그인 */
	@GetMapping("/login")
	public String login(UserInfo userInfo, Model model, HttpSession session,
			@CookieValue(value = "REMEMBERID", required = false) Cookie rCookie) {
		if (session == null || session.getAttribute("authInfo") == null) {
			if (rCookie != null) {
				userInfo.setUserid(rCookie.getValue());
				userInfo.setRememberid(true);
			}
		}
		return "login";
	}

	/* 로그인결과 */
	@PostMapping("/submit2")
	public String loginResult(UserInfo userInfo, Errors errors, HttpSession session, HttpServletResponse response) {
		if ("".equals(userInfo.getUserid())) // userid 입력이 null일 때 에러메세지
			errors.rejectValue("userid", "required");
		else if ("".equals(userInfo.getPwd())) // pwd 입력이 null일 때 에러메세지
			errors.rejectValue("pwd", "required");
		else {
			boolean flag = true;
			for (UserInfo item : userList) {
				if (item.getUserid().equals(userInfo.getUserid()) && item.getPwd().equals(userInfo.getPwd())) {
					flag = false;
					user_info.setUserid(item.getUserid());
					user_info.setPwd(item.getPwd());
					user_info.setName(item.getName());
					user_info.setBirthday(item.getBirthday());
					user_info.setEmail(item.getEmail());
					user_info.setAddress(item.getAddress());
					user_info.setPhoneNum(item.getPhoneNum());
					session.setAttribute("authInfo", item);

					Cookie rememberCookie = new Cookie("REMEMBERID", userInfo.getUserid());
					rememberCookie.setPath("/");
					if (userInfo.getRememberid()) {
						rememberCookie.setMaxAge(60 * 60 * 24 * 30);
					} else {
						rememberCookie.setMaxAge(0);
					}
					response.addCookie(rememberCookie);

				}
			}
			if (flag) { // userid와 pwd가 틀렸을 때 에러메세지
				errors.rejectValue("userid", "invalid");
				errors.rejectValue("pwd", "nomatch");
			}
		}
		if (errors.hasErrors()) {
			return "login";
		}
		return "login_result";
	}

	/* 리다이렉트 처리 */
	@GetMapping("/submit2")
	public String loginRedirect() {
		return "redirect:/";
	}

	/* 로그아웃 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		if (session != null)
			session.invalidate();
		return "logout";
	}

	/* 사용자 정보 */
	@RequestMapping("/userInfo")
	public String userinfo() {
		return "userinfo";
	}

	/* 사용자 정보 (아이디 포함) */
	@RequestMapping("/userInfo/{id}")
	public String userinfo(@PathVariable("id") String userid, UserInfo userInfo, Model model, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";
		model.addAttribute("authInfo", (UserInfo) session.getAttribute("authInfo"));
		return "userinfo";
	}

	/* 사용자 정보 수정 */
	@RequestMapping("/modifyUserInfo")
	public String modifyuserinfo(UserInfo userInfo, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";
		return "modifyuserinfo";
	}

	/* 사용자 정보 수정 제출 */
	@PostMapping("/submituserinfo")
	public String modifyUserInfoResult(UserInfo userInfo, Errors errors) {
		new UserInfoValidator().validate(userInfo, errors);

		for (UserInfo item : userList) {
			if (!item.getEmail().equals(user_info.getEmail()) && item.getEmail().equals(userInfo.getEmail())) {
				errors.rejectValue("email", "duplicated");
			}
			if (!item.getPhoneNum().equals(user_info.getPhoneNum())
					&& item.getPhoneNum().equals(userInfo.getPhoneNum())) {
				errors.rejectValue("phoneNum", "duplicated");
			}
		}

		if (errors.hasErrors()) {
			return "modifyuserinfo";
		}

		for (UserInfo item : userList) {
			if (item.getUserid().equals(user_info.getUserid())) {
				item.setName(userInfo.getName());
				item.setBirthday(userInfo.getBirthday());
				item.setEmail(userInfo.getEmail());
				item.setAddress(userInfo.getAddress());
				item.setPhoneNum(userInfo.getPhoneNum());
				// session.setAttribute("authInfo", item);
			}
		}
		user_info.setName(userInfo.getName());
		user_info.setBirthday(userInfo.getBirthday());
		user_info.setEmail(userInfo.getEmail());
		user_info.setAddress(userInfo.getAddress());
		user_info.setPhoneNum(userInfo.getPhoneNum());
		return "userinfo";
	}

	/* 리다이렉트 처리 */
	@GetMapping("/submituserinfo")
	public String modifyRedirect() {
		return "redirect:/";
	}

	/* 회원탈퇴 */
	@RequestMapping("/deleteId")
	public String deleteid(HttpSession session) {

		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		// userList에서 해당 사용자 정보 삭제
		int size = userList.size();
		for (int i = 0; i < size; i++) {
			UserInfo item = userList.get(i);
			if (item.getUserid().equals(user_info.getUserid())) {
				userList.remove(item);
				size--;
				i--;
			}
		}

		// orderList에서 해당 사용자 주문정보 삭제
		int size2 = orderList.size();
		for (int i = 0; i < size2; i++) {
			OrderHistory item = orderList.get(i);
			if (item.getUserid().equals(user_info.getUserid())) {
				orderList.remove(item);
				size2--;
				i--;
			}
		}

		if (session != null)
			session.invalidate();
		return "deleteid";
	}

	/* product1 상세 설명 */
	@GetMapping("/product1")
	public String product1(Model model) {

		model.addAttribute("queryResult", productList.get(0).printProductInfo());

		return "product1";
	}

	/* product2 상세 설명 */
	@GetMapping("/product2")
	public String product2(Model model) {

		model.addAttribute("queryResult", productList.get(1).printProductInfo());

		return "product2";
	}

	/* product3 상세 설명 */
	@GetMapping("/product3")
	public String product3(Model model) {

		model.addAttribute("queryResult", productList.get(2).printProductInfo());

		return "product3";
	}

	/* product4 상세 설명 */
	@GetMapping("/product4")
	public String product4(Model model) {

		model.addAttribute("queryResult", productList.get(3).printProductInfo());

		return "product4";
	}

	/* product5 상세 설명 */
	@GetMapping("/product5")
	public String product5(Model model) {

		model.addAttribute("queryResult", productList.get(4).printProductInfo());

		return "product5";
	}

	/* product1 주문결과 */
	@RequestMapping("/order1")
	public String order1(Model model, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		orderList.add(new OrderHistory(orderCnt++, user_info.getUserid(), productList.get(0).getProductName(),
				productList.get(0).getPrice(), LocalDate.now()));

		return "order";
	}

	/* product2 주문결과 */
	@RequestMapping("/order2")
	public String order2(Model model, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		orderList.add(new OrderHistory(orderCnt++, user_info.getUserid(), productList.get(1).getProductName(),
				productList.get(1).getPrice(), LocalDate.now()));

		return "order";
	}

	/* product3 주문결과 */
	@RequestMapping("/order3")
	public String order3(Model model, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		orderList.add(new OrderHistory(orderCnt++, user_info.getUserid(), productList.get(2).getProductName(),
				productList.get(2).getPrice(), LocalDate.now()));

		return "order";
	}

	/* product4 주문결과 */
	@RequestMapping("/order4")
	public String order4(Model model, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		orderList.add(new OrderHistory(orderCnt++, user_info.getUserid(), productList.get(3).getProductName(),
				productList.get(3).getPrice(), LocalDate.now()));

		return "order";
	}

	/* product5 주문결과 */
	@RequestMapping("/order5")
	public String order5(Model model, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		orderList.add(new OrderHistory(orderCnt++, user_info.getUserid(), productList.get(4).getProductName(),
				productList.get(4).getPrice(), LocalDate.now()));

		return "order";
	}

	/* 전체 구매 이력 */
	@RequestMapping("/orderhistory")
	public String orderhistory() {
		return "redirect:/";
	}

	/* 전체 구매 이력 (아이디 포함) */
	@RequestMapping("/orderhistory/{id}")
	public String orderhistory(@PathVariable("id") String userid, Model model, OrderHistory orderHistory,
			HttpSession session) {

		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		String result = "주문번호" + "\t\t" + "제품명" + "\t\t" + "가격" + "\t\t" + "주문날짜" + "<br>";
		boolean flag = true;

		for (OrderHistory item : orderList) {
			if (item.getUserid().equals(user_info.getUserid())) {
				flag = false;
				result += item.printOrderHistory();
			}
		}
		if (flag)
			result = "구매 이력이 없습니다.";

		model.addAttribute("queryResult", result);

		return "orderhistory";
	}

	/* 제품명으로 구매 이력 조회 (아이디 포함) */
	@PostMapping("/orderhistory/{id}")
	public String orderhistory(@PathVariable("id") String userid, OrderHistory orderHistory, Model model,
			HttpSession session) {

		if (session.getAttribute("authInfo") == null)
			return "redirect:/";

		String result = "주문번호" + "\t\t" + "제품명" + "\t\t" + "가격" + "\t\t" + "주문날짜" + "<br>";
		boolean flag = true;

		for (OrderHistory item : orderList) {
			if (item.getUserid().equals(user_info.getUserid())
					&& item.getProductName().equals(orderHistory.getProductName())) {
				flag = false;
				result += item.printOrderHistory();
			}
		}
		if (flag)
			result = "구매 이력이 없습니다.";

		model.addAttribute("queryResult", result);

		return "orderhistory";
	}

	/* 기간으로 구매 이력 조회 */
	@GetMapping("/searchbyperiod")
	public String searchbyperiod(OrderPeriod orderPeriod, HttpSession session) {
		if (session.getAttribute("authInfo") == null)
			return "redirect:/";
		return "searchbyperiod";
	}

	/* 기간으로 구매 이력 조회 완료 */
	@PostMapping("/orderhistory2")
	public String orderhistory2(@Valid OrderPeriod orderPeriod, Model model, HttpSession session, Errors errors) {

		if (session.getAttribute("authInfo") == null)
			return "redirect:/";
		if (orderPeriod.getOrderDate1() == null)
			errors.rejectValue("orderDate1", "required");
		else if (orderPeriod.getOrderDate2() == null)
			errors.rejectValue("orderDate2", "required");
		else if (orderPeriod.getOrderDate1().isAfter(orderPeriod.getOrderDate2())) // 기간1이 기간2 보다 클 경우
			errors.rejectValue("orderDate1", "size");
		if (errors.hasErrors()) {
			return "searchbyperiod";
		}

		model.addAttribute("queryOrderDate1", orderPeriod.getOrderDate1());
		model.addAttribute("queryOrderDate2", orderPeriod.getOrderDate2());

		String result = "주문번호" + "\t\t" + "제품명" + "\t\t" + "가격" + "\t\t" + "주문날짜" + "<br>";
		boolean flag = true;

		for (OrderHistory item : orderList) {
			if (item.getUserid().equals(user_info.getUserid())
					&& (item.getOrderDate().isEqual(orderPeriod.getOrderDate1())
							|| item.getOrderDate().isAfter(orderPeriod.getOrderDate1()))
					&& (item.getOrderDate().isBefore(orderPeriod.getOrderDate2())
							|| item.getOrderDate().isEqual(orderPeriod.getOrderDate2()))) {
				flag = false;
				result += item.printOrderHistory();
			}
		}
		if (flag)
			result = "구매 이력이 없습니다.";

		model.addAttribute("queryResult", result);

		return "orderhistory2";

	}

	/* 리다이렉트 처리 */
	@GetMapping("/orderhistory2")
	public String orderhistory2Redirect() {
		return "redirect:/";
	}

	/* 제품 검색 */
	@PostMapping("/searchforproduct")
	public String searchforproduct(Model model, ProductInfo productInfo) {
		boolean flag = true;
		for (ProductInfo item : productList) {
			if (item.getProductName().equals(productInfo.getProductName())) {
				flag = false;
				model.addAttribute("queryResult", item.getProductNum());
			}
		}
		if (flag)
			model.addAttribute("queryResult2", "검색 결과가 없습니다.");
		return "searchforproduct";
	}

	/* 리다이렉트 처리 */
	@GetMapping("/searchforproduct")
	public String searchforproductRedirect() {
		return "redirect:/";
	}

}