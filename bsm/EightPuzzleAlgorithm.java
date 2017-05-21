package com.gwh.rgzn.bsm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class EightPuzzleAlgorithm {

	private int[][] array = new int[3][3];
	private int[][] target = new int[3][3];
	private EightPuzzle ep, target_ep;
	private int depth = 0;
	private int maxDepth = 0;
	private Stack<EightPuzzle> ep_stack = new Stack<EightPuzzle>();
	private LinkedList<EightPuzzle> searched_list = new LinkedList<EightPuzzle>();
	private Queue<EightPuzzle> ep_queue = new LinkedList<EightPuzzle>();

	public EightPuzzleAlgorithm() {

		// 初始化栈和队列，以及列表
		ep_stack.clear();
		searched_list.clear();
		ep_queue.clear();

		Scanner scanner = new Scanner(System.in);
		// 输入初始位置
		System.out.println("请输入初始位置，用0代表空白块，例如：2 0 3 1 8 4 7 6 5");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				array[i][j] = scanner.nextInt();
			}
		}
		// 输入目标位置
		System.out.println("请输入目标位置，用0代表空白块，例如：1 2 3 8 0 4 7 6 5");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				target[i][j] = scanner.nextInt();
			}
		}
		// 最大界限
		System.out.print("请输入有界搜索的最大界限：");
		maxDepth = scanner.nextInt();
		ep = new EightPuzzle(array);
		ep.setDepth(depth);
		// 设置栈底元素
		ep_stack.push(ep);
		target_ep = new EightPuzzle(target);
		scanner.close();
		// 设置队首元素
		ep_queue.offer(ep);
	}

	// 深度优先搜索
	// 栈的方式实现
	public void depthFirstSearch() {
		System.out.println("\n现在开始深度优先搜索方法路径！");
		if (!searched_list.isEmpty())
			searched_list.clear();

		while (!ep_stack.isEmpty()) {
			EightPuzzle move_ep = ep_stack.pop();
			depth = move_ep.getDepth();
			move_ep.getPostion();
			int x = move_ep.getBlankPos_x(), y = move_ep.getBlankPos_y();
			move_ep.print();
			searched_list.add(move_ep);

			if (move_ep.isEquals(target_ep)) {
				System.out.println("深度优先搜索成功！");
				return;
			}
			depth++;
			EightPuzzle temp = null;

			temp = move_ep.depthClone();

			if (EightPuzzleOperator.canMove(x, y, 1)) {
				temp = EightPuzzleOperator.movePosition(move_ep, 1);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_stack.push(temp);
				}
			}
			if (EightPuzzleOperator.canMove(x, y, 2)) {
				temp = EightPuzzleOperator.movePosition(move_ep, 2);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_stack.push(temp);
				}
			}
			if (EightPuzzleOperator.canMove(x, y, -1)) {
				temp = EightPuzzleOperator.movePosition(move_ep, -1);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_stack.push(temp);
				}
			}
			if (EightPuzzleOperator.canMove(x, y, -2)) {
				temp = EightPuzzleOperator.movePosition(move_ep, -2);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_stack.push(temp);
				}
			}
		}
		System.out.println("深度优先搜索失败！");
	}

	// 深度优先搜索(有界，最大深度为maxDepth)
	// 栈的方式实现搜索
	public void boundedDepthFirstSearch() {
		System.out.println("\n现在开始有界深度优先搜索方法路径！");
		if (!searched_list.isEmpty())
			searched_list.clear();
		while (!ep_stack.isEmpty()) {
			EightPuzzle move_ep = ep_stack.pop();
			depth = move_ep.getDepth();
			move_ep.getPostion();
			int x = move_ep.getBlankPos_x(), y = move_ep.getBlankPos_y();
			move_ep.print();
			searched_list.add(move_ep);

			if (move_ep.isEquals(target_ep)) {
				System.out.println("有界深度优先搜索成功！");
				return;
			}
			if (depth < maxDepth - 1) {
				depth++;
				EightPuzzle temp = null;

				temp = move_ep.depthClone();

				if (EightPuzzleOperator.canMove(x, y, 1)) {
					temp = EightPuzzleOperator.movePosition(move_ep, 1);
					temp.setDepth(depth);
					if (!searched_list.contains(temp) || (searched_list.contains(temp)
							&& searched_list.get(searched_list.indexOf(temp)).getDepth() != temp.getDepth())) {
						ep_stack.push(temp);
					}
				}
				if (EightPuzzleOperator.canMove(x, y, 2)) {
					temp = EightPuzzleOperator.movePosition(move_ep, 2);
					temp.setDepth(depth);
					if (!searched_list.contains(temp) || (searched_list.contains(temp)
							&& searched_list.get(searched_list.indexOf(temp)).getDepth() != temp.getDepth())) {
						ep_stack.push(temp);
					}
				}
				if (EightPuzzleOperator.canMove(x, y, -1)) {
					temp = EightPuzzleOperator.movePosition(move_ep, -1);
					temp.setDepth(depth);
					if (!searched_list.contains(temp) || (searched_list.contains(temp)
							&& searched_list.get(searched_list.indexOf(temp)).getDepth() != temp.getDepth())) {
						ep_stack.push(temp);
					}
				}
				if (EightPuzzleOperator.canMove(x, y, -2)) {
					temp = EightPuzzleOperator.movePosition(move_ep, -2);
					temp.setDepth(depth);
					if (!searched_list.contains(temp) || (searched_list.contains(temp)
							&& searched_list.get(searched_list.indexOf(temp)).getDepth() != temp.getDepth())) {
						ep_stack.push(temp);
					}
				}
			}
		}
		System.out.println("有界深度优先搜索失败！");
	}

	// 宽度（广度）优先搜索实现
	public void breadthFirstSearch() {
		if (!searched_list.isEmpty())
			searched_list.clear();
		System.out.println("\n现在开始广度优先搜索方法路径！");
		while (!ep_queue.isEmpty()) {
			EightPuzzle move_ep = ep_queue.poll();
			depth = move_ep.getDepth();
			move_ep.getPostion();
			int x = move_ep.getBlankPos_x(), y = move_ep.getBlankPos_y();
			move_ep.print();
			searched_list.add(move_ep);

			if (move_ep.isEquals(target_ep)) {
				System.out.println("广度优先搜索成功！");
				return;
			}
			depth++;
			EightPuzzle temp = null;

			temp = move_ep.depthClone();

			if (EightPuzzleOperator.canMove(x, y, 1)) {
				temp = EightPuzzleOperator.movePosition(move_ep, 1);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_queue.offer(temp);
				}
			}
			if (EightPuzzleOperator.canMove(x, y, 2)) {
				temp = EightPuzzleOperator.movePosition(move_ep, 2);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_queue.offer(temp);
				}
			}
			if (EightPuzzleOperator.canMove(x, y, -1)) {
				temp = EightPuzzleOperator.movePosition(move_ep, -1);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_queue.offer(temp);
				}
			}
			if (EightPuzzleOperator.canMove(x, y, -2)) {
				temp = EightPuzzleOperator.movePosition(move_ep, -2);
				temp.setDepth(depth);
				if (!searched_list.contains(temp)) {
					ep_queue.offer(temp);
				}
			}
		}
		System.out.println("广度优先搜索失败！");
	}

	/*
	 * public void search() { // this.getPostion(); //
	 * this.depthFirstSearch(blankPos_x, blankPos_y, 1);
	 * this.depthFirstSearch(); this.boundedDepthFirstSearch();
	 * this.breadthFirstSearch(); }
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EightPuzzleAlgorithm epa = new EightPuzzleAlgorithm();
		epa.depthFirstSearch();
		epa.boundedDepthFirstSearch();
		epa.breadthFirstSearch();
	}
}
