package com.goodhouse.house_track.model;

import java.util.List;

public class Test_HouseTrackJDBCDAO {

	public static void main(String[] args) {
		
		House_TrackJDBCDAO dao = new House_TrackJDBCDAO();
		
		House_TrackVO houTraVO = new House_TrackVO();
		
		//新增
//		houTraVO.setMem_id("M000000005");
//		houTraVO.setHou_id("HOU0000005");
//		houTraVO.setHou_tra_status("S1追蹤");
//		
//		dao.insert(houTraVO);
		
		//修改
//		House_TrackVO houTraVO2 = new House_TrackVO();
//		
//		houTraVO2.setHou_tra_id("HT00000001");
//		houTraVO2.setHou_id("HOU0000001");
//		houTraVO2.setMem_id("M000000001");
//		houTraVO2.setHou_tra_status("S2取消追蹤");
//		
//		dao.update(houTraVO2);
		
		//刪除
//		dao.delete("HT00000002");
		
		//單一查詢
//		House_TrackVO houTraVO3 = dao.findByPrimaryKey("HT00000003");
//		System.out.println(houTraVO3.getMem_id());
//		System.out.println(houTraVO3.getHou_id());
//		System.out.println(houTraVO3.getHou_tra_status());
		
		System.out.println("----------------------------------------------");
		
		//查詢全部
		List<House_TrackVO> list = dao.getAll();
		for(House_TrackVO aHouseTrackVO :list) {
			System.out.println(aHouseTrackVO.getHou_tra_id());
			System.out.println(aHouseTrackVO.getHou_id());
			System.out.println(aHouseTrackVO.getMem_id());
			System.out.println(aHouseTrackVO.getHou_tra_status());
		}
	}

}
