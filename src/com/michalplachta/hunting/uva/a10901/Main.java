package com.michalplachta.hunting.uva.a10901;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * UVA 10901 - Ferry Loading III solution
 * 
 * http://uva.onlinejudge.org/external/109/10901.html
 * 
 * Description is wrong. It should be: "The arrival times for each test case are non-decreasing.".
 * 
 * @category ad-hoc, queue, simulation
 * @author micio
 */
class Main {
    private final Queue<Car> left = new LinkedList<Car>();
    private final Queue<Car> right = new LinkedList<Car>();
    private final LinkedHashMap<Car, String> cars = new LinkedHashMap<Car, String>();
    private final int maxCars;
    private final int travelTime;
    
    public Main(int maxCars, int travelTime) {
        this.maxCars = maxCars;
        this.travelTime = travelTime;
    }
    
    public void addNextCar(int arrivalTime, String where) { 
        Car c = new Car(arrivalTime);        
        cars.put(c, "");        
        if (where.equals("left")) {
            left.add(c);
        } else if (where.equals("right")) {
            right.add(c);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public Collection<String> simulateFerry() {
        int time = 0;
        Queue<Car> current = left;
        Queue<Car> other = right;
        while (!left.isEmpty() || !right.isEmpty()) {
            int loadedCars = 0;
            while (loadedCars < maxCars && !current.isEmpty() && current.peek().arrivalTime <= time) {
                cars.put(current.poll(), Integer.toString(time + travelTime));
                loadedCars++;
            }
            
            if (loadedCars > 0 || (!other.isEmpty() && other.peek().arrivalTime <= time)) {
                time += travelTime;
                Queue<Car> temp = current;
                current = other;
                other = temp;
            } else {
                Integer nexttime = null;
                if(!current.isEmpty()) {
                    nexttime = current.peek().arrivalTime;
                }
                if(!other.isEmpty() && (nexttime == null || nexttime > other.peek().arrivalTime)) {
                    nexttime = other.peek().arrivalTime;
                }
                if(nexttime != null) {
                    time = nexttime;
                }
            }
        }
        
        return cars.values();
    }
    
    private static class Car {
        public final int arrivalTime;
        
        public Car(int arrivalTime) {
            this.arrivalTime = arrivalTime;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int c = in.nextInt();
        while (c-- > 0) {
            int n = in.nextInt();
            int t = in.nextInt();
            int m = in.nextInt();
            Main main = new Main(n, t);
            while (m-- > 0) {
                int arrival = in.nextInt();
                String where = in.nextLine().trim();
                main.addNextCar(arrival, where);
            }
            
            for (String i: main.simulateFerry()) {
                bw.write(i);
                bw.write('\n');
            }            
            
            if (c > 0) {
                bw.write('\n');
            }
        }
        
        bw.flush();
        in.close();
    }
}
