import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * jdk java.net包中请求http
 */
public class HttpSendCase {
    // 测试get请求的url
    public static final String url = "https://postman-echo.com/get";
    // 测试post请求的url
    public static final String postUrl = "https://postman-echo.com/post";

    public static void main(String[] args) {
        System.out.println("doGet() = " + doGet(url));
        System.out.println("doPost() = " + doPost(postUrl, "{\"method\": \"POST\"}"));
    }

    /**
     * @return
     */
    public static String doGet(String url) {
        //接口返回结果
        StringBuffer result = new StringBuffer();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        HttpURLConnection httpURLConnection = null;
        try {
            //step1:创建远程连接
            URL serverUrl = new URL(url);//使用统一资源定位符定位资源
            httpURLConnection = (HttpURLConnection) serverUrl.openConnection();//创建连接对象
            /**
             * URLConnection 是根据 URL 创建的，是用于访问 URL 所指向资源的通信链接，最主要的几个功能：
             * 1、通过URL类，调用openConnection创建连接对象
             * 2、设置参数(例如setDoInput setDoOutput)和一般请求属性(setRequestProperty)
             * 3、通过connect方法建立到远程对象的实际连接
             * 4、设置远程对象变为可用 远程对象的头字段和内容变为可访问
             * 该类API见https://docs.oracle.com/javase/8/docs/api/?xd_co_f=47c934d9-e663-4eba-819c-b726fc2d0847
             *
             * HttpURLConnection:URLConnection的子类,提供一些特定于 HTTP 协议的附加功能
             * 所以一般openConnection()后都会强转为该对象
             * 内含各种http状态码(例如java.net.HttpURLConnection#HTTP_OK)，写代码的时候可以用上，以规范代码
             */
            //step2:设置连接方式 HttpURLConnection默认是GET
            httpURLConnection.setRequestMethod("GET");
            //step3:设置参数
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setReadTimeout(10 * 1000);
            //step4:发起请求
            httpURLConnection.connect();
            //step5:获取请求数据
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("调用失败，连接状态码：" + responseCode);
            }
            //获取连接的输入流(字节流，操作的是byte[])
            inputStream = httpURLConnection.getInputStream();
            //InputStreamReader类是从字节流到字符流的桥接器：它使用指定的字符集读取字节并将它们解码为字符
            //https://blog.csdn.net/ai_bao_zi/article/details/81133476
            //BufferedReader类从字符输入流中读取文本并缓冲字符，以便有效地读取字符，数组和行
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            //逐行对相应结果进行读取
            if (bufferedReader != null) {
                String temp = null;
                while ((temp = bufferedReader.readLine()) != null) {
                    result.append(temp);
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("远程连接地址错误！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取错误！");
            e.printStackTrace();
        } finally {
            //step6:关闭连接,字节流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
        }
        return result.toString();
    }

    public static String doPost(String url, String param) {
        StringBuffer result = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        try {
            //step1:建立远程连接
            URL serverUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) serverUrl.openConnection();
            //step2:设置请求方法
            httpURLConnection.setRequestMethod("POST");
            //step3:设置请求参数
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setReadTimeout(10 * 1000);

            httpURLConnection.setDoOutput(true);//默认是false，因为POST请求需要传入参数，所以必须手动设置成true;
            httpURLConnection.setDoInput(true);//默认是true
            //step4:设置一般请求属性
            httpURLConnection.setRequestProperty("Content-Type", "application/json");//传送数据以什么样的形式发送
            httpURLConnection.setRequestProperty("Accept", "*/*");//浏览器可接收的类型

            //还可以设置权限，设置请求头(都使用setRequestProperty来设置)
            httpURLConnection.setRequestProperty("timestamp",String.valueOf(System.currentTimeMillis()));//设置请求头
            //Basic Auth HTTP授权的授权证书参数(这里简单介绍一下Basic Auth授权，
            //这个授权的验证方式是直接在请求头中加入一个请求头参数Authorization，其值格式为 Basic+空格+(用户名+英文分号+密码)的base64字符串，例子如下 )
            String info = "Lisa:Lisa123";
            httpURLConnection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(info.getBytes()));

            //step5:设置参数
            if (param != null && param.length() > 0) {
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(param.getBytes("UTF-8"));
                outputStream.flush();
            }

            //step6:发起请求
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            //step7:获取请求返回数据
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("调用失败，连接状态码：" + responseCode);
            }
            inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            if (bufferedReader != null) {
                String temp = bufferedReader.readLine();
                while (temp != null) {
                    result.append(temp);
                    temp = bufferedReader.readLine();
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("远程连接地址错误！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取错误！");
            e.printStackTrace();
        } finally {
            //step8:关闭连接
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
        }
        return result.toString();
    }
}

