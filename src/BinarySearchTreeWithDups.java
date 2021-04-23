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
		Stack<BinaryNode> nodeStack = new Stack<>();
		nodeStack.push(getRootNode());

		while (!nodeStack.isEmpty()) {
			BinaryNode<T> currentNode = nodeStack.pop();
			result = currentNode.getData();
			int comparison = newEntry.compareTo(result);

			if (comparison <= 0) {
				if (currentNode.hasLeftChild()) {
					nodeStack.push(currentNode.getLeftChild());
				} else {
					currentNode.setLeftChild(new BinaryNode<>(newEntry));
				}
			} else {
				// Assertion: comparison > 0
				if (currentNode.hasRightChild()) {
					nodeStack.push(currentNode.getRightChild());
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
		Stack<BinaryNode> nodeStack = new Stack<>();
		nodeStack.push(getRootNode());

		// O(n) - TO BE DELETED:
//		while (!nodeStack.isEmpty()) {
//			BinaryNode<T> currentNode = nodeStack.pop();
//
//			if (target.equals(currentNode.getData())) {
//				count++;
//			}
//
//			if (currentNode.hasLeftChild()) {
//				nodeStack.push(currentNode.getLeftChild());
//			}
//
//			if (currentNode.hasRightChild()) {
//				nodeStack.push(currentNode.getRightChild());
//			}
//		}

		// O(log n):
		while (!nodeStack.isEmpty()) {
			BinaryNode<T> currentNode = nodeStack.pop();

			if (target.equals(currentNode.getData())) {
				count++;
			}

			int comparison = target.compareTo(currentNode.getData());

			if (comparison <= 0) {
				if (currentNode.hasLeftChild()) {
					nodeStack.push(currentNode.getLeftChild());
				}
			} else {
				// Assertion: comparison > 0
				if (currentNode.hasRightChild()) {
					nodeStack.push(currentNode.getRightChild());
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
					count += countGreaterRecursive(rootNode.getLeftChild(), target);
				}

				if (rootNode.hasRightChild()) {
					count += countGreaterRecursive(rootNode.getRightChild(), target);
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
		int count = 0;
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
		nodeStack.push(getRootNode());

		while (!nodeStack.isEmpty()) {
			BinaryNode<T> currentNode = nodeStack.pop();

			int comparison = target.compareTo(currentNode.getData());

			if (comparison < 0) {
				// Count node as it is greater than target
				count++;

				if (currentNode.hasLeftChild()) {
					nodeStack.push(currentNode.getLeftChild());
				}

				if (currentNode.hasRightChild()) {
					nodeStack.push(currentNode.getRightChild());
				}

			} else {
				// Assertion: comparison >= 0
				if (currentNode.hasRightChild()) {
					nodeStack.push(currentNode.getRightChild());
				}
			}
		}

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