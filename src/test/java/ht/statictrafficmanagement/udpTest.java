package ht.statictrafficmanagement;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ht.statictrafficmanagement.base.entity.MapInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class udpTest {

	@Test
	public void aaa() {
		send();
		get();
		 
	}
	private void send() {
		MapInfo map = new MapInfo();
		String data = map.toString();
		//String data="hello UDP"; 
        DatagramSocket datagramSocket=null; 
        try { 
            //实例化套接字，并指定发送端口 
            datagramSocket=new DatagramSocket(8080); 
            //指定数据目的地的地址，以及目标端口 
            InetAddress destination=InetAddress.getByName("localhost");  
            DatagramPacket datagramPacket= 
                    new DatagramPacket(data.getBytes(), data.getBytes().length,destination,8081); 
            //发送数据 
            datagramSocket.send(datagramPacket); 
        } catch (SocketException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally{ 
            datagramSocket.close(); 
        } 
//      DatagramSocket datagramSocket=null; 
//      try { 
//          //实例化套接字，并指定发送端口 
//          datagramSocket=new DatagramSocket(8080); 
//          //指定数据目的地的地址，以及目标端口 
//          InetAddress destination=InetAddress.getByName("192.168.11.100");  
//          DatagramPacket datagramPacket= 
//                  new DatagramPacket(data, data.length,destination,8191); 
//          //发送数据
//          datagramSocket.send(datagramPacket); 
//      } catch (SocketException e) { 
//          e.printStackTrace(); 
//      } catch (IOException e) { 
//          e.printStackTrace(); 
//      } finally{ 
//          datagramSocket.close(); 
//      } 
	}
	private void get() {
		 DatagramSocket datagramSocket=null; 
	       try { 
	           //监视8081端口的内容 
	           datagramSocket=new DatagramSocket(8081); 
	           byte[] buf=new byte[1024]; 
	            
	           //定义接收数据的数据包 
	           DatagramPacket datagramPacket=new DatagramPacket(buf, 0, buf.length); 
	           datagramSocket.receive(datagramPacket); 
	            
	           //从接收数据包取出数据 
	           String data=new String(datagramPacket.getData() , 0 ,datagramPacket.getLength()); 
	           System.out.println(data); 
	       } catch (SocketException e) { 
	           e.printStackTrace(); 
	       } catch (IOException e) { 
	           e.printStackTrace(); 
	       } finally { 
	           datagramSocket.close(); 
	       }       
	}

}
