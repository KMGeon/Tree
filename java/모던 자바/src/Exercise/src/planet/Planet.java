package planet;

public class Planet {
	public enum PlanetRadius { 수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		private int radius;
		
		PlanetRadius(int data){
			radius = data;
		}

		public int getRadius() {
			return radius;
		}

		public void setRadius(int radius) {
			this.radius = radius;
		}
		
	}
	
	public static void main(String[] args) {
		// 데이터 배열로 가져오는 법
		PlanetRadius[] enumArr = PlanetRadius.values();
		for(int i = 0 ; i < enumArr.length ; i++) {
			System.out.println(enumArr[i].name() + " : " + 4*Math.pow(enumArr[i].getRadius(),2)*Math.PI + "km²");
		}
		System.out.println("-----------------------------------------");
		
		// 향상된 for문으로 푸는 법
		for(PlanetRadius r : PlanetRadius.values()) {
			System.out.println(r + " : " + 4*Math.PI*Math.pow(r.getRadius(), 2) + "km²");
		}
	}
	
}
