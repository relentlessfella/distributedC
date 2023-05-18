public class Graph {
    int size;
    int numOfEdge;
    double[][] matrix;
    Edge[] edges;
    double[] distances; 

    public Graph(int size, int numOfEdges){
        this.size =  size;
        this.numOfEdge = numOfEdges;
        edges = new Edge[numOfEdges];
    }

    public int[] dijkstra(int source){
        boolean[] isUsed = new boolean[size];
        distances = new double[size];
        int[] path = new int[size];

        for(int i=0;i<size;i++){
            distances[i] = Double.MAX_VALUE;
        }

        int curr = source;
        distances[curr] = 0;
        path[curr] = -1;
        while(curr!=-1){
            isUsed[curr] = true;
            updateDistances(path, curr);
            curr = findMin(isUsed);
        }

        return path;
    }

    public int[] bellmanFord(int source){
        distances = new double[size];
        int[] path = new int[size];
        path[0] = -1;

        //init distances
        for(int i=0;i<size;i++){
            distances[i] = Double.MAX_VALUE;
        }
        distances[source] = 0;

        //start algorithm
        //temporary two variable
        int from;
        int to;
        for(int i=0;i<size-1;i++){
            for(int j=0;j<numOfEdge;j++){
                from = edges[j].source;
                to = edges[j].target;
                if(distances[to]>distances[from]+edges[j].distance){
                    distances[to] = distances[from]+edges[j].distance;
                    path[to] = from;
                }
            }
        }

        return path;
    }

    private void updateDistances(int[] path, int curr){
        double neighbors[] = matrix[curr];
        for(int i=0;i<size;i++){
            
            if(neighbors[i]>0&&distances[i]>neighbors[i]+distances[curr]){
                distances[i] = neighbors[i]+distances[curr];
                path[i] = curr;
            }
        }
    }

    private int findMin(boolean[] isUsed){
        double min = Integer.MAX_VALUE;
        int min_index = -1;

        for(int i=0;i<distances.length;i++){
            if(!isUsed[i]&&min>=distances[i]){
                min = distances[i];
                min_index = i;
            }
        }

        return min_index;
    }
}
