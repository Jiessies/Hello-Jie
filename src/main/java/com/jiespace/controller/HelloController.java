package com.jiespace.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jiespace.common.enums.OptType;
import com.jiespace.common.logs.FightOpty;
import com.jiespace.common.utils.YklyRestTemplate;
import com.jiespace.entity.DepartDetail;
import com.jiespace.entity.IFFlight;
import com.jiespace.entity.Msg;
import com.jiespace.entity.Segment;
import com.jiespace.exception.CommonResponse;
import com.jiespace.exception.OrderException;
import com.jiespace.utils.MapKeyComparator;
import com.jiespace.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by huangmingjie on 2017/10/5.
 */
@RestController
@Slf4j
@Validated
public class HelloController {
    
//    @Autowired
//    private CloudSolrServer solrserver;
    
    @Autowired
    private SolrClient solrClient;
    
    @Autowired
    private YklyRestTemplate restTemplate;
    
    private static final Gson gson = new Gson();
    
    /*StringBuilder uriSb = new StringBuilder();
        if (!org.apache.commons.lang3.StringUtil.isEmpty(uri)) {
        uriSb.append(uri).append("?");
    }
        sortRequestMap.entrySet().forEach(
            e -> uriSb.append(uriSb.toString().endsWith("?") ? "" : "&").append(e.getKey())
            .append("=").append(e.getValue()));*/
    
    /**
     * 将扩展字段extension转为Map
     *
     * @param extension
     * @return
     */
    public static Map<String, String> getExtMap(String extension) {
        Map<String, String> extMap;
        if (StringUtils.isNotEmpty(extension)) {
            try {
                extMap = gson.fromJson(extension, Map.class);
            } catch (JsonSyntaxException e) {
                extMap = new HashMap<>();
            }
        } else {
            extMap = new HashMap<>();
        }
        return extMap;
    }
    
    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        
        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());
        
        sortMap.putAll(map);
        
        return sortMap;
    }
    
    public static String getSingCode(Map requestMap) {
        String secret_key = "XXXXX";
        StringBuilder uriSb = new StringBuilder().append(secret_key).append("&");
        Map<String, String> sortRequestMap = sortMapByKey(requestMap);
        for (Map.Entry<String, String> entity : sortRequestMap.entrySet()) {
            uriSb.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
        }
        return StringUtil.md5(uriSb.append(secret_key).toString());
    }
    
    @GetMapping("/hello")
    public String index() throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        log.info("sleepTime:" + sleepTime);
        Thread.sleep(3000);
        return "Hello World";
    }
    
    @PostMapping("/test1")
    public Object getTest(HttpServletRequest request,@RequestParam("data") @NotBlank String data) {
//        HashMap hashMap = new HashMap();
//        List list = new ArrayList();
//        HashMap activityMap = new HashMap();
//        for (int i = 0; i < 3; i++) {
//            if (false) {
//                activityMap.put("activity_name", null);
//                activityMap.put("detail", null);
//                activityMap.put("remark", null);
//                activityMap.put("price", null);
//                list.add(activityMap);
//            }
//        }
//        hashMap.put("ttttt", list);
//        hashMap.put("cookies",request.getCookies());
//        System.out.println("======>"+new Gson().toJson(ifFlight));
        javax.servlet.http.Cookie[] cs = request.getCookies();
    
        if(StringUtils.isEmpty(data)){
            System.out.println("我是空值！！！！！");
            data = "我是空值！！！！！";
        }
        if(cs!=null){
            for(int i=0;i<cs.length;i++){
                if("u_usercode".equals(cs[i].getName().trim())){
                    System.out.println("=====>"+cs[i].getValue());
                }
                System.out.println(cs[i].getName()+"======"+cs[i].getValue());
            }
        }
        return data;
    }
    
    @PostMapping("/test2")
    public Object getTest2(@RequestBody String data) {
        Map<String, String> extMap = getExtMap(data);
        Map<String, String> map = sortMapByKey(extMap);
        return map;
    }
    
    @PostMapping("/test3")
    public Object getTest3(@RequestBody IFFlight ifFlight) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        Class clazz = ifFlight.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
            Method method = descriptor.getReadMethod();
            Object value = method.invoke(ifFlight);
            
            System.out.println(key + ":" + value);
            
        }
        return ifFlight;
    }
    
    @PostMapping("/test4")
    public String getTest4(@RequestBody IFFlight ifFlight) {
        HashMap requestMap = new HashMap();
        requestMap.put("api_key", ifFlight.getApi_key());
        requestMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        requestMap.put("depart_city_code", ifFlight.getData().getSegment_list().get(0).getArrive_city_code());
        requestMap.put("arrive_city_code", ifFlight.getData().getSegment_list().get(0).getArrive_city_code());
        requestMap.put("depart_date", ifFlight.getData().getSegment_list().get(0).getDepart_date());
        ifFlight.setSign(getSingCode(requestMap));
        return gson.toJson(ifFlight);
    }
    
    @PostMapping("/test5")
    public String getTest5(@RequestBody IFFlight ifFlight, HttpServletRequest request) {
        restTemplate.lPostBody(request,"http://localhost:8001/test1",null,"",new FightOpty(OptType.IF_BackChangeQuery,null));
        return "succ";
    }
    
    @PostMapping("/test6")
    public String getTest6(@Valid @RequestBody IFFlight ifFlight, BindingResult bindingResult) {
        checkParams(bindingResult);
        return "succ";
    }
    
    private void checkParams(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.error("param error : {}", error.getDefaultMessage());
                builder.append(error.getDefaultMessage()).append(",");
            }
            throw new OrderException(new CommonResponse("-1", "000001", "请求参数为空或非法"+builder.deleteCharAt(builder.length()-1)));
        }
    }
    
    @GetMapping("/gaode")
    public String testGaode(){
        String response = restTemplate.lGet("http://restapi.amap.com/v3/direction/walking?key=b3fcce0652f3fa69889389cfdd1f831f&origin=116.481028,39.989643&destination=116.434446,39.90816",null,null,new FightOpty(OptType.IF_SubmitOrder,""));
        return response;
    }
    
    @RequestMapping("/test")
    public String test(){
    
        /*// 第四步：创建一SolrInputDocument对象。
        SolrInputDocument document = new SolrInputDocument();
        // 第五步：向文档对象中添加域
        document.addField("item_title", "测试商品");
        document.addField("item_price", "100");
        document.addField("id", "test001");
        try {
            // 第六步：把文档对象写入索引库。
            solrserver.add(document);
            // 第七步：提交。
            solrserver.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        
        ModifiableSolrParams params =new ModifiableSolrParams();
        params.add("q","*:*");
        QueryResponse response=null;
        try {
            response = solrClient.query("demo",params);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
    
    public static void main(String[] args) {
        String dddd =  "[{\"site_id\":2,\"product_id\":123,\"site_position\":\"北京\",\"site_time\":\"2017-01-01 10:01:10\",\"site_money\":100.0},{\"site_id\":3,\"product_id\":123,\"site_position\":\"天津\",\"site_time\":\"2017-01-02 10:01:10\",\"site_money\":200.0},{\"site_id\":4,\"product_id\":123,\"site_position\":\"北京\",\"site_time\":\"2017-01-01 10:01:10\",\"site_money\":100.0},{\"site_id\":5,\"product_id\":123,\"site_position\":\"天津\",\"site_time\":\"2017-01-02 10:01:10\",\"site_money\":200.0},{\"site_id\":8,\"product_id\":123,\"site_position\":\"北京\",\"site_time\":\"2017-01-01 10:01:10\",\"site_money\":100.0},{\"site_id\":9,\"product_id\":123,\"site_position\":\"天津\",\"site_time\":\"2017-01-02 10:01:10\",\"site_money\":200.0},{\"site_id\":10,\"product_id\":123,\"site_position\":\"北京\",\"site_time\":\"2017-01-01 10:01:10\",\"site_money\":100.0},{\"site_id\":11,\"product_id\":123,\"site_position\":\"天津\",\"site_time\":\"2017-01-02 10:01:10\",\"site_money\":200.0},{\"site_id\":12,\"product_id\":123,\"site_position\":\"北京\",\"site_time\":\"2017-01-01 10:01:10\",\"site_money\":100.0},{\"site_id\":13,\"product_id\":123,\"site_position\":\"天津\",\"site_time\":\"2017-01-02 10:01:10\",\"site_money\":200.0},{\"site_id\":14,\"product_id\":123,\"site_position\":\"REW\",\"site_time\":\"2017-01-18 00:01:00\",\"site_money\":23400.0},{\"site_id\":19,\"product_id\":123,\"site_position\":\"北京\",\"site_time\":\"2017-01-01 10:01:10\",\"site_money\":100.0},{\"site_id\":20,\"product_id\":123,\"site_position\":\"天津\",\"site_time\":\"2017-01-02 10:01:10\",\"site_money\":200.0}]";
        IFFlight ifFlight = new IFFlight();
        List<DepartDetail> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            DepartDetail departDetail = new DepartDetail();
            departDetail.setArrive_city_code(String.valueOf(i));
            departDetail.setDepart_date(String.valueOf(i));
            departDetail.setDepart_city_code(String.valueOf(i));
            list.add(departDetail);
        }
//        ifFlight.setSign(new Gson().toJson(list));
        Segment segment = new Segment();
        segment.setSegment_list(list);
        ifFlight.setData(segment);
        System.out.println(new Gson().toJson(ifFlight));
    }
}
