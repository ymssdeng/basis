package com.mdeng.quiz.search;

import java.util.Stack;

public class BinaryTree <T extends Comparable<T>>{

	class Node
	{
		public Node(T t) {
			value = t;
		}
		T value;
		Node left;
		Node right;
		Node parent;
		int time;
	}
	
	public Node min(Node root)
	{
		if (root!=null)
			while (root.left!=null) {
				root = root.left;
			}
		
		return root;
	}
	
	public Node max(Node root)
	{
		if (root!=null)
			while (root.right!=null) {
				root = root.right;;
			}
		return root;
	}
	
	public Node find(Node root, T t)
	{
		while(root!=null)
		{
			if (root.value.compareTo(t)>0)
				root = root.left;
			else if (root.value.compareTo(t)>0)
				root = root.right;
			else
				return root;
		}
		return root;
	}
	
	public Node insert(Node root, T t)
	{
		if (root == null)
			return new Node(t);
		
		if (root.value.compareTo(t)<0)
			root.right = insert(root.right, t);
		else
			root.left = insert(root.left, t);
		
		return root;
	}
	
	public Node insert2(Node root, T t)
	{
		Node pNode =null, p=root;
		while (root != null) {
			pNode = root;
			if (root.value.compareTo(t)>0)
			{
				root = root.left;
			}
			else if (root.value.compareTo(t)>0)
			{
				root = root.right;
			}
		}
		
		if (pNode == null)
			return new Node(t);
		if (pNode.value.compareTo(t)<0)
		{
			Node tNode = new Node(t);
			pNode.right = tNode;
			tNode.parent = pNode;
		}
		else
		{
			Node tNode = new Node(t);
			pNode.left = tNode;
			tNode.parent = pNode;
		}
		
		return p;
	}
	
	public Node delete(Node root, T t)
	{
		if (root == null)
			return null;
		if (root.value.compareTo(t)<0)
			root.right = delete(root.right, t);
		else if (root.value.compareTo(t)>0)
			root.left = delete(root.left, t);
		else if (root.left!=null && root.right!=null)
		{
			Node rmin = min(root.right);
			root.value = rmin.value;
			if (rmin.parent.left == rmin)
				rmin.parent.left = rmin.right;
			else
				rmin.parent.right = rmin.right;
		}
		else {
			//
		}
		
		return root;
	}
	
	public Node delete2(Node root, T t)
	{
		if (root == null)
			return root;
		
		while (root!=null)
		{
			if (t.compareTo(root.value)<0)
				root = root.left;
			else if (t.compareTo(root.value)>0)
				root = root.right;
			else
				break;
		}
		
		if (root.left==null && root.right==null)
		{
			if (root.parent.left == root)
				root.parent.left = null;
			else
				root.parent.right = null;
		}else if (root.left!=null && root.right!=null)
		{
			Node rmin = min(root.right);
			root.value = rmin.value;
			if (rmin.parent.left == rmin)
				rmin.parent.left = rmin.right;
			else
				rmin.parent.right = rmin.right;
		}
		else {
			if (root.parent.left == root)
				root.parent.left = root.left!=null?root.left:root.right;
			else
				root.parent.right = root.left!=null?root.left:root.right;
		}
		
		return root;
	}
	
	public void preOrder(Node root)
	{
		if (root!=null)
		{
			visit(root);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	public void preOrder2(Node root)
	{
		if (root == null)
			return;
		
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			visit(node);
			
			if (node.right!=null)
				stack.push(node.right);
			if (node.left!=null)
				stack.push(node.left);
		}
	}
	
	public void inOrder(Node root)
	{
		if (root!=null)
		{
			inOrder(root.left);
			visit(root);
			inOrder(root.right);
		}
	}
	
	
	public void inOrder2(Node root)
	{
		if (root == null)
			return;
		
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node node = stack.peek();
			if (++node.time == 2)
			{
				visit(node);
				stack.pop();
				if (node.right!=null)
					stack.push(node.right);
				continue;
			}
			
			if (node.left!=null)
				stack.push(node.left);
		}
	}
	
	public void postOrder(Node root)
	{
		if (root!=null)
		{
			postOrder(root.left);
			postOrder(root.right);
			visit(root);
		}
	}
	
	public void postOrder2(Node root)
	{
		if (root == null)
			return;
		
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node node = stack.peek();
			if (++node.time == 1)
			{
				if (node.left!=null)
					stack.push(node.left);
			}
			else if (node.time == 2)
			{
				if (node.right!=null)
					stack.push(node.right);
			}
			else {
				visit(node);
				stack.pop();
			}
			
		}
	}
	
	public void visit(Node t)
	{
		System.out.print(t.value+" ");
	}
	
	public static void main(String[] args)
	{
		BinaryTree<String> tree = new BinaryTree<>();
		BinaryTree.Node a = tree.new Node("a");
		BinaryTree.Node b = tree.new Node("b");
		BinaryTree.Node c = tree.new Node("c");
		BinaryTree.Node d = tree.new Node("d");
		BinaryTree.Node e = tree.new Node("e");
		a.left = b;a.right=c;
		b.right=d;c.left=e;
		
		tree.preOrder(a);
		System.out.println();
		tree.inOrder(a);
		System.out.println();
		tree.postOrder(a);
		System.out.println();
		
		a = tree.new Node("a");
		b = tree.new Node("b");
		c = tree.new Node("c");
		d = tree.new Node("d");
		e = tree.new Node("e");
		a.left = b;a.right=c;
		b.right=d;c.left=e;
		
		tree.preOrder2(a);
		System.out.println();
		
		a = tree.new Node("a");
		b = tree.new Node("b");
		c = tree.new Node("c");
		d = tree.new Node("d");
		e = tree.new Node("e");
		a.left = b;a.right=c;
		b.right=d;c.left=e;
		
		tree.inOrder2(a);
		System.out.println();
		
		a = tree.new Node("a");
		b = tree.new Node("b");
		c = tree.new Node("c");
		d = tree.new Node("d");
		e = tree.new Node("e");
		a.left = b;a.right=c;
		b.right=d;c.left=e;
		
		tree.postOrder2(a);
		System.out.println();
	}
}
