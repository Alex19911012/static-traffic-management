package ht.statictrafficmanagement.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import ht.statictrafficmanagement.Dom.ParseIV;
import ht.statictrafficmanagement.base.entity.SegmentMessage;
import ht.statictrafficmanagement.util.ResponseResult;



@RestController
public class SegmentMessageController extends BaseController{
	
	private static List<SegmentMessage> segmentMessages = new ArrayList<SegmentMessage>();
	
//	@GetMapping("/map-update")
//	public void readIV() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, DocumentException {
//		ParseIV.readXmlFun();
//	}
	
	
//    /***********HTTP GET method*************/
//    @GetMapping("/map-update")
//    public void getSegments(){
//    	try {
//    		RestTemplate restTemplate = new RestTemplate();
//    		String url="http://192.168.11.135:8000/segment-info/list";//注意这里的端口号
//            List<Integer> results = restTemplate.getForObject(url, List.class);
//            System.err.println("返回地图数据ID==="+results);    
//            
//            String url2 = "http://192.168.11.135:8000/segment-info/{segmentId}";//注意这里的端口号
//            segmentMessages.clear();
//            for(Integer segmentId : results) {
//            	String result2 = restTemplate.getForObject(url2, String.class,segmentId);
//                List<SegmentMessage> segmentMessage = JSON.parseArray(result2, SegmentMessage.class);
//                
//                segmentMessages.addAll(segmentMessage);
//                        	 	
//            }
//            System.err.println(segmentMessages.size());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    @GetMapping("/segment-list")
//	public ResponseResult<List<SegmentMessage>> AGVInfoList() {	
//		System.err.println("请求Segment信息列表");
//		List<SegmentMessage> data = segmentMessages;
//		return new ResponseResult<List<SegmentMessage>>(SUCCESS,data);
//	}
//    
//    
   
    
    
    
    
 
//    /**********HTTP POST method**************/
//    @PostMapping(value = "/bbb")
//    public Object postJson(@RequestBody JSONObject param) {
//        System.out.println(param.toJSONString());
//        param.put("action", "post");
//        param.put("username", "tester");
//        param.put("pwd", "123456748");
//        return param;
//    }
 
//    @PostMapping(value = "/testPostApi")
//    public Object testPost() {
//        String url = "http://localhost:8081/girl/testPost";
//        JSONObject postData = new JSONObject();
//        postData.put("descp", "request for post");
//        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
//        return json;
//    }

}
