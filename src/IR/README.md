# LLVM-IR List

```
int: i32
bool: i1
string: i8* -> char*
```

+ ret

```
ret void
ret <ty> <operand>
```

+ alloca

```
%<num> = alloca <type>
<type>: i32 , i8 , [num * i32] , [num * i8] ...
```

+ store

```
store <type> <operand>, <type>* <Destoperand>
```

+ load

```
%<num> = load <type>, <type>* <operand>
```

+ binary(add,sub,mul,div,srem,...)

```
%<num> = <op> <type> <operand1>, <operand2>
```

+ br

```
br label <label>
br i1 <cond>, label <label1>, label <label2>
```

+ icmp

```
%<num> = icmp <cond> <ty> <operand1>, <operand2>
```

+ global

```
%class.<name> = type { <type1>, <type2>, ... }
@<name> = global <type> <value>
@<name> = constant <type> c"<str>"
```

+ class

```
%class.<name> = type { <type1>, <type2>, ... }
```

+ define

```
define <type> @<name>(<type1> %<num>, <type2> %<num>, ...) {...}
```

+ call

```
%<num> = call <ty> @<name>(<type1> <operand>, <type2> <operand>, ...)
call void @<name>(<type1> <operand>, <type2> <operand>, ...)
```

+ bitcast

```
%<num> = bitcast <oldty> <operand> <newty>
```

+ getelementptr

```
%<num> = getelementptr inbounds <ty>, <ty>* %<num>, [<offty> <offoperand>]*
```