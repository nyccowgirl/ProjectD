import java.util.*;

public class ProjectDPartADriver {

	public static void main(String[] args) {	
		
		System.out.println("******************TESTING TRADITIONAL BINARY SEARCH TREE FUNCTIONALITY******************");
		BinarySearchTreeWithDups<String> nonDupTree = new BinarySearchTreeWithDups<String>();
		String[] insertStrings = {"E", "B", "C", "A", "H", "D", "F", "G"};
		for(String text : insertStrings) {
			nonDupTree.add(text);
		}
		// parameter 1: the type of traversal to test
		// parameter 2: the tree
		// parameter 3: the expected order of the traversal
		testTraverse(TraverseType.INORDER, nonDupTree, new String[]{"A", "B", "C", "D", "E", "F", "G", "H"});
		testTraverse(TraverseType.PREORDER, nonDupTree, new String[]{"E", "B", "A", "C", "D", "H", "F", "G"});
		testTraverse(TraverseType.POSTORDER, nonDupTree, new String[]{"A", "D", "C", "B", "G", "F", "H", "E"});
		
		System.out.println("\n\n******************TESTING DUPLICATE FUNCTIONALITY******************");
		BinarySearchTreeWithDups<Integer> dupTree = new BinarySearchTreeWithDups<Integer>();
		int[] insertNumbers = {6, 3, 10, 1, 6, 8, 11, 5, 7, 9, 4, 6, 8, 5, 6};
		for(int number : insertNumbers) {
			dupTree.add(number);
		}
		testTraverse(TraverseType.INORDER, dupTree, new Integer[]{1, 3, 4, 5, 5, 6, 6, 6, 6, 7, 8, 8, 9, 10, 11});
		testTraverse(TraverseType.PREORDER, dupTree, new Integer[]{6, 3, 1, 6, 5, 4, 5, 6, 6, 10, 8, 7, 8, 9, 11});
		testTraverse(TraverseType.POSTORDER, dupTree, new Integer[]{1, 5, 4, 6, 6, 5, 6, 3, 8, 7, 9, 8, 11, 10, 6});

		System.out.println("\n\nThe left child of the root 6:  expected=3 actual=" + dupTree.getRootNode().getLeftChild().getData());
		System.out.println("The left child of the 3-node:  expected=1 actual=" + dupTree.getRootNode().getLeftChild().getLeftChild().getData());
		System.out.println("The right child of the 3-node: expected=6 actual=" + dupTree.getRootNode().getLeftChild().getRightChild().getData());

		
		System.out.println("\n\n******************TESTING COUNT ENTRIES******************");
		// parameter 1: the tree
		// parameter 2: the target to count
		// parameter 3: the expected count
		testCount(dupTree, 1, 1);
		testCount(dupTree, 4, 1);
		testCount(dupTree, 5, 2);
		testCount(dupTree, 6, 4);
		testCount(dupTree, 8, 2);
		testCount(dupTree, 11, 1);
		testCount(nonDupTree, new String("A"), 1);
		testCount(nonDupTree, new String("F"), 1);
		testCount(dupTree, 0, 0);
		testCount(dupTree, 12, 0);
		testCount(nonDupTree, new String("Q"), 0);


		System.out.println("\n******************TESTING COUNT GREATER THAN WITH STACK******************");
		// parameter 1: the tree
		// parameter 2: whether to test with the iterative or recursive method
		// parameter 3: the target to count values greater than
		// parameter 4: the expected count
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 0, 15);
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 1, 14);
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 5, 10);
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 6, 6);
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 8, 3);
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 10, 1);
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 11, 0);
//		testGreaterCount(dupTree, GreaterType.ITERATIVE, 12, 0);


		System.out.println("\n******************TESTING COUNT GREATER RECURSIVE******************");
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 0, 15);
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 1, 14);
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 5, 10);
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 6, 6);
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 8, 3);
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 10, 1);
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 11, 0);
		testGreaterCount(dupTree, GreaterType.RECURSIVE, 12, 0);


//		System.out.println("\n******************TESTING EXTRA CREDIT COUNT UNIQUE VALUES******************");
//		// parameter 1: the tree
//		// parameter 2: the expected count of unique values
//		testCountUnique(dupTree, 10);
//		testCountUnique(nonDupTree, 8);
//
//
//		System.out.println("\n******************EVALUATING METHOD EFFICIENCY******************");
//		// parameter 1: whether to print the initial description; it's long, so once you've read it, you might want to hide it!
//		methodEfficiencyEvaluator(true);
	}
	
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 */
	
	private static void methodEfficiencyEvaluator(boolean printDescription) {
		if(printDescription) {
			System.out.println("This method is designed to help you figure out if you are fully taking advantage of the sorted nature of a BST.");
			System.out.println("Essentially what it does is create very lopsided trees and then invokes methods with targets that are \"near the root\" or \"deep in the tree.\"");
			System.out.println("If you are fully taking advantage of the sorted nature of the tree, then the method should be faster when the target is near the root.");
			System.out.println("\n*****IMPORTANT*****: In order to use these tests, you must add code to the method you are evaluating.");
			System.out.println("The code should count how many iterations the method took- loops or recursion.");
			System.out.println("For the iterative methods, use a local variable inside the method. Initialize to 0. Example: int iterations = 0");
			System.out.println("  Then, inside the loop, increment for each pass of the loop. Example: while(...) { iterations++; ... }");
			System.out.println("  At the end of the method, before the return, print the total iteration count.");
			System.out.println("  Example: System.out.println(\"iterations=\" + iterations);");
			System.out.println("For recursion, declare a static variable outside of the method and initialize to 0. Example: private static recursions = 0;");
			System.out.println("  Reassign the variable 0 at the beginning of the *recursive* method. Example: recursions = 0;");
			System.out.println("  Inside the *helper* method, increment the variable. Example: recursions++;");
			System.out.println("  At the end of the *recursive* method, before the return, print the total recursion count.");
			System.out.println("  Note that you will need to assign your helper method call to a local variable instead of just returning.");
			System.out.println("  Example: int count = recursiveHelper(...); System.out.println(\"recursions=\" + recursions); return count;");
		}
		System.out.println("\nEach test below will then show two counts.");
		System.out.println("If the two counts are very different: you might be okay for that method.");
		System.out.println("If the two iteration counts are equal or close to each other... ALERT!!! You might not be fully taking advantage of the sorted nature of the BST for the  method specified.");
		System.out.println("\nDon't forget to remove the test code before submission! :)");
		
		final int SMALL_NUM = -1;
		final int SIZE = 5000;
		final int LARGE_NUM = SIZE*2;

		BinarySearchTreeWithDups<Integer> lotsOfDups; 

		System.out.println("\nTesting countEntriesNonRecursive Test A...");
		lotsOfDups = new BinarySearchTreeWithDups<Integer>();
		for(int i=0, num=0; i<SIZE; i++) {
			lotsOfDups.add(num);
			if(i%5==0) {
				num++;
			}
		}
		lotsOfDups.countEntriesNonRecursive(SMALL_NUM);
		
		lotsOfDups = new BinarySearchTreeWithDups<Integer>();
		for(int i=0; i<SIZE; i++) {
			lotsOfDups.add(LARGE_NUM);
		}
		lotsOfDups.countEntriesNonRecursive(SMALL_NUM);
		
		System.out.println("\nTesting countEntriesNonRecursive Test B...");
		lotsOfDups = new BinarySearchTreeWithDups<Integer>();
		for(int i=0; i<SIZE; i++) {
			lotsOfDups.add(SMALL_NUM);
		}
		lotsOfDups.countEntriesNonRecursive(SMALL_NUM);
		lotsOfDups.countEntriesNonRecursive(LARGE_NUM);


		System.out.println("\nTesting countGreaterWithStack...");
		lotsOfDups = new BinarySearchTreeWithDups<Integer>();
		for(int i=0; i<SIZE; i++) {
			lotsOfDups.add(SMALL_NUM);
		}
		lotsOfDups.countGreaterIterative(LARGE_NUM);
		lotsOfDups.countGreaterIterative(SMALL_NUM-1);

		System.out.println("\nTesting countGreaterRecursive...");
		lotsOfDups = new BinarySearchTreeWithDups<Integer>();
		for(int i=0; i<SIZE; i++) {
			lotsOfDups.add(SMALL_NUM);
		}
		lotsOfDups.countGreaterRecursive(LARGE_NUM);
		lotsOfDups.countGreaterRecursive(SMALL_NUM-1);
	}
	private static enum TraverseType {
		INORDER, PREORDER, POSTORDER;
	
		@Override
		public String toString() {
			return super.toString().substring(0,1) + super.toString().substring(1).toLowerCase();
		}
	};
	private static String arrayPrint(Object[] array) {
		String s = "";
		for(Object object : array) {
			s += object + " ";
		}
		return s;
	}
	private static <T> void testTraverse(TraverseType type, BinaryTree<T> tree, T[] expectedTraversal) {
		System.out.println("\nTesting " + type + " Traversal:");
		System.out.println("Expected traversal: " + arrayPrint(expectedTraversal));
		System.out.print("  Actual traversal: " );
	
		Iterator<T> treeIterator;
		if(type==TraverseType.INORDER) {
			treeIterator = tree.getInorderIterator();
			tree.recursiveInorderTraverse();
		} else if(type==TraverseType.PREORDER) {
			treeIterator = tree.getPreorderIterator();
			tree.recursivePreorderTraverse();
		} else { // type==TraverseType.POSTORDER
			treeIterator = tree.getPostorderIterator();
			tree.recursivePostorderTraverse();
		}
		boolean allMatches = true;
		Iterator<T> listIterator = Arrays.asList(expectedTraversal).iterator();
		T mismatchTreeElement = null, mismatchListElement = null;
		while(treeIterator.hasNext() && listIterator.hasNext() && allMatches) {
			T treeElement = treeIterator.next();
			T listElement = listIterator.next();
			if(!treeElement.equals(listElement)) {
				allMatches = false;
				mismatchTreeElement = treeElement;
				mismatchListElement = listElement;
			}
		}
		if(!allMatches) {
			System.out.println("***Test failed. Mismatched element during traversal. " +
					"Expected=" + mismatchListElement + " Actual=" + mismatchTreeElement);
		} else if(treeIterator.hasNext()||listIterator.hasNext()) {
			if(treeIterator.hasNext()) {
				System.out.println("***Test failed. Tree iterator has more elements than expected.");
			} else { // listIterator.hasNext()
				System.out.println("***Test failed. Tree iterator has fewer elements than expected.");
			}
		}	
	}
	private static <T extends Comparable<? super T>> void testCount(BinarySearchTreeWithDups<T> tree, T target, int expectedCount) {
		System.out.println("\nCount of " + target + "s");
		System.out.println("Expected=" + expectedCount);
		int actualCount = tree.countEntriesNonRecursive(target);
		System.out.println("  Actual=" + actualCount);
		if(actualCount!=expectedCount) {
			System.out.println("***Test failed. Count is not correct.");
		}
	}
	private static enum GreaterType {
		ITERATIVE, RECURSIVE;
	
		@Override
		public String toString() {
			return super.toString().substring(0,1) + super.toString().substring(1).toLowerCase();
		}
	};
	private static <T extends Comparable<? super T>> void testGreaterCount(BinarySearchTreeWithDups<T> tree, GreaterType type, T target, int expectedCount) {
		System.out.println("\nCount of greater than " + target);
		System.out.println("Expected=" + expectedCount);
		int actualCount;
		if(type==GreaterType.ITERATIVE) {
			actualCount = tree.countGreaterIterative(target);
		} else { // type==GreaterType.RECURSIVE
			actualCount = tree.countGreaterRecursive(target);
		}
		System.out.println("  Actual=" + actualCount);
		if(actualCount!=expectedCount) {
			System.out.println("***Test failed. Count is not correct.");
		}
	}
	private static <T extends Comparable<? super T>> void testCountUnique(BinarySearchTreeWithDups<T> tree, int expectedCount) {
		System.out.println("\nNumber of unique values");
		System.out.println("Expected=" + expectedCount);
		int actualCount = tree.countUniqueValues();
		System.out.println("  Actual=" + actualCount);
		if(actualCount!=expectedCount) {
			System.out.println("***Test failed. Count is not correct.");
		}
	}
}
