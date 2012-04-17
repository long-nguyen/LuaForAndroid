package com.src;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.util.EncodingUtils;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FirstLuaJavaAppActivity extends Activity {
    /** Called when the activity is first created. */
	private	String fileName="testLua.lua";
	private InputStream in=null;
	private String res="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv=new TextView(this);
        setContentView(tv);
        /*
         * Example 1 : Load String text
        	LuaState L=LuaStateFactory.newLuaState();				//LuaStateFactory acts like a factory to manage life time of Lua virtual machine
        	L.openLibs();											//Virtual machine is ready to work
        	L.LdoString("text='Hello Android, I am ádasd.'");			//Load string để thực thi
        	L.getGlobal("text");									//Lấy biến global( lúc này là biến text), đưa giá trị này vào stack
      		String text=L.toString(-1);								//-1 là top của stack, lấy ra
      		tv.setText(text);
      		
         */
        
        /*
         * Example 2: Load function in text
           
            LuaState L=LuaStateFactory.newLuaState();
	        L.openLibs();
	        String str="function imprime(str) print(str);return 'long',1 end;";			//Ham tra ve 2 gia tri la 'long' va 1
	        L.LdoString(str);															//Bien thanh lua code
	        LuaObject funct= L.getLuaObject("imprime");									//Dùng LuaObject để gọi hàm, chu y la ham print se ko dc Luajava catch lay
	        Object[]states;																//Dùng để chứa các giá trị trả về
	        try{
	        	states=funct.call(new Object[]{"Long"}, 2);								//	truyen vao 2 tham so, 1 la tham so ham, 2 la so ket qua tra ve
	            tv.setText(states[0]+" "+states[1]);
	            setContentView(tv);
	        }catch (Exception e) {
				// TODO: handle exception
	        	e.printStackTrace();
			}
         */
        
        /*
         * Example 3: Lua with parameters and load file
	        LuaState L=LuaStateFactory.newLuaState();
	        L.openLibs();
	        //Load file content
	        try{
	        	in=this.getResources().getAssets().open(fileName);
	        	int length=in.available();		//So bye
	        	byte[]buffer=new byte[length];
	        	in.read(buffer);
	        	res=EncodingUtils.getString(buffer, "ANSI");
	        }catch (IOException e) {
				e.printStackTrace();
			}
	        //tao Lua code
	        L.LdoString(res);
	        LuaObject func=L.getLuaObject("testAdd");
	        Object[]result;
	      	try
	      	{
	      		result=func.call(new Object[]{2,5}, 1);				//Truyen vao 2 tham so va chi tra ve 1
	    	   tv.setText("Sum: "+result[0]);
	    	   setContentView(tv);
	      	}catch (LuaException e) 
	      	{
				e.printStackTrace();
	      	}
	      	L.close();
       */
        
        /*
         * Example 4: pass Java variable- cách 1
         	LuaState L=LuaStateFactory.newLuaState();
         	L.openLibs();
         	try{
         		in=this.getResources().getAssets().open(fileName);
         		byte[]buffer=new byte[in.available()];
         		in.read(buffer);
         		res=EncodingUtils.getString(buffer, "ANSI");
         	}catch (Exception e) {
				e.printStackTrace();
			}
         	L.LdoString(res);
         	L.getField(LuaState.LUA_GLOBALSINDEX, "testObject");		//Retrieve the function 
         	SimpleClassData v=new SimpleClassData(2);
         	try{
         		L.pushObjectValue(v);					//push object vao stack cua Lua
         		L.call(1, 1);							//Truyen vao 1 bien, 1 return
         		L.setField(LuaState.LUA_GLOBALSINDEX, "returnVal");		//Set giá trị trả về
         		LuaObject l=L.getLuaObject("returnVal");
         		tv.setText("Result:"+l.getObject());		
         	}catch (LuaException e) {
				e.printStackTrace();
			}
         	L.close();
         */
        
        /*
         * Example 5: Pass java variable- cách 2
        	LuaState L=LuaStateFactory.newLuaState();
        	L.openLibs();
        	try{
         		in=this.getResources().getAssets().open(fileName);
         		byte[]buffer=new byte[in.available()];
         		in.read(buffer);
         		res=EncodingUtils.getString(buffer, "ANSI");
         	}catch (Exception e) {
				e.printStackTrace();
			}
         	L.LdoString(res);
         	L.getGlobal("testObject");					//Lấy hàm
         	SimpleClassData v=new SimpleClassData(3);		
         	L.pushJavaObject(v);						//PushObject
         	L.call(1, 1);								//Thực thi
         	
         	//Lấy kết quả trả về
         	L.setField(LuaState.LUA_GLOBALSINDEX, "returnVal");
         	LuaObject res=L.getLuaObject("returnVal");
         	try
         	
			{
				tv.setText("res:"+res.getObject());
			} catch (LuaException e)
			{
				e.printStackTrace();
			}
         */
        
//        /*
//         * cách 3: Dùng utils viết sẵn
         	LoadScript script=new LoadScript(this);
         	if(script.openScript(fileName))
         	{
         		ArrayList<Object> b=new ArrayList<Object>();
         		b.add(new SimpleClassData(3));
         		LuaObject res=script.runScriptFunction("testObject", b);
         		tv.setText("res:"+res.toString());
         		script.closeScript();
         	}
         	 
//         */
    }
}
