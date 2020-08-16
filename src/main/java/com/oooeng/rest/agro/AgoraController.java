package com.oooeng.rest.agro;


import com.oooeng.exception.ResponseStatus;
import com.oooeng.response.Response;
import com.oooeng.service.AgoraService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agro")
public class AgoraController {

    @Autowired
    private AgoraService agoraService;

    @RequestMapping(value = "/generateToken", method = RequestMethod.POST)
    public Response generateToken(@RequestBody JSONObject jsonBody) {
        if (jsonBody.containsKey("phoneNumber") && jsonBody.containsKey("uid")) {
            int uid = (int) jsonBody.get("uid");
            String channelName = (String) jsonBody.get("channelName");
            return agoraService.generateToken(uid, channelName);
        }
        return new Response(ResponseStatus.INVALID_REQUEST, "PHONE NUMBER IS MANDATORY");
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Response testMethod(){
        return new Response(ResponseStatus.OK,"Hey");
    }


}
