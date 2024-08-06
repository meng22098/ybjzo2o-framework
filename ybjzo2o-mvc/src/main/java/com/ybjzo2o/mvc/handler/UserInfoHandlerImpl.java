package com.ybjzo2o.mvc.handler;

import com.ybjzo2o.common.handler.UserInfoHandler;
import com.ybjzo2o.common.model.CurrentUserInfo;
import com.ybjzo2o.mvc.utils.UserContext;
import org.springframework.stereotype.Component;

@Component
public class UserInfoHandlerImpl implements UserInfoHandler {
    @Override
    public CurrentUserInfo currentUserInfo() {
        return UserContext.currentUser();
    }
}
