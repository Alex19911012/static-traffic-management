package ht.statictrafficmanagement.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ht.statictrafficmanagement.Dom.ParseIV;
import ht.statictrafficmanagement.base.Message;
import ht.statictrafficmanagement.base.communication.ISender;
import ht.statictrafficmanagement.base.communication.MessageSender;
import ht.statictrafficmanagement.base.entity.AGVInfo;
import ht.statictrafficmanagement.base.entity.MapInfo;
import ht.statictrafficmanagement.base.entity.NodeMessage;
import ht.statictrafficmanagement.base.entity.PathDataInfo;
import ht.statictrafficmanagement.base.entity.SegmentMessage;
import ht.statictrafficmanagement.base.entity.TaskAGVID;
import ht.statictrafficmanagement.base.entity.TaskDataInfo;
import ht.statictrafficmanagement.base.entity.TaskDataMessage;
import ht.statictrafficmanagement.base.entity.TaskExecMessage;
import ht.statictrafficmanagement.base.entity.TaskInfo;
import ht.statictrafficmanagement.util.ResponseResult;

@RestController
public class Controller extends BaseController{
	
	MessageSender messageSender = new MessageSender();
	
	
	MapInfo mapInfo = new MapInfo();
	List<PathDataInfo> pathList = new ArrayList<>();
	List<TaskDataInfo> taskList = new ArrayList<>();
	TaskDataMessage taskDataMessage = new TaskDataMessage();
	
	List<TaskInfo> taskInfos = new ArrayList<>();
	TaskExecMessage taskExecMessage = new TaskExecMessage();
	
	
	
	@GetMapping("/map-update")
	public void readIV() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, DocumentException {
		mapInfo = ParseIV.readXmlFun();
	}
	
	
	
	@GetMapping("/listPath")
	public ResponseResult<List<PathDataInfo>> pathList() {	
		System.err.println("请求路径列表");
		List<PathDataInfo> data = pathList;
		System.err.println(data);
		return new ResponseResult<List<PathDataInfo>>(SUCCESS,data);
	}
	@PostMapping("/addPath")
	public ResponseResult<Void> addPath(PathDataInfo path) {
		Integer[] nodeListP = path.getNodeList();
		boolean booNode = findNodeInMap(nodeListP);
		boolean booSegment = findSegmentInMap(nodeListP);
		if(booNode==false || booSegment==false) {
			return new ResponseResult<Void>();
		}
		path.setNodeListLen(path.getNodeList().length);
		pathList.add(path);
		System.out.println(path);
		return new ResponseResult<Void>(SUCCESS);
	}
	@PostMapping("/{pathID}/deletePath")
	public ResponseResult<Void> deletePath(@PathVariable("pathID")Integer pathID){
		System.err.println(pathID);
		int i=0;
		for(PathDataInfo p : pathList) {
			if(p.getPathID().equals(pathID)) {
				pathList.remove(i);
				break;
			}
			++i;
		}
		return new ResponseResult<Void>(SUCCESS);
	}
	@PostMapping("/updatePath")
	public ResponseResult<Void> updatePath(@RequestParam Integer pathID,@RequestParam Integer[] nodeList){	
		boolean booNode = findNodeInMap(nodeList);
		boolean booSegment = findSegmentInMap(nodeList);
		if(booNode==false || booSegment==false) {
			return new ResponseResult<Void>();
		}
		System.err.println(pathID);
		System.err.println(nodeList);
		int k=0;
		for(PathDataInfo p : pathList) {
			if(p.getPathID().equals(pathID)) {
				pathList.remove(k);
				PathDataInfo newP = new PathDataInfo();
				newP.setPathID(pathID);
				newP.setNodeListLen(nodeList.length);
				newP.setNodeList(nodeList);
				pathList.add(newP);
				return new ResponseResult<Void>(SUCCESS);
			}
			++k;
		}
		return new ResponseResult<Void>();
	}
	
	
	@GetMapping("/listTask")
	public ResponseResult<List<TaskDataInfo>> taskList() {	
		System.err.println("请求任务列表");
		List<TaskDataInfo> data = taskList;
		System.err.println(data);
		return new ResponseResult<List<TaskDataInfo>>(SUCCESS,data);
	}
	@PostMapping("/addTask")
	public ResponseResult<Void> addTask(TaskDataInfo task) {
		boolean checkPath = checkTaskPath(task.getPathList());
		if(checkPath == false) {
			return new ResponseResult<Void>();
		}
		task.setAlisLen(task.getAlisData().length());
		task.setPathListLen(task.getPathList().length);
		taskList.add(task);
		System.out.println(task);
		return new ResponseResult<Void>(SUCCESS);
	}
	@PostMapping("/{taskID}/deleteTask")
	public ResponseResult<Void> deleteTask(@PathVariable("taskID")Integer taskID){
		System.err.println(taskID);
		int i=0;
		for(TaskDataInfo p : taskList) {
			if(p.getTaskID().equals(taskID)) {
				taskList.remove(i);
				return new ResponseResult<Void>(SUCCESS);
			}
			++i;
		}
		return new ResponseResult<Void>();
	}
	@PostMapping("/updateTask")
	public ResponseResult<Void> updateTask(@RequestParam Integer taskID,@RequestParam Integer[] pathList){	
		int k=0;
		for(TaskDataInfo t : taskList) {
			if(t.getTaskID().equals(taskID)) {
				boolean checkPath = checkTaskPath(pathList);
				if(checkPath == false) {
					return new ResponseResult<Void>();
				}
				taskList.remove(k);
				TaskDataInfo newP = new TaskDataInfo();
				newP.setTaskID(taskID);
				newP.setAlisLen(t.getAlisData().length());
				newP.setAlisData(t.getAlisData());
				newP.setPathListLen(pathList.length);
				newP.setPathList(pathList);
				taskList.add(newP);
				return new ResponseResult<Void>(SUCCESS);
			}
			++k;
		}
		return new ResponseResult<Void>();
	}
	
	@GetMapping("/listAGVInfo")
	public ResponseResult<List<AGVInfo>> agvInfoList() {
		System.err.println("请求车辆列表");
		List<AGVInfo> data = mapInfo.getAgvInfos();
		System.err.println(data);
		return new ResponseResult<List<AGVInfo>>(SUCCESS,data);
	}
	
	@GetMapping("/sendMapInfo")
	public ResponseResult<Void> sendMapInfo(){
		System.err.println("发送地图数据!");
		byte[] data = mapInfo.encode();
		System.err.println(data.length);
		try {
			System.err.println(mapInfo);
			messageSender.send(mapInfo, "192.168.11.100", 8191);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/sendTPInfo")
	public ResponseResult<Void> sendPTInfo(){
		System.err.println("发送任务路径数据!");
		
		taskDataMessage.setTaskList(taskList);
		taskDataMessage.setPathList(pathList);
		
		byte[] data = taskDataMessage.encode();
		System.err.println(data.length);
		
		try {
			System.err.println(taskDataMessage);
			messageSender.send(taskDataMessage,"192.168.11.100", 8191);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/getTaskAGVID")
	public ResponseResult<TaskAGVID> findTaskAGVID(){
		System.err.println("给前端界面返回taskID[]和AGVID[]");
		TaskAGVID data = new TaskAGVID();
		List<Integer> taskId = new ArrayList<Integer>();
		List<Integer> agvId = new ArrayList<>();
		for(TaskDataInfo t : taskList) {
			taskId.add(t.getTaskID());
		}
		List<AGVInfo> agvs = new ArrayList<AGVInfo>();
		agvs = mapInfo.getAgvInfos();
		for(AGVInfo a : agvs) {
			agvId.add(a.getAgvId());
		}
		Integer[] arrAID = agvId.toArray(new Integer[agvId.size()]);
		Integer[] arrTID = taskId.toArray(new Integer[taskId.size()]);
		data.setAGVID(arrAID);
		data.setTaskID(arrTID);
		
		return new ResponseResult<TaskAGVID>(SUCCESS,data);
				
	}
	@PostMapping("/addTaskInfo")
	public ResponseResult<Void> addTaskInfo(TaskInfo taskInfo) {
		
		taskInfo.setPathNum(findPathNum(taskInfo.getTaskId()));
		taskInfo.setStartNode(findSNode(taskInfo.getTaskId()));
		System.out.println(taskInfo);
		
		if(booAdd(taskInfo.getTaskId())) {
			return new ResponseResult<Void>();
		}
		taskInfos.add(taskInfo);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/sendTaskExec")
	public ResponseResult<Void> sendTaskExec(){
		System.err.println("发送任务执行数据!");
		
		taskExecMessage.setTaskList(taskInfos);
		byte[] data = taskExecMessage.encode();
		System.err.println(data.length);
		try {
			System.err.println(taskExecMessage);
			messageSender.send(taskExecMessage, "192.168.11.100", 8191);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseResult<Void>(SUCCESS);
	}
	

	//传入点集合看map中是否有
	public boolean findNodeInMap(Integer[] nodeListP) {
		List<NodeMessage> nodeListM = mapInfo.getNodes();
		int nodeIdP; 
		Integer nodeIdM;
		int temp = 0;
		for(int i=0;i<nodeListP.length;i++) {
			nodeIdP = nodeListP[i];
			for(int j=0;j<nodeListM.size();j++) {
				nodeIdM = nodeListM.get(j).getid();
				if(nodeIdP == nodeIdM) {
					temp +=1;
					break;
				}
			}
		}
		if(temp == nodeListP.length) {
			return true;
		}else {
			return false;
		}
	}
	//传入点集看segment是不是存在
	public boolean findSegmentInMap(Integer[] nodeListP) {
		List<SegmentMessage> segmentListM = mapInfo.getSegments();
		int nodeIdPS;
		int nodeIdPE;
		Integer nodeIdMS;
		Integer nodeIdME;
		int temp = 0;
		for(int i=0;i<nodeListP.length-1;i++) {
			nodeIdPS = nodeListP[i];
			nodeIdPE = nodeListP[i+1];
			for(int j=0;j<segmentListM.size();j++) {
				nodeIdMS = segmentListM.get(j).getStartNodeId();
				nodeIdME = segmentListM.get(j).getEndNodeId();
				if(nodeIdPS == nodeIdMS && nodeIdPE == nodeIdME) {
					temp +=1;
					break;
				}
			}
		}
		if(temp == nodeListP.length-1) {
			return true;
		}else {
			return false;
		}
	}
	
	//传入任务的path集看首位接上
	public boolean checkTaskPath(Integer[] paths) {
		
		Integer nodeIdS = 9999;
		Integer nodeIdE = 8888;
		int temp = 0;
		for(int i = 0;i<paths.length-1;i++) {
			int k=0;
			for(PathDataInfo p : pathList) {
				if(p.getPathID().equals(paths[i])) {
					nodeIdS = p.getNodeList()[p.getNodeListLen()-1];
					break;
				}
			}
			for(PathDataInfo p : pathList) {
				if(p.getPathID().equals(paths[i+1])) {
					nodeIdE = p.getNodeList()[0];
					break;
				}
			}
			if(nodeIdS == nodeIdE) {
				temp +=1;
				nodeIdS = 9999;
				nodeIdE = 8888;
			}
		}
		if(temp == paths.length-1) {
			return true;
		}else {
			return false;
		}
	}
	
	//将任务信息和路径信息对象转成执行任务信息对象.
	public List<TaskInfo> parseToTaskInfo(List<PathDataInfo> ps,List<TaskDataInfo> ts){
		List<TaskInfo> TaskInfos = new ArrayList<>();
		
		for(TaskDataInfo t : ts) {
			TaskInfo TaskInfo = new TaskInfo();
			TaskInfo.setTaskId(t.getTaskID());
			TaskInfo.setPathNum(t.getPathListLen());
			int i = t.getPathList()[0];
			for(PathDataInfo p : ps) {
				if(p.getPathID() == i) {
					TaskInfo.setStartNode(p.getNodeList()[0]);
					TaskInfos.add(TaskInfo);
					break;
				}
			}
			
			
		}
		return TaskInfos;
	}
	//判断根据任务ID看TaskInfo是否已经添加了
	public boolean booAdd(Integer TaskId) {
		for(TaskInfo t : taskInfos) {
			if(t.getTaskId()==TaskId){
				return true;
			}
		}
		return false;
	}
	//根据任务id查询起点
	public Integer findSNode(Integer TaskId) {
		for(TaskDataInfo t : taskList) {
			if(t.getTaskID()==TaskId) {
				Integer[] ps = t.getPathList();
				for(PathDataInfo p : pathList) {
					if(p.getPathID()==ps[0]) {
						Integer[] ns = p.getNodeList();
						return ns[0];
					}
				}
			}
		}
		return -1;
	}
	//根据任务id查询路径数
	public Integer findPathNum(Integer TaskId) {
		for(TaskDataInfo t : taskList) {
			if(t.getTaskID()==TaskId) {
				Integer pathNum = t.getPathListLen();
				return pathNum;
			}
		}
		return -1;
	}
	
	
}
