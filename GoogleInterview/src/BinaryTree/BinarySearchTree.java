package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>>
{
	private Node<T> head;
	
	public BinarySearchTree()
	{
		this.head = null;
	}
	
	public void add(T data)
	{
		Node<T> newNode = new Node<T>(data);
		
		if (this.head == null)
			this.head = newNode;
		else
			addNode(head, newNode); // call recursive add node function
	}
	
	/* Recursive add node function */
	private void addNode(Node<T> cur, Node<T> newNode)
	{
		int comparison = cur.compareTo(newNode);
		
		if (comparison > 0 || comparison == 0)
		{
			if (cur.getLeft() == null)
			{
				newNode.setParent(cur);
				newNode.setLevel(cur.getLevel() + 1);
				cur.setLeft(newNode);
			}
			else
				addNode(cur.getLeft(), newNode);
		}
		else if (comparison < 0)
		{
			if (cur.getRight() == null)
			{
				newNode.setParent(cur);
				newNode.setLevel(cur.getLevel() + 1);
				cur.setRight(newNode);
			}
			else
				addNode(cur.getRight(), newNode);
		}
		
	}
	
	
	@Override
	public String toString()
	{
		return bfsToString() + '\n' + dfsToString() + '\n' + preOrderToString();
	}
	
	/* Converts traversal result to String */
	private String traversalToString(String type, LinkedList<Node<T>> result)
	{
		int curLevel = 0;
		StringBuilder output = new StringBuilder(type + " toString, () specifies parent:\nLevel " + curLevel + ':');
		
		while (!result.isEmpty())
		{
			Node<T> cur = result.pop();
			
			if (cur.getLevel() != curLevel)
			{
				curLevel = cur.getLevel();
				output.append("\nLevel " + curLevel + ':');
			}
			
			output.append("\t" + cur.getData());
			if (cur.getParent() != null)
				output.append(" (" + cur.getParent().getData() + ')');
		}
		
		return output.toString();
	}
	
	private String bfsToString()
	{
		/*
		 * Wikipedia: http://en.wikipedia.org/wiki/Breadth-first_search
		 	1  procedure BFS(G,v) is
			2      let Q be a queue
			3      Q.enqueue(v)
			4      label v as discovered
			5      while Q is not empty
			6         v <- Q.dequeue()
			7         for all edges from v to w in G.adjacentEdges(v) do
			8             if w is not labeled as discovered
			9                 Q.enqueue(w)
			10                label w as discovered
		 */
		
		LinkedList<Node<T>> bfs = new LinkedList<Node<T>>();
		
		Queue<Node<T>> q = new LinkedList<Node<T>>();
		q.add(this.head);
		
		while (!q.isEmpty())
		{
			Node<T> cur = q.poll(); // dequeue
			
			// do what you need to do
			bfs.add(cur);
			
			// Add children
			if (cur.getData() != null)
			{
				if (cur.getLeft() != null)
					q.add(cur.getLeft());
				
				if (cur.getRight() != null)
					q.add(cur.getRight());
			}
		}
		
		// Convert result to String
		return traversalToString("BFS", bfs);
	}
	
	private String dfsToString()
	{
		/*
		 * Wikipedia: http://en.wikipedia.org/wiki/Depth-first_search
			1  procedure DFS-iterative(G,v):
			2      let S be a stack
			3      S.push(v)
			4      while S is not empty
			5            v = S.pop() 
			6            if v is not labeled as discovered:
			7                label v as discovered
			8                for all edges from v to w in G.adjacentEdges(v) do
			9                    S.push(w)
		 */
		
		LinkedList<Node<T>> dfs = new LinkedList<Node<T>>();
		
		Stack<Node<T>> stack = new Stack<Node<T>>();
		stack.push(this.head);
		
		while (!stack.isEmpty())
		{
			Node<T> cur = stack.pop();
			
			// do what you need to do
			dfs.add(cur);
			
			// Add children
			if (cur.getData() != null)
			{
				if (cur.getLeft() != null)
					stack.push(cur.getLeft());
				
				if (cur.getRight() != null)
					stack.push(cur.getRight());
			}
		}
		
		// Convert result to String
		return traversalToString("DFS", dfs);
	}
	
	public String preOrderToString()
	{
		/*
		 *  Pre-Order (VLR)
			V. Display the data part of root element (or current element)
			L. Traverse the left subtree by recursively calling the pre-order function.
			R. Traverse the right subtree by recursively calling the pre-order function.
		 */
		
		String recursive = traversalToString("Pre-Order Recursive", preOrderRecursive(this.head, new LinkedList<Node<T>>()));
		String iterative = preOrderIterative();

		return recursive + '\n' + iterative;
	}
	
	private LinkedList<Node<T>> preOrderRecursive(Node<T> cur, LinkedList<Node<T>> result)
	{
		if (cur == null)
			return null;
		
		// V
		result.add(cur);
		
		// L
		preOrderRecursive(cur.getLeft(), result);
		
		// V
		preOrderRecursive(cur.getRight(), result);
		
		return result;
	}
	
	private String preOrderIterative()
	{
		/* Wikipedia: http://en.wikipedia.org/wiki/Tree_traversal
		 * iterativePreorder(node)
			  parentStack = empty stack
			  while (not parentStack.isEmpty() or node != null)
			    if (node != null) 
			      visit(node)
			      if (node.right != null) parentStack.push(node.right) 
			      node = node.left   
			    else     
			      node = parentStack.pop()
		 */
		
		LinkedList<Node<T>> result = new LinkedList<Node<T>>();
		
		Stack<Node<T>> parentStack = new Stack<Node<T>>();
		Node<T> cur = this.head;
		
		while (!parentStack.isEmpty() || cur != null)
		{
			if (cur != null)
			{
				// do what you need to do
				result.add(cur);
				
				if (cur.getRight() != null)
					parentStack.push(cur.getRight());
				
				cur = cur.getLeft();
			}
			else
				cur = parentStack.pop();
		}
		
		return traversalToString("Pre-Order Iterative", result);
	}
	
	/* Accessors */
	public Node<T> getHead() { return this.head; }
	
	/* Mutators */
	public void setHead(Node<T> head) { this.head = head; }
}
