package com.oooeng.service;

import com.oooeng.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CommunicationService {

    public void sendNotification(String title, String message, String accessToken, String channelName, String image, String url, int platform, String notificationType, JSONArray fcmIds) throws Exception;

    public Response sendNotification(JSONObject dataObject, JSONArray fcmIds) throws Exception;
}
