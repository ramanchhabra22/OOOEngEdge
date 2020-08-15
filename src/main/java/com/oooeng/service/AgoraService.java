package com.oooeng.service;

import com.oooeng.response.Response;

public interface AgoraService {

    Response generateToken(int uid, String channelName);
}
