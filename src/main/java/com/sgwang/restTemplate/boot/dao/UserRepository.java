package com.sgwang.restTemplate.boot.dao;

import com.sgwang.restTemplate.boot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @创建人 sgwang
 * @name UserRepository
 * @user shiguang.wang
 * @创建时间 2019/7/8
 * @描述
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
