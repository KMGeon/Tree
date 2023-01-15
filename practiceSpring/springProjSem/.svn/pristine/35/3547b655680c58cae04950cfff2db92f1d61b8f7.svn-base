package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class MemVO implements Serializable{
	//필수 입력
	private String memId;
	//필수 입력 + 최대 3글자까지 허용
	private String memName;
	private String memJob;
	private String memLike;
	private String memHp;
	//MEM테이블에 없어도 사용 가능
	private String userId;
	private String password;
	//자기 소개
	private String introduction;
	private MultipartFile picture;
	private MultipartFile picture2;
	//..name="pictureList[0]"
	private List<MultipartFile> pictureList;
	//..name="pictureArray" multiple
	private MultipartFile[] pictureArray;
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
	//개발자 여부(Y, null)
	private String developer;
	//외국인 여부(boolean형이 더 좋더라)
	private boolean foreigner;
	
	private List<AttachVO> attachVOList;
	
	public MemVO() {}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemJob() {
		return memJob;
	}

	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}

	public String getMemLike() {
		return memLike;
	}

	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

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

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public MultipartFile getPicture2() {
		return picture2;
	}

	public void setPicture2(MultipartFile picture2) {
		this.picture2 = picture2;
	}

	public List<MultipartFile> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<MultipartFile> pictureList) {
		this.pictureList = pictureList;
	}

	public MultipartFile[] getPictureArray() {
		return pictureArray;
	}

	public void setPictureArray(MultipartFile[] pictureArray) {
		this.pictureArray = pictureArray;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String[] getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(String[] hobbyList) {
		this.hobbyList = hobbyList;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
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

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public boolean isForeigner() {
		return foreigner;
	}

	public void setForeigner(boolean foreigner) {
		this.foreigner = foreigner;
	}

	public List<AttachVO> getAttachVOList() {
		return attachVOList;
	}

	public void setAttachVOList(List<AttachVO> attachVOList) {
		this.attachVOList = attachVOList;
	}

	@Override
	public String toString() {
		return "MemVO [memId=" + memId + ", memName=" + memName + ", memJob=" + memJob + ", memLike=" + memLike
				+ ", memHp=" + memHp + ", userId=" + userId + ", password=" + password + ", introduction="
				+ introduction + ", picture=" + picture + ", picture2=" + picture2 + ", pictureList=" + pictureList
				+ ", pictureArray=" + Arrays.toString(pictureArray) + ", coin=" + coin + ", birth=" + birth
				+ ", gender=" + gender + ", nationality=" + nationality + ", cars=" + Arrays.toString(cars) + ", car="
				+ car + ", hobbyList=" + Arrays.toString(hobbyList) + ", hobby=" + hobby + ", marriaged=" + marriaged
				+ ", addressVO=" + addressVO + ", cardVOList=" + cardVOList + ", developer=" + developer
				+ ", foreigner=" + foreigner + ", attachVOList=" + attachVOList + "]";
	}
	
}








