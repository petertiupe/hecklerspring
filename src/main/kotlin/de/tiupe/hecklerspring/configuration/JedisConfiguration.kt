package de.tiupe.hecklerspring.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Configuration
class JedisConfiguration {

    /*
    * To define the connection settings between the application client and the
    * Redis server instance, we need to use a Redis client.
    * There is a number of Redis client implementations available for Java.
    * In this tutorial, we'll use Jedis — a simple and powerful Redis client
    * implementation.
    * */

   @Bean
   fun jedisConnectionFactory(): JedisConnectionFactory {

        /*
        Hier wird gezeigt, wie man die Default-Eigenschaften ändern kann.
        * JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
            = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(6379);
        return jedisConFactory;
        }*/

        // In diesem Beispiel werden die Defaults der Jedis-Connection-Factory genutzt
       val jcFact = JedisConnectionFactory()
       return jcFact
     }

    /* Then we defined a RedisTemplate using the jedisConnectionFactory.
    *  This can be used for querying data with a custom repository.
    *
    * */

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }

}