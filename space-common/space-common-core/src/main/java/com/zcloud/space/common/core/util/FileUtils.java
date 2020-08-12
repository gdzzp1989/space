package com.zcloud.space.common.core.util;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 *文件操作工具类 拷贝|删除|写入|读取
 * @author <a href="http://www.xdemo.org/">http://www.xdemo.org/</a>
 * 252878950@qq.com
 */
public class FileUtils {

	/**
	 * 拷贝文件
	 *
	 * @param src
	 *            源文件
	 * @param dest
	 *            目标文件
	 * @param bufferSize
	 *            每次读取的字节数
	 * @throws IOException
	 */
	public static void copyFile(String src, String dest, int bufferSize)
			throws IOException {

		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(dest);
		byte[] buffer = new byte[bufferSize];
		int length;

		while ((length = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, length);
		}
		fis.close();
		fos.close();
	}

	/**
	 * 删除文件
	 *
	 * @param src
	 *            源文件
	 */
	/*public static void deleteFile(String src) {
		File file = new File(src);
		if(file.exists()){
			System.out.println("1");
			file.delete();
		}
	}*/
	/**
	 * 删除多个文件
	 *
	 * @param src
	 *            源文件数组
	 */
	/*public static void deleteFiles(String... src) {
		for (String s : src) {
			System.out.println(s);
			FileUtils.deleteFile(s);
		}
	}*/

	/**
	 * 根据路径删除文件
	 *
	 * @param dir
	 *            目录
	 * @throws Exception
	 */
	/*public static void deleteByDir(String dir) throws Exception {
		File d = new File(dir);
		if (!d.isDirectory()) throw new Exception("\"" + dir + "\"" + "不是一个目录");
		String[] fileNameArray = d.list();
		FileUtils.deleteFiles(fileNameArray);
	}*/

	 /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
					break;
				}
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
					break;
				}
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

	/**
	 * 将内容写入文件
	 *
	 * @param content
	 *            写入的内容
	 * @param dest
	 *            写入的文件
	 * @param append
	 *            是否追加
	 * @param newLine
	 *            是否换行
	 * @throws IOException
	 */
	public static void writeToFile(String content, String dest, boolean append, boolean newLine) throws IOException {
		FileWriter writer = new FileWriter(dest, append);
		writer.write(content + (newLine ? System.getProperty("line.separator") : ""));
		writer.close();
	}

	/**
	 * 获取文件内容
	 *
	 * @param src
	 *            源文件
	 * @return String[] 文件内容数组，每行占一个数组空间
	 * @throws IOException
	 */
	public static String[] readContent(String src, Charset charset)
			throws IOException {
		FileReader reader = new FileReader(src);
		BufferedReader br = new BufferedReader(reader);
		String[] array = new String[FileUtils.readLineNumber(src)];
		String line;
		int lineNumber = 0;
		while ((line = br.readLine()) != null) {
			array[lineNumber] = line;
			lineNumber++;
		}
		reader.close();
		br.close();
		return array;
	}

	/**
	 * 获取文件内容
	 *
	 * @param src
	 *            源文件
	 * @return String[] 文件内容数组，每行占一个数组空间
	 * @throws IOException
	 */
	public static String readStringContent(String src) throws IOException {
		FileReader reader = new FileReader(src);
		BufferedReader br = new BufferedReader(reader);
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		br.close();
		return sb.toString();
	}

	/**
	 * 获取文件行数
	 *
	 * @param src
	 *            源文件
	 * @return int 内容行数
	 * @throws IOException
	 */
	public static int readLineNumber(String src) throws IOException {
		FileReader reader = new FileReader(src);
		BufferedReader br = new BufferedReader(reader);
		int lineNumber = 0;
		while (br.readLine() != null) {
			lineNumber++;
		}
		reader.close();
		br.close();
		return lineNumber;
	}

	/**
	 * 获取目录下的文件和文件夹列表
	 *
	 * @param dir
	 *            源目录
	 * @return LinkedHashMap<String,Boolean> true表示目录
	 * @throws Exception
	 */
	public static LinkedHashMap<String, Boolean> readDir(String dir)
			throws Exception {
		File d = new File(dir);

		if (!d.isDirectory()) {
			throw new Exception("\"" + dir + "\"" + "不是一个目录");
		}

		String[] array = d.list();
		if (array == null) {
			return null;
		}

		LinkedHashMap<String, Boolean> map = new LinkedHashMap<String, Boolean>();
		for (String anArray : array) {
			map.put(anArray, new File(dir + File.separatorChar + anArray).isDirectory());
		}
		return map;
	}

	/**
	 * 移动文件,不可以移动文件家
	 *
	 * @param src
	 *            源文件
	 * @param dest
	 *            目标文件
	 */
	public static void moveFile(String src, String dest) {
		new File(src).renameTo(new File(dest));
	}

	/**
	 * 重命名文件||实际上调用本类的moveFile方法
	 *
	 * @param src
	 *            源文件
	 * @param dest
	 *            目标文件
	 */
	public static void renameFile(String src, String dest) {
		moveFile(src, dest);
	}

	/**
	 * 从URL抓取一个文件写到本地<br>，有可能会出现403的情况
	 * 这个方法摘自 <a href="http://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/FileUtils.html">org.apache.commons.io.FileUtils.copyURLToFile(URL source, File destination)</a>
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void copyFileFromURL(URL source, File destination) throws IOException {

		InputStream input = null;
		FileOutputStream output = null;
		byte[] buffer = new byte[1024];

		input = source.openStream();

		if (destination.exists()) {
			if (destination.isDirectory()) {
				throw new IOException("File '" + destination
						+ "' exists but is a directory");
			}
			if (destination.canWrite() == false) {
				throw new IOException("File '" + destination
						+ "' cannot be written to");
			}
		} else {
			File parent = destination.getParentFile();
			if (parent != null) {
				if (!parent.mkdirs() && !parent.isDirectory()) {
					throw new IOException("Directory '" + parent
							+ "' could not be created");
				}
			}
		}

		output = new FileOutputStream(destination, true);

		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		output.close();
		input.close();
	}

	/**
	 * 复制一个目录及其子目录、文件到另外一个目录
	 * @param _src
	 * @param _target
	 * @throws IOException
	 */
	public static void copyDir(String _src, String _target) throws IOException {

		File src=new File(_src);
		File dest=new File(_target);

		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();
			}
			String files[] = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// 递归复制
				copyDir(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
			}
		} else {
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
		}
	}

	public static void renameDirectory(String sourceDirName,String targetDirName){
		new File(sourceDirName).renameTo(new File(targetDirName));
	}


	/**
     *
     * 获取目录下所有文件(包括子文件夹)
     * 递归调用
     * @param realpath
     * @param files
     * @return
     */
    public static List<File> getFiles(String realpath, List<File> files) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

	/**
     * 获取目录下所有文件(按修改时间排序)
     *
     * @param path
     * @return
     */
    public static List<File> getFileSort(String path) {

        List<File> list = getFiles(path, new ArrayList<File>());

        if (list != null && list.size() > 0) {

            Collections.sort(list, new Comparator<File>() {
                @Override
				public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }

                }
            });
        }

        return list;
    }

    /**
     * 获取文件后缀名
     * @param file
     * @return
     */
    public static String getFileSuffix(File file){
    	if(!file.isFile()||!file.exists()) {
			return null;
		}

    	String name=file.getName();
    	int pos=name.lastIndexOf(".");
    	if(pos==-1){
    		return null;
    	}
    	return name.substring(pos+1, name.length());
    }

}
