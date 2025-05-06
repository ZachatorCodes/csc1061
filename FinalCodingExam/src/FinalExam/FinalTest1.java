package FinalExam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Stack;

public class FinalTest1 {

	public static void main(String[] args) {
		// Recursion - camelHumps()
		boolean working = camelHumps(10) == 15;
		System.out.println("camelHumps() working: " + working);

		// Graphs - depthFirstTraversal()
		GraphHelper graph = createGraph();
		String graphRoot = "Bob";

		System.out.println("\nPerforming a depth first traversal of the graph");
		System.out.println(depthFirstTraversal(graph, graphRoot));

		// Binary Search Trees - contains() and postorder()
		String[] people = "Geroge Michael Brittany Chloe Peter Dan Erin".split(" ");

		BST<String> bstTree = new BST<>();
		System.out.println("\n\nInserting people into the BST");
		for (String person : people) {
			System.out.printf(" %s", person);
			bstTree.insert(person);
		}
		TreeNode<String> bstTreeRoot = bstTree.getRoot();

		System.out.print("\nPostorder: ");
		BST.postorder(bstTreeRoot);

		System.out.println("\nIs Chloe in the tree? " + (bstTree.contains("Chloe") ? "yes" : "no"));
		if (!bstTree.contains("Chloe"))
			System.out.println("Contains not working");

		System.out.println("Is Sam in the tree? " + (bstTree.contains("Sam") ? "yes" : "no"));
		if (bstTree.contains("Sam"))
			System.out.println("Contains not working");

		// 2-4 Search Trees - postorder()
		Tree24<String> tree24 = new Tree24<>();
		System.out.println("\nInserting people into the 24Tree");
		for (String person : people) {
			System.out.printf(" %s", person);
			tree24.insert(person);
		}

		System.out.print("\nPostorder: ");
		tree24.postorder();

		// Heaps - Extra Credit - insert()
		System.out.println("\n\nInserting people into max heap");
		MyMaxHeap<String> personHeap = new MyMaxHeap<>(people); // max Heap ordered max to min
		PriorityQueue<String> testHeap = new PriorityQueue<>(new maxComparator<String>()); // PriorityWueue ordered max
																							// to min
		for (String person : people) {
			System.out.printf(" %s", person);
			testHeap.add(person);
		}

		System.out.println("\n\nExtracting people from max heap");
		for (int i = 0; i < people.length; i++) {
			String person = personHeap.extractMax();
			try {
				if (!person.equals(testHeap.poll())) {
					System.out.println("Heap insert not implemented correctly");
					break;
				}
			} catch (NullPointerException e) {
				System.out.println("Heap insert not implemented correctly");
				break;
			}
			System.out.println(person);
		}

	}

//*******************************************Recursion***************************************************

	// TODO fill in this method

	// We have camels standing in a line, numbered 1, 2, ... The odd camels (1, 3,
	// ..)
	// have the normal 1 humps. The even camels (2, 4, ..) we'll say have 2 humps.
	// Recursively return the number of "humps"
	// in the camel line 1, 2, ... n (without loops or multiplication).
	static int camelHumps(int camels) {
		if (camels % 2 == 0 && camels > 0) {
			return 2 + camelHumps(camels - 1);
		} else if (camels % 2 != 0 && camels > 0) {
			return 1 + camelHumps(camels - 1);
		}
		return 0;
	}

//*******************************************Graphs***************************************************

	// TODO fill in these methods
	static Set<String> depthFirstTraversal(GraphHelper graph, String root) {
		LinkedHashSet<String> dftSet = new LinkedHashSet<>();
		dfs(graph, root, dftSet);
		return dftSet;
	}

	static Set<String> dfs(GraphHelper graph, String root, LinkedHashSet<String> dftSet) {
		for (Vertex e : graph.getAdjVertices(root)) {
			if (!dftSet.contains(e.label)) {
				dftSet.add(e.label);
				dfs(graph, e.label, dftSet);
			}
		}
		return dftSet;
	}

	static GraphHelper createGraph() {
		GraphHelper graph = new GraphHelper();
		graph.addVertex("Bob");
		graph.addVertex("Alice");
		graph.addVertex("Mark");
		graph.addVertex("Rob");
		graph.addVertex("Maria");
		graph.addEdge("Bob", "Alice");
		graph.addEdge("Bob", "Rob");
		graph.addEdge("Alice", "Mark");
		graph.addEdge("Rob", "Mark");
		graph.addEdge("Alice", "Maria");
		graph.addEdge("Rob", "Maria");
		return graph;
	}
}

//*******************************************BST***************************************************

class BST<E extends Comparable<E>> {
	private TreeNode<E> root;
	private int size = 0;

	// TODO fill in this method
	// Returns true if the element is in the tree
	public boolean contains(E e) {
		TreeNode<E> current = root;
		while (current != null) {
			if (current.element.equals(e)) {
				return true;
			}
			if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				current = current.left;
			}
		}
		return false;

	}

	// TODO fill in this method
	static void postorder(TreeNode<?> root) {
		if (root == null) {
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	// Insert element e into the binary tree Return true if the element is inserted
	// successfully
	public boolean insert(E e) {
		if (root == null)
			root = createNewNode(e); // Create a new root
		else {
			// Locate the parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null)
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					return false; // Duplicate node not inserted

			// Create the new node and attach it to the parent node
			if (e.compareTo(parent.element) < 0)
				parent.left = createNewNode(e);
			else
				parent.right = createNewNode(e);
		}

		size++;
		return true; // Element inserted successfully
	}

	TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

	TreeNode<E> getRoot() {
		return root;
	}

// Inner class InorderIterator
	private class InorderIterator implements java.util.Iterator<E> {
		// Store the elements in a list
		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0; // Point to the current element in list

		public InorderIterator() {
			inorder(); // Traverse binary tree and store elements in list
		}

		/** Inorder traversal from the root */
		private void inorder() {
			inorder(root);
		}

		/** Inorder traversal from a subtree */
		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		@Override /** More elements for traversing? */
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		@Override /** Get the current element and move to the next */
		public E next() {
			return list.get(current++);
		}
	}
}

class TreeNode<E> {
	E element;
	TreeNode<E> left;
	TreeNode<E> right;

	public TreeNode(E e) {
		element = e;
	}
}

//*******************************************max Heap***************************************************

class MyMaxHeap<T extends Comparable<T>> {
	private ArrayList<T> list;

	MyMaxHeap(T[] people) {
		list = new ArrayList<>();
		for (T person : people)
			insert(person);
	}

	// TODO fill in this method
	// insert item into a max heap
	public void insert(T item) {

	}

	public T extractMax() {
		if (list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return list.remove(0);
		}
		T min = list.get(0);
		T last = list.remove(list.size() - 1);
		list.set(0, last);
		heapify(0);
		return min;
	}

	public T min() {
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public void print() {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println();
	}

	private void heapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < list.size() && list.get(l).compareTo(list.get(i)) >= 0) {
			smallest = l;
		}
		if (r < list.size() && list.get(r).compareTo(list.get(smallest)) >= 0) {
			smallest = r;
		}
		if (smallest != i) {
			swap(i, smallest);
			heapify(smallest);
		}
	}

	private void swap(int i, int j) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	private int parent(int i) {
		if (i == 0) {
			return -1;
		}
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}
}

//*******************************************2-4Tree***************************************************
class Tree24<E extends Comparable<E>> {
	private Tree24Node<E> root;
	private int size;

	/** Create a default 2-4 tree */
	public Tree24() {
	}

	/** Create a 2-4 tree from an array of objects */
	public Tree24(E[] elements) {
		for (int i = 0; i < elements.length; i++)
			insert(elements[i]);
	}

	/** Search an element in the tree */
	public boolean search(E e) {
		Tree24Node<E> current = root; // Start from the root

		while (current != null) {
			if (matched(e, current)) { // Element is in the node
				return true; // Element found
			} else {
				current = getChildNode(e, current); // Search in a subtree
			}
		}

		return false; // Element is not in the tree
	}

	/** Return true if the element is found in this node */
	private boolean matched(E e, Tree24Node<E> node) {
		for (int i = 0; i < node.elements.size(); i++)
			if (node.elements.get(i).compareTo(e) == 0)
				return true; // Element found

		return false; // No match in this node
	}

	/** Locate a child node to search element e */
	private Tree24Node<E> getChildNode(E e, Tree24Node<E> node) {
		if (node.child.size() == 0)
			return null; // node is a leaf

		int i = locate(e, node); // Locate the insertion point for e
		return node.child.get(i); // Return the child node
	}

	/**
	 * Insert element e into the tree Return true if the element is inserted
	 * successfully
	 */
	public boolean insert(E e) {
		if (root == null)
			root = new Tree24Node<E>(e); // Create a new root for element
		else {
			// Locate the leaf node for inserting e
			Tree24Node<E> leafNode = null;
			Tree24Node<E> current = root;
			while (current != null)
				if (matched(e, current)) {
					return false; // Duplicate element found, nothing inserted
				} else {
					leafNode = current;
					current = getChildNode(e, current);
				}

			// Insert the element e into the leaf node
			insert(e, null, leafNode); // The right child of e is null
		}

		size++; // Increase size
		return true; // Element inserted
	}

	/** Insert element e into node u */
	private void insert(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u) {
		// Get the search path that leads to element e
		ArrayList<Tree24Node<E>> path = path(e);

		for (int i = path.size() - 1; i >= 0; i--) {
			if (u.elements.size() < 3) { // u is a 2-node or 3-node
				insert23(e, rightChildOfe, u); // Insert e to node u
				break; // No further insertion to u's parent needed
			} else {
				Tree24Node<E> v = new Tree24Node<E>(); // Create a new node v
				E median = split(e, rightChildOfe, u, v); // Split u

				if (u == root) {
					root = new Tree24Node<E>(median); // New root
					root.child.add(u); // u is the left child of median
					root.child.add(v); // v is the right child of median
					break; // No further insertion to u's parent needed
				} else {
					// Use new values for the next iteration in the for loop
					e = median; // Element to be inserted to parent
					rightChildOfe = v; // Right child of the element
					u = path.get(i - 1); // New node to insert element
				}
			}
		}
	}

	/** Insert element to a 2- or 3- and return the insertion point */
	private void insert23(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> node) {
		int i = this.locate(e, node); // Locate where to insert
		node.elements.add(i, e); // Insert the element into the node
		if (rightChildOfe != null)
			node.child.add(i + 1, rightChildOfe); // Insert the child link
	}

	/** Split a 4-node u into u and v and insert e to u or v */
	private E split(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u, Tree24Node<E> v) {
		// Move the last element in node u to node v
		v.elements.add(u.elements.remove(2));
		E median = u.elements.remove(1);

		// Split children for a non-leaf node
		// Move the last two children in node u to node v
		if (u.child.size() > 0) {
			v.child.add(u.child.remove(2));
			v.child.add(u.child.remove(2));
		}

		// Insert e into a 2- or 3- node u or v.
		if (e.compareTo(median) < 0)
			insert23(e, rightChildOfe, u);
		else
			insert23(e, rightChildOfe, v);

		return median; // Return the median element
	}

	/** Return a search path that leads to element e */
	private ArrayList<Tree24Node<E>> path(E e) {
		ArrayList<Tree24Node<E>> list = new ArrayList<Tree24Node<E>>();
		Tree24Node<E> current = root; // Start from the root

		while (current != null) {
			list.add(current); // Add the node to the list
			if (matched(e, current)) {
				break; // Element found
			} else {
				current = getChildNode(e, current);
			}
		}

		return list; // Return an array of nodes
	}

	/** Delete the specified element from the tree */
	public boolean delete(E e) {
		// Locate the node that contains the element e
		Tree24Node<E> node = root;
		while (node != null)
			if (matched(e, node)) {
				delete(e, node); // Delete element e from node
				size--; // After one element deleted
				return true; // Element deleted successfully
			} else {
				node = getChildNode(e, node);
			}

		return false; // Element not in the tree
	}

	/** Delete the specified element from the node */
	private void delete(E e, Tree24Node<E> node) {
		if (node.child.size() == 0) { // e is in a leaf node
			// Get the path that leads to e from the root
			ArrayList<Tree24Node<E>> path = path(e);

			node.elements.remove(e); // Remove element e

			if (node == root) { // Special case
				if (node.elements.size() == 0)
					root = null; // Empty tree
				return; // Done
			}

			validate(e, node, path); // Check underflow node
		} else { // e is in an internal node
			// Locate the rightmost node in the left subtree of the node
			int index = locate(e, node); // Index of e in node
			Tree24Node<E> current = node.child.get(index);
			while (current.child.size() > 0) {
				current = current.child.get(current.child.size() - 1);
			}
			E rightmostElement = current.elements.get(current.elements.size() - 1);

			// Get the path that leads to e from the root
			ArrayList<Tree24Node<E>> path = path(rightmostElement);

			// Replace the deleted element with the rightmost element
			node.elements.set(index, current.elements.remove(current.elements.size() - 1));

			validate(rightmostElement, current, path); // Check underflow
		}
	}

	/** Perform transfer and confusion operations if necessary */
	private void validate(E e, Tree24Node<E> u, ArrayList<Tree24Node<E>> path) {
		for (int i = path.size() - 1; u.elements.size() == 0; i--) {
			Tree24Node<E> parentOfu = path.get(i - 1); // Get parent of u
			int k = locate(e, parentOfu); // Index of e in the parent node

			// Check two siblings
			if (k > 0 && parentOfu.child.get(k - 1).elements.size() > 1) {
				leftSiblingTransfer(k, u, parentOfu);
			} else if (k + 1 < parentOfu.child.size() && parentOfu.child.get(k + 1).elements.size() > 1) {
				rightSiblingTransfer(k, u, parentOfu);
			} else if (k - 1 >= 0) { // Fusion with a left sibling
				// Get left sibling of node u
				Tree24Node<E> leftNode = parentOfu.child.get(k - 1);

				// Perform a fusion with left sibling on node u
				leftSiblingFusion(k, leftNode, u, parentOfu);

				// Done when root becomes empty
				if (parentOfu == root && parentOfu.elements.size() == 0) {
					root = leftNode;
					break;
				}

				u = parentOfu; // Back to the loop to check the parent node
			} else { // Fusion with right sibling (right sibling must exist)
				// Get left sibling of node u
				Tree24Node<E> rightNode = parentOfu.child.get(k + 1);

				// Perform a fusion with right sibling on node u
				rightSiblingFusion(k, rightNode, u, parentOfu);

				// Done when root becomes empty
				if (parentOfu == root && parentOfu.elements.size() == 0) {
					root = rightNode;
					break;
				}

				u = parentOfu; // Back to the loop to check the parent node
			}
		}
	}

	/** Locate the insertion point of the element in the node */
	private int locate(E o, Tree24Node<E> node) {
		for (int i = 0; i < node.elements.size(); i++) {
			if (o.compareTo(node.elements.get(i)) <= 0) {
				return i;
			}
		}

		return node.elements.size();
	}

	/** Perform a transfer with a left sibling */
	private void leftSiblingTransfer(int k, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Move an element from the parent to u
		u.elements.add(0, parentOfu.elements.get(k - 1));

		// Move an element from the left node to the parent
		Tree24Node<E> leftNode = parentOfu.child.get(k - 1);
		parentOfu.elements.set(k - 1, leftNode.elements.remove(leftNode.elements.size() - 1));

		// Move the child link from left sibling to the node
		if (leftNode.child.size() > 0)
			u.child.add(0, leftNode.child.remove(leftNode.child.size() - 1));
	}

	/** Perform a transfer with a right sibling */
	private void rightSiblingTransfer(int k, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Transfer an element from the parent to u
		u.elements.add(parentOfu.elements.get(k));

		// Transfer an element from the right node to the parent
		Tree24Node<E> rightNode = parentOfu.child.get(k + 1);
		parentOfu.elements.set(k, rightNode.elements.remove(0));

		// Move the child link from right sibling to the node
		if (rightNode.child.size() > 0)
			u.child.add(rightNode.child.remove(0));
	}

	/** Perform a fusion with a left sibling */
	private void leftSiblingFusion(int k, Tree24Node<E> leftNode, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Transfer an element from the parent to the left sibling
		leftNode.elements.add(parentOfu.elements.remove(k - 1));

		// Remove the link to the empty node
		parentOfu.child.remove(k);

		// Adjust child links for non-leaf node
		if (u.child.size() > 0)
			leftNode.child.add(u.child.remove(0));
	}

	/** Perform a fusion with a right sibling */
	private void rightSiblingFusion(int k, Tree24Node<E> rightNode, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Transfer an element from the parent to the right sibling
		rightNode.elements.add(0, parentOfu.elements.remove(k));

		// Remove the link to the empty node
		parentOfu.child.remove(k);

		// Adjust child links for non-leaf node
		if (u.child.size() > 0)
			rightNode.child.add(0, u.child.remove(0));
	}

	/** Get the number of nodes in the tree */
	public int getSize() {
		return size;
	}

	/** Preorder traversal from the root */
	public void preorder() {
		preorder(root);
	}

	/** Preorder traversal from a subtree */
	private void preorder(Tree24Node<E> root) {
		if (root == null)
			return;
		for (int i = 0; i < root.elements.size(); i++)
			System.out.print(root.elements.get(i) + " ");

		for (int i = 0; i < root.child.size(); i++)
			preorder(root.child.get(i));
	}

	/** Inorder traversal from the root */
	public void inorder() {
		inorder(root);
	}

	private void inorder(Tree24Node<E> root) {
		if (root == null)
			return;

		// traverse left to root to right
		for (int i = 0; i < root.elements.size(); i++) {
			// left nodes
			if (root.child.size() >= 1) // ensure there is something to traverse
				inorder(root.child.get(i));

			// root of subtree
			System.out.print(root.elements.get(i) + " ");
		}
		// get final right node
		if (root.child.size() >= 1) // ensure there is something to traverse
			inorder(root.child.get(root.elements.size()));
	}

	/** Postorder traversal from the root */
	public void postorder() {
		postorder(root);
	}

	// TODO fill in this method
	private void postorder(Tree24Node<E> root) {
		if (root == null)
			return;
		for (int i = 0; i < root.child.size(); i++) {
			postorder(root.child.get(i));
		}
		for (int i = 0; i < root.elements.size(); i++) {
			System.out.print(root.elements.get(i) + " ");
		}
	}

	/** Return true if the tree is empty */
	public boolean isEmpty() {
		return root == null;
	}

	/** Remove all elements from the tree */
	public void clear() {
		root = null;
		size = 0;
	}

	/** Return an iterator to traverse elements in the tree */
	public java.util.Iterator<E> iterator() {
		return new InorderIterator();
	}

	private class InorderIterator implements java.util.Iterator<E> {
		// Store the elements in a list
		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0; // Point to the current element in list

		public InorderIterator() {
			inorder(root); // Traverse binary tree and store elements in list
		}

		public void inorder() {
			inorder(root);
		}

		private void inorder(Tree24Node<E> root) {
			if (root == null)
				return;

			// traverse left to root to right
			for (int i = 0; i < root.elements.size(); i++) {
				// left nodes
				if (root.child.size() >= 1) // ensure there is something to traverse
					inorder(root.child.get(i));

				// root of subtree
				list.add(root.elements.get(i));
			}
			// get final right node
			if (root.child.size() >= 1) { // ensure there is something to traverse
				inorder(root.child.get(root.elements.size()));
			}
		}

		/** More elements for traversing? */
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		/** Get the current element and move to the next */
		public E next() {
			return list.get(current++);
		}

		// Remove the element returned by the last next()
		public void remove() {
			if (current == 0) // next() has not been called yet
				throw new IllegalStateException();

			delete(list.get(--current));
			list.clear(); // Clear the list
			inorder(root); // Rebuild the list
		}
	}

	/** Define a 2-4 tree node */
	protected static class Tree24Node<E extends Comparable<E>> {
		// elements has maximum three values
		ArrayList<E> elements = new ArrayList<E>(3);
		// Each has maximum four childres
		ArrayList<Tree24Node<E>> child = new ArrayList<Tree24Node<E>>(4);

		/** Create an empty Tree24 node */
		Tree24Node() {
		}

		/** Create a Tree24 node with an initial element */
		Tree24Node(E o) {
			elements.add(o);
		}
	}
}

class maxComparator<T extends Comparable> implements Comparator<T> {
	public int compare(T o1, T o2) {
		return -(o1.compareTo(o2));
	}

	public boolean equals(Object ob) {
		return this.equals(ob);
	}
}
