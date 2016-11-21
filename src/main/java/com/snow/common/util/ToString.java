package com.snow.common.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * ToString和序列化基类
 * 
 * <pre>
 * 	 系统中一般都要打印日志的，因为所有实体的toString()方法 都用的是简单的"+"，因为每"＋" 一个就会 new 一个 String 对象，这样如果系统内存小的话会暴内存（前提系统实体比较多）。使用ToStringBuilder就可以避免暴内存这种问题的。
 * </pre>
 * 
 * 
 * @author zhouhui
 * @version $Id: ToString.java, v 0.1 2015年3月27日 下午6:43:56 zhouhui Exp $
 */
public abstract class ToString implements Serializable {
	
	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
