    import java.util.ArrayList;
    import java.util.List;

    public class DBSCAN implements AlgoClustering {

        private double eps;
        private int minPts;

        public DBSCAN(double eps, int minPts) {
            this.eps = eps;
            this.minPts = minPts;
        }

        @Override
        public int[] cluster(double[][] descriptions) {
            int[] clusters = new int[descriptions.length];
            boolean[] visited = new boolean[descriptions.length];
            int clusterId = 0;

            for (int i = 0; i < descriptions.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    List<Integer> neighbors = regionQuery(i, descriptions);
                    if (neighbors.size() >= minPts) {
                        expandCluster(i, neighbors, clusters, clusterId, visited, descriptions);
                        clusterId++;
                    }
                }
            }

            return clusters;
        }

        private void expandCluster(int i, List<Integer> neighbors, int[] clusters, int clusterId, boolean[] visited, double[][] descriptions) {
            clusters[i] = clusterId;

            int index = 0;
            while (index < neighbors.size()) {
                int j = neighbors.get(index);
                if (!visited[j]) {
                    visited[j] = true;
                    List<Integer> neighbors2 = regionQuery(j, descriptions);
                    if (neighbors2.size() >= minPts) {
                        neighbors.addAll(neighbors2);
                    }
                }
                if (clusters[j] == 0) {
                    clusters[j] = clusterId;
                }
                index++;
            }
            neighbors.clear(); // Clear the neighbors list to free up memory
        }

        private List<Integer> regionQuery(int i, double[][] descriptions) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < descriptions.length; j++) {
                if (i != j && euclideanDistance(descriptions[i], descriptions[j]) <= eps) {
                    neighbors.add(j);
                }
            }
            return neighbors;
        }

        private double euclideanDistance(double[] description1, double[] description2) {
            double sum = 0;
            for (int i = 0; i < description1.length; i++) {
                sum += Math.pow(description1[i] - description2[i], 2);
            }
            return Math.sqrt(sum);
        }
    }
