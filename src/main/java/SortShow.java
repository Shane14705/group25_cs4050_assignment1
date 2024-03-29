/**
 *
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel { 

	
		// An array to hold the lines_lengths to be sorted
		public int[] lines_lengths;
		//The amount of lines needed
		public final int total_number_of_lines = 256;
		 // An array to holds the scrambled lines_lengths
		public int[] scramble_lines;
		//A temp Array that is used later for sorts
		public int[] tempArray;
		
		//the default constructor for the SortShow class
		public SortShow(){
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++) 
				lines_lengths[i] =  i+5;
			
		}
		

		//A method that scrambles the lines
		public void scramble_the_lines(){
			//A random generator
			Random num = new Random(); 
			//Randomly switching the lines
			for(int i = 0; i < total_number_of_lines; i++){
				//getting a random number using the nextInt method (a number between 0 to i + 1)
				int j = num.nextInt(i + 1); 
				//swapping The element at i and j 
				swap(i, j);
			}
			//assigning the size for the scramble_lines below
			scramble_lines = new int[total_number_of_lines];
			//copying the now scrambled lines_lengths array into the scramble_lines array 
			//to store for reuse for other sort methods
			//so that all sort methods will use the same scrambled lines for fair comparison 
			for (int i = 0; i < total_number_of_lines; i++)
			{
				scramble_lines[i] = lines_lengths[i];
			}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
		
		//Swapping method that swaps two elements in the lines_lengths array
		public void swap(int i, int j){
			//storing the i element in lines_lengths in temp
			int temp = lines_lengths[i];
			//giving i element in lines_lengths the value of j element in lines_lengths
			lines_lengths[i] = lines_lengths[j];
			//giving j element in lines_lengths the value of temp
			lines_lengths[j] = temp;
		}
		
		//The selectionSort method
		public void SelectionSort(){
			//getting the date and time when the selection sort starts
			Calendar start = Calendar.getInstance();
			//Using the selection sort to lines_lengths sort the array

			for (int i = 0; i < lines_lengths.length - 1; i++) {
				int minIndex = i;
				for (int j = i + 1; j < lines_lengths.length; j++) {
					if (lines_lengths[j] < lines_lengths[minIndex]) {
						minIndex = j;
					}
				}
				swap(i, minIndex);
				paintComponent(this.getGraphics()); // Redraw after each swap
				delay(80); // Delay to account for lack of paintComponent calls in this algorithm
			}


			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			paintComponent(this.getGraphics());
			//getting the time it took for the selection sort to execute 
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//this method gets the smallest element in the array of lines_lengths
		public int getIndexOfSmallest(int first, int last){

			int index_of_smallest = first;
			//You need to complete this part.
			for (int smallest = lines_lengths[first]; first < last; first++) {
				if (lines_lengths[first] > smallest) {
					index_of_smallest = first;
				}
			}

			return index_of_smallest; //modify this line
		}
		
	///////////////////////////////////////////////////////////////////////////////////
		
		//recursive merge sort method
		public void R_MergeSort(){
			//getting the date and time when the recursive merge sort starts
			Calendar start = Calendar.getInstance();
			//assigning the size for the tempArray below
			tempArray = new int[lines_lengths.length];
			//You need to complete this part.
			R_MergeSort(0, lines_lengths.length - 1);
			Calendar end = Calendar.getInstance();
			paintComponent(this.getGraphics());
			//getting the time it took for the recursive merge sort to execute
			//subtracting the end time with the start time
	        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
			
		}
		
		//recursive merge sort method
		public void R_MergeSort(int first, int last){
			if(first < last){
				//You need to complete this part.
				int middle = (first + last) / 2;
				R_MergeSort(first, middle);
				R_MergeSort(middle + 1, last);

				if (lines_lengths[middle] >= lines_lengths[middle + 1]) {
					R_Merge(first, middle, last);
				}
				//redrawing the lines_lengths
				paintComponent(this.getGraphics());

			}
		}

		
		//recursive merge sort method
		public void R_Merge(int first, int mid, int last){

			//You need to complete this part.

			//Index into each respective subarray
			int firstHalfIndex = first;
			int secondHalfIndex = mid + 1;


			//Start by merging, we assume the two halves of the array we receive are sorted
			int currentIndex = first; //Need a reusable index variable so we know how far we are in the sorted array
			for (; (firstHalfIndex <= mid) && (secondHalfIndex <= last); currentIndex++) { //assume first and last are 0 indexed

				if (lines_lengths[firstHalfIndex] < lines_lengths[secondHalfIndex]) {
					tempArray[currentIndex] = lines_lengths[firstHalfIndex];
					firstHalfIndex++;
				}

				else {
					tempArray[currentIndex] = lines_lengths[secondHalfIndex];
					secondHalfIndex++;
				}
			}


			//Need to clear out anything left, if our array lengths weren't equal there will be one array that still has elements
			if (firstHalfIndex > mid) { //if we hit the end of the first half, then we need to clear the other end of the array
				for (; secondHalfIndex <= last; currentIndex++, secondHalfIndex++) {
					tempArray[currentIndex] = lines_lengths[secondHalfIndex];
				}
			}
			else { //otherwise, we ran out the second half first and need to finish copying the first half
				for (; firstHalfIndex <= mid; currentIndex++, firstHalfIndex++) {
					tempArray[currentIndex] = lines_lengths[firstHalfIndex];
				}
			}

			//copy back
			for (currentIndex = first; currentIndex <= last; currentIndex++) {
				lines_lengths[currentIndex] = tempArray[currentIndex];
			}



		}

	///////////////////////////////////////////////////////////////////////////////////

	//Insertion Sort Method

	public void InsertionSort()
	{
		//Start the timer
		Calendar start = Calendar.getInstance();

		//Run the recursive InsertionSort method
		InsertionSort(0, lines_lengths.length-1);

		//End timer
		Calendar end = Calendar.getInstance();
		paintComponent(this.getGraphics());
		//Computing the time it took to run insertion sort
		//Subtract the start time from the end time
		SortGUI.insertTime = end.getTime().getTime() - start.getTime().getTime();
	}

	//recursive insertion sort method
	public void InsertionSort(int first, int last)
	{

		if(first < last)
		{

			//sort all but the last element
			InsertionSort(first, last-1);

			//insert the last element in sorted order
			InsertInOrder(lines_lengths[last], first, last-1);
			paintComponent(this.getGraphics());

			delay(20); //Delay to account for relative lack of paintComponent calls
		}
	}

	//Function used in insertion sort to insert an element into the sorted array
	public void InsertInOrder(int element, int start, int end)
	{
		if(element >= lines_lengths[end])
		{
			lines_lengths[end+1] = element;
		}
		else
		{
			if(start < end)
			{
				lines_lengths[end+1] = lines_lengths[end];
				InsertInOrder(element, start, end-1);
			}
			else
			{
				lines_lengths[end+1] = lines_lengths[end];
				lines_lengths[end] = element;
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////////////

	//Bubblesort Method

	public void BubbleSort()
	{
		//Start the timer
		Calendar start = Calendar.getInstance();

		for(int i = 0; i < lines_lengths.length - 1; i++)
		{
			for(int j = 0; j < lines_lengths.length - i - 1; j++)
			{
				if(lines_lengths[j] > lines_lengths[j+1]) {
					swap(j, j+1);
					paintComponent(this.getGraphics());
				}


			}

		}

		//End timer
		Calendar end = Calendar.getInstance();
		paintComponent(this.getGraphics());
		//Computing the time it took to run BubbleSort sort
		//Subtract the start time from the end time
		SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
	}

	///////////////////////////////////////////////////////////////////////////////////

	//Shell Sort methods

	public void ShellSort()
	{
		//Start the timer
		Calendar start = Calendar.getInstance();

		//Run Shell Sort
		ShellSort(0, lines_lengths.length - 1);

		//End timer
		Calendar end = Calendar.getInstance();
		paintComponent(this.getGraphics());
		//Computing the time it took to run shell sort
		//Subtract the start time from the end time
		SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();
	}

	public void ShellSort(int first, int last)
	{
		int n = last - first + 1;
		for(int space = n/2; space > 0; space = space / 2)
		{
			for(int start = first; start < first + space; start++)
			{
				IncrementalInsertionSort(start, last, space);
				paintComponent(this.getGraphics());

			}

		}
		delay(10); //delay to account for paintComponent calls
	}

	public void IncrementalInsertionSort(int first, int last, int space)
	{
		int unsorted, index;
		for(unsorted = first+space; unsorted <= last; unsorted = unsorted+space)
		{
			Integer firstUnsorted = lines_lengths[unsorted];
			for (index = unsorted - space ; (index >= first) &&
					(firstUnsorted.compareTo (lines_lengths[index]) < 0); index = index - space)
			{
				lines_lengths[index + space] = lines_lengths[index];

			}
			lines_lengths[index + space] = firstUnsorted;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////

	//recursive quicksort method
	public void R_Qsort() {
		//getting the date and time when the recursive quick sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[lines_lengths.length];
		//You need to complete this part.
		R_Qsort(0, lines_lengths.length - 1);

		Calendar end = Calendar.getInstance();
		paintComponent(this.getGraphics());
		//getting the time it took for quicksort sort to execute
		//subtracting the end time with the start time
		SortGUI.qsortTime = end.getTime().getTime() - start.getTime().getTime();
	}

	private void R_Qsort(int first, int last) {

		if (first >= last) {
			return;
		}

		int middle = (first + last) / 2;
		int pivotIndex = make_partition(first, lines_lengths[middle], last);

		R_Qsort(first, pivotIndex - 1);
		R_Qsort(pivotIndex, last);
		paintComponent(this.getGraphics());


	}

	private int make_partition(int first, int pivot, int last) {
		while (first <= last) {
			//find the two indices that need to be swapped
			while (lines_lengths[first] < pivot) {
				first++;
			}

			while (lines_lengths[last] > pivot) {
				last--;
			}

			if (first <= last) {
				swap(first, last);

				first++;
				last--;
			}

		}

		return first;
	}

	//////////////////////////////////////////////////////////////////////////////////////////
		
		//iterative merge sort method
		public void I_MergeSort()
		{
		//getting the date and time when the iterative merge sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines]; 
		//saving the value of total_number_of_lines
		int beginLeftovers = total_number_of_lines;

		
		for (int segmentLength = 1; segmentLength <= total_number_of_lines/2; segmentLength = 2*segmentLength)
		{
			beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
			int endSegment = beginLeftovers + segmentLength - 1;
			if (endSegment < total_number_of_lines - 1) 
			{
			I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
			}
		} 

		// merge the sorted leftovers with the rest of the sorted array
		if (beginLeftovers < total_number_of_lines) {
			I_Merge(0, beginLeftovers-1, total_number_of_lines - 1);
		}
		paintComponent(this.getGraphics());
		//getting the date and time when the iterative merge sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute 
		//subtracting the end time with the start time
	    SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
	} 

	// Merges segments pairs (certain length) within an array 
	public int I_MergeSegmentPairs(int l, int segmentLength)
	{
		//The length of the two merged segments 

		//You suppose  to complete this part (Given).
		int mergedPairLength = 2 * segmentLength;
		int numberOfPairs = l / mergedPairLength;

		int beginSegment1 = 0;
		for (int count = 1; count <= numberOfPairs; count++)
		{
			int endSegment1 = beginSegment1 + segmentLength - 1;

			int beginSegment2 = endSegment1 + 1;
			int endSegment2 = beginSegment2 + segmentLength - 1;
			I_Merge(beginSegment1, endSegment1, endSegment2);

			beginSegment1 = endSegment2 + 1;
			//redrawing the lines_lengths
			paintComponent(this.getGraphics());
			//Causing a delay for 10ms
			delay(10);
		}
		// Returns index of last merged pair
		return beginSegment1;
		//return 1;//modify this line
	}

	public void I_Merge(int first, int mid, int last)
	{
		//You suppose  to complete this part (Given).
		// Two adjacent sub-arrays
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// While both sub-arrays are not empty, copy the
		// smaller item into the temporary array
		int index = beginHalf1; // Next available location in tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
		{
			// Invariant: tempArray[beginHalf1..index-1] is in order
			if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
			}
			else
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
			}
		}
		//redrawing the lines_lengths
		paintComponent(this.getGraphics());

		// Finish off the nonempty sub-array

		// Finish off the first sub-array, if necessary
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			// Invariant: tempArray[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf1];

		// Finish off the second sub-array, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
			// Invariant: tempa[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf2];

		// Copy the result back into the original array
		for (index = first; index <= last; index++)
			lines_lengths[index] = tempArray[index];
	}

	//////////////////////////////////////////////////////////////////////	
		
		//This method resets the window to the scrambled lines display
		public void reset(){
			if(scramble_lines != null)
			{
				//copying the old scrambled lines into lines_lengths
				for (int i = 0; i < total_number_of_lines; i++)
				{
					lines_lengths[i] = scramble_lines[i] ;
				}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
			}
		
	
		//This method colours the lines and prints the lines
		public void paintComponent(Graphics g){
 			super.paintComponent(g);
			//A loop to assign a colour to each line
			for(int i = 0; i < total_number_of_lines; i++){
				//using eight colours for the lines
				if(i % 8 == 0){
					g.setColor(Color.green);
				} else if(i % 8 == 1){
					g.setColor(Color.blue);
				} else if(i % 8 == 2){
					g.setColor(Color.yellow);
				} else if(i%8 == 3){
					g.setColor(Color.red);
				} else if(i%8 == 4){
					g.setColor(Color.black);
				} else if(i%8 == 5){
					g.setColor(Color.orange);
				} else if(i%8 == 6){
					g.setColor(Color.magenta);
				} else
					g.setColor(Color.gray);
				
				//Drawing the lines using the x and y-components 
				g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
			}
			
		}
		
		//A delay method that pauses the execution for the milliseconds time given as a parameter
		public void delay(int time){
			try{
	        	Thread.sleep(time);
	        }catch(InterruptedException ie){
	        	Thread.currentThread().interrupt();
	        }
		}


}

