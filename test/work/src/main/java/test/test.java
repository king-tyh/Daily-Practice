package test;

import impl.RedisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import tool.CSVReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader();
        List<Map<String,String>> datas;
        String[] fields = {"appId"};
        datas = reader.readData("work/src/main/resources/ai_app_identify_appId.csv",fields);
        List<String> keys = new LinkedList<>();
/*
        RedisImpl redis = new RedisImpl();
        for(Map<String,String> map:datas){
            for(String s: map.values()){
                String result = redis.getValue(s);
                if(result!=null)
                    System.out.println(result);
            }
        }
*/

/*        RedisImpl redis = new RedisImpl();
        redis.delKeys(keys.toArray(new String[keys.size()]));
        redis.close();*/
    }
}
