# ASM List

### Attention:

For ASM, the malloc `singleSize` of IR is changed to `4`. This will cause IR errors. If you want IR files, please modify
it back.

```
intType: 4
pointerType: 4
classType: ty1.size+ty2.size+...
arrayType: size*ty.size (shouldn't be used)
voidType: 0
```

+ arithmetic

```
<op>    R[rd], R[rs1], R[rs1]/imm
```

+ j

```
j    <Dest>
```

+ li

```
li    R[rd], imm
```

+ mv

```
mv    R[rd], R[rs1]
```

+ branch

```
<op>    R[rs1], <Dest>
```

+ call

```
call    <Dest>
```

+ compare

```
<op>    R[rd], R[rs1]    (zero)
<op>    R[rd], R[rs1], R[rs2]
```

+ la

```
la    R[rd], A
```

+ lw

```
lw    R[rd], imm(R[rs1])
lw    R[rd], global
```

+ sw

```
sw R[rs2], imm(R[rs1])
```

+ ret

```
ret
```