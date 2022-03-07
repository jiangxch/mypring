package com.github.jiangxch.mypring.util.io;

import com.github.jiangxch.mypring.util.ClassUtil;

import java.io.FilenameFilter;

/**
 * @author: jiangxch
 * @date: 2022/3/7 11:59 PM
 */
public class FileUtil {
    public static final FilenameFilter CLASS_FILE_FILTER = (file, fileName) -> {
        String lowerCase = fileName.toLowerCase();
        return lowerCase.endsWith(ClassUtil.FILE_EXTENSION_SEPARATOR + ClassUtil.CLASS_FILE_SUFFIX);
    };

}
