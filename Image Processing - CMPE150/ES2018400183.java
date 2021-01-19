import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
public class ES2018400183 {
	public static void main(String[] args) throws FileNotFoundException {
		int mode=Integer.parseInt(args[0]);
		File originalImage = new File(args[1]);
		Scanner x = new Scanner(originalImage);			//takes the original ppm file 
		int[][][] image = ppmToArray(x);				//Reads the contents of the PPM file into a 3D array
		if(mode==0) {									//if mode is 0 it does the first process
			printArray(image,0);						//prints the array to the output file
		}if(mode==1) {									//if mode is 1 it does black and white process
			printArray(blackWhite(image),1);			
		}if(mode==2) {									//if mode is 2 it does the convolution part
			File filter = new File(args[2]);			//it reads the filter file to a scanner 
			Scanner y = new Scanner(filter);
			int[][] filterArray = txtToArray(y);		//using a method, reads the contents of the TXT file into a 2D array
			int[][][] convolutionArray = new int[image.length-filterArray.length+1][image[0].length-filterArray[0].length+1][3];
			//creates a new array which has less pixels than the original image
			printArray(convolution(image, convolutionArray,filterArray),2);	//prints the array to the convolution file
		}if(mode==3) {									//if mode is 2 it does the quantization
			int range = Integer.parseInt(args[2]);		//takes the 2nd argument as range
			printArray(quantization(image, range),3);	//prints the array to the quantized file
		}
		
	}
	
	public static int[][][] ppmToArray(Scanner x) throws FileNotFoundException {
		//the method to read the contents of the PPM file into a 3D array
		x.nextLine();									//skips the P3 part
		int rowLen = x.nextInt();						//reads the row length of the pixels
		int colLen = x.nextInt();						//reads the column length of the pixels
		int maxValue = x.nextInt();						//reads the maximum color value
		int[][][] photo = new int[rowLen][colLen][3];	//creates a new 3D array for the pixel values
		for(int i = 0; i < photo.length; i++) {			//this nested loops reads the pixels to the array
			for(int k=0; k < photo[0].length; k++) {
				for(int z=0; z < 3; z++) {
					photo[i][k][z] = x.nextInt();
				}
			}
		}
		return photo;									//returns the array
	}
	public static int[][] txtToArray(Scanner y) throws FileNotFoundException {
		//the method to read the contents of TXT file into a 2D array
		String len=y.nextLine();						//takes the "3x3" etc. part
		String row=len.substring(0,1);					//takes the row and column length separately
		String column=len.substring(2);
		int len1=Integer.parseInt(row);					//turns the sizes to integers
		int len2=Integer.parseInt(column);
		int[][] filter = new int[len1][len2];			//this nested loops reads the contents of the filter to a 2D array
		for(int u=0; u<len1; u++) {						//row of the array
			for(int f=0; f<len2; f++) {					//column of the array
				filter[u][f]=y.nextInt();
			
			}
		}
		return filter;									//returns the 2D array
	}
		public static void printArray(int[][][] image, int mode) throws FileNotFoundException {
			//the method to print an array to a file
			if(mode==0) {								//if mode is 0, prints the whole array to the "output" file
				PrintStream output = new PrintStream("output.ppm");
				output.println("P3");					//prints the file type
				output.println(image.length+" "+image[0].length);	//prints the sizes
				output.println("255");						//prints the maximum color value
				for(int i = 0; i < image.length; i++) {
					for(int k=0; k < image[0].length; k++) {
						for(int z=0; z < 3; z++) {
							output.print(image[i][k][z] + " ");
						}
					}
				}
			}
			if(mode==1) {								//if mode is 1, prints the whole array to the "black-and-white" file
				PrintStream output = new PrintStream(new File("black-and-white.ppm"));
				output.println("P3");					//prints the file type and the length and the maximum color value
				output.println(image.length +" "+image[0].length);
				output.println("255");
				
				for(int i = 0; i < image.length; i++) {
					for(int k=0; k < image[0].length; k++) {
						for(int z=0; z < 3; z++) {
							output.print(image[i][k][z] + " ");
						}
					}
				}	
			}
			if(mode==2) {								//if mode is 2, prints the whole array to the "convolution" file
				PrintStream output = new PrintStream("convolution.ppm");
				output.println("P3");					//prints the file type and the length and the maximum color value
				output.println(image.length +" "+image[0].length);
				output.println("255");
				for(int i = 0; i < image.length; i++) {
					for(int k=0; k < image[0].length; k++) {
						for(int z=0; z < 3; z++) {
							output.print(image[i][k][z] + " ");
						}
					}
				}	
			}
			if(mode==3) {								//if mode is 3, prints the whole array to the "quantized" file
				PrintStream output = new PrintStream("quantized.ppm");
				output.println("P3");					//prints the file type and the length and the maximum color value
				output.println(image.length +" "+image[0].length);
				output.println("255");
				for(int i = 0; i < image.length; i++) {
					for(int k=0; k < image[0].length; k++) {
						for(int z=0; z < 3; z++) {
							output.print(image[i][k][z] + " ");
						}
						output.print("\t");				//prints a tab between every third group of pixels
					}
					output.println();					//prints a new line 
				}	
			}
		}
		public static int[][][] blackWhite(int[][][] image) {
			//method to Calculate the color-channel average values and convert the colored image to black-and-white.
			int sum=0;									//integer used to add the 3 pixels' values
			int avg=0;									//integer used to keep the average of the three pixel group
			for(int i = 0; i < image.length; i++) {		// nested loops to add the 3 pixels' value 
				for(int k=0; k < image[0].length; k++) {
					for(int z=0; z < 3; z++) {
						
						sum=sum+image[i][k][z];
					}
					avg=sum/3;							//takes the average of the 3 pixels' sum 
					for(int z=0; z<3; z++) {			//transfers the average value to all 3 pixels
					image[i][k][z]=avg;
				}
					sum=0;								//sets the average and sum values back to zero
					avg=0;
				}
			}
			return image;								//returns the image array
		}
	
		public static int[][][] convolution(int[][][] image, int[][][] convolutionArray, int[][] filterArray) {
			//method to apply a kernel filter to an image
			int keep=1;									//neutral element for multiplication of a pixel and the value of the filter
			int sum=0;									//neutral element for addition of all multiplications
				for(int row = 0; row<convolutionArray.length; row++) {	// Nested loops to apply convolution to each color channel of the image separately
					for(int column = 0; column<convolutionArray[0].length; column++) {
						for(int channel=0; channel<3; channel++) {
						for(int filterRow=0; filterRow<filterArray.length; filterRow++) {
							for(int filterColumn=0; filterColumn<filterArray[0].length; filterColumn++) {
							keep=image[row+filterRow][column+filterColumn][channel]*filterArray[filterRow][filterColumn];
							sum=sum+keep;
							keep=1;	
						}
							
					}
						if(sum>255) {					//if sum values are greater than maximum value or negative, changes them to proper value
							sum=255;
						}
						else if(sum<0) {
							sum=0;
						}
						convolutionArray[row][column][channel]=sum; //before moving the filter, transfers the sum to the convolution array
						sum=0;							//sets the sum back to zero
					}		
				}
			}
				return blackWhite(convolutionArray);	//returns the array after applying black and white filter
		}
		public static int[][][] quantization(int[][][] image, int range) {	
			//method to do quantization 
			boolean[][][] check = new boolean[image.length][image[0].length][3]; //creates a boolean array to check if the pixels are already painted
			int keep=0;									//keeps the value of the pixel whose neighbors' will be checked
			for(int z=0; z < 3; z++) {					//does the quantization for every pixel using for loops
				for(int x = 0; x < image.length; x++) {
					for(int y=0; y < image[0].length; y++) {
						keep=image[x][y][z] ;
						quantized(image, x, y, z, check, range, keep);	//calls the helper quantization method
					}
				}
			}
			return image;								//returns the quantized array
		}
		
		public static void quantized(int[][][] image, int x, int y, int z, boolean[][][] check, int range, int keep)  {
			//helper method for quantization
			if(x<0 || x>=image.length ||y<0 || y>=image[0].length || z<0 || z>=3 || image[x][y][z]>keep+range || image[x][y][z]<keep-range || check[x][y][z]==true ) {
			//if the pixel doesn't have neighbors(out of bounds), or if it is not in range, or it has already painted(boolean value for that pixel must be true), exits the method 	
				return;
			
			}else { //else, makes the neighbor's value same as the reference pixel and makes its boolean value true(makes it painted) 
			image[x][y][z]=keep;
			check[x][y][z]=true; 		
			quantized(image, x+1, y, z, check, range, keep); //does the whole process recursively to all neighbors of a pixel in an order
			quantized(image, x-1, y, z, check, range, keep);
			quantized(image, x, y+1, z, check, range, keep);
			quantized(image, x, y-1, z, check, range, keep);
			quantized(image, x, y, z+1, check, range, keep);
			quantized(image, x, y, z-1, check, range, keep);
					}
			
				}
			
		}
  
    

