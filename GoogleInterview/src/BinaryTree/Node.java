package BinaryTree;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>>
{
	private T data;
	private Node<T> parent;
	private Node<T> left;
	private Node<T> right;
	private int level;
	
	public Node(T data)
	{
		this.data = data;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.level = 0;
	}
	
	@Override
	public String toString()
	{
		return "Node " + this.data + "\n\tLevel: " + this.level;
	}
	
	@Override
	public int compareTo(Node<T> arg0)
	{	
		return this.data.compareTo(arg0.getData());
	}
	
	/* Accessors */
	public T getData() { return this.data; }
	public Node<T> getParent() { return this.parent; }
	public Node<T> getLeft() { return this.left; }
	public Node<T> getRight() { return this.right; }
	public int getLevel() { return this.level; }
	
	/* Mutators */
	public void setData(T data) { this.data = data; }
	public void setParent(Node<T> parent) { this.parent = parent; }
	public void setLeft(Node<T> left) { this.left = left; }
	public void setRight(Node<T> right) { this.right = right; }
	public void setLevel(int level) { this.level = level; }
}
