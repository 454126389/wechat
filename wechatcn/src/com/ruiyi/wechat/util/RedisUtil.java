/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ruiyi.wechat.util;

import java.util.List;
import java.util.Map;
import java.util.Set;


import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 *
 * @author Administrator
 */
public class RedisUtil {
    
    public static JedisPool pool;
    
    static {
        //?????
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(20);
        config.setMaxIdle(5);
        config.setMaxWait(1000l);
        config.setTestOnBorrow(false);
        pool= new JedisPool(config,"192.168.2.105", 6379);
    }

    /**
     * ??Redis
     */
    public static void destory() {
        pool.destroy();
    }

    /**
     * redis?List????key??list????
     *
     * @param key List??
     * @param string ??
     * @return
     */
    public static long rpush(String key, String string) {
        long r = 0;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            r = jedis.rpush(key, string);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
        return r;
    }

    /**
     * ??key??List????????????? LRANGE key start
     * stop????key????????????????start?stop???
     * ??(index)??start?stop??0?????????0????????????1????????????????
     * ???????????-1????????????-2??????????????????
     *
     * @param key List??
     * @param start ????
     * @param end ????
     * @return
     */
    public static List<String> lrange(String key, long start, long end) {
        List list = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            list = jedis.lrange(key, start, end);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
        return list;
    }

    /**
     * ????key??field????value.
     *
     * @param key ?????
     * @param field ?
     * @param value ?
     */
    public static void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
    }
    
    /**
     * ????key??field????value.
     *
     * @param key ?????
     * @param field ?
     * @param value ?
     */
    public static void hdel(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hdel(key, field);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
    }
    

    /**
     * ?key??
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
    }
    /**
     * ??key??
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
        return value;
    }
    
    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(key);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
            }
        }
    }
    
    /**
     * ???field-value??-?????????key??
     * @parma key
     * @parma map
     */
    public static void hmset(String key,Map<String,String> map){
         Jedis jedis = null;
         try{
           jedis=pool.getResource();
           jedis.hmset(key, map);
         }catch(Exception e){
         }finally{
           if(jedis!=null)
               pool.returnResource(jedis);
         }
    }
    
    /**
     * ?key???????????seconds
     * @param key
     * @param seconds ????????
     * @param value
     */
    public static void setex(String key,int seconds,String value){
        Jedis jedis = null;
        try{
            jedis=pool.getResource();
            jedis.setex(key, seconds, value);
        }catch(Exception e){
        }finally{
            if(jedis!=null)
                pool.returnResource(jedis);
        }
    }
    
    /**
     * ????key??????
     * @param key
     * @param seconds ???? ????
     */
    public static void expire(String key,int seconds){
        Jedis jedis = null;
        try{
            jedis=pool.getResource();
            jedis.expire(key, seconds);
        }catch(Exception e){
        }finally{
            if(jedis!=null)
                pool.returnResource(jedis);
        }
    }
    
    /**
     * ??key????
     * @param key
     * @return
     */
    public static boolean exists(String key){
     boolean falg=false;
     Jedis jedis = null;
     try{
       jedis=pool.getResource();
       falg=jedis.exists(key);
     }catch(Exception e){
         
     }finally{
        if(jedis!=null)
          pool.returnResource(jedis);
     }
     return falg;
    }
    
    /**
     * ??key???? none(key????,string(???),list(??),set(??),zset(????),
     * hash(???)
     * @param key
     * @return
     */
    public static String type(String key){
      String type="none";
      Jedis jedis = null;
      try{
       jedis=pool.getResource();
       type=jedis.type(key);
      }catch(Exception e){
       
      }finally{
          if(jedis!=null)
             pool.returnResource(jedis);
      }
      return type;
    }
    
    /**
     * ????key???field?value
     * @param key
     * @param field
     */
    public static String hget(String key,String field){
        String value=null;
        Jedis jedis = null;
        try{
          jedis=pool.getResource();
          value=jedis.hget(key, field);
        }catch(Exception e){
           
        }finally{
          if(jedis!=null)
             pool.returnResource(jedis);
        }
        return value;
    }
    
    /**
     * ?????key????????
     * @param key
     * @return
     */
    public static Map<String,String> hgetAll(String key){
       Map<String,String> map=null;
       Jedis jedis = null;
       try{
        jedis=pool.getResource();
        map=jedis.hgetAll(key);
       }catch(Exception e){
        
       }finally{
         if(jedis!=null)
             pool.returnResource(jedis);
       }
       return map;
    }
    
    /**
     * ??Set key????????
     * @param key
     * @return
     */
    public static Set<?> smembers(String key){
       Set<?> set=null;
       Jedis jedis = null;
       try{
           jedis=pool.getResource();
           set=jedis.smembers(key);
       }catch(Exception e){
           
       }finally{
           if(jedis!=null)
               pool.returnResource(jedis);
       }
       return set;
    }
    
    /**
     * ??????member??
     * @param key List,MAP??
     * @param field ?
     */
    public static void delSetObj(String key,String field){
       Jedis jedis = null;
       try{
          jedis=pool.getResource();
          jedis.srem(key, field);
       }catch(Exception e){
       }finally{
         if(jedis!=null)
             pool.returnResource(jedis);
       }       
    }
    
    /**
     * ??member???????key??????true?,???false?
     * @param key
     * @param field
     * @return
     */
    public static boolean isNotField(String key,String field){
      boolean flag=false;
      Jedis jedis = null;
      try{
        jedis=pool.getResource();
        flag=jedis.sismember(key, field);
      }catch(Exception e){
      }finally{
          if(jedis!=null)
             pool.returnResource(jedis);
      }
      return flag;
    }
    
    /**
     * ??key??????????????value???key??????
     * @param key
     * @param value
     */
    public void append(String key,String value){
     Jedis jedis = null;
     try{
       jedis=pool.getResource();
       jedis.append(key, value);
     }catch(Exception e){
     }finally{
      if(jedis!=null)
          pool.returnResource(jedis);
     }
    }
    
    public void sadd(String key,String value){
         Jedis jedis = null;
     try{
       jedis=pool.getResource();
       jedis.sadd(key, value);
     }catch(Exception e){
      
     }finally{
      if(jedis!=null)
          pool.returnResource(jedis);
     }
    }
    
	public static void publish(String channel, String message) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.publish(channel, message);
		} catch (Exception e) {
		} finally {
			if (jedis != null) {
				pool.returnResourceObject(jedis);
			}
		}
	}
	
    
     
}
