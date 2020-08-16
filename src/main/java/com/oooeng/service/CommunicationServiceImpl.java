package com.oooeng.service;

import com.oooeng.constants.PushNotificationConstants;
import com.oooeng.exception.ResponseStatus;
import com.oooeng.http.RestClient;
import com.oooeng.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class CommunicationServiceImpl implements CommunicationService {

    @Value("${firebase.android.auth.key}")
    private String firebaseAuthKey;

    public void sendNotification(String title, String message, String fcmId, String image, String Url) throws Exception {
        RestClient restClient = new RestClient();

        JSONObject pushNotificationBody = new JSONObject();
        if (!fcmId.isEmpty()) {
            JSONObject dataObject = new JSONObject();
            dataObject.put("id", "1494324029544");
            dataObject.put("title", title);
            dataObject.put("big_picture_url", image);
            dataObject.put("long_message", message);
            dataObject.put("type", "external_url");
            if (!"".equals(Url)) {
                dataObject.put("url", Url);
            }
            dataObject.put("notification_type", "consult");
            pushNotificationBody.put("data", dataObject);
            pushNotificationBody.put("time_to_live", 2000);
            JSONArray registrationId = new JSONArray();
            registrationId.put(fcmId);
            pushNotificationBody.put("registration_ids", registrationId);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "key=" + firebaseAuthKey);
        String res = restClient.post(PushNotificationConstants.URL, pushNotificationBody.toString(), headers);

    }

    public Response sendNotification(JSONObject dataObject, JSONArray fcmIds ) throws Exception {
        RestClient restClient = new RestClient();

        JSONObject pushNotificationBody = new JSONObject();
        if (!fcmIds.isEmpty()) {
            dataObject.put("notification_type", "consult");
            pushNotificationBody.put("data", dataObject);
            pushNotificationBody.put("time_to_live", 2000);
            pushNotificationBody.put("registration_ids", fcmIds);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "key=" + firebaseAuthKey);
        String res = restClient.post(PushNotificationConstants.URL, pushNotificationBody.toString(), headers);
        return new Response(ResponseStatus.OK,res);

    }
}
