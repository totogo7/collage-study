# gcc编译、静态库和动态库制作、GDB、Makefile

## 1. 什么是gcc

gcc的全称是GNU Compiler Collection，它是一个能够编译多种语言的编译器。最开始gcc是作为C语言的编译器（GNU C Compiler），现在除了c语言，还支持C++、java、Pascal等语言。gcc支持多种硬件平台。

## 2. gcc的特点

- gcc是一个可移植的编译器，支持多种硬件平台。例如ARM、X86等等。
- gcc不仅是个本地编译器，它还能跨平台交叉编译。所谓的本地编译器，是指编译出来的程序只能够在本地环境进行运行。而gcc编译出来的程序能够在其他平台进行运行。例如嵌入式程序可在x86上编译，然后在arm上运行。
- gcc有多种语言前端，用于解析不同的语言。
- gcc是按模块化设计的，可以加入新语言和新CPU架构的支持。
- gcc是自由软件。任何人都可以使用或更改这个软件。

## 3. gcc编译程序的过程

gcc编译程序主要经过四个过程：

- 预处理（Pre-Processing）
- 编译 （Compiling）
- 汇编 （Assembling）
- 链接 （Linking）

![img](linux%E5%86%85%E6%A0%B8.assets/610439-20160503224835638-2040885438.png)

- 预处理实际上是将头文件、宏进行展开。
- 编译阶段，gcc调用不同语言的编译器，例如c语言调用编译器ccl。gcc实际上是个工具链，在编译程序的过程中调用不同的工具。
- 汇编阶段，gcc调用汇编器进行汇编。
- 链接过程会将程序所需要的目标文件进行链接成可执行文件。
- 汇编器生成的是可重定位的目标文件，学过操作系统，我们知道，在源程序中地址是从0开始的，这是一个相对地址，而程序真正在内存中运行时的地址肯定不是从0开始的，而且在编写源代码的时候也不能知道程序的绝对地址，所以**重定位**能够将源代码的代码、变量等定位为内存具体地址。

这是GCC编译的四个步骤。

## 4. gcc常用选项

来看一下gcc常用选项

| 选项名 | 作用                                                         |
| ------ | ------------------------------------------------------------ |
| -o     | 产生目标（.i、.s、.o、可执行文件等）                         |
| -E     | 只运行C预编译器                                              |
| -S     | 告诉编译器产生汇编程序文件后停止编译，产生的汇编语言文件拓展名为.s |
| -c     | 通知gcc取消连接步骤，即编译源码，并在最后生成目标文件        |
| -Wall  | 使gcc对源文件的代码有问题的地方发出警告                      |
| -Idir  | 将dir目录加入搜索头文件的目录路径                            |
| -Ldir  | 将dir目录加入搜索库的目录路径                                |
| -llib  | 连接lib库                                                    |
| -g     | 在目标文件中嵌入调试信息，以便gdb之类的调试程序调试          |

现在我们有源文件hello.c，下面是一些gcc的使用示例：

```cpp
//第一步：对hello.c文件进行预处理，生成了hello.i 文件
gcc -E hello.c -o hello.i   
//第二步：对预处理文件进行编译，生成了汇编文件
gcc -S hello.i -o hello.s    
//第三步：对汇编文件进行编译，生成了目标文件
gcc -c hello.s -o hello.o 
//第四步：对目标文件进行链接，生成可执行文件
gcc hello.o -o hello    
//第五步：执行
./hello

//编译生成可重定位目标文件
gcc -c hello.c 或 gcc -c hello.c -o hello.o 
//直接编译链接成可执行目标文件
gcc hello.c -o hello 

```

### 实例：

![image-20240929175948977](linux%E5%86%85%E6%A0%B8.assets/image-20240929175948977.png)



## 5. 静态库和动态库

- 所谓“程序库”，就是包含了数据和执行码的文件。其不能单独执行，可以作为其他程序的一部分来完成某些功能。
- 库的存在可以使程序模块化，加快程序的再编译，实现代码重用，使得程序便于升级。
- 程序库可分为静态库和动态库



## 6. 静态库制作

静态库可以认为是一些目标代码的集合，是在可执行程序运行前就已经加入到执行码中，成为执行程序的一部分。

静态库的命名一般分为三个部分：

- 前缀：lib
- 库名称：自己定义
- 后缀：-a

### 设计一个加减乘除的静态库

#### 1. 写库函数

实现“加”的头文件（add.h）

```c
#ifndef __ADD_H__
#define __ADD_H__

int add(int x,int y);

#endif /*__ADD_H__*/

```

实现“加”的源文件（add.c）

```c
#include "add.h"

int add(int x,int y)
{
        return x+y;
}
```

> 以此类推写出加减乘除四个文件的头文件和程序文件



得到的结果：![image-20240929190331641](linux%E5%86%85%E6%A0%B8.assets/image-20240929190331641.png)

#### 2.  将加减乘除的.c源文件生成对应的.o文件

![image-20240929190907329](linux%E5%86%85%E6%A0%B8.assets/image-20240929190907329.png)

#### 3. 使用打包工具ar将准备好的.o文件打包为.a文件 libtest.a

命令`ar -rcs libtest.a add.o sub.o mul.o div.o`

- r更新
- c创建
- s建立索引

![image-20240929191229143](linux%E5%86%85%E6%A0%B8.assets/image-20240929191229143.png)



#### 4. 静态库使用

创建一个新的文件夹：只包含加减乘除的头文件、生成的静态库libtest.a和一个测试源文件test.c

其中test.c 源文件：

![image-20240930231059524](linux%E5%86%85%E6%A0%B8.assets/image-20240930231059524.png)



然后进行编译：`gcc test.c -I./ -L./ -ltest`

- -L：表示要连接的库所在的目录（上面的代码表示连接在当前目录）
- -l：指定链接时需要的库，需要去掉前缀和后缀

得到的结果为：

![image-20240929191859559](linux%E5%86%85%E6%A0%B8.assets/image-20240929191859559.png)





## 7. 动态库的制作

共享库在程序编译时并不会被连接到目标代码中，而是在程序运行时才被载入。不同的应用程序如果调入相同的库，那么在内存里只需要有一份该共享库的实例，规避了空间浪费问题。

共享库的命名一般分为三个部分：

- 前缀：lib
- 库名称
- 后缀：.so

初始时 share_lib1文件夹中的文件有：

![image-20240929193145070](linux%E5%86%85%E6%A0%B8.assets/image-20240929193145070.png)



#### 1. 生成目标文件

```cpp
totogo@ubuntu:~/Desktop/share_lib1$ gcc -fpic -c add.c
totogo@ubuntu:~/Desktop/share_lib1$ gcc -fpic -c sub.c
totogo@ubuntu:~/Desktop/share_lib1$ gcc -fpic -c mul.c
totogo@ubuntu:~/Desktop/share_lib1$ gcc -fpic -c div.c
```

参数：-fpic 创建与地址无关的编译程序（pic，position independent code），为了能够在多个应用程序间共享

结果生成.o的目标文件：gcc

![image-20240929193202439](linux%E5%86%85%E6%A0%B8.assets/image-20240929193202439.png)



#### 2. 生成共享库

代码：`gcc -shared add.o sub.o mul.o div.o -o  libabc.so`

生成libabc.so 文件

![image-20240929193737420](linux%E5%86%85%E6%A0%B8.assets/image-20240929193737420.png)

#### 3. 通过nm命令查看对应的函数

```cpp
totogo@ubuntu:~/Desktop/share_lib1$ nm libabc.so | grep add
00000000000010f9 T add
```

#### 4. 动态库测试

如果直接编译的话会出现错误：

![image-20240929195020288](linux%E5%86%85%E6%A0%B8.assets/image-20240929195020288.png)

如何让系统找到动态库？

- 临时设置LD_LIBRARY_PATH：

代码：`export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/totogo/Desktop/share_lib`

执行成功：

![image-20240929195309742](linux%E5%86%85%E6%A0%B8.assets/image-20240929195309742.png)

> 还有很多种方法，例如拷贝自己制作的共享库到/lib目录；永久设置路径；将其添加到/etc/ld.so.conf 文件中；使用符号链接等方法。

## 8.GDB调试器

#### GDB简介

GNU工具集中的调试器是GDB（GNU Debugger），该程序是一个交互式工具，工作在字符模式。

GDB能实现以下功能：

1. 启动程序，可以按照自定义要求运行程序
2. 可让被调试的程序在指定的断点处停下
3. 当程序停止后，可以检查此时的程序
4. 可以动态改变程序的执行环境

#### 生成调试信息

GDB主要调试的是c/c++程序，要调试c/c++程序，首先在编译时，必须要把调试信息加到可执行文件中。用gcc的-g参数可以实现：

> gcc -g test.c -o  //默认生成可执行文件a.out

test.c源代码：

![image-20240930230821661](linux%E5%86%85%E6%A0%B8.assets/image-20240930230821661.png)



#### 启动GDB

- 启动gdb：`gdb program`
  program就是你的执行文件

对于上面test.c 文件，先进行`gcc -g test.c`生成可执行文件`a.out`

然后进行`gdb a.out`

![image-20240930201547009](linux%E5%86%85%E6%A0%B8.assets/image-20240930201547009.png)



- 设置运行参数
  set args  指定运行的参数（如：set args 10 20 30 40）

  show args 可以查看设置好的运行参数



- 启动程序
  run： 程序开始执行，若有断点，停在第一个断点处

  start：程序向下执行一行

![image-20240930201802639](linux%E5%86%85%E6%A0%B8.assets/image-20240930201802639.png)

> start案例

![image-20240930201829441](linux%E5%86%85%E6%A0%B8.assets/image-20240930201829441.png)

> run案例

#### 断点操作

break设置断点，简写成b

`b 10`：表示在源程序第十行设置断点

`b fun`：表示在fun函数入口初设置断点

`info b` ：显示断点

![image-20240930202325353](linux%E5%86%85%E6%A0%B8.assets/image-20240930202325353.png)

#### 维护断点

1）delete [range…] 删除指定的断点，其简写命令为d

- 如果不指定断点号，则表示删除所有的断点，range表示断点号的范围

2）disable [range…] 使指定断点无效，简写命令为dis

- 如果不指定断点号，表示禁用所有的断点

3）enable [range…] 使无效断点生效，间歇命令ena

- 如果不指定断点号，表示使所有的断点生效

![image-20240930203231862](linux%E5%86%85%E6%A0%B8.assets/image-20240930203231862.png)

#### 调试程序

- run运行程序，简写r
- next单步跟踪，函数调用当作一条简单语句执行，简写n
- step单步跟踪，函数调进被调用函数体内，简写s
- finish  退出进入的函数
- until 在一个循环体内单步跟踪时，可以运行程序直到推出循环体，简写u
- continue 继续运行程序，停在下一个断点的位置，简写c
- quit 退出gdb，简写q



## 9. makefile 简介

Makefile 文件描述了 Linux 系统下 C/C++ 工程的编译规则，它用来自动化编译 C/C++ 项目。一旦写编写好 Makefile 文件，只需要一个 make 命令，整个工程就开始自动编译，不再需要手动执行 GCC 命令。一个中大型 C/C++ 工程的源文件有成百上千个，它们按照功能、模块、类型分别放在不同的目录中，Makefile 文件定义了一系列规则，指明了源文件的编译顺序、依赖关系、是否需要重新编译等。

make主要解决两个问题：

1）大量代码的关系维护

- 大项目中的源代码比较多，手工维护、编译时间长而且编译命令复杂，难以记忆及维护
- 把代码维护命令及编译命令写在makefile文件中，再用make工具解析此文件自动执行相应命令，可实现代码的合理编译

2）减少重复编译事件

- 在改动其中一个文件的时候，能判断哪个文件被修改过，可以只对该文件进行重新编译，然后重新链接所有的目标文件，节省编译事件。



## 10. makefile语法规则

makefile基本规则三要素：

1）目标

- 通常是要产生文件的文件名称，目标可以是可执行文件或其他obj文件，也可以时一个动作的名称

2）依赖文件

- 用来输入从而产生目标的文件
- 一个目标通常有几个依赖文件（也可以没有）

3）命令

- make执行动作，一个规则可以含有几个命令
- 有多个命令时，每个命令占一行



例子：

对于1.mk

```makefile
all:test1  test2
        echo "hello all"

test1:
        echo "hello test1"

test2:
        echo "hello test2"
```

执行：

![image-20240930213502846](linux%E5%86%85%E6%A0%B8.assets/image-20240930213502846.png)

## 11. make命令格式

make是一个命令工具，用来解释makefile中的指令。

make命名格式：

​		`make [ -f file][option][targets]`

1）[ -f file]：

- make默认在工作目录中寻找名为GNUmakefile、makefile、Makefile文件作为makefile输入文件
- -f 可以指定以上名字以外的文件作为makefile输入文件



2）[options]

- -v ： 显示make工具的版本信息
- -w： 在处理makefile之前和之后显示工作路径
- -C dir：读取makefile之前改变工作路径至dir目录
- -n：只打印要执行的命令但不执行
- -s：执行但不显示执行的命令



3）[targets]

- 若使用make命令时没有指定目标，则make工具默认会实现makefile文件内的第一个目标，然后推出
- 制定了make工具要实现的目标，目标可以是一个或多个（多个目标间用空格隔开）



## 12. Makefile示例

#### 最简单的Makefile

![image-20240930230702305](linux%E5%86%85%E6%A0%B8.assets/image-20240930230702305.png)

缺点：效率低，修改一个文件，所有文件会被全部编译

![image-20240930215103049](linux%E5%86%85%E6%A0%B8.assets/image-20240930215103049.png)

#### 第二个版本

![image-20240930230629094](linux%E5%86%85%E6%A0%B8.assets/image-20240930230629094.png)

此时先执行后面四行语句，对四个源文件进行编译，然后再生成可执行文件。

执行make语句之后的结果

![image-20240930222754615](linux%E5%86%85%E6%A0%B8.assets/image-20240930222754615.png)



当修改了一个源文件，编译的时候就只重新编译一个文件

![image-20240930223113552](linux%E5%86%85%E6%A0%B8.assets/image-20240930223113552.png)

> 例子是修改了add.c文件，所以只执行 gcc -c add,c -o add.o



## 13. Makefile中的变量

1）定义变量方法

​	变量名=变量值

2）引用变量

​	$(变量名)或{变量名}

示例：

![image-20240930230545714](linux%E5%86%85%E6%A0%B8.assets/image-20240930230545714.png)



除了 使用用户自定义变量，makefile中也提供了一些变量供用户使用，我们可以直接对其进行赋值。

> CC=gcc  
>
> CPPFLAGS：C预处理的选项 如：-l
>
> CFLAGS：     C编译器的选项  -Wall -g -c
>
> LDFLAGS：   链接器选项 -L -l



#### 自动变量

- $@：表示规则中的目标文件的集合
- $<：表示规则中的第一个相依文件名
- $^：表示规则中所有相依文件的集合，以空格分开

> 自动变量只能在规则的命令中使用

![image-20240930230403720](linux%E5%86%85%E6%A0%B8.assets/image-20240930230403720.png)

示例：



#### 模式规则

模式规则实例：

> %.o:%c
>
> $(CC) -c &(CFLAGS) $(CPPFLAGS) $< -o $@

实例：



![image-20240930230253698](linux%E5%86%85%E6%A0%B8.assets/image-20240930230253698.png)

