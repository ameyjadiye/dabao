package com.codeinventory.heart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Ball<K> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Map<K, Long> map = new HashMap<K, Long>();
	
	
	Queue<Long> positionTracker = new LinkedList<Long>();

	/**
	 * @return the map
	 */
	public Map<K, Long> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<K, Long> map) {
		this.map = map;
	}

	/**
	 * @return the positionTracker
	 */
	public Queue<Long> getPositionTracker() {
		return positionTracker;
	}

	/**
	 * @param positionTracker the positionTracker to set
	 */
	public void setPositionTracker(Queue<Long> positionTracker) {
		this.positionTracker = positionTracker;
	}

		

}
