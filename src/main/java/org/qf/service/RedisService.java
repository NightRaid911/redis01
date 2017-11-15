package org.qf.service;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Thanks for Everything.
 */
public class RedisService {

	public void client1(){
		//创建链接
		Jedis jedis = new Jedis("10.0.127.138",6382);
		//设置值
		jedis.set("java1712","萌萌哒");
		//取值
		String java1712 = jedis.get("java1712");
		System.out.println(java1712);
		//关闭
		jedis.close();
	}

	public void client2(){
		//创建连接池
		JedisPool jedisPool = new JedisPool("10.0.127.138",6381);
		//找连接池要具体的对象
		Jedis resource = jedisPool.getResource();
		//操作
		resource.set("java17","java");
		String java17 = resource.get("java17");
		System.out.println(java17);
		resource.close();
	}

	public void client3(){
		//集群参数
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		set.add(new HostAndPort("10.0.127.138",6381));
		set.add(new HostAndPort("10.0.127.138",6382));
		set.add(new HostAndPort("10.0.127.138",6383));
		set.add(new HostAndPort("10.0.127.138",6384));
		set.add(new HostAndPort("10.0.127.138",6385));
		set.add(new HostAndPort("10.0.127.138",6386));
		//  创建集群对象:集群自带连接池
		JedisCluster jedisCluster = new JedisCluster(set,10000,10000);
		//集群设置值
		jedisCluster.set("java1713","美好的一天从感恩开始!");
		//集群取值
		String java1713 = jedisCluster.get("java1713");//ctrl+alt+v
		System.out.println(java1713);

		//系统退出
		jedisCluster.close();
	}

	public static void main(String[] args) {
		RedisService redisService = new RedisService();
//		redisService.client1();
//		redisService.client2();
		redisService.client3();
	}

}
