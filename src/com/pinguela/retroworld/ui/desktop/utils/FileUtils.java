package com.pinguela.retroworld.ui.desktop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
	public static final int DEFAULT_BUFFER_SIZE = 1000;

	public static void copy(String from, String to) throws FileNotFoundException, IOException{
		FileInputStream fis = new FileInputStream(from);
		FileOutputStream fos = new FileOutputStream(to);
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		try {
			int bytesLeidos = fis.read(buffer);
			while(bytesLeidos==DEFAULT_BUFFER_SIZE) {
				fos.write(buffer, 0 ,bytesLeidos);
				bytesLeidos = fis.read(buffer);
			}
			fos.write(buffer, 0 ,bytesLeidos);
		} finally {
			try {
				fis.close();
			} catch(Exception e) {

			}
			try {
				fos.close();
			} catch(Exception e) {

			}
		}
	}
	/**
	 * Si el path es un directorio, imprime por consola
	 * el listado de ficheros que hay en él:
	 * Ejemplo:
	 * FilesUtils.list("c:windows")
	 * Sacará algo como :
	 * fichero1 100 bytes
	 * directorio1
	 * fichero2 200bytes
	 * System
	 * ...
	 * **/
	public static void listFiles(String path) throws IOException {
		File directory = new File(path);
		if(directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			for(int i=0;i<files.length;i++) {
				System.out.print(files[i].getName()+" ");
				System.out.println(files[i].length()+"bytes");
			}
		}
	}
	
	public static void listFiles2(String path) throws IOException {
		File directory = new File(path);
		if(directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			for(int i=0;i<files.length;i++) {
				if(files[i].isDirectory()) {
					System.out.print(files[i].getName()+" ");
					System.out.println(files[i].length()+"bytes");
					listFiles2(files[i].getPath());
				}else {
					System.out.print(files[i].getName()+" ");
					System.out.println(files[i].length()+"bytes");
				}
			}
		}
	}
	
	public static void listFiles3(String path, int tabNumber) throws IOException {
		File directory = new File(path);
		if(directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			for(int i=0;i<files.length;i++) {
				if(files[i].isDirectory()) {
					if(tabNumber!=0) {
						for(int j=0;j<tabNumber;j++) {
							System.out.print("\t");
						}
					}
					tabNumber++;
					System.out.print(files[i].getName()+" ");
					System.out.println(files[i].length()+"bytes");
					listFiles3(files[i].getPath(), tabNumber);
				}else {
					for(int j=0;j<tabNumber;j++) {
						System.out.print("\t");
					}
					System.out.print(files[i].getName()+" ");
					System.out.println(files[i].length()+"bytes");
				}
			}
		}
	}
	
	public static long size(String path, boolean recursive) {
		File directory = new File(path);
		long totalSize=0l;
		if(directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			for(int i=0;i<files.length;i++) {
				if(files[i].isDirectory() && recursive==true) {
					totalSize=size(files[i].getPath(), recursive);
				} else {
					totalSize=files[i].length();
				}
			}
		}
		return totalSize;
	}

	public static void main(String[]args) {
		try {
//			String from = "C:\\Users\\Dimit\\Documents\\DAW\\PROGRAMACIÓN\\imgs\\Donkey Kong Country 2\\nuevo1.txt";
//			String to = "C:\\Users\\Dimit\\Documents\\DAW\\PROGRAMACIÓN\\imgs\\Donkey Kong Country 2\\nuevo1-copy.txt";
//			copy(from, to);
//			System.out.println("copiado.");
			String path="C:\\Users\\Dimit\\Documents\\DAW\\PROGRAMACIÓN\\imgs";
//			listFiles3(path, 0);
			long size=size(path, true);
			System.out.println(size);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}