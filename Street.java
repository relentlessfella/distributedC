public class Street {
    int a; //street point a
    int b; //street point b
    int distance;
    int ratio; //congestion ratio
    
    /*
    * Velocity depend on type
    * 0 - car
    * 1 - bus
    * 2 - metro
    * 3 - walk
    */
    int[] velocity;

    public Street(int a, int b, int distance, int ratio, int[] velocity){
        this.a = a;
        this.b = b;
        this.distance = distance;
        this.ratio = ratio;
        this.velocity = velocity;
    }

    public Street(int a, int b, int distance, int ratio){
        this.a = a;
        this.b = b;
        this.distance = distance;
        this.ratio = ratio;
        this.velocity = new int[]{90, 60, 80, 4};
    }
}
