- Here given totalArea, number of bedrooms and halls, we will print if it not possible to design within given constraints.
- If it is possible, then we will print the dimesnions of each type of room in the house.

## Steps to run the program
    ghci HousePlanner.hs #Runs in interactive manner
    
## How to execute
    design totalArea numBedrooms numHalls

## Sample Example
```haskell
*Main> design 100 3 2
No design possible for the given constraints

*Main> design 1000 3 2
Bedroom: 3 (10,10)
Hall: 2 (15,10)
Kitchen: 1 (7,5)
Bathroom: 4 (4,5)
Garden: 1 (12,17)
Balcony: 1 (9,9)
Unused Space: 0

*Main> design 1000 5 2
No design possible for the given constraints

*Main> design 2000 5 2
Bedroom: 5 (10,10)
Hall: 2 (15,14)
Kitchen: 2 (10,10)
Bathroom: 6 (8,9)
Garden: 1 (20,20)
Balcony: 1 (6,8)
Unused Space: 0

*Main> design 4000 5 2
Bedroom: 5 (15,15)
Hall: 2 (20,15)
Kitchen: 2 (15,13)
Bathroom: 6 (8,9)
Garden: 1 (20,20)
Balcony: 1 (10,10)
Unused Space: 953
````

## Note for Haskell
 - Press ':q' to quit from Haskell interface
