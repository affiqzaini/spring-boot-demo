package com.group.demo.utils;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class Helper {

    public String prepareUrl(String url, Map<String, Object> pathVariables, Map<String, Object> queryParams) {
        if (pathVariables != null && pathVariables.size() > 0) {
            Set<String> pathKeys = pathVariables.keySet();
            for (String key : pathKeys) {
                url.replace("{" + key + "}", pathVariables.get(key).toString());
            }
        }

        if (queryParams != null && queryParams.size() > 0) {
            Set<String> queryKeys = queryParams.keySet();
            url = url.concat("?");
            for (String key : queryKeys) {
                url = url.concat(key).concat("=").concat(queryParams.get(key).toString()).concat("&");
            }
        }
        return url;
    }

}
