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

char result[1000000];

string cache[10000002];

long ones(string r) {
  long res = 0;
  for(string::iterator it = r.begin(); it != r.end(); ++it)
    if(*it == '1') res++;
  return res;
}

string calcones(long n) {
  if(n < 10000002 && cache[n] != "") {
    return cache[n];
  }
  if(n == 1) {
    string res = "1";
    cache[n] = res;
    return res;
  } else if(n == 2) {
    string res = "(1+1)";
    cache[n] = res;
    return res;
  } else if(n == 3) {
    string res = "(1+1+1)";
    cache[n] = res;
    return res;
  } else {
    long threes = n / 3;
    long threes_remainder = n % 3;
    string threes_using_ones = calcones(threes);
    string three_based_result = calcones(3);
    if(threes > 1) {
      three_based_result += "*" + threes_using_ones;
    }     
    if(threes_remainder > 0) {
      three_based_result += "+" + calcones(threes_remainder);
    }

    long twos = n / 2;
    long twos_remainder = n % 2;
    string twos_using_ones = calcones(twos);
    string two_based_result = calcones(2);
    if(twos > 1) {
      two_based_result += "*" + twos_using_ones;
    }     
    if(twos_remainder > 0) {
      two_based_result += "+" + calcones(twos_remainder);
    }

    string result = two_based_result;
    if(ones(three_based_result) < ones(two_based_result)) result = three_based_result;

    result = "(" + result + ")";
    if(n < 10000002) {
      cache[n] = result;
    }

    return result;
  }
}

void fill_ones(long n) {
  D(printf("---\nFILLING ONES FOR %ld\n", n););
  string r = calcones(n);
  D(printf("%ld ones needed\n", ones(r)););
  strcpy(result, r.c_str());
  D(printf("RESULT: %s\n", result););
}

#ifdef DEBUG
int main() {
  fill_ones(1);
  assert(strcmp("1", result) == 0);

  fill_ones(2);
  assert(strcmp("(1+1)", result) == 0);

  fill_ones(3);
  assert(strcmp("(1+1+1)", result) == 0);

  fill_ones(4);
  assert(strcmp("((1+1)*(1+1))", result) == 0);

  fill_ones(5);
  assert(strcmp("((1+1)*(1+1)+1)", result) == 0);

  fill_ones(6);
  assert(strcmp("((1+1)*(1+1+1))", result) == 0);

  fill_ones(7);
  assert(strcmp("((1+1)*(1+1+1)+1)", result) == 0);

  fill_ones(8);
  assert(strcmp("((1+1)*((1+1)*(1+1)))", result) == 0);

  fill_ones(9);
  assert(strcmp("((1+1+1)*(1+1+1))", result) == 0);

  fill_ones(10);
  assert(strcmp("((1+1)*((1+1)*(1+1)+1))", result) == 0);

  fill_ones(11);
  assert(strcmp("((1+1)*((1+1)*(1+1)+1)+1)", result) == 0);

  fill_ones(50);
  assert(strcmp("((1+1)*((1+1)*((1+1)*((1+1)*(1+1+1)))+1))", result) == 0);

  fill_ones(97);
  assert(strcmp("((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*(1+1+1)))))+1)", result) == 0);

  fill_ones(101);
  assert(strcmp("((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*(1+1+1)))+1))+1)", result) == 0);

  fill_ones(202);
  assert(strcmp("((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*(1+1+1)))+1))+1))", result) == 0);

  fill_ones(999999937);
  assert(strcmp("((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1+1)*((1+1+1)*((1+1+1)*((1+1+1)*((1+1)*((1+1)*((1+1+1)*((1+1)*((1+1+1)*((1+1+1)*((1+1)*((1+1)*((1+1)*((1+1+1)*((1+1)*((1+1)*((1+1+1)*(1+1+1)))+1))+1))+1)))+1)))+1))+(1+1)))))))))+1)", result) == 0);

  fill_ones(1073741824);
  assert(strcmp("((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*((1+1)*(1+1))))))))))))))))))))))))))))))", result) == 0);

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
