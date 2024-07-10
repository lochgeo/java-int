import java.util.*;

public class RouteMap {
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public void addRoute(int source, int destination) {
        map.putIfAbsent(source, new ArrayList<>());
        map.get(source).add(destination);
    }

    public List<Integer> startExploration(int startNode) {
        List<Integer> visitedNodes = new ArrayList<>();
        Set<Integer> visitedSet = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(startNode);
        while (!stack.isEmpty()) {
            int currentNode = stack.pop();
            if (!visitedSet.contains(currentNode)) {
                visitedSet.add(currentNode);
                visitedNodes.add(currentNode);

                List<Integer> neighbors = map.getOrDefault(currentNode, new ArrayList<>());
                for (int neighbor : neighbors) {
                    if (!visitedSet.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return visitedNodes;
    }

    public static void main(String[] args) {
        RouteMap routes = new RouteMap();
        routes.addRoute(1, 2);
        routes.addRoute(1, 3);
        routes.addRoute(2, 4);
        routes.addRoute(2, 5);
        routes.addRoute(3, 6);
        routes.addRoute(3, 7);

        System.out.println(routes.startExploration(1));
    }
}
