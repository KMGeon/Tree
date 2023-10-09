package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

//자바빈 클래스
public class MemberVO {
	//회원 아이디
	private String userId = "gaeddongi";
	//비밀번호
	private String password = "java";
	//보유 코인
	private int coin = 100;
	//생일(기본 : 2022/11/01 => 변경 : 20221101 => 변경 : 2022-11-01)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	//성별
	private String gender;
	//국적
	private String nationality;
	//보유 자동차들(qm6,volvo)
	private String[] cars;
	private String car;
	//취미들(movie, baseball, basketball)
	private String[] hobbyList;
	private String hobby;
	//결혼유무
	private boolean marriaged;
	//중첩된 자바빈(1:1)
	private AddressVO addressVO;
	//중첩된 자바빈(1:N)
	private List<CardVO> cardVOList;
	//기본 생성자
	public MemberVO() {}
	//getter/setter메소드
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String[] getCars() {
		return cars;
	}
	public void setCars(String[] cars) {
		this.cars = cars;
	}
	public String[] getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(String[] hobbyList) {
		this.hobbyList = hobbyList;
	}
	public boolean isMarriaged() {
		return marriaged;
	}
	public void setMarriaged(boolean marriaged) {
		this.marriaged = marriaged;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public List<CardVO> getCardVOList() {
		return cardVOList;
	}
	public void setCardVOList(List<CardVO> cardVOList) {
		this.cardVOList = cardVOList;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", password=" + password + ", coin=" + coin + ", birth=" + birth
				+ ", gender=" + gender + ", nationality=" + nationality + ", cars=" + Arrays.toString(cars) + ", car="
				+ car + ", hobbyList=" + Arrays.toString(hobbyList) + ", hobby=" + hobby + ", marriaged=" + marriaged
				+ ", addressVO=" + addressVO + ", cardVOList=" + cardVOList + "]";
	}
	
	
	
}







