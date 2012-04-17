package com.src;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.util.EncodingUtils;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import android.content.Context;

public class LoadScript
{
	//This is a utils class for running Lua function
	private InputStream in=null;
	private String res="";
	public LuaState luaState;
	private String fileName;
	private Context context;
	public LoadScript(Context c)
	{
		this.luaState=LuaStateFactory.newLuaState();
		this.luaState.openLibs();
		context=c;
		
	}
	public boolean openScript(String fileName)
	{
		try{
     		in=context.getResources().getAssets().open(fileName);
     		byte[]buffer=new byte[in.available()];
     		in.read(buffer);
     		res=EncodingUtils.getString(buffer, "ANSI");
     	}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		this.luaState.LdoString(res);
		this.fileName=fileName;
		return true;
	}
	public void closeScript()
	{
		this.luaState.close();
		
	}
	public LuaObject runScriptFunction(String functionName,ArrayList<Object> inputOb)
	{
		//Input array of Object
		this.luaState.getGlobal(functionName);
		for(Object b:inputOb)
		{
			this.luaState.pushJavaObject(b);
		}
		this.luaState.call(inputOb.size(), 1);
		this.luaState.setField(LuaState.LUA_GLOBALSINDEX, "res");
     	LuaObject res=this.luaState.getLuaObject("res");
     	return res;
	}
	public String getGlobalString(String globalName)
	{
		//Get global value of lua file
		this.luaState.getGlobal(globalName);
		return this.luaState.toString(luaState.getTop());
	}
}
