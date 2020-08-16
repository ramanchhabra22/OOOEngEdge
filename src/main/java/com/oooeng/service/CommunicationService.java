package com.oooeng.service;

import com.oooeng.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public interface CommunicationService {

    public void sendNotification(String title, String message, String fcmId, String image, String Url) throws Exception;

    public Response sendNotification(JSONObject dataObject, JSONArray fcmIds) throws Exception;
}
