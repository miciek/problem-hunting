#include <cstdio>
#include <algorithm>
#include <cstring>

using namespace std;

int main() {
    char* input = new char[51];
    while(true) {
	scanf("%s", input);
	if(input[0] == '#') break;
	if(next_permutation(input, input + strlen(input))) {
	    printf("%s\n", input);
	} else {
	    printf("No Successor\n");
	}
    }
    return 0;
}
