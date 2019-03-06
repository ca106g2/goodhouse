package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcutil_CompositeQuery_House {
	public static String get_aCondition_For_Oracle(String columnName, String value) {
		
		String aCondition = null ;
		
		if("hou_id".equals(columnName)||"hou_name".equals(columnName)||"hou_type".equals(columnName)||"hou_parkspace".equals(columnName)
			||"hou_cook".equals(columnName)||"hou_address".equals(columnName)||"lan_id".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";
		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for(String key : keys) {
			String value = map.get(key)[0];
			if(value != null && value.trim().length() !=0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				
				if(count == 1)
					whereCondition.append(" where "+aCondition);
				else
					whereCondition.append(" and " + aCondition);
				
				System.out.println("有送出查詢資料的欄位數" + count);
			}
		}
		
		 return whereCondition.toString();
	}
	
	
	public static void main(String[] args) {
		
/*
HOU_NAME VARCHAR2(200)  ,平價學生套房出租
HOU_TYPE VARCHAR2(25) ,公寓
HOU_PARKSPACE VARCHAR2(25),有**
HOU_COOK VARCHAR2(25)  ,可**
HOU_ADDRESS VARCHAR2(200)  ,台北市松山區復興北路
LAN_ID VARCHAR2(25)  ,L000000005
*/
		
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("hou_id", new String [] {"HOU0000001"});
		map.put("hou_name", new String [] {"平價學生套房出租"});
		map.put("hou_type", new String [] {"套房"});
		map.put("hou_parkspace", new String [] {"無"});
		map.put("hou_cook", new String [] {"不可"});
		map.put("hou_address", new String [] {"桃園市中壢區五興路331巷43號"});
		map.put("lan_id", new String [] {"L000000001"});
		map.put("action", new String [] {"getXXX"});
		
		String finalSQL = "select * from house"
							+ jdbcutil_CompositeQuery_House.get_WhereCondition(map)
							+"order by hou_id";
		System.out.println("●●finalSQL = " + finalSQL);
	}
}
