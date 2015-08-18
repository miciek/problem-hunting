#include <cstdio>
#include <algorithm>
#include <cmath>

//#define DEBUG

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
  int target_x;
  int position;
  int target_position;
};

const int MAX_CARS = 50005;
Car init[MAX_CARS];
Car target[MAX_CARS];
Car temp[MAX_CARS];

// DEBUG & I/O HELPERS

void print_cars(Car cars[], int qty) {
  printf("%d cars: ", qty);
  for(int i = 0; i < qty; i++)
  {
    printf("{%d -> %d, %d -> %d, %d}, ", cars[i].position, cars[i].target_position, cars[i].x, cars[i].target_x, cars[i].width);
  }
  printf("\n");
}

void read_cars_to(Car cars[], int qty) {
  int x1, y1, x2, y2;
  for(int i = 0; i < qty; i++) {
    scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
    cars[i] = (Car){i, min(x1, x2), fabs(y2 - y1), 0, 0, 0};
  }
}

// SOLUTION PARTS
bool cmp_car_by_x(const Car &c1, const Car &c2) {
  return c1.x < c2.x;
}

void sort_cars(Car cars[], int qty, bool (*cmp)(const Car &, const Car &)) {
  sort(cars, cars+qty, cmp);
}

void fill_position_and_target_position(int qty) {
  sort_cars(target, qty, cmp_car_by_x);
  for(int i = 0; i < qty; i++) {
    init[target[i].index].target_position = i;
    init[target[i].index].target_x = target[i].x;
  }

  sort_cars(init, qty, cmp_car_by_x);
  for(int i = 0; i < qty; i++) {
    init[i].position = i;
  }
}

bool should_c1_go_before_c2(const Car &c1, const Car &c2) {
  return c1.target_position < c2.target_position;
}

bool sort_init_and_check_inversions(const int start_inc, const int end_exc, const int max_inversion_width) {
  D(printf("sorting [%d to %d)\n", start_inc, end_exc);)
  if(end_exc <= start_inc + 1) return true;

  const int left_start_inc = start_inc;
  const int left_end_exc = start_inc + (end_exc - start_inc)/2;
  const int right_start_inc = left_end_exc;
  const int right_end_exc = end_exc;
  D(printf("new sortings are: [%d, %d) and [%d, %d)\n", left_start_inc, left_end_exc, right_start_inc, right_end_exc);)
  if(sort_init_and_check_inversions(left_start_inc, left_end_exc, max_inversion_width) && sort_init_and_check_inversions(right_start_inc, right_end_exc, max_inversion_width)) {
    int left = left_start_inc;
    int right = right_start_inc;
    const int to_be_merged = end_exc - start_inc;
    int currently_merging = 0;
    int width_of_the_biggest_already_merged = 0;
    D(printf("starting merge [%d, %d), left = %d, right = %d\n", start_inc, end_exc, left, right);)
    while(currently_merging < to_be_merged) {
      D(printf("merging %d: ", currently_merging);)
      if(left == left_end_exc || (right != right_end_exc && should_c1_go_before_c2(init[right], init[left]))) {
        temp[left_start_inc + currently_merging] = init[right];

        if(init[right].width > width_of_the_biggest_already_merged) {
          width_of_the_biggest_already_merged = init[right].width;
        }
        right++;
        D(printf("chose right: %d\n", right);)
      } else { // this is the only possible invalid inversion, so we need to check it
        if(width_of_the_biggest_already_merged + init[left].width > max_inversion_width) return false;
        temp[left_start_inc + currently_merging] = init[left];
        left++;
        D(printf("chose left: %d\n", left);)
      }
      currently_merging++;
    }

    D(printf("BEFORE MERGE: ");)
    D(print_cars(init + start_inc, end_exc - start_inc);)

    for(int i = start_inc; i < end_exc; i++) {
      init[i] = temp[i];
    }

    D(printf("AFTER MERGE: ");)
    D(print_cars(init + start_inc, end_exc - start_inc);)

    return true;
  }
  return false;
}

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

    fill_position_and_target_position(n);
    bool result = sort_init_and_check_inversions(0, n, w);
    printf("%s\n", result ? "TAK" : "NIE");
  }

  return 0;
}
