#include <cstdio>
#include <algorithm>
#include <cmath>

#define DEBUG

using namespace std;

struct Car {
  int index;
  int x;
  int width;
};

const int MAX_CARS = 50005;
Car init[MAX_CARS];
Car target[MAX_CARS];

void read_cars_to(Car cars[], int qty) {
  int x1, y1, x2, y2;
  for(int i = 0; i < qty; i++) {
    scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
    Car c = {i, min(x1, x2), abs((x2 - x1) * (y2 - y1))};
    cars[i] = c;
  }

  #ifdef DEBUG
  printf("Read %d cars:\n", qty);
  for(int i = 0; i < qty; i++) {
    printf("Car %d: {%d, %d, %d}, ", i, cars[i].index, cars[i].x, cars[i].width);
  }
  printf("\n");
  #endif
}

int main() {
  int T;
  scanf("%d", &T);
  while(T--) {
    int n, w;
    scanf("%d %d", &n, &w);

    #ifdef DEBUG
    printf("Case %d for n = %d and w = %d\n", T, n, w);
    #endif

    read_cars_to(init, n);
    read_cars_to(target, n);
  }
  return 0;
}
