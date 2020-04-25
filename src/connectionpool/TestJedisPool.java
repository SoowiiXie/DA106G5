package connectionpool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedisPool {
	private static JedisPool pool = null;

	public static void main(String[] args) {
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		jedis.auth("DA106G5");
		System.out.println(jedis.ping());
		jedis.set("abc","123");
		jedis.set("pwd","456");
		jedis.hset("whe01","tem","{maxT:456 ,minT:-456}");
		jedis.hset("whe01","lowtem","6");
		jedis.hset("whe01","howtem","6999");

		jedis.expire("abc", 10);
		System.out.println(jedis.get("abc"));
		System.out.println(jedis.hget("whe01","howtem"));
		System.out.println(jedis.hget("whe01","lowtem"));
		jedis.close();
		JedisUtil.shutdownJedisPool();
	}

}
