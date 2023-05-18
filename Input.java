import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);
    public static Map map;
    public static boolean isMapCreated = false;

    private static String[] main = {
        "0. Exit",
        "1. Create a map",
        "2. Return analyze",
        "3. Search vertex",
        "4. Sort vertex"
    };

    private static String[] createMap = {
        "0. Exit(will not save the map)",
        "1. Add location", 
        "2. Add street(edge)",
        "3. Finish"
    };

    public static void start(){
        System.out.println("\n<---Welcome to SDU MAP--->");
        while(true){
            main();
        }
    }

    private static void main(){
        Output.printMenu(main);

        int input = sc.nextInt(); sc.nextLine();
        switch(input){
            case 0:
                System.exit(0);
            case 1:
                createMap();
                break;
            case 2:
                analyze();
                break;
            case 3:
                searchVertex();
                break;
            case 4:
                sortVertex();
                break;

        }
    }

    private static void analyze(){
        Graph[] graphs = new Graph[4];
        for(int i=0;i<Map.types.length;i++){
            graphs[i] = Util.mapToGraph(map, i);
        }

        long[] times = new long[8];
        int[][] paths = new int[4][map.locations.size()];

        for(int i=0;i<graphs.length;i++){
            long startTime = System.nanoTime();
            paths[i] = graphs[i].dijkstra(0);
            times[i*2] = (System.nanoTime()-startTime)/1000;
            startTime = System.nanoTime();
            paths[i] = graphs[i].bellmanFord(0);
            times[i*2+1] = (System.nanoTime()-startTime)/1000;  
        }

        for(int i=0;i<Map.types.length;i++){
            System.out.print("\n"+Map.types[i]+": ");
            Output.printPath(paths[i], map.locations, Util.getDistance(map, paths[i]));
            System.out.print("Dijkstra: "+times[i*2]+"ms, ");
            System.out.println(" BeelmanFord: "+times[i*2+1]+"ms");
        }
    }

    private static void searchVertex(){
        System.out.print("Enter the vertex name: ");
        String input = sc.nextLine();
        String[] list = new String[map.locations.size()];
        for(int i=0;i<list.length;i++){
            list[i] = map.locations.get(i);
        }
        boolean result = Util.search(input, list);
        if(result){
            System.out.println("\nYes, we have localtion "+input);
        }else{
            System.out.println("\nSorry, we dont have such location");
        }
    }

    private static void sortVertex(){
        String[] list = new String[map.locations.size()];
        for(int i=0;i<list.length;i++){
            list[i] = map.locations.get(i);
        }

        System.out.println();
        Util.MSDSorting(list, 0, list.length);
        for(int i=0;i<list.length;i++){
            System.out.print((i+1)+"."+list[i]+" ");
            if(i%3==0&&i!=0){
                System.out.println();
            }
        }

        if(!((list.length-1)%4==0)){
            System.out.println();
        }
    }

    private static void createMap(){
        isMapCreated = false;
        map = new Map();
        while(true){
            Output.printMenu(createMap);
            int input = sc.nextInt(); sc.nextLine();
            switch(input){
                case 0:
                    map = new Map();
                    return;
                case 1:
                    addLocaltion();
                    break;
                case 2:
                    addStreet();
                    break;
                case 3:
                    isMapCreated = true;
                    return;
            }
        }
    }

    private static void addLocaltion(){
        System.out.print("\nPlease write the name of location: ");
        String name = sc.nextLine();
        map.locations.add(name);
    }

    private static void addStreet(){
        Output.printLocations(map);
        System.out.println("\nPlease write the data of street");
        System.out.println("format: a b distance ratio (a and b is index of locations)");
        System.out.println("ratio level should be 1-10");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int distance = sc.nextInt();
        int ratio = sc.nextInt();
        sc.nextLine();
        Street street = new Street(a, b, distance, ratio);
        map.streets.add(street);
    }
}
