package com.gxjtkyy.standardcloud.common.utils;

import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


@Slf4j
public class ZipUtil {

	public static void zip(String zipFileName, String inputFile) throws Exception {
		zip(zipFileName, new File(inputFile));
	}

	public static void zip(String zipFileName, File file) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
		zip(out, file, file.getName());
		out.close();
	}

	public static void zip(ZipOutputStream out, File file, String base) throws Exception {
		log.info("Zipping " + file.getName());
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < files.length; i++) {
				zip(out, files[i], base + files[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			int b = 0;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}
	
	public static void zipFiles(String zipFileName, List<File> inputFiles) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
		for (File file : inputFiles) {
			zip(out, file, file.getName());
		}
		out.close();
	} 
	
	public static void unzip(String zipFIleName, String outputDirectory) throws Exception {
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFIleName)));
		ZipEntry zipEntry;
		File file1=new File(outputDirectory);
		if(!file1.exists()) file1.mkdirs();
		while ((zipEntry = zis.getNextEntry()) != null) {
			log.info("unziping " + zipEntry.getName());
			if (zipEntry.isDirectory()) {
				String name = zipEntry.getName();
				name = name.substring(0, name.length() - 1);
				File file = new File(outputDirectory + File.separator + name);
				file.mkdirs();
				log.info("mkdir " + outputDirectory + File.separator + name);
			} else {
				log.info(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "正在解压...", zipEntry.getName());
				File file = new File(outputDirectory + File.separator + zipEntry.getName());
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				int b = 0;
				while ((b = zis.read()) != -1) {
					fos.write(b);
				}
				fos.close();
			}
		}
		zis.close();
	}
}

