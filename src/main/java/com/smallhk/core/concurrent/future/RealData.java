package com.smallhk.core.concurrent.future;

/**
 * @author eric
 *
 */
public class RealData implements Data {

	protected final String result;
	
	public RealData(String param){
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < 10;i++){
			sb.append(param);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				
			}
			
		}
		result = sb.toString();
	}
	
	public String get() {
		return result;
	}

}
