/*This class provide methods for quick action, like change map to Graph,
and also contain some algorithm
*/

public class Util {
    public static Graph mapToGraph(Map map, int type){
        Graph g = new Graph(map.locations.size(), map.streets.size());
        int i = 0;
        int size = map.locations.size();
        double matrix[][] = new double[size][size];

        for(Street s: map.streets){
            Edge e = new Edge();
            e.source = s.a;
            e.target = s.b;


            if(s.velocity[type] == 0){
                e.distance = Double.MAX_VALUE;
            }

            //ratio effect to distance(virtual),but does not effect walk and metro.
            if(type == 0){
                e.distance = s.distance+s.distance*(s.ratio/5.0);
            }else if(type == 1){
                e.distance = s.distance+s.distance*(s.ratio/20.0);
            }else if(type == 2 || type == 3){
                e.distance = s.distance;
            }

            g.edges[i] = e;
            matrix[e.source][e.target] = e.distance;
            i++;
        }

        g.matrix = matrix;

        return g;
    }

    public static int getDistance(Map map, int[] path){
        int curr = path.length-1;
        int distance = 0;
        while(curr!=-1){
            distance += map.streets.get(curr).distance;
            curr = path[curr];
        }

        return distance;
    }


    //String sorting by using MSD algorithm
    public static void MSDSorting(String[] list, int n, int length){
        String[][] buckets = new String[44][list.length];
        int[] order = new int[44];
        int k = 0;

        for(int i=0;i<length;i++){
            int index;
            String s = list[i];
            if(n>=s.length()){
                index = 0;
            }else{
                char c = Character.toUpperCase(s.charAt(n));
                index = ((int)c)-'0'-1;
            }
            buckets[index][order[index]] = s;
            order[index]++;
        }

        for(int i=0;i<buckets.length;i++){
            if(order[i]>1&&i!=0){
                for(int j=0;j<order[i];j++){
                }

                MSDSorting(buckets[i], n+1, order[i]);
            }

            for(int j=0;j<order[i];j++){
                list[k] = buckets[i][j];
                k++;
            }
        }
    }


    //Search function
    public static boolean search(String keyWord, String[] list){
        String key = keyWord.toUpperCase();
        TrieNode root = new TrieNode();
        for(int i=0;i<list.length;i++){
            insertTrieNode(root, list[i]);
        }

        TrieNode curr = root;
        for(char c: key.toCharArray()){
            int index = c-'!';
            if(curr.children[index]==null){
                return false;
            }

            curr = curr.children[index];
        }

        return curr.isEndOfWord;
    }

    private static void insertTrieNode(TrieNode curr, String word){
        word = word.toUpperCase();
        for(char c: word.toCharArray()){
            int index = c-'!';
            System.out.println(c+" "+index);
            if(curr.children[index]==null){
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

}

class TrieNode{
    TrieNode[] children = new TrieNode[58];
    boolean isEndOfWord;
}
