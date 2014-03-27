#include <cstdio>
#include <algorithm>
#include <cstring>

using namespace std;

/**
 * UVA 146 - ID Codes solution
 * 
 * http://uva.onlinejudge.org/external/1/146.html
 * 
 * @category ad-hoc, permutations
 * @author micio
 */
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
