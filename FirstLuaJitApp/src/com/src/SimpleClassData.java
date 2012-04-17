package com.src;

public class SimpleClassData
{
	private int i=0;
	public SimpleClassData(int into)
	{
		i=into;
	}
	
	public void increase()
	{
		i++;
	}
	public int get()
	{
		return i;
	}
	public String toString()
	{
		return "Value is "+i;
	}
}
