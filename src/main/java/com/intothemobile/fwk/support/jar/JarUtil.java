package com.intothemobile.fwk.support.jar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public class JarUtil {
	public static void add(String basePackage, String baseDirectory, File source, JarOutputStream target) throws IOException {
		File dir = new File(baseDirectory);
		String root = dir.getPath();
		
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name = source.getPath().replace(root, basePackage.replaceAll("\\.", "-")).replace("\\", "/");
				if (!name.isEmpty()) {
					if (!name.endsWith("/"))
						name += "/";
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile : source.listFiles())
					add(basePackage, baseDirectory, nestedFile, target);
				return;
			}

			JarEntry entry = new JarEntry(source.getPath().replace(root, basePackage.replaceAll("\\.", "-")).replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));

			byte[] buffer = new byte[1024];
			while (true) {
				int count = in.read(buffer);
				if (count == -1)
					break;
				target.write(buffer, 0, count);
			}
			target.closeEntry();
		} finally {
			if (in != null)
				in.close();
		}
	}
}
