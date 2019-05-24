package ht.statictrafficmanagement.Dom;





import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import ht.statictrafficmanagement.base.entity.AGVInfo;
import ht.statictrafficmanagement.base.entity.ActionPoint;
import ht.statictrafficmanagement.base.entity.MagPoint;
import ht.statictrafficmanagement.base.entity.MapInfo;
import ht.statictrafficmanagement.base.entity.NodeMessage;
import ht.statictrafficmanagement.base.entity.SegmentMessage;

public  class ParseIV {
	
public static MapInfo readXmlFun() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        
        
       
        //创建一个list 来放多个node等等的对象
        List<NodeMessage> nodes = new ArrayList<NodeMessage>();
        List<SegmentMessage> segments = new ArrayList<SegmentMessage>();
        List<MagPoint> magPoints = new ArrayList<MagPoint>();
        List<ActionPoint> actionPoints = new ArrayList<ActionPoint>();
        List<AGVInfo> agvInfos = new ArrayList<AGVInfo>();
        
        
        SAXReader reader = new SAXReader();
        //将XML文件读取为一份document对象
        Document document = reader.read(new File("src/main/resources/xushuangjia-test.iv"));
        //利用Document类中的方法，获取根节点.返回的是Element
        Element rootElement = document.getRootElement();
        //利用Element中的方法，获取根节点下的全部子节点.返回一个List<element> 就是所有node或者segment节点
        List<Element> elements = rootElement.elements();
        System.err.println(elements.size());
        
        
        //利用Element中的方法，获取子节点中的属性（）
        //1.遍历list,获得每个元素
        
        for (Element element : elements) {
        	String type;
            String key;
            String value;
            String keyM;
            String valueM;
            String valueA;
        	StringBuilder Miscellaneous = new StringBuilder();
            NodeMessage node = new NodeMessage();
            SegmentMessage segment = new SegmentMessage();
            MagPoint magPoint = new MagPoint();
            ActionPoint actionPoint = new ActionPoint();
            AGVInfo agvInfo = new AGVInfo();
            System.out.println("---------------------------------");
            //遍历并得到每个元素执行属性名称的属性值
            type = element.attributeValue("type");
            System.out.println("类型:"+ type);
            
            //遍历并获得每个元素的全部子节点，返回一个List<element> 每个node和segment下也有节点
            List<Element> subElement = element.elements(); 
            
            for (Element properyElement : subElement) {
                Element keyElement = properyElement.element("key");
                key = keyElement.getText();
                Element valueElement = properyElement.element("value");
                value = valueElement.getText();
                
                
                if(type.equals("node")) {
                	if(key.equals("id")) {
                		node.setid(Integer.parseInt(value));
                	}else if(key.equals("Name")) {
                		node.setName(value);
                	}else if(key.equals("POSITION_X")) {
                		node.setPOSITION_X(Double.parseDouble(value));
                	}else if(key.equals("POSITION_Y")) {
                		node.setPOSITION_Y(Double.parseDouble(value));
                	}else if(key.equals("isStation")) {
                		if(value.equals("true")) {
                			value = "1";
                		}else {
                			value = "0";
                		}
                		node.setIsStation(Byte.parseByte(value));
                	}else if(key.equals("stationType")) {
                		if(value.equals("A")) {
                			value = "0";
                		}else if(value.equals("B")){
                			value = "1";
                		}else {
                			value = "2";
                		}
                		node.setStationType(Byte.parseByte(value));
                	}else if(key.equals("endMode")) {
                		if(value.equals("A")) {
                			value = "0";
                		}else if(value.equals("B")){
                			value = "1";
                		}else {
                			value = "2";
                		}
                		node.setEndMode(Byte.parseByte(value));
                	}else if(key.equals("height")) {
                		node.setHeight(Double.parseDouble(value));
                	}else if(key.equals("width")) {
                		node.setWidth(Double.parseDouble(value));
                	}else if(key.equals("codeId")) {
                		node.setCodeId(Integer.parseInt(value));
                	}else if(key.equals("Miscellaneous")) {
                    	List<Element> keyValue = valueElement.elements();
                    	
                        for(Element elementM : keyValue) {
                        	 Element keyMElement = elementM.element("key");
                             keyM = keyMElement.getText();
                             Element keyMValue = elementM.element("value");
                             valueM = keyMValue.getText();
                             Miscellaneous.append(valueM).append(",");
                        }
                        if(Miscellaneous.length()>0) {
                        	Miscellaneous.deleteCharAt(Miscellaneous.length()-1);
                            node.setMiscellaneous(Miscellaneous.toString());
                            System.out.println(Miscellaneous.toString());
                            Miscellaneous = new StringBuilder();
                        }
                        
                    }
                }else if(type.equals("segment")) {
                	if(key.equals("id")) {
                		segment.setSegmentId(Integer.parseInt(value));
                	}else if(key.equals("Name")) {
                		segment.setSegmentName(value);
                	}else if(key.equals("CONN_TYPE")) {
                		if(value.equals("DIRECT")) {
                			value = "0";
                		}else if(value.equals("BEZIER")) {
                			value = "2";
                		}else if(value.equals("CIRCULAR")) {
                			value = "1";
                		}
                		segment.setType(Byte.parseByte(value));
                	}else if(key.equals("startComponent")) {
                		segment.setStartNodeId(Integer.parseInt(value));
                	}else if(key.equals("endComponent")) {
                		segment.setEndNodeId(Integer.parseInt(value));
                	}else if(key.equals("arcAngle")) {
                		segment.setRotAngle(Double.parseDouble(value));
                	}else if(key.equals("isColckWise")) {
                		if(value.equals("true")) {
                			value = "1";
                		}else {
                			value = "0";
                		}
                		segment.setIsClockwiseDirection(Byte.parseByte(value));
                	}else if(key.equals("control1X")) {
                		segment.setControl1X(Double.parseDouble(value));
                	}else if(key.equals("control1Y")) {
                		segment.setControl1Y(Double.parseDouble(value));
                	}else if(key.equals("control2X")) {
                		segment.setControl2X(Double.parseDouble(value));
                	}else if(key.equals("control2Y")) {
                		segment.setControl2Y(Double.parseDouble(value));
                	}else if(key.equals("length")) {
                		segment.setLength(Double.parseDouble(value));
                	}else if(key.equals("isLink")) {
                		if(value.equals("true")) {
                			value = "1";
                		}else {
                			value = "0";
                		}
                		segment.setIsLink(Byte.parseByte(value));
                	}else if(key.equals("isBidirectional")) {
                		if(value.equals("true")) {
                			value = "1";
                		}else {
                			value = "0";
                		}
                		segment.setDirectionality(Byte.parseByte(value));
                	}else if(key.equals("maxSpeed")) {
                		segment.setMaxSpeed(Integer.parseInt(value));
                	}else if(key.equals("Miscellaneous")) {
                    	List<Element> keyValue = valueElement.elements();
                        for(Element elementM : keyValue) {
                        	 Element keyMElement = elementM.element("key");
                             keyM = keyMElement.getText();
                             Element keyMValue = elementM.element("value");
                             valueM = keyMValue.getText();
                             Miscellaneous.append(valueM).append(",");
                        }
                        if(Miscellaneous.length()>0) {
                        	Miscellaneous.deleteCharAt(Miscellaneous.length()-1);
                            segment.setMiscellaneous(Miscellaneous.toString());
                            System.out.println(Miscellaneous.toString());
                            Miscellaneous = new StringBuilder();
                        }
                        
                    }
                	
                }else if(type.equals("magPoint")) {
                	if(key.equals("id")) {
                		magPoint.setMagPointId(Integer.parseInt(value));
                	}else if(key.equals("Name")) {
                		magPoint.setMagName(value);
                	}else if(key.equals("magType")) {
                		if(value.equals("double")) {
                			value = "0";
                		}else {
                			value = "1";
                		}
                		magPoint.setMagType(Byte.parseByte(value));
                	}else if(key.equals("POSITION_X")) {
                		magPoint.setxValue(Double.parseDouble(value));
                	}else if(key.equals("POSITION_Y")) {
                		magPoint.setyValue(Double.parseDouble(value));
                	}else if(key.equals("magInterval")) {
                		magPoint.setMagInterval(Integer.parseInt(value));
                	}
                }else if(type.equals("actionPoint")) {
                	if(key.equals("id")) {
                		actionPoint.setActionID(Integer.parseInt(value));
                	}else if(key.equals("Name")) {
                		actionPoint.setActionName(value);
                	}else if(key.equals("POSITION_X")) {
                		actionPoint.setxValue(Double.parseDouble(value));
                	}else if(key.equals("POSITION_Y")) {
                		actionPoint.setyValue(Double.parseDouble(value));
                	}else if(key.equals("actionType")) {
                		List<Element> keyValue = valueElement.elements();
                		Byte[] actionTypes = new Byte[] {};
                		Integer[] actionContents = new Integer[] {};
                		List<Byte> typeTemp = new ArrayList<Byte>(Arrays.asList(actionTypes));
                		List<Integer> contentsTemp = new ArrayList<Integer>(Arrays.asList(actionContents));
                        for(Element elementA : keyValue) {
                            Element keyAValue = elementA.element("value");
                            valueA = keyAValue.getText();
                            String[] temp = valueA.split(":");
                            byte typeValue;
                            if(temp[0].equals("停止")) {
                            	 typeValue = 1;
                            	 typeTemp.add(typeValue);
                            	 contentsTemp.add(Integer.parseInt(temp[1]));
                            }else if(temp[0].equals("停止并定时启动")) {
                            	typeValue = 2;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("速度设置")) {
                            	typeValue = 3;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("举升")) {
                            	typeValue = 4;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("降落")) {
                            	typeValue = 5;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("上料")) {
                            	typeValue = 6;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("下料")) {
                            	typeValue = 7;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("左上料")) {
                            	typeValue = 8;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("左下料")) {
                            	typeValue = 9;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("右上料")) {
                            	typeValue = 10;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("右下料")) {
                            	typeValue = 11;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("牵引销0下降丨1上升")) {
                            	typeValue = 12;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("手动信号复位")) {
                            	typeValue = 13;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("等待按钮按下")) {
                            	typeValue = 14;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("进入自动工位前确认")) {
                            	typeValue = 15;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("自动移载")) {
                            	typeValue = 16;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("自动涂胶")) {
                            	typeValue = 17;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }else if(temp[0].equals("自动蒙皮")) {
                            	typeValue = 18;
                            	typeTemp.add(typeValue);
                           	 	contentsTemp.add(Integer.parseInt(temp[1]));	
                            }
                            
                        }
                        
                        actionTypes = new Byte[typeTemp.size()];
                        actionContents = new Integer[contentsTemp.size()];
                        typeTemp.toArray(actionTypes);
                        contentsTemp.toArray(actionContents);
                        if(actionTypes.length>0) {
                        	actionPoint.setActionTypes(actionTypes);
                        }else {
                        	Byte[] temp = new Byte[] {0};
                        	actionPoint.setActionTypes(temp);
                        }
                        if(actionContents.length>0) {
                            actionPoint.setActionContents(actionContents);
                        }else {
                        	Integer[] temp = new Integer[] {0};
                        	actionPoint.setActionContents(temp);
                        }
                	}
                }else if(type.equals("vehicle")) {
                	if(key.equals("id")) {
                		agvInfo.setAgvId(Integer.parseInt(value));
                	}else if(key.equals("Name")) {
                		agvInfo.setAgvName(value);
                	}else if(key.equals("agvType")) {
                		if(value.equals("DoubleDifferential")) {
                			value = "0";
                		}else if(value.equals("SingleSteeringEngine")) {
                			value = "2";
                		}else if(value.equals("DoubleSteeringEngine")) {
                			value = "1";
                		}
                		agvInfo.setAgvType(Byte.parseByte(value));
                	}else if(key.equals("length")) {
                		agvInfo.setLength(Double.parseDouble(value));
                	}else if(key.equals("width")) {
                		agvInfo.setWidth(Double.parseDouble(value));
                	}else if(key.equals("height")) {
                		agvInfo.setHeight(Double.parseDouble(value));
                	}else if(key.equals("IP")) {
                		
                		String[] ip = value.split("");
                		
                		List<Byte> ipList= new ArrayList<Byte>();
                		Byte[] result;
                		Byte temp;
                		for(int i=0;i<ip.length;i++) {
                			
                			if(ip[i].equals(".")) {
                				ip[i]="46";
                			}else if(ip[i].equals("0")){
                				ip[i]="48";
                  			}else if(ip[i].equals("1")){
                				ip[i]="49";
                  			}else if(ip[i].equals("2")){
                				ip[i]="50";
                  			}else if(ip[i].equals("3")){
                				ip[i]="51";
                  			}else if(ip[i].equals("4")){
                				ip[i]="52";
                  			}else if(ip[i].equals("5")){
                				ip[i]="53";
                  			}else if(ip[i].equals("6")){
                				ip[i]="54";
                  			}else if(ip[i].equals("7")){
                				ip[i]="55";
                  			}else if(ip[i].equals("8")){
                				ip[i]="56";
                  			}else if(ip[i].equals("9")){
                				ip[i]="57";
                  			}
                			temp = Byte.parseByte(ip[i]);
                			ipList.add(temp);
                		}
                		for(int j=0;j<20-ip.length;j++) {
                			temp = 0;
                			ipList.add(temp);
                		}
                		result = new Byte[ipList.size()];
                		ipList.toArray(result);
                		
                		agvInfo.setAgvIp(result);
                		
                	}else if(key.equals("tcpPort")) {
                		agvInfo.setUdpPort(Integer.parseInt(value));
                	}else if(key.equals("relatedNode")) {
                		agvInfo.setNodeId(Integer.parseInt(value));
                	}else if(key.equals("frontAngle")) {
                		agvInfo.setFrontAngle(Double.parseDouble(value));
                	}else if(key.equals("moveMode")) {
                		if(value.equals("FORWARD")) {
                			value = "0";
                		}
                		agvInfo.setMoveMode(Byte.parseByte(value));
                	}else if(key.equals("displayColor")) {
                		agvInfo.setDisplayColor(Integer.parseInt(value));
                	}else if(key.equals("Miscellaneous")) {
                    	List<Element> keyValue = valueElement.elements();
                    	
                        for(Element elementM : keyValue) {
                        	 Element keyMElement = elementM.element("key");
                             keyM = keyMElement.getText();
                             Element keyMValue = elementM.element("value");
                             valueM = keyMValue.getText();
                             Miscellaneous.append(valueM).append(",");
                        }
                        if(Miscellaneous.length()>0) {
                        	Miscellaneous.deleteCharAt(Miscellaneous.length()-1);
                            node.setMiscellaneous(Miscellaneous.toString());
                            System.out.println(Miscellaneous.toString());
                            Miscellaneous = new StringBuilder();
                        }
                        
                    }else if(key.equals("currentSpeed")) {
                    	agvInfo.setCurrentSpeed(Double.parseDouble(value));
                	}else if(key.equals("POSITION_X")) {
                		agvInfo.setPOSITION_X(Double.parseDouble(value));
                	}else if(key.equals("POSITION_Y")) {
                		agvInfo.setPOSITION_Y(Double.parseDouble(value));
                	}else if(key.equals("warningType")) {
                		agvInfo.setWarningType(value);
                	}else if(key.equals("status")) {
                		agvInfo.setStatus(value);
                	}else if(key.equals("battery")) {
                		agvInfo.setBattery(value);
                	}
                }
                
            }
            if("node".equals(type)) {
            	nodes.add(node);
            	System.err.println(node);
            }else if("segment".equals(type)) {
            	segments.add(segment);
            	System.err.println(segment);
            }else if("magPoint".equals(type)) {
            	magPoints.add(magPoint);
            	System.err.println(magPoint);
            }else if("actionPoint".equals(type)) {
            	actionPoints.add(actionPoint);
            	System.err.println(actionPoint);
            }else if("vehicle".equals(type)) {
            	agvInfos.add(agvInfo);
            	System.err.println(agvInfo);
            }
            
           
                   
        }
        MapInfo m = new MapInfo();
        m.setNodes(nodes);
        m.setSegments(segments);
        m.setMagPoints(magPoints);
        m.setActionPoints(actionPoints);
        m.setAgvInfos(agvInfos);
        return m;
        
    }
	
	
	
	
}
