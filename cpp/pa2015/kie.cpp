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

int main() {
  int n;
  scanf("%d", &n);
  int sum = 0;
  int min_odd = 0;

  while(n--) {
    int temp;
    scanf("%d", &temp);
    sum += temp;
    if(temp % 2 != 0 && (min_odd == 0 || temp < min_odd)) {
      min_odd = temp;
    }
  }

  if(sum % 2 != 0) {
    sum -= min_odd;
  }

  if(sum > 0) {
    printf("%d\n", sum);
  } else {
    printf("NIESTETY\n");
  }

  return 0;
}
