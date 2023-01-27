package com.yunie.usercenter.mapper;

import com.yunie.usercenter.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.yunie.usercenter.model.domain.User
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}




