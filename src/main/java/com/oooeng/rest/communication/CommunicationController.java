package com.oooeng.rest.communication;


import com.google.gson.Gson;
import com.oooeng.response.Response;
import com.oooeng.service.CommunicationService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.oooeng.constants.PushNotificationConstants.*;

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
    public void sendAgoraNotification(@RequestBody JSONObject jsonBody, @RequestHeader String platform) throws Exception {
        ArrayList list = (ArrayList) jsonBody.get("fcmIds");
        JSONArray fcmIds = new JSONArray();
        int length = list.size();
        for (int i = 0; i < length; i++) {
            fcmIds.add(list.get(i));
        }

        String accessToken = (String) jsonBody.get("accessToken");
        String channelName = (String) jsonBody.get("channelName");
        String type = (String) jsonBody.get("type");


        service.sendNotification(TITLE, MESSAGE, accessToken, channelName, IMAGE, ACTION_CALL_URL, Integer.parseInt(platform), type, fcmIds);
    }

}
