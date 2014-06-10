package br.furb.compiladores.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Persistencia {

	public static void salvar(File file, String content) throws IOException {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		try (FileOutputStream fos = new FileOutputStream(file); //
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			fos.write(content.getBytes());
		}
	}

	public static String ler(File file) throws IOException {
		if (file == null || !file.exists()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		byte[] bucket = new byte[4096];
		int index;
		try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis)) {
			while ((index = bis.read(bucket)) > 0) {
				sb.append(new String(bucket, 0, index));
			}
		}
		return sb.toString();
	}

}
