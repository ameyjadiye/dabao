package com.codeinventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.codeinventory.dumper.Serializer;
import com.codeinventory.heart.Ball;
import com.codeinventory.util.Utils;

/**
 * 
 *
 */
public class App {
	public static void main(String[] args) {
		if (args.length > 0) {

			if (args[1].equals("-d")) {
				BufferedReader br = null;
				try {
					File f = new File(args[0]);
					br = new BufferedReader(new FileReader(f));
					Map<String, Long> map = new HashMap<String, Long>();
					map.put(" ", Long.valueOf(0));
					map.put("\n", Long.valueOf(-1));
					Queue<Long> positionTracker = new LinkedList<Long>();
					String line;
					Long count = (long) 1;
					while ((line = br.readLine()) != null) {
						String[] words = line.split(" ");
						for (String word : words) {
							if (!map.containsKey(word)) {
								map.put(word, count);
								count = count + 1;
							}
							positionTracker.add(map.get(word));
							positionTracker.add(map.get(" "));
						}
						positionTracker.add(map.get("\n"));
					}

					Ball<String> ball = new Ball<String>();
					ball.setMap(map);
					ball.setPositionTracker(positionTracker);

					Serializer.serialize(ball, Utils.filterPath(f), f.getName()	+ ".dabao");

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (args[1].equals("-r")) {
				File f = new File(args[0]);
				Ball<String> ball = (Ball<String>) Serializer.deSerialize(f
						.getPath());

				StringBuilder builder = new StringBuilder();

				Map<String, Long> map = ball.getMap();
				Queue<Long> positionTracker = ball.getPositionTracker();

				Map<Long, String> invertedMap = new HashMap<Long, String>();
				for (Map.Entry<String, Long> entry : map.entrySet()) {
					invertedMap.put(entry.getValue(), entry.getKey());
				}

				for (Long s : positionTracker) {
					builder.append(invertedMap.get(s));
				}

				try {
					File file = new File(Utils.filterPath(f)+ File.separator  + f.getName().replace(".dabao", ""));

					if (!file.exists()) {
						file.delete();
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(builder.toString());
					bw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
