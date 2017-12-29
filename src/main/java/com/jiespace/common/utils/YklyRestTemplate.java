package com.jiespace.common.utils;

import com.google.common.base.Stopwatch;
import com.jiespace.common.logs.FightOpty;
import com.jiespace.common.logs.OgLog;
import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

/**
 * Created by huangmingjie on 2017/10/17.
 */
@Component
public class YklyRestTemplate {
    private static final Log logger = LogFactory.getLog(YklyRestTemplate.class);
    private static final Logger ogLog = LoggerFactory.getLogger("bis_outgoing_log");
    private final String DEF_ENCODING_UTF_8 = "UTF-8";
    @Autowired
    @Qualifier("lRestTemplate")
    private RestTemplate lRestTemplate;
    @Autowired
    @Qualifier("sRestTemplate")
    private RestTemplate sRestTemplate;
    /*@Autowired
    @Qualifier("asyncRestTemplate")
    private AsyncRestTemplate asyncRestTemplate;
    public void sendRequest(String endpoint, String body) {
        HttpEntity<String> entity = setRequest(body);
        ListenableFuture<ResponseEntity<String>> futureEntity = asyncRestTemplate.exchange(
                endpoint, HttpMethod.POST, entity,
                String.class);
        futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity>() {
            @Override
            public void onSuccess(ResponseEntity result) {
                int statusCode = result.getStatusCode().value();
            }
            
            @Override
            public void onFailure(Throwable t) {
                int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                if (t instanceof IOException) {
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE.value();
                } else if (t instanceof HttpStatusCodeException) {
                    HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) t;
                    statusCode = httpStatusCodeException.getStatusCode().value();
                }
            }
        });
    }
    
    private HttpEntity<String> setRequest(String body) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<String>(body,
                requestHeaders);
    }

    public String asyncsget(String url, Map<String, String> headerMap, Map<String, String> reqMap,
                            FightOpty fightOpty){
        final String response = StringUtils.EMPTY;
        ListenableFuture<ResponseEntity<String>> listenableFuture =  asyncRestTemplate.getForEntity(url, String.class);
        listenableFuture.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable ex) {
        
            }
    
            @Override
            public void onSuccess(ResponseEntity<String> result) {
            }
        });
        return response;
    }*/
    
    public String lGet(String url, Map<String, String> headerMap, Map<String, String> reqMap,
                       FightOpty fightOpty) {
        ResponseEntity<byte[]> response =
                httpGet(url, headerMap, reqMap, byte[].class, lRestTemplate, fightOpty);
        return getHttpResponseContent(response);
    }
    
    public ResponseEntity<byte[]> lGet(String url, Map<String, String> headerMap,
                                       Map<String, String> reqMap, Class<byte[]> responseType, FightOpty fightOpty) {
        return httpGet(url, headerMap, reqMap, responseType, lRestTemplate, fightOpty);
        
    }
    
    public String lPost(String url, Map<String, String> headerMap, Map<String, String> reqMap,
                        FightOpty fightOpty) {
        ResponseEntity<byte[]> response =
                httpPost(url, headerMap, reqMap, byte[].class, lRestTemplate, fightOpty);
        return getHttpResponseContent(response);
    }
    
    public ResponseEntity<byte[]> lPost(String url, Map<String, String> headerMap,
                                        Map<String, String> reqMap, Class<byte[]> responseType, FightOpty fightOpty) {
        return httpPost(url, headerMap, reqMap, responseType, lRestTemplate, fightOpty);
    }
    
    public String sGet(String url, Map<String, String> headerMap, Map<String, String> reqMap,
                       FightOpty fightOpty) {
        ResponseEntity<byte[]> response =
                httpGet(url, headerMap, reqMap, byte[].class, sRestTemplate, fightOpty);
        return getHttpResponseContent(response);
    }
    
    public ResponseEntity<byte[]> sGet(String url, Map<String, String> headerMap,
                                       Map<String, String> reqMap, Class<byte[]> responseType, FightOpty fightOpty) {
        return httpGet(url, headerMap, reqMap, responseType, sRestTemplate, fightOpty);
    }
    
    public String sPost(String url, Map<String, String> headerMap, Map<String, String> reqMap,
                        FightOpty fightOpty) {
        ResponseEntity<byte[]> response =
                httpPost(url, headerMap, reqMap, byte[].class, sRestTemplate, fightOpty);
        return getHttpResponseContent(response);
        
    }
    
    public ResponseEntity<byte[]> sPost(String url, Map<String, String> headerMap,
                                        Map<String, String> reqMap, Class<byte[]> responseType, FightOpty fightOpty) {
        return httpPost(url, headerMap, reqMap, responseType, sRestTemplate, fightOpty);
    }
    
    public String sPostBody(String url, Map<String, String> headerMap, String body,
                            FightOpty fightOpty) {
        ResponseEntity<byte[]> response =
                httpPostBody(url, headerMap, body, byte[].class, sRestTemplate, fightOpty);
        return getHttpResponseContent(response);
    }
    
    public String lPostBody(String url, Map<String, String> headerMap, String body,
                            FightOpty fightOpty) {
        ResponseEntity<byte[]> response =
                httpPostBody(url, headerMap, body, byte[].class, lRestTemplate, fightOpty);
        return getHttpResponseContent(response);
    }
    
    public String lPostBody(HttpServletRequest request, String url, Map<String, String> headerMap, String body,
                            FightOpty fightOpty) {
        ResponseEntity<byte[]> response =
                httpPostBody(request, url, headerMap, body, byte[].class, lRestTemplate, fightOpty);
        return getHttpResponseContent(response);
    }
    
    public ResponseEntity<byte[]> sPost(String url, Map<String, String> headerMap, String body,
                                        Class<byte[]> responseType, FightOpty fightOpty) {
        return httpPostBody(url, headerMap, body, responseType, sRestTemplate, fightOpty);
    }
    
    public ResponseEntity<byte[]> lPost(String url, Map<String, String> headerMap, String body,
                                        Class<byte[]> responseType, FightOpty fightOpty) {
        return httpPostBody(url, headerMap, body, responseType, lRestTemplate, fightOpty);
    }
    
    /**
     * httpPost body
     */
    private ResponseEntity<byte[]> httpPostBody(String url, Map<String, String> headerMap,
                                                String body, Class<byte[]> responseType, RestTemplate restTemplate, FightOpty fightOpty) {
        Stopwatch stopWatch = Stopwatch.createStarted();
        ResponseEntity<byte[]> responseEntity = null;
        String error = StringUtils.EMPTY;
        try {
            if(headerMap == null){
                headerMap = new HashMap<>();
            }
            headerMap.put("Content-Type", "application/json");
            HttpHeaders headers = new HttpHeaders();
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.set(entry.getKey(), entry.getValue());
            }
            body = body == null ? "" : body;
            HttpEntity<byte[]> entity = new HttpEntity<>(body.getBytes(DEF_ENCODING_UTF_8), headers);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        } catch (Exception e) {
            logger.error("post error", e);
            error = getError(e);
        } finally {
            Map<String, String> requestMessage = new HashMap<>();
            requestMessage.put("body", body);
            OgLog logInfo = OgLog
                    .getOgLog(fightOpty, url, headerMap, requestMessage, keepShort(getHttpResponseContent(responseEntity)),
                            error, stopWatch.stop().elapsed(TimeUnit.MILLISECONDS));
            ogLog.info(JsonUtils.jacksonString(logInfo));
        }
        return responseEntity;
    }
    
    /**
     * httpPost body带request
     */
    private ResponseEntity<byte[]> httpPostBody(HttpServletRequest request, String url, Map<String, String> headerMap,
                                                String body, Class<byte[]> responseType, RestTemplate restTemplate, FightOpty fightOpty) {
        Stopwatch stopWatch = Stopwatch.createStarted();
        ResponseEntity<byte[]> responseEntity = null;
        String error = StringUtils.EMPTY;
        try {
            if(headerMap == null){
                headerMap = new HashMap<>();
            }
            headerMap.put("Content-Type", "application/json");
            HttpHeaders headers = new HttpHeaders();
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.set(entry.getKey(), entry.getValue());
            }
    
            List<String> cookieList = new ArrayList<String>();
            for(Cookie cookie:request.getCookies()){ //将浏览器cookies放入list中
                System.out.println("当前cookies为:" +  cookie.getDomain() + " " + cookie.getName() + ":" + cookie.getValue());
                cookieList.add(cookie.getName() + "=" + cookie.getValue());
            }
            cookieList.add("u_usercode=huangmingjie");
            System.out.println("cookie为：" + cookieList.toString());
            headers.put(HttpHeaders.COOKIE,cookieList); //将cookie放入header
    
            body = body == null ? "" : body;
            HttpEntity<byte[]> entity = new HttpEntity<>(body.getBytes(DEF_ENCODING_UTF_8), headers);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        } catch (Exception e) {
            logger.error("post error", e);
            error = getError(e);
        } finally {
            Map<String, String> requestMessage = new HashMap<>();
            requestMessage.put("body", body);
            OgLog logInfo = OgLog
                    .getOgLog(fightOpty, url, headerMap, requestMessage, keepShort(getHttpResponseContent(responseEntity)),
                            error, stopWatch.stop().elapsed(TimeUnit.MILLISECONDS));
            ogLog.info(JsonUtils.jacksonString(logInfo));
        }
        return responseEntity;
    }
    
    private ResponseEntity<byte[]> httpPost(String url, Map<String, String> headerMap,
                                            Map<String, String> reqMap, Class<byte[]> responseType, RestTemplate restTemplate,
                                            FightOpty fightOpty) {
        Stopwatch stopWatch = Stopwatch.createStarted();
        ResponseEntity<byte[]> responseEntity = null;
        String error = StringUtils.EMPTY;
        try {
            MultiValueMap<String, String> mvMap = new LinkedMultiValueMap<>();
            if (reqMap != null) {
                for (Map.Entry<String, String> e : reqMap.entrySet()) {
                    mvMap.set(e.getKey(), e.getValue());
                }
            }
//            headerMap = addRequestId2BisHeader(headerMap, fightOpty.getRequestId());
            HttpHeaders headers = new HttpHeaders();
            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    headers.set(entry.getKey(), entry.getValue());
                }
            }
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(mvMap, headers);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        } catch (Exception e) {
            logger.error("post error", e);
            error = getError(e);
            throw e;
        } finally {
            OgLog logInfo = OgLog
                    .getOgLog(fightOpty, url, headerMap, reqMap, keepShort(getHttpResponseContent(responseEntity)),
                            error, stopWatch.stop().elapsed(TimeUnit.MILLISECONDS));
            ogLog.info(JsonUtils.jacksonString(logInfo));
        }
        return responseEntity;
    }
    
    private ResponseEntity<byte[]> httpGet(String url, Map<String, String> headerMap,
                                           Map<String, String> reqMap, Class<byte[]> responseType, RestTemplate restTemplate,
                                           FightOpty fightOpty) {
        Stopwatch stopWatch = Stopwatch.createStarted();
        ResponseEntity<byte[]> responseEntity = null;
        String error = StringUtils.EMPTY;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        try {
            if (reqMap != null) {
                for (Map.Entry<String, String> rmap : reqMap.entrySet()) {
                    builder.queryParam(rmap.getKey(), rmap.getValue());
                }
            }
//            headerMap = addRequestId2BisHeader(headerMap, fightOpty.getRequestId());
            HttpHeaders headers = new HttpHeaders();
            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    headers.set(entry.getKey(), entry.getValue());
                }
            }
            HttpEntity entity = new HttpEntity<>(headers);
            responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, responseType);
        } catch (Exception e) {
            logger.error("get error", e);
            error = getError(e);
            throw e;
        } finally {
            OgLog logInfo = OgLog
                    .getOgLog(fightOpty, builder.build().encode().toUri().toString(), headerMap, reqMap, keepShort(getHttpResponseContent(responseEntity)),
                            error, stopWatch.stop().elapsed(TimeUnit.MILLISECONDS));
            ogLog.info(JsonUtils.jacksonString(logInfo));
        }
        return responseEntity;
    }
    
    public String getHttpResponseContent(ResponseEntity<byte[]> httpResponse) {
        if (httpResponse != null) {
            List<String> encodings = httpResponse.getHeaders().get("Content-Encoding");
            List<String> contentTypes = httpResponse.getHeaders().get("Content-Type");
            String charset = DEF_ENCODING_UTF_8;
            // application/json;odata=minimalmetadata;streaming=true;charset=utf-8
            if (contentTypes != null) {
                for (String contentType : contentTypes) {
                    if (contentType.contains("charset")) {
                        String[] types = contentType.split(";");
                        for (String type : types) {
                            String[] kv = type.split("=");
                            if ("charset".equals(kv[0])) {
                                charset = kv[1];
                                break;
                            }
                        }
                    }
                }
            }
            try {
                if (encodings == null) {
                    return httpResponse.getBody() != null ? new String(httpResponse.getBody(), charset) : StringUtils.EMPTY;
                }
                boolean flag = false;
                for (String string : encodings) {
                    if (string.equalsIgnoreCase("gzip")) {
                        flag = true;
                    }
                }
                if (flag) {
                    @Cleanup
                    GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(httpResponse.getBody()));
                    try {
                        return StreamUtils.copyToString(gis, Charset.forName(charset));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    return httpResponse.getBody() != null ? new String(httpResponse.getBody(), charset) : StringUtils.EMPTY;
                }
            } catch (Exception e) {
                logger.error("getHttpResponseContent error", e);
            }
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * 超过60000字符不记录日志
     */
    private String keepShort(String str) {
        // 日志中会省略超长日志，debug模式下打印出省略前的接入商接口返回信息，便于调试
        //log.debug("--> RESPONSE[BIS-API] : " + str);
        if (StringUtils.isNotEmpty(str) && str.length() > 2000) {
            str = str.substring(0, 200) + "...";
        }
        return str;
    }
    
    private String getError(Exception e) {
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException hceException = (HttpClientErrorException) e;
            return hceException.getResponseBodyAsString();
        } else if (e instanceof HttpServerErrorException) {
            HttpServerErrorException hseException = (HttpServerErrorException) e;
            return hseException.getResponseBodyAsString();
        } else {
            return e.toString();
        }
    }
    
    /**
     * 在调用接入商的http请求中增加X-Request-ID
     *
     * @param headerMap
     * @param reqId
     * @return
     */
    private Map<String, String> addRequestId2BisHeader(Map<String, String> headerMap, String reqId) {
        if (headerMap == null) {
            headerMap = new HashMap<>();
        }
        headerMap.put("Content-Type", "application/json");
        if (reqId != null) {
            headerMap.put("X-Request-ID", reqId);
        }
        return headerMap;
    }
}
