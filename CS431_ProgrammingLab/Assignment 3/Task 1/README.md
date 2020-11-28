- We perform isEmpty, union, intersection, subtraction and addition operations on set.
- All inputs are taken as lists

## Steps to run the program
    ghci Set.hs #Runs in interactive manner
    
## How to execute
    isEmpty list1
    findUnionLists list1 list2
    findIntersectionLists list1 list2
    findSubtractionLists list1 list2
    findAdditionLists list1 list2

## Sample Example
```haskell
*Main> isEmpty []
True

*Main> isEmpty [1,2,3]
False

*Main> findUnionLists [1,2,3,4] [3,4,5,6]
[1,2,3,4,5,6]

*Main> findIntersectionLists [1,2,3,4] [3,4,5,6]
[3,4]

*Main> findSubtractionLists [1,2,3,4] [3,4,5,6]
[1,2]

*Main> findAdditionLists [1,2] [3,4]
[4,5,6]
````

## Note for Haskell
 - Press ':q' to quit from Haskell interface
