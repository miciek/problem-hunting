#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <cstring>
using namespace std;

#define MAXN 30000

int RA[MAXN], SA[MAXN], LCP[MAXN], *FC, *SC, step;
char S[MAXN], S2[MAXN];

bool cmp(int  a, int  b)  {
  if (step == -1 || FC[a] != FC[b]) return FC[a] < FC[b];
  return FC[a + (1 << step)] < FC[b + (1 << step)];
}

void suffix_array(char *S, int n) { // O(n log^2(n))
  for (int i = 0; i < n; i++) RA[i] = S[SA[i] = i];
  for (FC = RA, SC = LCP, step = -1; (1 << step) < n; step++) {
    sort(SA, SA + n, cmp);
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      if (i > 0 && cmp(SA[i - 1], SA[i])) cnt++;
      SC[SA[i]] = cnt;
    }
    if (cnt == n-1) break; // all distinct, no need to continue
    swap(FC, SC);
  }

  for (int i = 0; i < n; i++) RA[SA[i]] = i;
}

/**
 * UVA 719 - Glass Beads solution
 * http://uva.onlinejudge.org/external/7/719.html
 * @category string, suffix-array
 * @author micio
 */
int main() {
  int N;

  scanf("%d", &N);
  while (N--) {
    scanf("%s", S);
    int m = strlen(S);
    S2[0] = '\0';
    strcat(S2, S);
    strcat(S2, S);
    strcat(S2, "{");
    int n = strlen(S2);
    suffix_array(S2, n);
    printf("%d\n", SA[0] % m + 1);
  }

  return 0;
}
