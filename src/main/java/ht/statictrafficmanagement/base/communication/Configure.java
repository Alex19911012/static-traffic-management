package ht.statictrafficmanagement.base.communication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wudw on 2017/11/2.
 */

@Component
public class Configure {
	
	@Value("${udpsocket.udp_port}")
    private int udp_port;
	
	@Value("${udpsocket.receive_packet_max_length}")
    private int receive_packet_max_length;
	
	@Value("${udpsocket.send_packet_max_length}")
    private int send_packet_max_length;
	
	@Value("${udpsocket.resend_message_interval}")
    private int clear_sent_message_interval;
	
	@Value("${udpsocket.clear_sent_message_interval}")
    private int resend_message_interval;

    public int getResend_message_interval() {
        return resend_message_interval;
    }

    public void setResend_message_interval(int resend_message_interval) {
        this.resend_message_interval = resend_message_interval;
    }

    public int getClear_sent_message_interval() {
        return clear_sent_message_interval;
    }

    public void setClear_sent_message_interval(int clear_sent_message_interval) {
        this.clear_sent_message_interval = clear_sent_message_interval;
    }

    public int getSend_packet_max_length() {
        return send_packet_max_length;
    }

    public void setSend_packet_max_length(int send_packet_max_length) {
        this.send_packet_max_length = send_packet_max_length;
    }

    public int getUdp_port() {
        return udp_port;
    }

    public void setUdp_port(int udp_port) {
        this.udp_port = udp_port;
    }

    public int getReceive_packet_max_length() {
        return receive_packet_max_length;
    }

    public void setReceive_packet_max_length(int receive_packet_max_length) {
        this.receive_packet_max_length = receive_packet_max_length;
    }

	@Override
	public String toString() {
		return "Configure [udp_port=" + udp_port + ", receive_packet_max_length=" + receive_packet_max_length
				+ ", send_packet_max_length=" + send_packet_max_length + ", clear_sent_message_interval="
				+ clear_sent_message_interval + ", resend_message_interval=" + resend_message_interval + "]";
	}
    
}
