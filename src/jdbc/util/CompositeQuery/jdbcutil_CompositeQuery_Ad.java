package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcutil_CompositeQuery_Ad {
	public static String get_Condition_For_Oracle(String columnName, String value) {
		String Condition = null;
		
		if("ad_id".equals(columnName) || 
				"lan_id".equals(columnName) || "hou_id".equals(columnName)|| 
				"ad_sort_id".equals(columnName) || "ad_status".equals(columnName) || 
				"ad_forfree".equals(columnName) || "ad_statue".equals(columnName) ||
				"ad_paymethod".equals(columnName)) 
			Condition = columnName + " like '%" + value + "%'";
		return Condition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for(String key : keys) {
			String value = map.get(key)[0];
			if(value != null && value.trim().length() !=0 && !"action".equals(key)) {
				count++;
				String Condition = get_Condition_For_Oracle(key, value.trim());
				
				if(count == 1)
					whereCondition.append(" where "+Condition);
				else
					whereCondition.append(" and " + Condition);
				
				System.out.println("有送出查詢資料的欄位數" + count);
			}
		}
		
		 return whereCondition.toString();
	}
	
	public static void main(String[] args) {
		

		
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("ad_id", new String [] {"AD00000001"});
		map.put("lan_id", new String [] {"L000000001"});
		map.put("hou_id", new String [] {"HOU0000001"});
		map.put("ad_sort_id", new String [] {"ADS0000001"});
		map.put("ad_status", new String [] {"上架"});
		map.put("ad_forfree", new String [] {"綠園道之禪意淡定品質生活(含網路)"});
		map.put("ad_statue", new String [] {"已收"});
		map.put("ad_paymethod", new String [] {"無"});
		map.put("action", new String [] {"getXXX"});
		
		String finalSQL = "select * from ad"
							+ jdbcutil_CompositeQuery_Ad.get_WhereCondition(map)
							+"order by ad_id";
		System.out.println("●●finalSQL = " + finalSQL);
	}
	
}
