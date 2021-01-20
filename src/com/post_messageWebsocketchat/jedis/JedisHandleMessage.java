package com.post_messageWebsocketchat.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String admin_id, Integer target_type) {
		String key = new StringBuilder(admin_id).append(":").append(target_type).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String admin_id, Integer target_type, String post_content) {
		
		String senderKey = new StringBuilder(admin_id).append(":").append(target_type).toString();
		String receiverKey = new StringBuilder(target_type).append(":").append(admin_id).toString();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.rpush(senderKey, post_content);
		jedis.rpush(receiverKey, post_content);

		jedis.close();
	}

}
