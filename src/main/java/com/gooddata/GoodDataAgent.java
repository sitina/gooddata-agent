package com.gooddata;


import com.gooddata.agent.api.GdcRESTApiWrapper;
import org.apache.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoodDataAgent extends GoodData {

    public GoodDataAgent(String hostname, String login, String password, int port) {
        this(hostname, login, password, port, "https", new GoodDataSettings());
    }

    protected GoodDataAgent(String hostname, String login, String password, int port, String protocol, GoodDataSettings settings) {
        super(hostname, login, password, port, protocol, settings);
        RestTemplate restTemplate = super.getRestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new HeaderSettingRequestInterceptor(Collections.singletonMap(HttpHeaders.USER_AGENT, GdcRESTApiWrapper.getUserAgent())));
        interceptors.addAll(restTemplate.getInterceptors());
        restTemplate.setInterceptors(interceptors);
    }
}
