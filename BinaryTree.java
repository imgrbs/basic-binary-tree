package com.binary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class TreeNode {
	PersonAddress address;
	TreeNode left;
	TreeNode right;

	TreeNode(PersonAddress address) {
		this.address = address;
		this.left = null;
		this.right = null;
	}

	public PersonAddress getAddress() {
		return address;
	}

	public void setAddress(PersonAddress address) {
		this.address = address;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
}

class PersonAddress {
	private int sid;
	private String name;
	private String city;

	public PersonAddress(String sid, String name, String city) {
		this.sid = Integer.parseInt(sid);
		this.name = name;
		this.city = city;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}

public class BinaryTree {
	private static TreeNode root = null;

	public static void main(String[] args) {
		String path = "/Users/tae/Downloads/text.csv";
		File file = new File(path);

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String row;

			while ((row = br.readLine()) != null) {
				String[] arr = row.split(",");

				if (arr[0].equalsIgnoreCase("SID")) {
					continue;
				}

				PersonAddress address = new PersonAddress(arr[0], arr[1], arr[2]);

				if (root == null) {
					root = new TreeNode(address);
				}

				if (root.getAddress().getSid() == address.getSid()) {
					continue;
				}

				addTreeNode(root, address);
			}

			TreeNode node1 = findNode(root, 1);
			TreeNode node2 = findNode(root, 2);
			TreeNode node3 = findNode(root, 3);
			TreeNode node4 = findNode(root, 4);
			TreeNode node5 = findNode(root, 5);
			TreeNode node6 = findNode(root, 6);
			TreeNode node7 = findNode(root, 7);
			TreeNode node8 = findNode(root, 8);

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static TreeNode findNode(TreeNode node, int searchId) {
		if (node == null) {
			return null;
		}

		int currentId = node.getAddress().getSid();

		if (currentId == searchId) {
			return node;
		}

		TreeNode leftNode = node.getLeft();
		if (leftNode != null && leftNode.getAddress().getSid() == searchId) {
			return leftNode;
		}

		TreeNode rightNode = node.getRight();
		if (rightNode != null && rightNode.getAddress().getSid() == searchId) {
			return rightNode;
		}

		TreeNode result = null;

		if (currentId < searchId) {
			result = findNode(rightNode, searchId);
		}

		if (currentId > searchId) {
			result = findNode(leftNode, searchId);
		}

		return result;
	}

	private static void addTreeNode(TreeNode node, PersonAddress address) {
		int nodeId = node.getAddress().getSid();
		int addressSid = address.getSid();

		if (node.getLeft() == null) {
			node.setLeft(new TreeNode(address));
			return;
		}

		if (node.getRight() == null) {
			node.setRight(new TreeNode(address));
			return;
		}

		if (nodeId > addressSid) {
			TreeNode leftNode = node.getLeft();

			if (leftNode == null) {
				node.setLeft(new TreeNode(address));
			} else {
				addTreeNode(leftNode, address);
			}
		}

		if (nodeId < addressSid) {
			TreeNode rightNode = node.getRight();

			if (rightNode == null) {
				node.setRight(new TreeNode(address));
			} else {
				addTreeNode(rightNode, address);
			}
		}
	}
}
