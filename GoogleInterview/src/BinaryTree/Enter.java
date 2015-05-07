package BinaryTree;

public class Enter {
	
	public static void main(String args[])
	{
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(5);
		bst.add(6);
		bst.add(4);
		bst.add(10);
		bst.add(7);
		bst.add(3);
		
		System.out.println(bst);
	}

}
