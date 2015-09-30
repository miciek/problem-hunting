#include <cstdio>
#include <set>
#include <list>
#include <queue>
#include <algorithm>

//#define DEBUG

#ifdef DEBUG
#define D(x) x
#else
#define D(x)
#endif

using namespace std;

struct City {
  set<int> neighbours;
  int degree;
  int marker;
  bool processed;
};

const int MAX_CITIES = 200005;
City cities[MAX_CITIES];
int sorted[MAX_CITIES];

void print_set(const set<int>& l) {
    set<int>::const_iterator it = l.begin();
    while(it != l.end()) {
      printf("%d ", *it);
      it++;
    }
}

void print_cities(const int n) {
  for(int i = 1; i <= n; i++) {
    printf("City #%d (%d/%d): ", i, cities[i].degree, cities[i].marker);
    print_set(cities[i].neighbours);
    printf("\n");
  }
}

bool cmp_by_degree(const int x, const int y) {
  return cities[x].degree < cities[y].degree;
}

int mark(int city, int marker, int d) {
  if(cities[city].marker == 0 && cities[city].degree >= d) {
    cities[city].marker = marker;
    set<int>::const_iterator it = cities[city].neighbours.begin();
    int sum = 1;
    while(it != cities[city].neighbours.end()) {
      sum += mark(*it, marker, d);
      it++;
    }
    return sum;
  }
  return 0;
}

int main() {
  int n, m, d;
  scanf("%d %d %d", &n, &m, &d);

  for(int i = 1; i <= n; i++) {
    cities[i] = (City){set<int>(), 0, 0, false};
  }

  for(int j = 1; j <= m; j++) {
    int a, b;
    scanf("%d %d", &a, &b);
    cities[a].neighbours.insert(b);
    cities[b].neighbours.insert(a);
    cities[a].degree++;
    cities[b].degree++;
  }

  D(print_cities(n);)

  queue<int> q;

  for(int i = 1; i <= n; i++) {
    q.push(i);
  }

  while(!q.empty()) {
    int i = q.front();
    q.pop();
    if(cities[i].degree < d) {
      cities[i].degree = 0;
      set<int>::const_iterator it = cities[i].neighbours.begin();
      while(it != cities[i].neighbours.end()) {
        cities[*it].degree--;
        if(cities[*it].processed && cities[*it].degree + 1 == d) q.push(*it);
        it++;
      }
    }
    cities[i].processed = true;
  }

  D(printf("----\n");)
  D(print_cities(n);)

  int marker = 1;
  int best_marker = 0;
  int best_qty = 0;
  for(int i = 1; i <= n; i++) {
    if(cities[i].marker == 0 && cities[i].degree >= d) {
      list<int> current;
      int marked = mark(i, marker, d);
      D(printf("Marked %d with marker %d.\n", marked, marker);)
      if(marked > best_qty) {
        best_marker = marker;
        best_qty = marked;
      }
      marker++;
    }
  }
  
  D(printf("----\n");)
  D(print_cities(n);)

  D(printf("Result: %d (marker %d)\n", best_qty, best_marker);)

  if(best_qty > 0) {
    printf("%d\n", best_qty);
    for(int i = 1; i <= n; i++) {
      if(cities[i].marker == best_marker) {
        printf("%d ", i);
      }
    }
    printf("\n");
  } else {
    printf("NIE\n");
  }

  return 0;
}
