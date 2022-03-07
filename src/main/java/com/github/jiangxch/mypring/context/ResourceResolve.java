package com.github.jiangxch.mypring.context;

import com.github.jiangxch.mypring.util.ClassUtil;
import com.github.jiangxch.mypring.util.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: jiangxch
 * @date: 2022/3/6 5:35 PM
 */
public class ResourceResolve {
    /**
     *
     * @param packageName 包名
     * @return 包下所有的.class文件的URL
     * @throws IOException
     */
    public URL[] getResources(String packageName) throws IOException {
        String packagePath = ClassUtil.convertPackageNameToResourceFilePath(packageName);
        if (packagePath.startsWith("/")) {
            packagePath = packagePath.substring(1);
        }
        Set<URL> result = new LinkedHashSet<>(16);
        // 使用app class loader
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resourceUrls = (cl != null ? cl.getResources(packagePath) : ClassLoader.getSystemResources(packagePath));
        try {
            while (resourceUrls.hasMoreElements()) {
                URL url = resourceUrls.nextElement();
                File packageDir = new File(url.toURI());
                if (packageDir.isDirectory()) {
                    File[] classFiles = packageDir.listFiles(FileUtil.CLASS_FILE_FILTER);
                    if (classFiles != null) {
                        for (File classFile : classFiles) {
                            result.add(classFile.toURI().toURL());
                        }
                    }
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return result.toArray(new URL[0]);
    }

}
