package com.smallhk.core.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/4
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class URLExample {
    public static void main(String[] args) throws IOException {
        urlHandler();
        jarUrlHandler();
    }

    private static void jarUrlHandler() {
        String urlString = "jar:file:/D:/Maven/repo/com/alibaba/fastjson/1.2.29/fastjson-1.2.29.jar!/";
//        String urlString = "jar:http://butterfly.jenkov.com/"
//                + "container/download/"
//                + "jenkov-butterfly-container-2.9.9-beta.jar!/";

        try {
            URL jarUrl = new URL(urlString);
            JarURLConnection connection = (JarURLConnection)jarUrl.openConnection();
            JarFile jarFile = connection.getJarFile();
            System.out.println(jarFile.getName());
            Manifest manifest = jarFile.getManifest();
            for (Map.Entry entry : manifest.getMainAttributes().entrySet()) {
                System.out.println(entry.getKey() +": "+ entry.getValue());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void urlHandler() {
        try {
            URL url = new URL("file:/E:/1.txt");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String context = bufferedReader.readLine();
            System.out.println(context);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
