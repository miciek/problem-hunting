#include <cstdio>
#include <algorithm>
#include <cmath>

#define DEBUG

using namespace std;

// DATA STRUCTURES AND CONSTS

struct Car {
  int index;
  int x;
  int width;
};

const int MAX_CARS = 50005;
Car init[MAX_CARS];
Car target[MAX_CARS];

// DEBUG & I/O HELPERS

void print_debug(Car cars[], int qty) {
  #ifdef DEBUG
  printf("Read %d cars:\n", qty);
  for(int i = 0; i < qty; i++) {
    printf("Car %d: {%d, %d, %d}, ", i, cars[i].index, cars[i].x, cars[i].width);
  }
  printf("\n");
  #endif
}

void read_cars_to(Car cars[], int qty) {
  int x1, y1, x2, y2;
  for(int i = 0; i < qty; i++) {
    scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
    cars[i] = (Car){i, min(x1, x2), fabs((x2 - x1) * (y2 - y1))};
  }
}

int main() {
  int T;
  scanf("%d", &T);
  while(T--) {
    int n, w;
    scanf("%d %d", &n, &w);

    #ifdef DEBUG
    printf("==========\nCase %d for n = %d and w = %d\n", T, n, w);
    #endif

    read_cars_to(init, n);
    read_cars_to(target, n);

    print_debug(init, n);
    print_debug(target, n);
  }
  return 0;
}
