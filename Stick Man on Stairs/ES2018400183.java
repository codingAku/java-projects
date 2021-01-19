
public class ES2018400183 {

	public static void main(String[] args) {
		int stickmanHeight = Integer.parseInt(args[0]);
		int stairHeight = Integer.parseInt(args[1]);
			StickManOnStair(stickmanHeight, stairHeight); /*this method is used to create the required pattern: stickman climbing up*/
		}
		public static void StickManOnStair(int x, int y) {
			for(int stepcount=0; stepcount<y+1; stepcount++) /*count of the frame */ {
				spaces(stepcount); /*this method is used for the number of spaces before the stickman*/
			System.out.println(" O");
			spaces(stepcount);
			System.out.println("/|\\");
			for(int torso=1; torso<=x-y-3+stepcount; torso++) { /*count of the torso without stair*/
				spaces(stepcount);
				System.out.println(" |");
			}
			for( int line=0; line<y-stepcount; line++) { /*count of the stair line*/
				spaces(stepcount);
				System.out.print(" |");
				for (int spaces=1; spaces<=-3*line+3*y+1-(3*stepcount); spaces++) { /*count of the spaces between the man and the stair*/
					System.out.print(" ");
				}
				System.out.print("___");
				System.out.print("|");
				for(int stars=0; stars<3*line; stars++) { /*count of stars in the stair*/
					System.out.print("*");
				}
				System.out.println("|");
			}
			spaces(stepcount);
			System.out.print("/ \\");
			System.out.print("___");
			System.out.print("|");
			for(int starsBottom=1; starsBottom<=3*y-3*stepcount; starsBottom++) { /*count of the stars in the part of stair after the stickman's feet*/
				System.out.print("*");
			}
			System.out.println("|");

			//for the part of stair below the stickman's feet
			for(int line2=0; line2<stepcount; line2++) {
				for(int spaces=0; spaces<-3*line2+3*stepcount; spaces++) {/*count of the spaces at the beginning of the stair*/
					System.out.print(" ");
				}
				System.out.print("___|");
			for(int stars=0; stars<-3*stepcount+(3*y+3)+(3*line2); stars++) {/* count of the stars at the stair*/
				System.out.print("*");
			}
			System.out.println("|");
			
			}
			for(int a=0; a<y+1-stepcount; a++) { //count of the lines between each frame
				System.out.println();
				}
			}
			}
		public static void spaces(int x) { /*this method is used to print the spaces before printing stickman*/
			for(int spaces=0; spaces<3*x; spaces++) { /*count of spaces before the stickman*/
				System.out.print(" "); }}
		
public static void headandArms(int x) { /* this method is used to print the head and arms of the stickman*/
	System.out.println(" O");
	spaces(x);
	System.out.println("/|\\");
}
}
		
	

