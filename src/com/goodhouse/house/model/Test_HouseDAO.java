//package com.goodhouse.house.model;
//
//import java.io.*;
//import java.util.List;
//
//public class Test_HouseDAO {
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
//	public static void main(String[] args) {
//		HouseJDBCDAO dao = new HouseJDBCDAO();
////新增
//		
////		HouseVO ar1 = new HouseVO();
////		ar1.setHou_name("明子很長");
////		ar1.setHou_type("1");	
////		ar1.setHou_size("20坪");
////		ar1.setHou_property("有");
////		ar1.setHou_parkspace("有");
////		ar1.setHou_cook("有");
////		ar1.setHou_managefee("有");
////		ar1.setHou_address("左邊東路溪邊南路右邊房子左邊第二排");
////		ar1.setLan_id("L000000001");
////		ar1.setHou_rent(2500);
////		try {
////			byte[] pic2 = getPictureByteArray
////					("C:\\G2_workspaces\\CA106_G2_GoodHouse_TIM\\WebContent\\image\\p2.gif");
////			ar1.setHou_f_picture(pic2);
////			byte[] pic3 = getPictureByteArray
////					("C:\\G2_workspaces\\CA106_G2_GoodHouse_TIM\\WebContent\\image\\p1.jpg");
////			ar1.setHou_s_picture(pic3);
////			byte[] pic4 = getPictureByteArray
////					("C:\\G2_workspaces\\CA106_G2_GoodHouse_TIM\\WebContent\\image\\p2.gif");
////			ar1.setHou_t_picture(pic4);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		ar1.setHou_note("12");
////		dao.insert(ar1);
//
////修改
//		
////		HouseVO ar2 = new HouseVO();
////		ar2.setHou_name("21");
////		ar2.setHou_type("2");
////		ar2.setHou_size("2");
////		ar2.setHou_property("2");
////		ar2.setHou_parkspace("2");
////		ar2.setHou_cook("2");
////		ar2.setHou_managefee("2");
////		ar2.setHou_address("123132123132");
////		ar2.setHou_rent(0);
////		try {
////			byte[] pic2 = getPictureByteArray
////					("C:\\G2_workspaces\\CA106_G2_GoodHouse_TIM\\WebContent\\image\\p1.jpg");
////			ar2.setHou_f_picture(pic2);
////			byte[] pic3 = getPictureByteArray
////					("C:\\G2_workspaces\\CA106_G2_GoodHouse_TIM\\WebContent\\image\\p2.gif");
////			ar2.setHou_s_picture(pic3);
////			byte[] pic4 = getPictureByteArray
////					("C:\\G2_workspaces\\CA106_G2_GoodHouse_TIM\\WebContent\\image\\p1.jpg");
////			ar2.setHou_t_picture(pic4);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		ar2.setHou_note("12");
////		ar2.setHou_id("HUR0000008");
////		dao.update(ar2);
//		
////�R��
////		String ar3 = "HOU0000009";
////		dao.delete(ar3);
//		
////�d��
////		HouseVO ar4 = new HouseVO();
////		ar4 = dao.findByPrimaryKey("HUR0000008");
////		System.out.println(ar4.getHou_id());
////		System.out.println(ar4.getHou_name());
////		System.out.println(ar4.getHou_type());
////		System.out.println(ar4.getHou_size());
////		System.out.println(ar4.getHou_property());
////		System.out.println(ar4.getHou_parkspace());
////		System.out.println(ar4.getHou_cook());
////		System.out.println(ar4.getHou_managefee());
////		System.out.println(ar4.getHou_address());
////		System.out.println(ar4.getLan_id());
////		System.out.println(ar4.getHou_rent());
////		System.out.println(ar4.getHou_f_picture());
////		System.out.println(ar4.getHou_s_picture());
////		System.out.println(ar4.getHou_t_picture());
////		System.out.println(ar4.getHou_note());
//		
////12
////		List<HouseVO> list = dao.getAll();
////		for(HouseVO ar5 :list) {
////			System.out.println("Hou_id : "+ar5.getHou_id());
////			System.out.println("Hou_name : "+ar5.getHou_name());
////			System.out.println("Hou_type : "+ar5.getHou_type());
////			System.out.println("Hou_size : "+ar5.getHou_size());
////			System.out.println("Hou_property : "+ar5.getHou_property());
////			System.out.println("Hou_parkspace : "+ar5.getHou_parkspace());
////			System.out.println("Hou_cook : "+ar5.getHou_cook());
////			System.out.println("Hou_managefee : "+ar5.getHou_managefee());
////			System.out.println("Hou_address : "+ar5.getHou_address());
////			System.out.println("Lan_id : "+ar5.getLan_id());
////			System.out.println("Hou_rent : "+ar5.getHou_rent());
////			System.out.println("Hou_f_picture : "+ar5.getHou_f_picture());
////			System.out.println("Hou_s_picture : "+ar5.getHou_s_picture());
////			System.out.println("Hou_t_picture : "+ar5.getHou_t_picture());
////			System.out.println("Hou_note : "+ar5.getHou_note());
////			System.out.println();
////			
////		}
//	}
//
//}
