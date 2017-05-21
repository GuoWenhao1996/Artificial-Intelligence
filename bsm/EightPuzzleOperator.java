package com.gwh.rgzn.bsm;

public class EightPuzzleOperator {

	// 判断是不是可以继续移动
	public static boolean canMove(int x, int y, int direction) {
		if ((direction == 1 && x == 0) || (direction == -1 && x == 2) || (direction == 2 && y == 2)
				|| (direction == -2 && y == 0)) {
			return false;
		}
		return true;
	}

	// 根据给出的参数，进行空格位置的移动
	// 其中1表示向上，2表示向右，-1表示向下，-2表示向左
	public static EightPuzzle movePosition(EightPuzzle ep, int args) {

		EightPuzzle arg_ep = null;

		arg_ep = ep.depthClone();
		arg_ep.getPostion();
		int blankPos_x = arg_ep.getBlankPos_x(), blankPos_y = arg_ep.getBlankPos_y();

		// 指令为向上移动
		if (args == 1) {
			int temp = arg_ep.data[blankPos_x][blankPos_y];
			arg_ep.data[blankPos_x][blankPos_y] = arg_ep.data[blankPos_x - 1][blankPos_y];
			arg_ep.data[blankPos_x - 1][blankPos_y] = temp;
			// 表示移动成功
		}
		// 指令为向下移动
		else if (args == -1) {
			int temp = arg_ep.data[blankPos_x][blankPos_y];
			arg_ep.data[blankPos_x][blankPos_y] = arg_ep.data[blankPos_x + 1][blankPos_y];
			arg_ep.data[blankPos_x + 1][blankPos_y] = temp;
			// 表示移动成功
		}
		// 指令为向右移动
		else if (args == 2) {
			int temp = arg_ep.data[blankPos_x][blankPos_y];
			arg_ep.data[blankPos_x][blankPos_y] = arg_ep.data[blankPos_x][blankPos_y + 1];
			arg_ep.data[blankPos_x][blankPos_y + 1] = temp;
			// 表示移动成功

		}
		// 指令为向左移动
		else if (args == -2) {
			int temp = arg_ep.data[blankPos_x][blankPos_y];
			arg_ep.data[blankPos_x][blankPos_y] = arg_ep.data[blankPos_x][blankPos_y - 1];
			arg_ep.data[blankPos_x][blankPos_y - 1] = temp;
			// 表示移动成功
		}
		// 指令输入错误
		else {
			return null;
		}
		return arg_ep;
	}
}
