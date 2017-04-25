package com.ruiyi.wechat.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {

	
	public static StringBuffer urlConnectionPost(String tourl, StringBuffer data) {
		StringBuffer sb = null;
		BufferedReader reader = null;
		OutputStreamWriter wr = null;
		URL url;
		try {
			url = new URL(tourl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 5);
			
			
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(10 * 1000);
			
			// 当存在post的值时，才打开OutputStreamWriter
			if (data != null && data.toString().trim().length() > 0) {
				wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				wr.write(data.toString());
				wr.flush();
			}

			// Get the response
			reader = new BufferedReader(new InputStreamReader(conn
					.getInputStream(), "UTF-8"));
			sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (wr != null) {
					wr.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb;
	}

	
	
    /**
     * 从指定的URL中获取数组
     * @param urlPath
     * @return
     * @throws Exception
     */
    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();	
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        return new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
    }
	
}
