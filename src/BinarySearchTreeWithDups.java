import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
			result = addEntryHelperNonRecursive(newEntry);
		return result;
	}

	
	// THIS METHOD CANNOT BE RECURSIVE.
	private T addEntryHelperNonRecursive(T newEntry) {
		// Assertion: rootNode != null
		T result = null;

		// Depth-first traversal version:
//		BinaryNode<T> currentNode = getRootNode();
//
//		while (currentNode != null) {
//			result = currentNode.getData();
//			int comparison = newEntry.compareTo(result);
//
//			if (comparison <= 0) {
//				if (currentNode.hasLeftChild()) {
//					currentNode = currentNode.getLeftChild();
//				} else {
//					currentNode.setLeftChild(new BinaryNode(newEntry));
//					currentNode = null;
//				}
//			} else {
//				// Assertion: comparison > 0
//				if (currentNode.hasRightChild()) {
//					currentNode = currentNode.getRightChild();
//				} else {
//					currentNode.setRightChild((new BinaryNode(newEntry)));
//					currentNode = null;
//				}
//			}
//		}

		// Stack version:
		Stack<BinaryNode> stack = new Stack<>();
		stack.push(getRootNode());

		while (!stack.isEmpty()) {
			BinaryNode<T> currentNode = stack.pop();
			result = currentNode.getData();
			int comparison = newEntry.compareTo(result);

			if (comparison <= 0) {
				if (currentNode.hasLeftChild()) {
					stack.push(currentNode.getLeftChild());
				} else {
					currentNode.setLeftChild(new BinaryNode<>(newEntry));
				}
			} else {
				// Assertion: comparison > 0
				if (currentNode.hasRightChild()) {
					stack.push(currentNode.getRightChild());
				} else {
					currentNode.setRightChild(new BinaryNode<>(newEntry));
				}
			}
		}
		
		return result;
	}

	
	// THIS METHOD CANNOT BE RECURSIVE.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countEntriesNonRecursive(T target) {
		int count = 0;

		// Depth-first traversal version:
//		BinaryNode<T> currentNode = getRootNode();
//
//		while (currentNode != null) {
//			if (target.equals(currentNode.getData())) {
//				count++;
//			}
//
//			int comparison = target.compareTo(currentNode.getData());
//
//			if (comparison <= 0) {
//				if (currentNode.hasLeftChild()) {
//					currentNode = currentNode.getLeftChild();
//				} else {
//					currentNode = null;
//				}
//			} else {
//				// Assertion: comparison > 0
//				if (currentNode.hasRightChild()) {
//					currentNode = currentNode.getRightChild();
//				} else {
//					currentNode = null;
//				}
//			}
//		}

		// Stack version:
		Stack<BinaryNode> stack = new Stack<>();
		stack.push(getRootNode());

		// O(n) - TO BE DELETED:
//		while (!stack.isEmpty()) {
//			BinaryNode<T> currentNode = stack.pop();
//
//			if (target.equals(currentNode.getData())) {
//				count++;
//			}
//
//			if (currentNode.hasLeftChild()) {
//				stack.push(currentNode.getLeftChild());
//			}
//
//			if (currentNode.hasRightChild()) {
//				stack.push(currentNode.getRightChild());
//			}
//		}

		// O(log n):
		while (!stack.isEmpty()) {
			BinaryNode<T> currentNode = stack.pop();

			if (target.equals(currentNode.getData())) {
				count++;
			}

			int comparison = target.compareTo(currentNode.getData());

			if (comparison <= 0) {
				if (currentNode.hasLeftChild()) {
					stack.push(currentNode.getLeftChild());
				}
			} else {
				// Assertion: comparison > 0
				if (currentNode.hasRightChild()) {
					stack.push(currentNode.getRightChild());
				}
			}
		}

		return count; 
	}
	
	
	// THIS METHOD MUST BE RECURSIVE! 
	// You are allowed to create a private helper.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterRecursive(T target) {
		BinaryNode<T> rootNode = getRootNode();

		return countGreaterRecursive(rootNode, target);
	}

	// Helper method for countGreaterRecursive(T target)
	private int countGreaterRecursive(BinaryNode<T> rootNode, T target) {
		int count = 0;

		if (rootNode != null) {
			int comparison = target.compareTo(rootNode.getData());

			if (comparison < 0) {
				// Count node as it is greater than target
				count++;

				if (rootNode.hasLeftChild()) {
					// Traverse through both left and right since target is < rootNode
					count = count + countGreaterRecursive(rootNode.getLeftChild(), target);
					count = count + countGreaterRecursive(rootNode.getRightChild(), target);
				} else if (rootNode.hasRightChild()) {
					count = count + countGreaterRecursive(rootNode.getRightChild(), target);
				}

			} else {
				// Assertion: comparison >= 0
				if (rootNode.hasRightChild()) {
					count = countGreaterRecursive(rootNode.getRightChild(), target);
				}
			}
		}
		return count;
	}
		
	
	// THIS METHOD CANNOT BE RECURSIVE.
	// Hint: use a stack!
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterIterative(T target) {
		// YOUR CODE HERE! 
		
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = getRootNode();
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
		nodeStack.push(rootNode);

		// consider a loop based on the stack!

		return count;
	}
		
	
	// For full credit, the method should be O(n).
	// You are allowed to use a helper method.
	// The method can be iterative or recursive.
	// If you make the method recursive, you might need to comment out the call to the method in Part B.
	public int countUniqueValues() {
		// YOUR EXTRA CREDIT CODE HERE! 
		return 0; // placeholder: replace with your own code
	}
		
	
	public int getLeftHeight() {
		BinaryNode<T> rootNode = getRootNode();
		if(rootNode==null) {
			return 0;
		} else if(!rootNode.hasLeftChild()) {
			return 0;
		} else {
			return rootNode.getLeftChild().getHeight();
		}
	}

	public int getRightHeight() {
		BinaryNode<T> rootNode = getRootNode();
		if(rootNode==null) {
			return 0;
		} else if(!rootNode.hasRightChild()) {
			return 0;
		} else {
			return rootNode.getRightChild().getHeight();
		}
	}
	

}