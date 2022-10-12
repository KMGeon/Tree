package studentListSort;

public class Student implements Comparable<Student>{
	private String studentNo;
	private String name;
	private int koreanScore;
	private int englishScore;
	private int mathScore;
	private int totalScore;
	private int ranking;

	public Student() {
		
	}
	
	public Student(String studentNo, String name, int koreanScore, int englishScore, int mathScore) {
		this.studentNo = studentNo;
		this.name = name;
		this.koreanScore = koreanScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.totalScore = koreanScore + englishScore + mathScore;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoreanScore() {
		return koreanScore;
	}

	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	
	@Override
	public String toString() {
		return String.format("학번 : %s, 이름: %s, 국어 점수 : %d, 영어점수: %d, 수학점수 : %d, 총점: %d, 순위: %d", studentNo, name, koreanScore, englishScore, mathScore, totalScore, ranking);
	}
	

	// 학번기준 정렬 
	@Override
	public int compareTo(Student std) {
		return this.getStudentNo().compareTo(std.getStudentNo());
	}
	

	
	
	
}
