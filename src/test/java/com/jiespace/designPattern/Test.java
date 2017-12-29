package com.jiespace.designPattern;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Test<T> {
    
    public Class<T> clazz;
    public Class<?> aClass;
    public static void main(String[] args) {
//        List<DepartDetail> seatList = new ArrayList<>();
//        List<DepartDetail> seatList1 = new ArrayList<>();
//        for(int i =0;i<10;i++){
//            DepartDetail departDetail = new DepartDetail();
//            departDetail.setDepart_city_code(i+"city");
//            departDetail.setArrive_city_code(i+"code");
//            seatList.add(departDetail);
//        }
//        for(int i =0;i<20;i++){
//            DepartDetail departDetail = new DepartDetail();
//            departDetail.setDepart_city_code(i+"city");
//            departDetail.setArrive_city_code(i+"code");
//            seatList1.add(departDetail);
//        }
////        System.out.println(seatList);
////        System.out.println(seatList1);
//        seatList1.addAll(seatList);
//        System.out.println(seatList1);
//        System.out.println(seatList1.size());
//        String string = "1,2,3,21,22,24";
//
//        System.out.println("1".contains(string));
//        List<String> list  =   new  ArrayList<String>();
//        list.add("aaa");
//        list.add("bbb");
//        String ddd = getInt(list);
//        System.out.println(ddd);
//        double i = 114.481028;
//        HashMap map = new HashMap();
//        map.put("age", "100");
//        map.put("score", "99");
//        HashMap map1 = new HashMap();
//        map1.put("age", "101");
//        map1.put("score", "98");
//        HashMap map2 = new HashMap();
//        map1.put("age", null);
//        map1.put("score", "98");
//        List<Students> students = new ArrayList<Students>();
//        students.add(new Students(23, 100));
//        students.add(new Students(27, 98));
//        students.add(new Students(29, 99));
//        students.add(new Students(29, 98));
//        students.add(new Students(22, 89));
//        List<Map<String, Object>> list = new ArrayList<>();
//        list.add(map1);
//        list.add(map);
//        list.add(map2);
//        try {
//            listSort(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 120; i++) {
//            list.add(i+1+"");
//        }
//        List<List<?>> listList = splitList(list,100);
//        System.out.println(listList.size());
    
//        Map<String, Integer> budget = new HashMap<>();
//        budget.put("clothes", 120);
//        budget.put("grocery", 150);
//        budget.put("transportation", 100);
//        budget.put("utility", 130);
//        budget.put("rent", 1150);
//        budget.put("aiscellneous", 90);
//        System.out.println("map before sorting: " + budget);
//        // let's sort this map by values first
//        Map<String, Integer> sorted = budget
//                .entrySet()
//                .stream()
//                .sorted(comparingByKey())
//                .collect(
//                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
//                                LinkedHashMap::new));
//        System.out.println(sorted);
//
//        budget.entrySet().stream().sorted(comparingByKey()).collect(toMap(e -> e.getKey(),e -> e.getValue(), (e1,e2) -> e2,LinkedHashMap :: new
//        ));
    
//        try {
//            long remainingTime = new Date().getTime() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-12-06 10:49:49").getTime();
//            System.out.println(remainingTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = "2017-07-06 18:19:10";
//
//        System.out.println(isDate(time));
//
//        List list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(0,3);
//        System.out.println(list);
//
//
//        if(StringUtils.isNotEmpty("sadg")){
//            System.out.println("--------");
//
//        }
        
        final HashMap map = new HashMap();
        map.put(null,"");
        map.put(null,"2");
        map.put("1",1);
        map.put("2",2);
        
        System.out.println(map);
        
    }
    
    private static String getInt(List<String> list) {

        for (String s : list) {
            if (s.equals("aaa")) {
                return s;
            }
        }
        return null;
    }
    
    public static void listSort(List<Map<String, Object>> resultList) throws Exception {
        // resultList是需要排序的list，其内放的是Map
        // 返回的结果集
        Collections.sort(resultList, new Comparator<Map<String, Object>>() {
            
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                
                //o1，o2是list中的Map，可以在其内取得值，按其排序，此例为升序，s1和s2是排序字段值
                Integer s1 = o1.get("age") == null ? Integer.MAX_VALUE : Integer.valueOf(o1.get("age").toString());
                Integer s2 = o2.get("age") == null ? Integer.MAX_VALUE : Integer.valueOf(o2.get("age").toString());
                
                if (s1 > s2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        
    }
    
    public static List<List<?>> splitList(List<?> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<?>> result = new ArrayList<List<?>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<?> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
    
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static <T> void printList2(List<T> list) {
        for (T elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }
    
    
}
class GetMax {
    @SafeVarargs
    public static <T extends Comparable> T findMax(T... in) {
        T max = in[0];
        for (T one : in) {
            if (one.compareTo(max) > 0) {
                max = one;
            }
        }
        
        return max;
    }
}
