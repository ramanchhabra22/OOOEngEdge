package com.oooeng.rest.communication;


import com.google.gson.Gson;
import com.oooeng.response.Response;
import com.oooeng.service.CommunicationService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    CommunicationService service;

    @RequestMapping(value = "/sendPush", method = RequestMethod.POST)
    public Response sendNotification(@RequestBody JSONObject jsonBody) throws Exception {
        JSONObject dataObject = new Gson().fromJson(jsonBody.get("data").toString(), JSONObject.class);
        ArrayList list = (ArrayList) jsonBody.get("fcmIds");
        JSONArray fcmIds = new JSONArray();
        int length = list.size();
        for (int i = 0; i < length; i++) {
            fcmIds.add(list.get(i));
        }
        return service.sendNotification(dataObject, fcmIds);
    }

    @RequestMapping(value = "/sendAgoraNotification", method = RequestMethod.POST)
    public void sendAgoraNotification(@RequestBody JSONObject jsonBody) throws Exception {
        ArrayList list = (ArrayList) jsonBody.get("fcmIds");
        JSONArray fcmIds = new JSONArray();
        int length = list.size();
        for (int i = 0; i < length; i++) {
            fcmIds.add(list.get(i));
        }
        String title = (String) jsonBody.get("title");
        String message = (String) jsonBody.get("message");
        String accessToken = (String) jsonBody.get("accessToken");
        String channelName = (String) jsonBody.get("channelName");
        String type = (String) jsonBody.get("type");
        String image = (String) jsonBody.get("image");
        String url = (String) jsonBody.get("url");
        int platform = 1;

        service.sendNotification(title, message, accessToken, channelName, image, url, platform, type, fcmIds);
    }

}
