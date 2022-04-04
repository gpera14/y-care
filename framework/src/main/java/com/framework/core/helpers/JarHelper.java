package com.framework.core.helpers;

import com.framework.core.execution.ExecutionConfig;
import org.springframework.boot.SpringApplication;

import java.util.List;

public class JarHelper {

    public static void loadJar(List<ExecutionConfig> executionConfigList) {

        String className = null;

        Class c = null;

        try {
            for (ExecutionConfig executionConfig : executionConfigList) {

                if (executionConfig.getComponentType().name().equalsIgnoreCase("browser")) {

                    c = Class.forName("com.browser.BrowserApplication");

                    SpringApplication.run(c);

                } else if (executionConfig.getComponentType().name().equalsIgnoreCase("device")) {

                    c = Class.forName("com.automation.appiumservice.AppiumServiceApplication");

                    SpringApplication.run(c);
                }
            }
        } catch (Exception e){

            e.printStackTrace();
        }
    }
//        String pathToJar = CommonPathUtils.libFolder + "browser-0.0.1-SNAPSHOT.jar";
//
//        final Map<String, Class<?>> classMap = new HashMap<>();
//
//        final JarFile jarFile = new JarFile(pathToJar);
//        final Enumeration<JarEntry> jarEntryEnum = jarFile.entries();
//
//        final URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
//        final URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);
//
//        while (jarEntryEnum.hasMoreElements()) {
//
//            final JarEntry jarEntry = jarEntryEnum.nextElement();
//
//            if (jarEntry.getName().startsWith("org/springframework/boot")
//                    && jarEntry.getName().endsWith(".class") == true) {
//
//                int endIndex = jarEntryName.lastIndexOf(".class");
//
//                String className = jarEntryName.substring(0, endIndex).replace('/', '.');
//
//                try {
//
//                    final Class<?> loadedClass = urlClassLoader.loadClass(className);
//
//                    result.put(loadedClass.getName(), loadedClass);
//                }
//                catch (final ClassNotFoundException ex) {
//
//                }
//            }
//        }
//
//        jarFile.close();
//    }
}
