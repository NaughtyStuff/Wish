#to encode or decode my wishes in microsoft todo
#can also be accessed in bit.ly/mgcencode
def enc():
    secret = input()
    lst = []
    for i in secret:
        lst.append(oct(ord(i)**2)[2:])


    return ' '.join(lst[::-1])


def dec():
    secret = input()
    from math import sqrt
    lst = list(map(str, secret.split()))
    lst.reverse()

    sec = []
    
    for i in lst:
        sec.append(chr(int(sqrt(int(i,8)))))
    return ''.join(sec)

#print(enc())
#print(dec())
