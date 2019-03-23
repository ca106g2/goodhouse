/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_ForIndex {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("hou_rent".equals(columnName)) // 用於其他
			aCondition = columnName + "<=" + value ;
		else if ("hou_name".equals(columnName) || "hou_type".equals(columnName) || "hou_size".equals(columnName) || "hou_property".equals(columnName) || "hou_parkspace".equals(columnName)  || "hou_cook".equals(columnName)   || "hou_managefee".equals(columnName) || "hou_address".equals(columnName)  || "hou_address".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				System.out.println(aCondition);

				if (count == 1) {
					whereCondition.append(" where " + aCondition);
					System.out.println("count == 1 " + aCondition);
				}
				else {
					whereCondition.append(" and " + aCondition);
					System.out.println("else " +aCondition);
				}

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("hou_name", new String[] { "7001" });
		map.put("hou_rent", new String[] { "5000" });
		map.put("hou_type", new String[] { "KING" });
		map.put("hou_address", new String[] { "桃園" });
		map.put("hou_id", new String [] {"HOU0000001"});
		map.put("hou_name", new String [] {"平價學生套房出租"});
		map.put("hou_type", new String [] {"套房"});
		map.put("hou_parkspace", new String [] {"無"});
		map.put("hou_cook", new String [] {"不可"});
		map.put("hou_address", new String [] {"桃園市中壢區五興路331巷43號"});
		map.put("lan_id", new String [] {"L000000001"});
		map.put("action", new String [] {"getXXX"});
		

		String finalSQL = "select * from house "
				          + jdbcUtil_CompositeQuery_ForIndex.get_WhereCondition(map)
				          + "order by hou_id";
		System.out.println("����finalSQL = " + finalSQL);

	}
}
