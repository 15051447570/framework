package com.snow.common.util;

import java.util.ArrayList;
import java.util.List;


/**
 * 基于内存分页的工具类
 * 
 * @author zhouhui
 * @version $Id: PagingUtil.java, v 0.1 2015年3月27日 下午3:20:05 zhouhui Exp $
 */
public class PagingUtil {

	/***
	 * 根据结果列表，页号和页大小计算需要返回的分页列表
	 * 
	 * @param list
	 *            结果列表
	 * @param pageNum
	 *            页号
	 * @param pageSize
	 *            页大小
	 * @return
	 * @throws Exception
	 *             <li>若传入的pageNum(页号)大于根据list长度和pageSize计算出来的总页数则抛出内存分页错误异常</li>
	 *
	 *
	 */
	public static <T> List<T> getPagingList(List<T> list, int pageNum,
			int pageSize) throws Exception {
		List<T> pagingList = new ArrayList<T>(20);
		if (list.size() == 0 || list.isEmpty() || pageNum == 0 || pageSize == 0) {
			// 如果参数不合法，则将list直接返回给调用端
			return list;
		}
		// 获取总数
		int total = list.size();
		// 获取总页数
		int pages = getPageSize(total, pageSize);

		// 如果分页数大于页总数
		if (pageNum > pages) {
			throw new RuntimeException("基于内存分页时候出错，原因为请求的分页数大于页总数");
		}

		if (pageNum == pages) {
			// 如果取得的为最后一页,或第一页
			pagingList = list.subList((pages - 1) * pageSize, total);
		} else {
			// 中间页
			pagingList = list.subList((pageNum - 1) * pageSize, pageNum
					* pageSize);
		}

		// 返回分页显示的列表
		return pagingList;
	}

	/***
	 * 根据记录总数和每页显示的记录数计算分页数 <br>
	 * demo如下
	 * 
	 * <pre>
	 * getPageSize(14,5)      = 3
	 * getPageSize(15,5)      = 3
	 * getPageSize(16,5)      = 4
	 * </pre>
	 * 
	 * @param total
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 *             若pageSize为0抛出参数错误异常
	 */
	public static int getPageSize(int total, int pageSize) throws Exception {
		if (pageSize <= 0 || total < 0) {
			throw new RuntimeException(
					"基于内存分页时候出错，原因totalSize和pageSize方法，totalSize=" + total
							+ ",pageSize=" + pageSize);
		}
		return (int) Math.ceil((double) total / pageSize);
	}
}
