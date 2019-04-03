package Utils;

public class StringUtil {

	/**
	 * 首字母大写转小写
	 * @param string
	 * @return
	 */
	public static String toLowerFirstOne(String string){
		if(Character.isLowerCase(string.charAt(0))){
			return string;
		}else{
			return (new StringBuilder()).append(Character.toLowerCase(string.charAt(0))).append(string.substring(1)).toString();
		}
	}
	
}
