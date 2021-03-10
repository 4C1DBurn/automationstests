package libs.Utilites;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Utilities {

    public static String fixFilePath(String path) {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            if (path.contains("/"))
                path = path.replace("/", "\\");
        } else {
            if (path.contains("\\"))
                path = path.replace("\\", "/");
        }
        return path;
    }

    private static InputStream getFileFromURLByGetRequest(String fileURL) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(fileURL);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        httpGet.addHeader("Referer", "https://www.google.com");

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity fileEntity = httpResponse.getEntity();

        return fileEntity.getContent();
    }

    public static File saveFile(String fileURL, String fileSavePath) throws IOException {

        FileUtils.copyInputStreamToFile(getFileFromURLByGetRequest(fileURL), new File(fileSavePath));

        return new File(fileSavePath);

    }

    public static void deleteFile(String path) {
        try {
            FileUtils.cleanDirectory(new File(path));
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

}

