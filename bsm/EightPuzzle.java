package com.gwh.rgzn.bsm;

import java.util.Arrays;

public class EightPuzzle implements Cloneable {

	/* 利用一个二维的数组来存储数据 */
	public int[][] data;
	private int blankPos_x, blankPos_y;
	private int depth;

	// 无参构造函数
	public EightPuzzle() {
		data = new int[3][3];
	}

	// 传递一个数组，进行初始化的构造函数
	public EightPuzzle(int[][] data) {
		this.data = data;
	}

	// 判断是不是和目标位置相同
	/*
	 * int[][] data1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; int[][] data2 = new
	 * int[][]{{1,2,3},{4,5,6},{7,8,9}};
	 * System.out.println("Equals--->"+Arrays.equals(data1, data2)); false
	 * System.out.println("deepEquals--->"+Arrays.deepEquals(data1, data2));
	 * true
	 */
	public boolean isEquals(EightPuzzle ep) {
		return Arrays.deepEquals(this.data, ep.data);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(20);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(this.data[i][j]);
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	// 获取空格的位置
	public void getPostion() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.data[i][j] == 0) {
					this.setBlankPos_x(i);
					this.setBlankPos_y(j);
				}
			}
		}
	}

	public void setBlankPos_x(int blankPos_x) {
		this.blankPos_x = blankPos_x;
	}

	public void setBlankPos_y(int blankPos_y) {
		this.blankPos_y = blankPos_y;
	}

	public int getBlankPos_x() {
		return blankPos_x;
	}

	public int getBlankPos_y() {
		return blankPos_y;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void print() {
		System.out.println(this.toString());
	}

	// 浅拷贝
	@Override
	protected EightPuzzle clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new EightPuzzle(Arrays.copyOf(this.data, this.data.length));
	}

	// 深拷贝
	public EightPuzzle depthClone() {
		EightPuzzle tmp_ep = new EightPuzzle();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				tmp_ep.data[i][j] = this.data[i][j];
		tmp_ep.depth = this.depth;
		return tmp_ep;
	}

	public static void main(String[] args) {

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.isEquals((EightPuzzle) obj);
		// &&(this.getDepth() == ((EightPuzzle)obj).getDepth()
	}

}
