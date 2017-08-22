# KnapsackOptimizerService
Knapsack Optimizer Service

Prerequisites:
1) Maven repository access
2) org.apache.logging.log4j version 2.8.2 must exists on specified maven repository
3) To compile & execute code, java version 8 is required (i.e. javac as well as java version must be 8) i.e. JDK & JRE version 8
4) gradle is required to build the project
--------------------------------------------------
To clean generated files :
1) Goto "KnapsackOptimizerService" folder
2) execute command -->gradle clean
Note*:
Make sure that folder & files to clean are not opened
--------------------------------------------------
To build executable jar :
1) Goto "KnapsackOptimizerService" folder
2) execute command -->gradle build
--------------------------------------------------
To start execution       
1) Once "KnapsackOptimizerService-0.1.0.jar" is built under folder "KnapsackOptimizerService\build\libs"
2) execute command -->java -jar KnapsackOptimizerService-0.1.0.jar   
3) Copy & paste following sample input on command line & press Enter
-------input start below----------
20
    1    91    29
    2    60    65
    3    61    71
    4     9    60
    5    79    45
    6    46    71
    7    19    22
    8    57    97
    9     8     6
   10    84    91
   11    20    57
   12    72    60
   13    32    49
   14    31    89
   15    28     2
   16    81    30
   17    55    90
   18    43    25
   19   100    82
   20    27    19
524		
-------input ends above----------
4) Based on number of inputs, Wait for program to execute 
5) Output of the solutions will be shown on command line
6) Input details are as follows;
   a) First line contains number of Items, in above example it is 20
   b) Next lines with 3 different number denotes, first as Item's label, second as Item's value and third as Item's Weight respectively
      In above example , on line 2,  1 is label, 91 is value & 29 is weight
   c) Last line contains Capacity
      In above example , last line ,  524 is the capacity
   
   
-------------------------------------------------------------------------------------------
