package LaiOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yuehu on 12/14/19.
 * solution: after detecing the first node that missed one child, then check whether all following
 * nodes expanded to see whether they have any node generated (if any - then false)
 */
public class CheckCompleted {
    public boolean isComplete(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //if the flag is set true, there should not be any child nodes afterwards.
        boolean flag = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            //if any of the child is not present, set the flag to true.
            if (cur.left == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
            //if flag is not set and left child is present
                queue.offer(cur.left);
            }
            //same logic applied to the right child
            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(8);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);


        root.left = null;
        root.right = t2;
        t1.left = t3;
        t1.right = t4;

        CheckCompleted s = new CheckCompleted();
        System.out.println(s.isComplete(root));

    }
}
