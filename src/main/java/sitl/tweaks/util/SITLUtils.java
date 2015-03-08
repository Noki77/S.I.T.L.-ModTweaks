package sitl.tweaks.util;

import java.util.ArrayList;
import java.util.List;

public class SITLUtils {
	public static List<String> shortStringToMcTT(String str, Object prefix, Object suffix){
		List<String> lst = new ArrayList<String>();
		String tmp_cur = "";
		int lastSpace = 0, lastSpaceRel = 0, i_cur = 0, i_counter = 0;
		while(!(i_cur >= str.length())){
			char c = str.charAt(i_cur);
			
			tmp_cur += c;
			if(i_counter >= 30){
				if(lastSpace != 0 || c == ' '){
					if(c == ' ')
						if(tmp_cur.charAt(i_counter-1) == ' ')
							lst.add((prefix != null ? prefix : "") + 
									tmp_cur.substring(0, i_counter-1)
									+ (suffix != null ? suffix : ""));
						else
							lst.add((prefix != null ? prefix : "") + tmp_cur + (suffix != null ? suffix : ""));
					else {
						lst.add((prefix != null ? prefix : "") + tmp_cur.substring(0, lastSpace) + (suffix != null ? suffix : ""));
						i_cur = lastSpaceRel;
					}
					
					lastSpace = 0;
					tmp_cur = "";
					i_counter = 0;
				} else {
					lst.add((prefix != null ? prefix : "") + tmp_cur + (suffix != null ? suffix : ""));
					tmp_cur = "";
					i_counter = 0;
				}
			}
			if(c == ' ') {
				lastSpace = i_counter;
				lastSpaceRel = i_cur;
			}

			i_cur++;
			i_counter++;
		}
		
		lst.add((prefix != null ? prefix : "") + tmp_cur + (suffix != null ? suffix : ""));
		return lst;
	}
	
	public static String convertListIntoString(List<String> lst){
		String str = "";
		for(String s : lst)
			str += s;
		return str;
	}
	
}
