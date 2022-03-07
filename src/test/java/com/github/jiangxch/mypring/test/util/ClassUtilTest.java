package com.github.jiangxch.mypring.test.util;

import com.github.jiangxch.mypring.util.ClassUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author: jiangxch
 * @date: 2022/3/6 5:47 PM
 */
public class ClassUtilTest {
    @Test
    public void convertClassNameToResourcePath() {
        String packageName = "com.github.jiangxch.mypring.test.util";
        String resourcePath = ClassUtil.convertPackageNameToResourceFilePath(packageName);
        Assert.assertEquals(resourcePath, "com" + File.separator + "github" + File.separator + "jiangxch" + File.separator + "mypring" + File.separator + "test" + File.separator + "util");
    }

    @Test
    public void convertUrlResourcePathToClassName() throws IOException {
        String className = "com.github.jiangxch.mypring.util.ClassUtil";
        String classFileResource = ClassUtil.convertClassNameToResourceFilePath(className);
        Enumeration<URL> resources =
                ClassUtil.class.getClassLoader().getResources(classFileResource);
        Assert.assertTrue(resources.hasMoreElements());
        URL url = resources.nextElement();
        String res = ClassUtil.convertUrlResourcePathToClassName(url);
        Assert.assertEquals(res, className);
    }
}
