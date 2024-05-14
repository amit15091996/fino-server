package com.fino.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinoMap<T,V,S,L> {
	
	T mapKey;
	V mapValue;
	S mapData;
	L mapType;
	
	
//	public <B,A> boolean aBC(B first,A second) {
//		
//		
//		return first==second;
//	}
	
	
//	public static void main(String[] args) {
//		
//		FinoMap<String> map=new FinoMap<String>("ppanda");
////		FinoMap<Integer,Double> map1=new FinoMap<Integer,Double>(123, 123.89);
//		
//		System.out.println(map+"  ");
//		
//	}


	

}
