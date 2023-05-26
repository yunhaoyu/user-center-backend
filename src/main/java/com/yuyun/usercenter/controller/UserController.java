package com.yuyun.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuyun.usercenter.common.BaseResponse;
import com.yuyun.usercenter.common.ErrorCode;
import com.yuyun.usercenter.common.ResultUtils;
import com.yuyun.usercenter.exception.BusinessException;
import com.yuyun.usercenter.model.domain.User;
import com.yuyun.usercenter.model.domain.request.*;
import com.yuyun.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.yuyun.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.yuyun.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 *
 * @author yuyun
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://192.168.45.129"})
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            // return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
//        return new BaseResponse<>(0, result, "ok");
        return ResultUtils.success(result);
    }


    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
//        return new BaseResponse<>(0, user, "ok");
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);

    }


    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);


    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

       /* //仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null || user.getRole() != ADMIN_ROLE) {
            return new ArrayList<>();
        }*/
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser( @RequestBody UserDeleteRequest deleteRequest,HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH, "您没有权限");
        }
        boolean result = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(result);
    }

        @PostMapping("/add")
        public BaseResponse<Integer> addUser (@RequestBody UserAddRequest addUser, HttpServletRequest request){
            if (!isAdmin(request)) {
                throw new BusinessException(ErrorCode.NO_AUTH, "您没有权限");
            }
            Integer a = userService.addUser(addUser);
            return ResultUtils.success(a);
        }

    @PostMapping("/updateUser")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest updateUser, HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH,"您没有该权限");
        }
        User user = new User();
        BeanUtils.copyProperties(updateUser,user);
        boolean result = userService.updateById(user);
        if (result==false){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"更新失败");
        }
        return ResultUtils.success(result);
    }

        /**
         * 是否为管理员
         *
         * @param request
         * @return
         */
        private boolean isAdmin (HttpServletRequest request){
            //仅管理员可查询
            Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
            User user = (User) userObj;
            return user != null && user.getUserRole() == ADMIN_ROLE;

        }
    }
