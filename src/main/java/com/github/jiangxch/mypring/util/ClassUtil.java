package com.github.jiangxch.mypring.util;

import java.io.File;
import java.net.URL;

/**
 * @author: jiangxch
 * @date: 2022/3/6 5:47 PM
 */
public class ClassUtil {
    public static final String PACKAGE_SEPARATOR = ".";
    public static final String TEST_TARGET_DIR_NAME = "target/test-classes";
    public static final String TARGET_DIR_NAME = "target/classes";
    public static final String CLASS_FILE_SUFFIX = "class";
    /**
     * 文件后缀名分割符号
     */
    public static final String FILE_EXTENSION_SEPARATOR = ".";

    public static String convertClassNameToResourceFilePath(String className) {
        AssertUtil.notNull(className, "className param must not be null");
        return className.replace(PACKAGE_SEPARATOR, File.separator) + FILE_EXTENSION_SEPARATOR + CLASS_FILE_SUFFIX;
    }

    public static String convertPackageNameToResourceFilePath(String packageName) {
        AssertUtil.notNull(packageName, "packageName param must not be null");
        return packageName.replace(PACKAGE_SEPARATOR, File.separator);
    }

    public static String convertUrlResourcePathToClassName(URL urlResource) {
        AssertUtil.notNull(urlResource, "urlResource param must not be null");
        // /Users/jiangxch/program/java/project/mypring/target/test-classes/com/github/jiangxch/mypring/test/util/ClassUtilTest.class
        // /Users/jiangxch/program/java/project/mypring/target/classes/com/github/jiangxch/mypring/util/ClassUtil.class
        String className = urlResource.getPath();
        if (className.endsWith(FILE_EXTENSION_SEPARATOR + CLASS_FILE_SUFFIX)) {// .class
            className = className.substring(0, className.indexOf(FILE_EXTENSION_SEPARATOR + CLASS_FILE_SUFFIX));
        }
        // 以 target/test-classes 分割
        int testTargetIndex = className.indexOf(TEST_TARGET_DIR_NAME);
        int targetIndex = className.indexOf(TARGET_DIR_NAME);
        if (testTargetIndex != -1) {
            className = className.substring(testTargetIndex + TEST_TARGET_DIR_NAME.length());
        }
        if (targetIndex != -1) {
            className = className.substring(targetIndex + TARGET_DIR_NAME.length());
        }
        if (className.startsWith(File.separator)) {
            className = className.substring(1);
        }
        return className.replace(File.separator, PACKAGE_SEPARATOR);
    }
}
