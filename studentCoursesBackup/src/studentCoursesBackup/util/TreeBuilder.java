package studentCoursesBackup;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.lang.NumberFormatException;



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
	 * @return nothing
	 * @param version specifies original, clone1, or clone2 tree
	 * stores string version of tree in Results instance from parameter
	 */
	public void printNodes(Results r, int version) {
		if(version == 0) { // original
			r.storeNewResult(toString());
			return;
		}
		if(version == 1) { // clone1
			r.storeNewResult(toStringClone1());
			return;
		}
		if(version == 2) { // clone2
			r.storeNewResult(toStringClone2());
			return;
		}
	}

	/**
	 * @return nothing
	 * processes delete file and forwards to delete function
	 */
	public void deleteInputs(String deleteFile) {
		File delete = new File(deleteFile);
		if(!delete.isFile()) { // file does not exist
			System.err.println("Delete file specified does not exist. Exiting.");
			System.exit(0);
		}
		String s;
		int tempInt = 0;
		FileProcessor fp = new FileProcessor(delete);
		while((s = fp.readLine()) != null) {
			try {
				String array[] = s.split(":");
				tempInt = Integer.parseInt(array[0]);
				if(String.valueOf(tempInt).length() != 4)
					continue;
				if(array[array.length-1].length() != 1)
					continue;
				delete(tempInt, array[array.length-1]);
			}
			catch(NumberFormatException n) {
				System.err.println(s + " is not a number, so it was skipped");
				continue;
			}
		}
		fp.closeFile();
	}

	
	/**
	 * @return boolean - true if delete worked
	 * removes specified course from specified student with b num
	 */
	public boolean delete(int num, String c) {
		Node temp = find(num);
		if(temp == null)
			return false;
		boolean removed = temp.removeCourse(c);
		if(removed)
			temp.notifyObservers(c);
		return removed;
	}

	/**
	 * @return String representing this tree (inorder traversal)
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(root == null)
			return "Empty tree";
		else {
			try{
			if(root.getLeftChild() != null) {
				sb.append(toStringAux(root.getLeftChild()));
			}
			sb.append(root.toString());
			sb.append("\n");
			if(root.getRightChild() != null)
				sb.append(toStringAux(root.getRightChild()));
		
			}
			catch(Exception e) {
				System.err.println(e.toString());
				e.printStackTrace();
				System.exit(0);
			}
			return sb.toString();
		}
	
	}

	/**
	 * @return String representing this tree (inorder traversal)
	 */
	public String toStringClone1() {
		StringBuilder sb = new StringBuilder();
		if(root_clone1 == null)
			return "Empty tree";
		else {
			if(root_clone1.getLeftChild() != null) {
				sb.append(toStringAux(root_clone1.getLeftChild()));
			}
			sb.append(root_clone1.toString());
			sb.append("\n");
			if(root_clone1.getRightChild() != null)
				sb.append(toStringAux(root_clone1.getRightChild()));
			return sb.toString();
		}
	}

	/**
	 * @return String representing this tree (inorder traversal)
	 */
	public String toStringClone2() {
		StringBuilder sb = new StringBuilder();
		if(root_clone2 == null)
			return "Empty tree";
		else {
			if(root_clone2.getLeftChild() != null) {
				sb.append(toStringAux(root_clone2.getLeftChild()));
			}
			sb.append(root_clone2.toString());
			sb.append("\n");
			if(root_clone2.getRightChild() != null)
				sb.append(toStringAux(root_clone2.getRightChild()));
			return sb.toString();
		}
	}

	/**
	 * @return String of certain part of tree (inorder traversal)
	 * @param current - "root" of current tree we want to print out
	 */
	public String toStringAux(Node current) {
		StringBuilder sb = new StringBuilder();
		try {
		if(current == null)
			return "";
		if(current.getLeftChild() != null) {
			sb.append(toStringAux(current.getLeftChild()));
			//sb.append(" ");
		}
		sb.append(current.toString());
		sb.append("\n");
		if(current.getRightChild() != null) {
			sb.append(toStringAux(current.getRightChild()));
			//sb.append(" ");
		}
		}
		catch(Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			System.exit(0);
		}

		return sb.toString();
	}

	/**
	 * @return original non-clone root of tree
	 */
	public Node getRoot() {
		return root;
	}


	/**
	 * @return nothing
	 * processes file and calls insert
	 */
	public void insertInputs(String inputFile) {
		File input = new File(inputFile);
		if(!input.isFile()) { // file does not exist
			System.out.println("Input file specified does not exist. Exiting.");
			System.exit(0);
		}
		String s;
		int tempInt = 0;
		FileProcessor fp = new FileProcessor(input);
		while((s = fp.readLine()) != null) {
			try {
				String array[] = s.split(":");
				tempInt = Integer.parseInt(array[0]);
				if(String.valueOf(tempInt).length() != 4)
					continue;
				if(array[array.length-1].length() != 1)
					continue;
				insert(tempInt, array[array.length-1]);
			}
			catch(NumberFormatException n) {
				System.err.println(s + " is not a number, so it was skipped");
				continue;
			}
		}
		fp.closeFile();
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
				try {
				root = new Node(b, c);

				root_clone1 = (Node) root.clone();
				root_clone2 = (Node) root.clone();

				root.registerObserver(root_clone1);
				root.registerObserver(root_clone2);

				root_clone1.setSubject(root);
				root_clone2.setSubject(root);
				}
				catch(Exception e) {
					System.err.println(e.toString());
					e.printStackTrace();
					System.exit(0);
				}

				return true;
			}
			else
				return insertAux(root,root, b, c, 0, root_clone1, root_clone1, root_clone2, root_clone2);
		}

		else { // if student already exists in tree
			boolean ret =  find(b).addCourse(c);
		 	if(ret == true) {
				findClone1(b).addCourse(c);
				findClone2(b).addCourse(c);
			}
			return ret;
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
				try {
				parent.setLeftChild(new Node(b, c));
				parent_clone1.setLeftChild((Node)parent.getLeftChild().clone());
				parent_clone2.setLeftChild((Node)parent.getLeftChild().clone());

				parent.getLeftChild().registerObserver(parent_clone1.getLeftChild());
				parent.getLeftChild().registerObserver(parent_clone2.getLeftChild());

				parent_clone1.getLeftChild().setSubject(parent.getLeftChild());
				parent_clone2.getLeftChild().setSubject(parent.getLeftChild());
				}
				catch(Exception e) {
					System.err.println(e.toString());
					e.printStackTrace();
					System.exit(0);
				}
			}
			else { // right
				try {
				parent.setRightChild(new Node(b, c));
				parent_clone1.setRightChild((Node)parent.getRightChild().clone());
				parent_clone2.setRightChild((Node)parent.getRightChild().clone());

				parent.getRightChild().registerObserver(parent_clone1.getRightChild());
				parent.getRightChild().registerObserver(parent_clone2.getRightChild());
				
				parent_clone1.getRightChild().setSubject(parent.getRightChild());
				parent_clone2.getRightChild().setSubject(parent.getRightChild());
				}
				catch(Exception e) {
					System.err.println(e.toString());
					e.printStackTrace();
					System.exit(0);
				}
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
	 * @param b bnum of student trying to find
	 * finds student with bnum = b
	 */
	public Node findClone1(int b) {
		if(root_clone1 == null)
			return null;
		if(b == root_clone1.getBNum())
			return root_clone1;
		if(b < root_clone1.getBNum())
			return findAux(root_clone1.getLeftChild(), b);
		return findAux(root_clone1.getRightChild(), b);
	}

	/**
	 * @return Node from tree with b-num = b
	 * @param b bnum of student trying to find
	 * finds student with bnum = b
	 */
	public Node findClone2(int b) {
		if(root_clone2 == null)
			return null;
		if(b == root_clone2.getBNum())
			return root_clone2;
		if(b < root_clone2.getBNum())
			return findAux(root_clone2.getLeftChild(), b);
		return findAux(root_clone2.getRightChild(), b);
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
			return findAux(current.getLeftChild(), b);
		return findAux(current.getRightChild(), b);
		
	}
}
