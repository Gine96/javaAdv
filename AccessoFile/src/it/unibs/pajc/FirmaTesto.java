package it.unibs.pajc;

import java.util.HashMap;

public class FirmaTesto implements StringTransformer{

	public String transform(String in) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (char cr : in.trim().toCharArray()) 
			if(map.containsKey(cr)) {
				int counter = map.get(cr);
				map.put(cr, counter+1);
			}else
				map.put(cr,1);
		StringBuffer sb=new StringBuffer();
		for(HashMap.Entry<Character, Integer> kv : map.entrySet()) 
			for(int i=0;i<kv.getValue();i++) 
				sb.append(kv.getKey());
		return sb.toString();
	}

}
