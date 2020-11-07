## Steps to run the program

    swipl -l PrisonerEscape.pl # If want to run in interactive manner
    swipl -l PrisonerEscape.pl < test.txt # If want to run from a file test.txt
    
## How to execute
    allPaths() # Prints all the valid paths for a prisoner to escape
    optimal() # Prints all the optimal paths with minimum distance
    valid(Path) # Checks whether the Path is a valid path for a prisoner to escape or not

## Sample Example
 
```prolog
?- allPaths().
G4 --> G6 --> G5 --> G8 --> G7 --> G12 --> G11 --> G13 --> G14 --> G17
G4 --> G6 --> G5 --> G8 --> G7 --> G12 --> G11 --> G13 --> G14 --> G18 --> G17
G4 --> G6 --> G5 --> G8 --> G7 --> G12 --> G11 --> G10 --> G15 --> G13 --> G14 --> G17
G4 --> G6 --> G5 --> G8 --> G7 --> G12 --> G11 --> G10 --> G15 --> G13 --> G14 --> G18 --> G17
false.

?- optimal().
G3 --> G6 --> G12 --> G14 --> G17
Minimum Distance: 19
true.

?- valid(['G4', 'G6', 'G5', 'G8', 'G7', 'G12', 'G11', 'G13', 'G14', 'G17']).
true.

?- valid(['G4', 'G7', 'G12', 'G11', 'G16', 'G14', 'G17']).
false.
```


## Note for Prolog
 - Press '.' to stop execution of current command
 - Press ';' to find another possible solution to current command
