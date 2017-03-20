package org.sagesource.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>Redis 缓存工具类</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class RedisCacheUtil {
	private static StringRedisTemplate           stringRedisTemplate;
	private static RedisTemplate<String, Object> redisTemplate;

	// 静态代码块 保证初始化的线程安全
	static {
		stringRedisTemplate = SpringContextHolder.getBean("stringRedisTemplate");
		redisTemplate = SpringContextHolder.getBean("redisTemplate");

		if (stringRedisTemplate == null || redisTemplate == null) {
			System.out.println(">>>>Redis操作Template为空,请检查applicationContext-redis.xml配置<<<<");
		}
	}

	/**
	 * 删除缓存
	 *
	 * @param key 1个或多个待删除的key
	 */
	public static void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(Arrays.asList(key));
			}
		}
	}

	/**
	 * 批量删除<br>
	 * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
	 *
	 * @param pattern key值表达式
	 */
	public static void batchDel(String... pattern) {
		for (String kp : pattern) {
			redisTemplate.delete(redisTemplate.keys(kp + "*"));
		}
	}

	/**
	 * 取得缓存（int型）
	 *
	 * @param key 缓存的key值
	 * @return
	 */
	public static Integer getInt(String key) {
		String value = stringRedisTemplate.boundValueOps(key).get();
		if (!Strings.isNullOrEmpty(value)) {
			return Integer.valueOf(value);
		}
		return null;
	}

	/**
	 * 取得缓存（字符串类型）
	 *
	 * @param key 缓存的key值
	 * @return 缓存结果
	 */
	public static String getStr(String key) {
		return stringRedisTemplate.boundValueOps(key).get();
	}

	/**
	 * 取得缓存（字符串类型）
	 *
	 * @param key    缓存的key值
	 * @param retain true:保留 false:获取后删除
	 * @return
	 */
	public static String getStr(String key, boolean retain) {
		String value = stringRedisTemplate.boundValueOps(key).get();
		if (!retain) {
			redisTemplate.delete(key);
		}
		return value;
	}

	/**
	 * 获取缓存<br>
	 * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
	 *
	 * @param key 缓存的key值
	 * @return
	 */
	public static Object getObj(String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 获取缓存<br>
	 * 注：java 8种基本类型的数据请直接使用get(String key, Class<T> clazz)取值
	 *
	 * @param key    缓存的key值
	 * @param retain true:保留 false:获取后删除
	 * @return
	 */
	public static Object getObj(String key, boolean retain) {
		Object obj = redisTemplate.boundValueOps(key).get();
		if (!retain) {
			redisTemplate.delete(key);
		}
		return obj;
	}

	/**
	 * 获取缓存<br>
	 * 注：该方法暂不支持Character数据类型
	 *
	 * @param key   缓存的key值
	 * @param clazz 类型
	 * @return
	 */
	public static <T> T get(String key, Class<T> clazz) {
		return (T) redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 获取缓存json对象<br>
	 *
	 * @param key   缓存的key值
	 * @param clazz 类型
	 * @return
	 */
	public static <T> T getJson(String key, Class<T> clazz) {
		String val = stringRedisTemplate.boundValueOps(key).get();
		return JSON.parseObject(val, clazz);
	}

	/**
	 * 将value对象写入缓存
	 *
	 * @param key     缓存的key值
	 * @param value   待写入对象
	 * @param timeout 失效时间(秒)
	 */
	public static void set(String key, Object value, long timeout) {
		if (value.getClass().equals(String.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Integer.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Double.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Float.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Short.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Long.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Boolean.class)) {
			stringRedisTemplate.opsForValue().set(key, value.toString());
		} else {
			redisTemplate.opsForValue().set(key, value);
		}
		if (timeout > 0L) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 将value对象以JSON格式写入缓存
	 *
	 * @param key     缓存的key值
	 * @param value   json格式的value值
	 * @param timeout 失效时间(秒)
	 */
	public static void setJson(String key, Object value, long timeout) {
		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
		if (timeout > 0L) {
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 更新key对象field的值
	 *
	 * @param key   缓存的key值
	 * @param field 缓存对象field
	 * @param value 缓存对象field值
	 */
	public static void setJsonField(String key, String field, String value) {
		JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
		obj.put(field, value);
		stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
	}

	/**
	 * 递减操作
	 *
	 * @param key 缓存的key值
	 * @param by  递减步数
	 * @return
	 */
	public static double decr(String key, double by) {
		return redisTemplate.opsForValue().increment(key, -by);
	}

	/**
	 * 递增操作
	 *
	 * @param key 缓存的key值
	 * @param by  递增步数
	 * @return
	 */
	public static double incr(String key, double by) {
		return redisTemplate.opsForValue().increment(key, by);
	}

	/**
	 * 获取double类型值
	 *
	 * @param key 缓存的key值
	 * @return
	 */
	public static double getDouble(String key) {
		String value = stringRedisTemplate.boundValueOps(key).get();
		if (!Strings.isNullOrEmpty(value)) {
			return Double.valueOf(value);
		}
		return 0d;
	}

	/**
	 * 设置double类型值
	 *
	 * @param key     缓存的key值
	 * @param value   double类型的值
	 * @param timeout 失效时间(秒)
	 */
	public static void setDouble(String key, double value, long timeout) {
		stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
		if (timeout > 0L) {
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 设置double类型值
	 *
	 * @param key     缓存key
	 * @param value   double类型的值
	 * @param timeout 失效时间(秒)
	 */
	public static void setInt(String key, double value, long timeout) {
		stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
		if (timeout > 0L) {
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 将map写入缓存
	 *
	 * @param key     缓存的key值
	 * @param value   map类型的value
	 * @param timeout 失效时间(秒)
	 */
	public static <T> void setMap(String key, Map<String, T> value, long timeout) {
		redisTemplate.opsForHash().putAll(key, value);
		if (timeout > 0L) {
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 向key对应的map中添加缓存对象
	 *
	 * @param key   缓存的key值
	 * @param field map对应的key
	 * @param value 值
	 */
	public static void addMap(String key, String field, String value) {
		redisTemplate.opsForHash().put(key, field, value);
	}

	/**
	 * 向key对应的map中添加缓存对象
	 *
	 * @param key   缓存的key值
	 * @param field map对应的key
	 * @param obj   对象
	 */
	public static <T> void addMap(String key, String field, T obj) {
		redisTemplate.opsForHash().put(key, field, obj);
	}

	/**
	 * 获取map缓存
	 *
	 * @param key   缓存的key值
	 * @param clazz map对象value的类型
	 * @return
	 */
	public static <T> Map<String, T> mget(String key, Class<T> clazz) {
		BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
		return boundHashOperations.entries();
	}

	/**
	 * 获取map缓存中的某个对象
	 *
	 * @param key   缓存的key值
	 * @param field map的key值
	 * @param clazz 对象的类型
	 * @return
	 */
	public static <T> T getMapField(String key, String field, Class<T> clazz) {
		return (T) redisTemplate.boundHashOps(key).get(field);
	}

	/**
	 * 删除map中的某个对象
	 *
	 * @param key   缓存的key值
	 * @param field map中该对象的key
	 */
	public void delMapField(String key, String... field) {
		BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
		boundHashOperations.delete(field);
	}

	/**
	 * 指定缓存的失效时间
	 *
	 * @param key     缓存的key值
	 * @param timeout 失效时间(秒)
	 */
	public static void expire(String key, long timeout) {
		if (timeout > 0L) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 添加set
	 *
	 * @param key   缓存的key值
	 * @param value 值列表
	 */
	public static void sadd(String key, long timeout, String... value) {
		redisTemplate.boundSetOps(key).add(value);
		if (timeout > 0L) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 删除set集合中的对象
	 *
	 * @param key   缓存的key值
	 * @param value 待删除的值列表
	 */
	public static void srem(String key, String... value) {
		redisTemplate.boundSetOps(key).remove(value);
	}

	/**
	 * set重命名
	 *
	 * @param oldkey 旧的缓存的key值
	 * @param newkey 新的缓存的key值
	 */
	public static void srename(String oldkey, String newkey) {
		redisTemplate.boundSetOps(oldkey).rename(newkey);
	}

	/**
	 * 模糊查询keys
	 *
	 * @param pattern
	 * @return
	 */
	public static Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}
}
