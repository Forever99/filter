package com.zhku.jsj144.zk.filter.requestFilter;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//获取参数的三种方式
//方法1：request.getParameter("");    	返回类型：String
//方法2：request.getParameterNames();		返回类型：String【】
//方法3：request.getParameterMap();		返回类型：Map<String,String[]>

//思路分析：重心放在方法3：request.getParameterMap(); 因为此方法，实际功能涵盖了方法1和方法2
//把方法3实现了，然后方法1和方法2调用方法3就可以了

//注意：通用方法getParameterMap是存在弊端的。

//因为当servlet种存在几行请求参数代码时：
//request.getParamger("aaa");request.getParamger("bbb");request.getParamger("ccc");
//每行代码都会：调用 getParameterMap()方法，因此，每行代码执行时，都会获得三个参数：aaa,bbb,ccc的中文参数值，
//相当于：values[i]=new String(values[i].getBytes("iso-8859-1"),"utf-8");这行代码执行三次
//解释：代码执行第一次:中文参数，用iso-8859-1进行解码，然后用utf-8进行编码【因为一开始获得中文参数时是默认：iso-8859-1进行编码的】，所以
//此时是得到了正确的中文参数。       代码执行第二次：把正确的中文参数【utf-8编码过来的数值】,用iso-8859-1进行解码，然后用utf-8进行编码，此时
//获得了错误的中文参数，因为那个正确的中文参数，再次进行编码，解码用的是不同的码表，所以会出现乱码现象。   同理，代码执行第三次，也是跟代码执行第二次
//一样的结果，获得乱码。

//因此，我们要保证的是，对于获取过来的参数，我们只能获取一次，这样我们就可以获得正确的中文参数值了。
//解决思路：设置bool值，保证只进行一次获取，从而保证编码，解码的正确性。
//private boolean firstGet=false;//设置的bool数值

public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{

	//目的：获得原有的requset
	private HttpServletRequest request;
	
	//有参构造函数，将原来的request获取，然后进行相关的加强后返回
	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request=request;
	}

	@Override
	public String getParameter(String name) {
		Map<String, String[]> map = getParameterMap();//调用 getParameterMap()方法
		String[] values = map.get(name);
		//要进一步判断是否为空
		if(values!=null){
			String value=values[0];//返回数组的第一个元素		
			return value;
		}
		return null;
//		return super.getParameter(name);
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = getParameterMap();//调用 getParameterMap()方法
		
		String[] values = map.get(name);
		return values;
//		return super.getParameterValues(name);
	}
	
	//成员变量的bool数值，相当于一个全局变量
	private boolean firstGet=false;//设置的bool数值
	//默认是false，再调用一次后，即甚至为true。即不会第二次调用该方法
	
	@Override
	public Map<String, String[]> getParameterMap() {
		
		if(firstGet==false){//只有在第一次时，才会进行调用的操作
			//对于get和post的请求方式做不同的处理
			String method = request.getMethod();
			
			//post的请求方式
			if("post".equalsIgnoreCase(method)){
				try {
					request.setCharacterEncoding("utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return super.getParameterMap();
			}
			
			//get的请求方式
			else{
				
				Map<String, String[]> maps = request.getParameterMap();//所有参数
				
				Map<String,String[]> newMaps=new HashMap<String,String[]>();//保存修改后的数据
				//遍历获取到的所有参数
				for (int i = 0; i < maps.size(); i++) 
				{
		
					Set<String> keySet = maps.keySet();
					
					for (String key : keySet) 
					{
						String[] values = maps.get(key);//得到数值
						//对数值进行重新编码，解码
						for (int j=0;j<values.length;j++) 
						{
							try {
								values[i]=new String(values[i].getBytes("iso-8859-1"),"utf-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							
						}
						
						//因为java.util.Map所包装的HttpServletRequest对象的参数是不可改变的，
						//强行更改就会报java.lang.IllegalStateException: 
						//No modifications are allowed to a locked ParameterMap异常，
						//这个时候就会通过间接更改值的方式解决这个问题

						//所以 此段代码作废：    maps.put(key, values);//maps对于原来的可以进行覆盖
						//正确思路：是重新new一个新的map，然后进行赋值，最后进行返回，即可达到要求
						
						newMaps.put(key, values);//用新的map进行保存
					}
				}
				firstGet=true;//设置为true后，是为了避免二次使用
//				return maps;
				return newMaps;//修改后的maps
			}
		}
		
		//由于已经调用过了，所以直接返回上一次的数值即可，无须做任何处理
		return super.getParameterMap();
	}
}
