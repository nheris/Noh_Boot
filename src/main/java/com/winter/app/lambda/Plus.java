package com.winter.app.lambda;

import org.eclipse.jdt.internal.compiler.lookup.InferenceContext18;

public class Plus implements TestInterface{
	
	
	//람다식 
	@Override
	public int t1(int n1, int n2) {
		System.out.println(n1+n2);
		
		return n1+n2;
	}
	
	
}
