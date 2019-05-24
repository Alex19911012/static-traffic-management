package ht.statictrafficmanagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import ht.statictrafficmanagement.util.ResponseResult;




/**
 * 控制器类的基类
 * @author soft01
 *
 */
public abstract class BaseController {
	/**
	 * 响应结果的状体：成功
	 */
	public static final Integer SUCCESS = 200;
	
	/**
	 * 从Session获取当前登录的用户id
	 * @param session
	 * @return
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("UserID").toString());
	}
	
//	/**
//	 * 统一处理异常
//	 * @param e
//	 * @return 
//	 */
//	@ExceptionHandler({ServiceException.class})
//	public ResponseResult<Void> aaaa(Throwable e){
//		ResponseResult<Void> rr = new ResponseResult<Void>();
//		rr.setMessage(e.getMessage());
//		if(e instanceof UsernameDuplicateException) {
//			//400-用户名冲突
//			rr.setState(400);
//			
//		}else if(e instanceof UserNotFoundException) {
//			//401-用户数据不存在
//			rr.setState(401);
//			
//		}else if(e instanceof PasswordNotMatchException) {
//			//402-验证密码失败
//			rr.setState(402);
//			
//		}else if(e instanceof InsertException) {
//			//500-插入数据异常
//			rr.setState(500);		
//		}else if(e instanceof UpdateException) {
//			//501-更改数据异常
//			rr.setState(501);
//		}else if(e instanceof DeleteException) {
//			//502-删除数据异常
//			rr.setState(502);
//		}
//		return rr;
//	}
//	

}
