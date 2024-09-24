#include <stdio.h>

int main()
{
    int a, b, c;
    scanf("%d %d %d", &a, &b, &c);
    int max = a > b ? a : b;
    max = max > c ? max : c;
    int result = a == b ? (b == c ? 10000 + a * 1000 : 1000 + a * 100) : b == c ? 1000 + b * 100 : a == c ? 1000 + a * 100 : max * 100;
    printf("%d", result);

    return 0;
}