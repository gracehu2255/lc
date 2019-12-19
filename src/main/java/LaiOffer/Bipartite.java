package LaiOffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Created by yuehu on 12/8/19.
 * Assumptions: The graph is not null
 */
public class Bipartite {
    public boolean isBipartite(List<GraphNode> graph) {
        //use 0 and 1 to denote two different groups
        //the map maintains for each node which group it belongs to
        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
        //the graph can be represented by a list of nodes (if it is not guaranteed to be connected)
        // we have to do BFS from each of the nodes
        for (GraphNode node : graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }
    private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
        //if this node has been traversed, no need to do BFS again
        if (visited.containsKey(node)) {
            return true;
        }

        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(node);
        //start breadth-first-search from the node, since the node has not been
        //visited, we can assign it to any of the groups, for example, group0.
        visited.put(node,0);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            int curGroup = visited.get(cur);
            int neiGroup = curGroup == 0 ? 1 : 0;
            for (GraphNode nei : cur.neighbors) {
                if (!visited.containsKey(nei)) {
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                } else if (visited.get(nei) != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }

}
