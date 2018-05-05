package gietlap.csgo.treebuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeFromFolderTree {
	private static String contentPath = "data/content";

	// public static void main() {
	//
	// }

	/**
	 * Compiles a JTree from file storage tree (fixed allocation).
	 * 
	 * @return The compiled JTree
	 */
	public static JTree getJTree() {
		JTree tree = new JTree();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Maps");
		for (DefaultMutableTreeNode mapNode : getMapNodes()) {
			for (DefaultMutableTreeNode sideNode : getSideNodes(mapNode)) {
				mapNode.add(sideNode);
				for (DefaultMutableTreeNode oppNode : getOppNodes(sideNode)) {
					sideNode.add(oppNode);
					for (DefaultMutableTreeNode varNode : getNadeOrRoutineNodes(oppNode)) {
						oppNode.add(varNode);
						for (DefaultMutableTreeNode insNode : getNadeOrRoutineInstanceNodes(varNode)) {
							varNode.add(insNode);
						}
					}
				}
			}
			root.add(mapNode);
		}
		tree.setModel(new DefaultTreeModel(root));
		return tree;
	}

	/**
	 * Get all map nodes.
	 * 
	 * @return Array of map nodes
	 */
	public static DefaultMutableTreeNode[] getMapNodes() {
		File file = new File(contentPath + "/Maps");
		File[] files = file.listFiles();
		List<File> dir = new ArrayList<>();
		List<DefaultMutableTreeNode> listDMTN = new ArrayList<>();
		if (files != null) {
			for (File ifile : files) {
				if (ifile.isDirectory()) {
					dir.add(ifile);
				}
			}
			for (File ifile : dir) {
				listDMTN.add(new DefaultMutableTreeNode(ifile.getName()));
			}
			return listDMTN.toArray(new DefaultMutableTreeNode[listDMTN.size()]);
		}
		return null;
	}

	/**
	 * Get all Side nodes from specified map node.
	 * 
	 * @param mapNode
	 *            where the sides should be retrieved and added to
	 * @return
	 */
	public static DefaultMutableTreeNode[] getSideNodes(DefaultMutableTreeNode mapNode) {
		File file = new File(contentPath + "/Maps/" + mapNode.toString());
		File[] files = file.listFiles();
		List<File> dir = new ArrayList<>();
		List<DefaultMutableTreeNode> listDMTN = new ArrayList<>();
		for (File ifile : files) {
			if (ifile.isDirectory()) {
				dir.add(ifile);
			}
		}
		for (File ifile : dir) {
			listDMTN.add(new DefaultMutableTreeNode(ifile.getName()));
		}
		return listDMTN.toArray(new DefaultMutableTreeNode[listDMTN.size()]);
	}

	/**
	 * Get various Opportunities like Grenades, routines etc. from specified side
	 * node
	 * 
	 * @param sideNode
	 *            where the opportunities should be retrieved and added to
	 * @return
	 */
	public static DefaultMutableTreeNode[] getOppNodes(DefaultMutableTreeNode sideNode) {
		File file = new File(contentPath + "/Maps/" + sideNode.getParent().toString() + "/" + sideNode.toString());
		File[] files = file.listFiles();
		List<File> dir = new ArrayList<>();
		List<DefaultMutableTreeNode> listDMTN = new ArrayList<>();
		for (File ifile : files) {
			if (ifile.isDirectory()) {
				dir.add(ifile);
			}
		}
		for (File ifile : dir) {
			listDMTN.add(new DefaultMutableTreeNode(ifile.getName()));
		}
		return listDMTN.toArray(new DefaultMutableTreeNode[listDMTN.size()]);
	}

	/**
	 * Get Grenade nodes or Routine nodes depending on which folder is accessed to
	 * from specified side node
	 * 
	 * @param oppNode
	 *            where the nodes should be retrieved and added to
	 * @return
	 */
	public static DefaultMutableTreeNode[] getNadeOrRoutineNodes(DefaultMutableTreeNode oppNode) {
		File file = new File(contentPath + "/Maps/" + oppNode.getParent().getParent().toString() + "/"
				+ oppNode.getParent().toString() + "/" + oppNode.toString());
		File[] files = file.listFiles();
		List<File> dir = new ArrayList<>();
		List<DefaultMutableTreeNode> listDMTN = new ArrayList<>();
		for (File ifile : files) {
			if (ifile.isDirectory()) {
				dir.add(ifile);
			}
		}
		for (File ifile : dir) {
			listDMTN.add(new DefaultMutableTreeNode(ifile.getName()));
		}
		return listDMTN.toArray(new DefaultMutableTreeNode[listDMTN.size()]);
	}

	/**
	 * Get Grenade instance nodes or Routine instance nodes depending on which
	 * folder is accessed to from specified node
	 * 
	 * @param varNode
	 *            where the nodes should be retrieved and added to
	 * @return
	 */
	public static DefaultMutableTreeNode[] getNadeOrRoutineInstanceNodes(DefaultMutableTreeNode varNode) {
		File file = new File(contentPath + "/Maps/" + varNode.getParent().getParent().getParent().toString() + "/"
				+ varNode.getParent().getParent().toString() + "/" + varNode.getParent().toString() + "/"
				+ varNode.toString());
		System.out.println(contentPath + "/Maps/" + varNode.getParent().getParent().getParent().toString() + "/"
				+ varNode.getParent().getParent().toString() + "/" + varNode.getParent().toString() + "/"
				+ varNode.toString());
		File[] files = file.listFiles();
		List<File> dir = new ArrayList<>();
		List<DefaultMutableTreeNode> listDMTN = new ArrayList<>();
		if (files != null) {
			for (File ifile : files) {
				if (ifile.isDirectory()) {
					dir.add(ifile);
				}
			}
			for (File ifile : dir) {
				listDMTN.add(new DefaultMutableTreeNode(ifile.getName()));
			}
		}
		return listDMTN.toArray(new DefaultMutableTreeNode[listDMTN.size()]);
	}

	public static DefaultMutableTreeNode getDirsAsDMTN(File path) {

		return null;
	}
}
