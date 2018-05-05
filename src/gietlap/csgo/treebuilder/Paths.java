package gietlap.csgo.treebuilder;

import javax.swing.tree.TreeNode;

public class Paths {
	public static String getNodePath(TreeNode node) {
		String path = node.toString();
		TreeNode prevNode = node.getParent();
		if (prevNode != null) {
			path = (getNodePath(prevNode) + "/" + path);
		}
		return path;
	}

}
