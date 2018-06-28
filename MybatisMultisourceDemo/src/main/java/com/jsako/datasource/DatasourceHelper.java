package com.jsako.datasource;

public class DatasourceHelper {
	public static ThreadLocal<String> datasource=new ThreadLocal<String>();
	
	public static String getDatasource(){
		return datasource.get();
	}
	
	public static void setDataSource(String datasource){
		DatasourceHelper.datasource.set(datasource);
	}
	
	public static void remove(){
		datasource.remove();
	}
}
