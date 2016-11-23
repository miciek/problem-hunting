#include <cstdio>
#include <cstring>
#include <cmath>
#include <cassert> 
#define DEBUG

#ifdef DEBUG
#define D(x); x
#else
#define D(x);
#endif

using namespace std;

struct Result {
  long twos;
  long threes;
  long ones;
};

struct CompResult {
  Result r1;
  Result r2;
};

char result[1000000];

long ones(Result r) {
  return r.ones + 2*r.twos + 3*r.threes;
}

bool not_possible(Result r) {
  return ones(r) == 0;
}

int app2buf(char *buffer, int where, const char *appendee) {
  strcpy(result + where, appendee);
  return where + strlen(appendee);
}

Result calcones(Result cur, long n) {
  if(n == 1) {
    cur.ones++;
    D(printf("GOT %ld, returning %ld/%ld/%ld\n", n, cur.threes, cur.twos, cur.ones););
    return cur;
  } else if(n == 2) {
    cur.twos++;
    D(printf("GOT %ld, returning %ld/%ld/%ld\n", n, cur.threes, cur.twos, cur.ones););
    return cur;
  } else if(n == 3) {
    cur.threes++;
    D(printf("GOT %ld, returning %ld/%ld/%ld\n", n, cur.threes, cur.twos, cur.ones););
    return cur;
  } else {
    long threes = n/3;
    long threes_remainder = n%3;
    Result with_threes = cur;
    if(threes > 1) {
      with_threes = calcones(cur, threes);
    }

    with_threes.threes += 1;
    with_threes.ones *= threes;
    with_threes.ones += threes_remainder;

    long twos = n/2;
    long twos_remainder = n%2;
    Result with_twos = cur;
    if(twos > 1) {
      with_twos = calcones(cur, twos);
    }

    with_twos.twos += 1;
    with_twos.ones *= 2;
    with_twos.ones += twos_remainder;

    if(ones(with_twos) <= ones(with_threes)) {
      D(printf("GOT %ld, returning 2-based %ld/%ld/%ld\n", n, with_twos.threes, with_twos.twos, with_twos.ones););
      return with_twos;
    } else {
      D(printf("GOT %ld, returning 3-based %ld/%ld/%ld\n", n, with_threes.threes, with_threes.twos, with_threes.ones););
      return with_threes;
    }
  }
}

int result2buf(Result r, char *b, int where) {
  int w = where;
  w = app2buf(b, w, "(");
  for(int i = 0; i < r.threes; i++) {
    w = app2buf(b, w, "(1+1+1)");
    if(i < r.threes - 1) w = app2buf(b, w, "*");
  }
  if(r.threes > 0 && r.twos > 0) w = app2buf(b, w, "*");
  for(int i = 0; i < r.twos; i++) {
    w = app2buf(b, w, "(1+1)");
    if(i < r.twos - 1) w = app2buf(b, w, "*");
  }
  if((r.threes > 0 || r.twos > 0) && r.ones > 0) w = app2buf(b, w, "+");
  if(r.ones > 3) {
    Result rones = {0, 0, 0};
    rones = calcones(rones, r.ones);
    w = result2buf(rones, b, w);
  } else {
    for(int i = 0; i < r.ones; i++) {
      w = app2buf(b, w, "1");
      if(i < r.ones - 1) w = app2buf(b, w, "+");
    }
  }
  w = app2buf(b, w, ")");
  return w;
}

void fill_ones(long n) {
  D(printf("---\nFILLING ONES FOR %ld\n", n););
  Result init = {0, 0, 0};
  Result r = calcones(init, n);
  D(printf("RAW: %ld/%ld/%ld\n", r.threes, r.twos, r.ones););
  result2buf(r, result, 0);
  D(printf("%ld ones needed\n", ones(r)););
  if(not_possible(r)) {
    strcpy(result, "NIE");  
  }
  D(printf("RESULT: %s\n", result););
}

#ifdef DEBUG
int main() {
  fill_ones(1);
  assert(strcmp("(1)", result) == 0);

  fill_ones(2);
  assert(strcmp("((1+1))", result) == 0);

  fill_ones(3);
  assert(strcmp("((1+1+1))", result) == 0);

  fill_ones(4);
  assert(strcmp("((1+1)*(1+1))", result) == 0);

  fill_ones(5);
  assert(strcmp("((1+1)*(1+1)+1)", result) == 0);

  fill_ones(6);
  assert(strcmp("((1+1+1)*(1+1))", result) == 0);

  fill_ones(7);
  assert(strcmp("((1+1+1)*(1+1)+1)", result) == 0);

  fill_ones(8);
  assert(strcmp("((1+1)*(1+1)*(1+1))", result) == 0);

  fill_ones(9);
  assert(strcmp("((1+1+1)*(1+1+1))", result) == 0);

  fill_ones(10);
  assert(strcmp("((1+1+1)*(1+1+1)+1)", result) == 0);

  fill_ones(11);
  assert(strcmp("((1+1+1)*(1+1+1)+1+1)", result) == 0);

  fill_ones(50);
  assert(strcmp("((1+1+1)*(1+1)*(1+1)*(1+1)*(1+1)+1+1)", result) == 0);

  fill_ones(97);
  assert(strcmp("((1+1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)+1)", result) == 0);

  fill_ones(101);
  assert(strcmp("((1+1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)+((1+1)*(1+1)+1))", result) == 0);

  fill_ones(202);
  assert(strcmp("((1+1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)+1+1+1+1+1)", result) == 0);

  //fill_ones(999999937);
  //assert(strcmp("NIE", result) == 0);

  //fill_ones(1073741824);
  //assert(strcmp("(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)*(1+1)", result) == 0);

  return 0;
}
#else
int main() {
  int t;
  scanf("%d", &t);
  while(t--) {
    long k;
    scanf("%ld", &k);
    fill_ones(k);
    printf("%s\n", result);
  }

  return 0;
}
#endif
