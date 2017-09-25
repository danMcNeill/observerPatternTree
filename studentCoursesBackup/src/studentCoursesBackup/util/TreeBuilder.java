package studentCoursesBackup;

import java.util.List;
import java.util.ArrayList;


public class TreeBuilder {

	private Node root;
	private Node root_clone1;
	private Node root_clone2;

	/**
	 * constructor with no root specified
	 */
	public TreeBuilder() {
		root = null;
		root_clone1 = null;
		root_clone2 = null;
	}

	/**
	 * constructor with root specified
	 */
	public TreeBuilder(Node r) {
		root = r;
		root_clone1 = new Node(r);
		root_clone2 = new Node(r);
	}

	/**
	 * @return String representing this tree (inorder traversal)
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(root == null)
			return "Empty tree";
		else {
			if(root.getLeftChild() != null) {
				sb.append(toStringAux(root.getLeftChild()));
				sb.append(" ");
			}
			sb.append(root.toString());
			sb.append("\n");
			if(root.getRightChild() != null)
				sb.append(toStringAux(root.getRightChild()));
			return sb.toString();
		}
	}

	/**
	 * @return String of certain part of tree (inorder traversal)
	 * @param current - "root" of current tree we want to print out
	 */
	public String toStringAux(Node current) {
		StringBuilder sb = new StringBuilder();
		if(current == null)
			return " ";
		if(current.getLeftChild() != null) {
			sb.append(toStringAux(current.getLeftChild()));
			sb.append(" ");
		}
		sb.append(current.toString());
		sb.append("\n ");
		if(current.getRightChild() != null) {
			sb.append(toStringAux(current.getRightChild()));
			sb.append(" ");
		}

		return sb.toString();
	}


	/**
	 * @return boolean, TRUE if successful insert, otherwise FALSE
	 * @param b, int representing BNUM to be added
	 * @param c, string representing course to be addded
	 * inserts Node with bnum = b if doesnt already exist
	 */
	public boolean insert(int b, String c) {
		
		if(!(c.equals("A") || c.equals("B") || c.equals("C") || c.equals("D") || c.equals("E") || c.equals("F") || c.equals("G") || c.equals("H") || c.equals("I") || c.equals("J") || c.equals("K"))) {
			System.err.println("" + c + " is not a valid course, so it was not added.");
			return false;
		}
		
		if(find(b) == null) { // if student does not exist in tree
			if(root == null) {
				root = new Node(b, c);

				root_clone1 = new Node(b, c);
				root_clone2 = new Node(b, c);

				root.registerObserver(root_clone1);
				root.registerObserver(root_clone2);

				root_clone1.setSubject(root);
				root_clone2.setSubject(root);

				return true;
			}
			else
				return insertAux(root,root, b, c, 0, root_clone1, root_clone1, root_clone2, root_clone2);
		}

		else { // if student already exists in tree
			return find(b).addCourse(c);
		}

	}

	/**
	 * @return boolean, TRUE if successful insert, otherwise FALSE
	 * @param parent , parent of curernt Node
	 * @param current , current Node in search
	 * @param b, bnum of student to be added
	 * @param c, String representing course to add to student
	 * helps insert Node with bnum = b
	 */
	public boolean insertAux(Node parent, Node current, int b, String c, int direction, Node parent_clone1, Node current_clone1, Node parent_clone2, Node current_clone2) {
		if(current == null) {
			if(direction == 0) {  // left
				parent.setLeftChild(new Node(b, c));
				parent_clone1.setLeftChild(new Node(b, c));
				parent_clone2.setLeftChild(new Node(b, c));

				parent.getLeftChild().registerObserver(parent_clone1.getLeftChild());
				parent.getLeftChild().registerObserver(parent_clone2.getLeftChild());

				parent_clone1.getLeftChild().setSubject(parent.getLeftChild());
				parent_clone2.getLeftChild().setSubject(parent.getLeftChild());
			}
			else { // right
				parent.setRightChild(new Node(b, c));
				parent_clone1.setRightChild(new Node(b, c));
				parent_clone2.setRightChild(new Node(b, c));

				parent.getRightChild().registerObserver(parent_clone1.getRightChild());
				parent.getRightChild().registerObserver(parent_clone2.getRightChild());
				
				parent_clone1.getRightChild().setSubject(parent.getRightChild());
				parent_clone2.getRightChild().setSubject(parent.getRightChild());
			}
			return true;
		}

		if(current.getBNum() == b)
			return false;

		if(b < current.getBNum())
			return insertAux(current, current.getLeftChild(), b, c, 0, current_clone1, current_clone1.getLeftChild(), current_clone2, current_clone2.getLeftChild());

		return insertAux(current, current.getRightChild(), b, c, 1, current_clone1, current_clone1.getRightChild(), current_clone2, current_clone2.getRightChild());

	}

	/**
	 * @return Node from tree with b-num = b
	 * @param b bnum of student trying to find
	 * finds student with bnum = b
	 */
	public Node find(int b) {
		if(root == null)
			return null;
		if(b == root.getBNum())
			return root;
		if(b < root.getBNum())
			return findAux(root.getLeftChild(), b);
		return findAux(root.getRightChild(), b);
	}

	/**
	 * @return Node from tree with b-num = b
	 * @param current current node in search of tree
	 * @param b bnum of student trying to find
	 * helps find student with bnum = b
	 */
	public Node findAux(Node current, int b) {
		if(current == null)
			return null;
		if(b == current.getBNum())
			return current;
		if(b < current.getBNum())
			return current.getLeftChild();
		return current.getRightChild();

	}
}
