package com.goodhouse.pointgoods.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TestpointgoodsDAO {
	
	public static void main(String[] args) {
		PointgoodsJDBCDAO dao = new PointgoodsJDBCDAO();
		
		//  新增
//		PointgoodsVO pointgoodsVO = new PointgoodsVO();
//		pointgoodsVO.setGood_nam("Tim");
//		pointgoodsVO.setGood_dsc("餅乾");
//		pointgoodsVO.setGood_amo(100);
//		pointgoodsVO.setGood_pri(3500);
//		pointgoodsVO.setGood_sta("P002");
//		byte[] pic1 = getPictureByteArray("D:\\ServLet\\CA106_WebApp\\RentHouse\\WebContent\\images\\test1.jpg");
//		pointgoodsVO.setGood_pic(pic1);
//		
//		dao.insert(pointgoodsVO);
		
		// 修改
//		PointgoodsVO pointgoodsVO = new PointgoodsVO();
//		pointgoodsVO.setGood_nam("David");
//		pointgoodsVO.setGood_dsc("大衛海鮮好吃");
//		pointgoodsVO.setGood_amo(123);
//		pointgoodsVO.setGood_pri(6000);
//		pointgoodsVO.setGood_sta("P003");
//		pointgoodsVO.setGood_id("P000000011");
//		byte[] pic1 = getPictureByteArray("D:\\ServLet\\CA106_WebApp\\RentHouse\\WebContent\\images\\test3.jpeg");
//		pointgoodsVO.setGood_pic(pic1);
//		
//		dao.update(pointgoodsVO);
		
		// 刪除
		
//		dao.delete("P000000011");
		
		// 查詢
//		PointgoodsVO pointgoodsVO = new PointgoodsVO();
//		
//		pointgoodsVO = dao.findByPrimaryKey("P000000002");
//		System.out.println(pointgoodsVO.getGood_id());
//		System.out.println(pointgoodsVO.getGood_nam());
//		System.out.println(pointgoodsVO.getGood_dsc());
//		System.out.println(pointgoodsVO.getGood_amo());
//		System.out.println(pointgoodsVO.getGood_pri());
//		System.out.println(pointgoodsVO.getGood_sta());
//		System.out.println(pointgoodsVO.getGood_pic());
		
		// 查詢全部
		List<PointgoodsVO> list = dao.getAll();
		for(PointgoodsVO pointgoodsVO : list) {
			System.out.print(pointgoodsVO.getGood_id() + ",");
			System.out.print(pointgoodsVO.getGood_nam() + ",");
			System.out.print(pointgoodsVO.getGood_dsc() + ",");
			System.out.print(pointgoodsVO.getGood_amo() + ",");
			System.out.print(pointgoodsVO.getGood_pri() + ",");
			System.out.print(pointgoodsVO.getGood_sta() + ",");
			System.out.print(pointgoodsVO.getGood_pic() + ",");
			System.out.println();
		}
	}
	
	public static byte[] getPictureByteArray(String path) {
		File file = new File(path);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}
}
