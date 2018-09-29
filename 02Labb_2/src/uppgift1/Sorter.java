package uppgift1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * README This API is can sort an array, with the insertion sort in both ascending and descnding order. 
 * It can also print out the inversion count for the descending order and print the list in a nice format.
 * 
 * @author danielduner
 *
 * @param
 */
public class Sorter{
	Scanner sc = new Scanner(System.in);
	/**
	 * A sorting algorithm that sorts in ascending order. the algorithm first save 
	 * the current number and then start looking at each value that is to the left 
	 * of the stored value in the array. if a value is bigger than the stored value, 
	 * it will move one place up in the array.
	 * @param list the list which to sort
	 * @return
	 * @throws InterruptedException 
	 */
	public int[] insertionSort(int[] list) throws InterruptedException{

		for (int i = 1; i < list.length; i++) {
			int mem = list[i];
			int k = i-1;
			System.out.println(i+". the next element to sort is: ["+mem+"]");
			while (k >= 0 && list[k] > mem) {
				System.out.println("Is "+ list[k]+" larger than " + mem +"? "+(list[k] > mem));
				list[k + 1] = list[k];
				k--;
			}
				list[k+1] = mem;
			k = 0;
			printList(list);
			System.out.println("");
		}
		
		return list;
	}
	/**
	 * The updated version of the sorting algorithm, still insertion sort, but changed to sort in descending order
	 * @param list the list which to sort
	 * @return returns the values of the 
	 * @throws InterruptedException
	 */
	public int[] insertionSortDescending(int[] list) throws InterruptedException{
		int swaps = 0;
		for (int i = 1; i < list.length; i++) {
			int mem = list[i];
			int k = i-1;
			System.out.println(i+". the next element to sort is: ["+mem+"]");
			while (k >= 0 && list[k] < mem) {
				swaps++;
				System.out.println("Is "+ list[k]+" larger than " + mem +"? "+(list[k] > mem));
				list[k + 1] = list[k];
				k--;
			}
			list[k+1] = mem;
			k = 0;
			printList(list);
			System.out.println("");
		}
		System.out.println("Amount of swaps: "+swaps);
		return list;
	}

	/** 
	 * Prints the list with the elements
	 * @param list the list to print
	 */
	public void printList(int[] list) {
		StringBuilder str = new StringBuilder();
		
		for(int var:list) {
			str.append("["+var+"],");
		}
		
		System.out.println(str);
	}
	
	public int[] userInput() {
		System.out.println("Write the size of the array: ");
		int[] sampleList =  new int[sc.nextInt()];
		int i = 0;
		while(i < sampleList.length) {
			System.out.println("Write element no. "+(i+1)+" of "+(sampleList.length)+": ");
			sampleList[i] = sc.nextInt();
			i++;
		}
		return sampleList;
		
	}
	/**
	 * Counts inversions (for descending order)
	 * @param array
	 */
	public void inversions(int[] array) {
		int inversions = 0;
		for (int i = 0; i < array.length-1;i++) {
			for(int k = i+1;k<array.length;k++) {
				if(array[i] < array[k]) {
					inversions++;
				}
			}
		}
		
		System.out.println("\nNumber of inversions is: "+inversions+"\n");
		
	}
	
	
	
	/**
	 * Test
	 * @param arg
	 * @throws InterruptedException
	 */
	public static void main(String[] arg) throws InterruptedException {
		Sorter sort	= new Sorter();
		int list[] = sort.userInput();
		sort.inversions(list);
		System.out.println("The chosen array is: ");
		sort.printList(list);
		System.out.println("");
//		sort.insertionSort(list);
		sort.insertionSortDescending(list);
	}
}
