num = int(input())

a = [0 for i in range(1000001)]
a[1] = 0
a[2] = a[3] = 1


def func(n):
    if n <= 3:
        return

    minCnt = 1000000

    if n % 3 == 0:
        if minCnt > a[int(n / 3)]:
            minCnt = a[int(n / 3)]

    if n % 2 == 0:
        if minCnt > a[int(n / 2)]:
            minCnt = a[int(n / 2)]

    if minCnt > a[n - 1]:
        minCnt = a[n - 1]

    a[n] = minCnt + 1


for i in range(4, num + 1):
    if i > num:
        break

    func(i)

print(a[num])
