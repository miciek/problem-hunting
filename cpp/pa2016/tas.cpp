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

int IN[(1 << 20) + 5];
int OUT[(1 << 20) + 5];

void run(int m, int t) {
  D(printf("m = %d\n", m);)
  for(int i = 0; i < m; i++) {
    if(t % 2 == 1) {
      OUT[i] = IN[m - i - 1];
    } else {
      OUT[i] = IN[i];
    }
  }
}

int main() {
  int n, t;
  scanf("%d %d", &n, &t);
  int m = 1 << n;

  for(int i = 0; i < m; i++) {
    int temp;
    scanf("%d", &temp);
    IN[i] = temp;
  }

  run(m, t);

  for(int i = 0; i < m; i++) {
    printf("%d ", OUT[i]);
  }

  printf("\n");

  return 0;
}
