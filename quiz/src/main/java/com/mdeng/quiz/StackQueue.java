package com.mdeng.quiz;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A simple queue implementation using two stacks
 * 
 * @author Deng Hui
 *
 * @param <E> Element type in queue
 */
public class StackQueue <E>{

	private Stack<E> inStack = new Stack<>();
	private Stack<E> outStack = new Stack<>();
	
	/**
	 * Inserts the specified element at the end of this queue
	 * @param e the element to be inserted into the queue
	 */
	public void add(E e)
	{
		inStack.push(e);
	}
	
	/**
	 * Remove an element from the head of this queue
	 * @return the element at the head of this queue
	 */
	public E remove()
	{
		if (outStack.isEmpty())
		{
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
		else
			return outStack.pop();
		
		if (outStack.isEmpty())
			throw new NoSuchElementException();
		
		return outStack.pop();
	}
}
