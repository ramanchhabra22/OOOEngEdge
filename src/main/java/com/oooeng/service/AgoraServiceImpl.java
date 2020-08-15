package com.oooeng.service;

import com.oooeng.agora.RtcTokenBuilder;
import com.oooeng.constants.AgoraConstants;
import com.oooeng.exception.ResponseStatus;
import com.oooeng.response.Response;
import org.springframework.stereotype.Service;

import static com.oooeng.constants.AgoraConstants.APP_CERTIFICATE;
import static com.oooeng.constants.AgoraConstants.APP_ID;

@Service
public class AgoraServiceImpl implements AgoraService {

    @Override
    public Response generateToken(int uid, String channelName) {
        RtcTokenBuilder token = new RtcTokenBuilder();
        int timestamp = (int)(System.currentTimeMillis() / 1000 + AgoraConstants.expirationTimeInSeconds);
        String result = token.buildTokenWithUid(APP_ID, APP_CERTIFICATE,
                channelName, uid, RtcTokenBuilder.Role.Role_Publisher, timestamp);
        return  new Response(ResponseStatus.OK, result);
    }
}
