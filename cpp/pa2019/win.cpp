#include <cstdio>
#include <cmath>
#include <cassert>
#include <string>
//#define DEBUG

#ifdef DEBUG
#define D(x); x
#else
#define D(x);
#endif

using namespace std;

int WINES[2002][2002];

int winesAbove(int i, int j) {
  if(j == 0 || j == i) {
    WINES[i][j] = i;
  } else {
    WINES[i][j] = min(WINES[i - 1][j - 1] + i - j + 1, WINES[i - 1][j] + j + 1);
  }
  return WINES[i][j];
}

int main() {
  int n = 0;
  int k = 0;
  int minyear = 0;

  scanf("%d %d", &n, &k);
  scanf("%d", &minyear);

  for(int i = 1; i < n; i++) {
    int next;
    for(int j = 0; j < i + 1; j++) {
      scanf("%d", &next);
      if(winesAbove(i, j) < k && next < minyear)
        minyear = next;
    }
  }

  printf("%d\n", minyear);
  return 0;
}
