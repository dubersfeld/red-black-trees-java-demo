package com.dub;

import com.dub.site.redBlackTrees.Queue;

public class QueueTest {
	
	public static void main(String[] args) {

		System.out.println("main begin");
		  
		Queue<String> queue = new Queue<String>();
		
		queue.display();
		
		queue.push_back("sator");
		queue.display();	
		
		System.out.println("isEmpty " + queue.isEmpty());
		
		queue.pop_front();
		
		System.out.println("isEmpty " + queue.isEmpty());
		
		queue.push_back("arepo");
		queue.display();	
		
		System.out.println("after push isEmpty " + queue.isEmpty());
		
		queue.push_back("tenet");
		
		System.out.println("isEmpty " + queue.isEmpty());
		
		
		System.out.println("\nmain completed");
	
	}
	
}