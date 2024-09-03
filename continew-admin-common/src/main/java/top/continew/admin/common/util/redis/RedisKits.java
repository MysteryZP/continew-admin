/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.continew.admin.common.util.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.continew.admin.common.util.spring.SpringBeansUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* Redis工具类
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/5/24 17:58
*/
public class RedisKits {

    private static StringRedisTemplate stringRedisTemplate = null;

    public static final Lock lock = new ReentrantLock();


    /** 获取RedisTemplate对象, 默认使用 StringRedisTemplate, 客户端可查询 **/
    private static final RedisTemplate getStringRedisTemplate(){
        if(stringRedisTemplate == null){

            if(SpringBeansUtil.getApplicationContext().containsBean("defaultStringRedisTemplate")){
                stringRedisTemplate = SpringBeansUtil.getBean("defaultStringRedisTemplate", StringRedisTemplate.class);
            }else{
                stringRedisTemplate = SpringBeansUtil.getBean(StringRedisTemplate.class);
            }
        }
        return stringRedisTemplate;
    }

    /** 设置分布式锁并设置重试机制 */
    public static boolean acquireLock(String key, String value, long expireTime, int maxRetries, long retryInterval) {
        int retries = 0;
        boolean locked = false;
        while (retries <= maxRetries) {
            if (lock.tryLock()) {
                try {
                    if (getStringRedisTemplate().opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS)) {
                        locked = true;
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
            retries++;
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return locked;
    }

}
