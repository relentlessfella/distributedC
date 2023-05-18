import java.util.ArrayList;
import java.util.Date;

public class Output {
    public static void printPath(int[] path, ArrayList<String> locations, int distance){
        ArrayList<String> list = new ArrayList<>();
        
        int temp = path.length-1;
        while(temp!=-1){
            list.add(locations.get(temp));
            temp = path[temp];
        }

        for(int i=list.size()-1;i>0;i--){
            System.out.print(list.get(i)+" -> ");
        }
        System.out.print(list.get(0));
        System.out.println("  Distance: "+distance+"km");
    }

    public static void print(long[] time1, long[] time2, Date date){

    }

    public static void printMap(Map map){

        for(Street s: map.streets){
            System.out.print(map.locations.get(s.a)+" - "+map.locations.get(s.b));
            System.out.println(" "+s.distance+"km  "+s.ratio+" level");
        }
    }


    public static void printLocations(Map map){
        System.out.println("\nLocations:");
        int k = 0;
        for(int i=0;i<map.locations.size();i++){
            System.out.print(i+"."+map.locations.get(i)+" ");
            k++;
            if(k%3==0){
                k=0;
                System.out.println();
            }
        }
    }

    public static void printMenu(String[] list){
        System.out.println();
        for(String s: list){
            System.out.println(s);
        }
        System.out.print("\nPlease choose a option: ");
    }
}
