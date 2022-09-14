package impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.AiAppIdentify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisImpl{
    JedisPoolConfig config = new JedisPoolConfig();
    JedisPool pool;
    public RedisImpl(){
        config.setMaxIdle(3);
        config.setMaxTotal(5);
        pool = new JedisPool(config, "ys-ai-k8s-new.redis.rds.aliyuncs.com", 6379, 2000, "j1fAH7QpKCS7kufZ");
    }

    public void close(){
        pool.close();
    }

    public String getValue(String appId){
        Jedis jedis = pool.getResource();
        String value = jedis.get("access:AppIdentify:" + appId);
        jedis.close();
        return value;
    }

    public String getAppName(String appId){
        Jedis jedis = pool.getResource();
        String value = jedis.get("access:AppIdentify:" + appId);
        JSONObject js = JSON.parseObject(value);
        jedis.close();
        return js.getString("appName");
    }

    public Long delKeys(String[] keys){
        Jedis jedis = pool.getResource();
        Long num = jedis.del(keys);
        jedis.close();
        System.out.println("del: " + num + "条数据");
        return num;
    }


}
