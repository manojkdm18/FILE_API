/**
 * 
 */
package com.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author manojkadam
 *
 */
public class TEST {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void testApi() throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet("http://localhost:8080/api/v1/digitalsign/zip-download");
			System.out.println("executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response content length: " + resEntity.getContentLength());
					String inputLine = "";
					String filename ="";
					BufferedReader br = new BufferedReader(new InputStreamReader(resEntity.getContent()));
					  try {
						while ((inputLine = br.readLine()) != null) {
							System.out.println(inputLine); 
						}
					br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
			response.close();
		} finally {
			httpclient.close();
		}

	}
	
	public static void testApi1() throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("http://localhost:8080/api/v1/digitalsign/upload");
            FileBody bin = new FileBody(new File("D:\\test.pdf"));
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("file", bin)
                    .addTextBody("name","manoj")
                    .build();
            httppost.setEntity(reqEntity);
            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            response.close();
        } finally {
            httpclient.close();
        }
	
	}

	
	public static void main(String[] args) throws IOException {
		//testApi();
		testApi1();
		//System.out.print("test");
	}

}
	

