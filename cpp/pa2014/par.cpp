#include <cstdio>
#include <algorithm>
#include <cmath>

#define DEBUG

#ifdef DEBUG
#define D(x) x
#else
#define D(x)
#endif

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
Car temp[MAX_CARS];

// DEBUG & I/O HELPERS

void print_cars(Car cars[], int qty) {
  printf("%d cars: ", qty);
  for(int i = 0; i < qty; i++) {
    printf("Car %d: {%d, %d, %d}, ", i, cars[i].index, cars[i].x, cars[i].width);
  }
  printf("\n");
}

void read_cars_to(Car cars[], int qty) {
  int x1, y1, x2, y2;
  for(int i = 0; i < qty; i++) {
    scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
    cars[i] = (Car){i, min(x1, x2), fabs((x2 - x1) * (y2 - y1))};
  }
}

// SOLUTION PARTS
bool cmp_car_by_x(Car c1, Car c2) {
  return c1.x < c2.x;
}

void sort_cars(Car cars[], int qty, bool (*cmp)(Car, Car)) {
  sort(cars, cars+qty, cmp);
}

bool merge_sort_and_check_whether_inversions_are_below_w(int w) {


  return true;
}

void unit_tests();

int main() {
  int T;
  scanf("%d", &T);
  while(T--) {
    int n, w;
    scanf("%d %d", &n, &w);

    D(printf("==========\nCase %d for n = %d and w = %d\n", T, n, w);)

    read_cars_to(init, n);
    read_cars_to(target, n);

    D(print_cars(init, n);)
    D(print_cars(target, n);)

    sort_cars(init, n, cmp_car_by_x);
    bool result = merge_sort_and_check_whether_inversions_are_below_w(w);
    printf("%s\n", result ? "TAK" : "NIE");
  }

  D(unit_tests();)
  return 0;
}

// UNIT TESTS FOR EACH PART OF THE ALGORITHM

bool sorting_by_x() {
  Car arr[] = {(Car){0, 2, 0}, (Car){0, 1, 0}, (Car){0, 0, 0}};
  sort_cars(arr, 3, cmp_car_by_x);
  return arr[0].x == 0 && arr[1].x == 1 && arr[2].x == 2;
}

void unit_tests() {
  printf("sorting_by_x: %d\n", sorting_by_x());
}
