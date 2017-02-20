package com.dub;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dub.site.redBlackTrees.Queue;


public class QueueTestJunit {

	@Test
	public void testAdd() {
		
		Queue<String> queue = new Queue<>();
	
		assertEquals(true,queue.isEmpty());
	
		queue.push_back("sator");
	
		assertEquals(false,queue.isEmpty());
	
		assertEquals("sator",queue.front());
	
		queue.push_back("arepo");
	
		assertEquals("sator",queue.front());
	
		queue.push_back("tenet");
	
		assertEquals("sator",queue.pop_front());
	
		assertEquals("arepo",queue.front());
	
		assertEquals("arepo",queue.pop_front());
	
		assertEquals("tenet",queue.pop_front());
	
	assertEquals(true,queue.isEmpty());
	
	
   }
}