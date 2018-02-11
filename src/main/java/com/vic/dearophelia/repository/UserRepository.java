package com.vic.dearophelia.repository;

import com.vic.dearophelia.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 *
 * @author vicdor
 * @create 2018-02-11 12:25
 */
@Repository
public class UserRepository {
    /**
     * 采用内存型的存储方式
     */
    private final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();

    /**
     * 保存用户对象
     *
     * @param user {@link User} 对象
     * @return 如果保存成功，返回<code>true</code>，
     * 否则返回<code>false</code>
     */
    public boolean save(User user) {
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id, user) == null;//put方法返回原来已存在key对应的value
    }

    public Collection<User> findAll() {
        return repository.values();
    }
}
