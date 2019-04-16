package edu.ua.culverhouse.mis.bookstore;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

class BookFile {
    public static List<Book> GetAllBooks(String cwid) throws Exception {
        String url = "http://159.89.235.64:3000/books/" + cwid;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response);
        List<Book> myBooks = Converter.fromJsonString(response.toString());

        return myBooks;
    }

    public static void SaveBook(Book myBook, String cwid, String mode) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();

        String json = Converter.toJsonString(myBook);
        StringEntity entity = new StringEntity(json);

        if (mode == "edit") {
            HttpPut httpPut = new HttpPut("http://159.89.235.64:3000/books/" + cwid + "/" + myBook.getId());
            httpPut.setEntity(entity);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPut);
            client.close();
        } else {
            HttpPost httpPost = new HttpPost("http://159.89.235.64:3000/books/" + cwid);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            client.close();
        }

    }

}
