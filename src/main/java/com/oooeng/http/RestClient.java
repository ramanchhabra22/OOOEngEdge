package com.oooeng.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;


public class RestClient {


    private static Logger logger = LoggerFactory.getLogger(RestClient.class);
    private HttpStatus status;


    public RestClient() {

    }

    public String get(String url, HttpHeaders headers) {
        RestTemplate rest = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @SuppressWarnings("unchecked")
    public Object getForObject(String url, HttpHeaders headers,Class cls) {
        RestTemplate rest = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<Object> responseEntity =  rest.exchange(url, HttpMethod.GET, requestEntity,cls);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();

    }


    public String post(String url, String json, HttpHeaders headers) {
        RestTemplate rest = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public String postForFormData(String url, MultiValueMap<String, String> map, HttpHeaders headers) {
        RestTemplate rest = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @SuppressWarnings("unchecked")
    public Object postForObject(String url, String json, HttpHeaders headers, Class cls) {
        RestTemplate rest = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        return rest.postForObject(url, requestEntity, cls);

    }

    public JSONObject postForPush(String url, String json, HttpHeaders headers) {
        try {
            RestTemplate rest = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
            ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, String.class);
            this.setStatus(responseEntity.getStatusCode());
            String response = responseEntity.getBody();
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("success") || jsonObject.has("failure")) {
                return jsonObject;
            }
        } catch (Exception e) {
            logger.error("Exception in postForPush", e);
        }
        return null;
    }

    public Integer postUsingBasicAuth(String url, MultiValueMap<String, String> json, HttpHeaders headers, String username, String password) {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(json, headers);
            ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, String.class);
            this.setStatus(responseEntity.getStatusCode());
            return responseEntity.getStatusCode().value();
        } catch (Exception e) {
            logger.error("Exception in postForPush", e);
        }
        return 0;
    }

    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}