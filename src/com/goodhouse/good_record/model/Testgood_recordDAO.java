package com.goodhouse.good_record.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Testgood_recordDAO {

	public static void main(String[] args) {
		Good_recordJDBCDAO dao = new Good_recordJDBCDAO();
		
		// 新增
//		Good_recordVO good_recordVO = new Good_recordVO();
//		good_recordVO.setMem_id("M000000003");
//		good_recordVO.setGood_rec_des("回復文章+1分");
//		Timestamp recorddate = new Timestamp(System.currentTimeMillis());
//		good_recordVO.setGood_rec_dat(recorddate);
//		
//		dao.insert(good_recordVO);
		
		// 刪除
		
//		dao.delete("R000000011");
		
		// 查詢
//		Good_recordVO good_recordVO = new Good_recordVO();
//		
//		good_recordVO = dao.findByPrimaryKey("R000000003");
//		System.out.println(good_recordVO.getGood_rec_id());
//		System.out.println(good_recordVO.getMem_id());
//		System.out.println(good_recordVO.getGood_rec_des());
//		System.out.println(good_recordVO.getGood_rec_dat());
		
		// 查詢全部
		List<Good_recordVO> list = dao.getAll();
		for(Good_recordVO good_recordVO: list) {
			System.out.print(good_recordVO.getGood_rec_id() + ",");
			System.out.print(good_recordVO.getMem_id() + ",");
			System.out.print(good_recordVO.getGood_rec_des() + ",");
			System.out.print(good_recordVO.getGood_rec_dat() + ",");
			System.out.println();
		}
	}

}
