import java.util.List;
import java.util.ArrayList;


public class TreeBuilder {

	private Node root;

	/**
	 * constructor with no root specified
	 */
	public TreeBuilder() {
		root = null;
	}

	/**
	 * constructor with root specified
	 */
	public TreeBuilder(Node r) {
		root = r;
	}


	/**
	 * @return boolean, TRUE if successful insert, otherwise FALSE
	 * @param b, int representing BNUM to be added
	 * @param c, string representing course to be addded
	 * inserts Node with bnum = b if doesnt already exist
	 */
	public boolean insert(int b, String c) {
		
		if(find(b) == null) { // if student does not exist in tree
			if(root == null) {
				root = new Node(b, c);
				return true;
			}
			else
				return insertAux(root, b, c);
		}

		else { // if student already exists in tree
			return find(b).addNewCourse(c);
		}

	}

	/**
	 * @return boolean, TRUE if successful insert, otherwise FALSE
	 * @param current, current Node in search
	 * @param b, bnum of student to be added
	 * @param c, String representing course to add to student
	 * helps insert Node with bnum = b
	 */
	public boolean insertAux(Node current, int b, String c) {
		if(current == null) {
			current = new Node(b, c);
			return true;
		}

		if(current.getBNum() == b)
			return false;

		if(b < current.getBNum())
			return insertAux(current.getLeftChild(), b, c);

		return insertAux(current.getRightChild(), b, c);

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
