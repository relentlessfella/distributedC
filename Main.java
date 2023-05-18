public class Main {
    public static void main(String[] args) {
        boolean developerMode = true;

        if(developerMode){
            System.out.println("You are in developer mode now\n");
            test();
        }
        Input.start();
    }

    private static void test(){
        String[] locations = {
            "SDU", "Aport", "Alatau", "Baikonyr", "19", "KBTU"
        };

        int[] velocity = {
            90,60,80,4
        };

        //init map
        Map map = new Map();
        for(String location: locations){
            map.locations.add(location);
        }

        for(int i=0;i<locations.length;i++){
            for(int j=i+1;j<i+3&&j<locations.length;j++){
                int distance = (int)(Math.random()*10+2); //random distance 2-12km
                int ration = (int)(Math.random()*9+1); //random traffic jump level 1-10
                Street s = new Street(i, j, distance, ration, velocity);
                map.streets.add(s);
            }
        }


        Output.printMap(map);
        Input.map = map;
    }
}