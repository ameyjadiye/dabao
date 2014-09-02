package com.codeinventory.dumper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

import com.codeinventory.heart.Ball;
import com.google.common.base.Joiner;

/**
 * 
 */

/**
 * @author amey
 * 
 */
public class Serializer {

	public static void serialize(Object o, String path, String fileName) {

		Ball<String> ball = (Ball<String>) o;
		Map<String, Long> map = ball.getMap();
		Queue<Long> positionTracker = ball.getPositionTracker();
		List<String> list = new ArrayList<String>();
		for (Entry<String, Long> entry : map.entrySet()) {
			String key = entry.getKey();
			Long value = entry.getValue();
			list.add(key + ":" + value);
		}
		String dictionary = Joiner.on("^").join(list);
		list.clear();
		List<Long> list2 = new ArrayList<Long>();
		
		for (Long s : positionTracker) {
			list2.add(s);
		}
		String positions = Joiner.on(" ").join(list2);
		try{
		PrintStream fileStream = new PrintStream(new File(path+  File.separator + fileName));
		fileStream.println(dictionary);
		fileStream.println(positions);
		fileStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public static Object deSerialize(String path) {
		Object o = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return o;
	}

}
