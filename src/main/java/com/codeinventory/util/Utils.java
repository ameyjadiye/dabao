package com.codeinventory.util;

import java.io.File;

public class Utils {

	public static String filterPath(File file) {

		String absolutePath = file.getAbsolutePath();

		String filePath = absolutePath.substring(0,
				absolutePath.lastIndexOf(File.separator));
		return filePath;
	}

}
