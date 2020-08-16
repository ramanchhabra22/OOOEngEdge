package com.oooeng.rest.communication;


import com.oooeng.response.Response;
import com.oooeng.service.CommunicationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    CommunicationService service;

    @RequestMapping(value = "/sendPush", method = RequestMethod.POST)
    public Response sendNotification(JSONObject dataObject, JSONArray fcmIdArray) throws Exception {
        return service.sendNotification(dataObject, fcmIdArray);
    }

}
