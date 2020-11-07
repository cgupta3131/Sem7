## Steps to run the program

    swipl -l FindRelation.pl # If want to run in interactive manner
    swipl -l FindRelation.pl < test.txt # If want to run from a file test.txt
    
## Sample Example for Uncle
 A is uncle of B if parent of A and grandparent of B is common, and also A is not parent of B and A is a male
 
```prolog
?- uncle(A,B).
A = kattappa,
B = avantika ;
false.

?- uncle(jatin, avantika).
false.

?- uncle(A, avantika).
A = kattappa ;
false.
```

## Sample Example for Half-sister
A is half-sister of B if both have exactly one parent common(and other is not common) and A is a female

```prolog
?- halfsister(A,B).
A = avantika,
B = shivkami ;
A = shivkami,
B = avantika ;
false.

?- halfsister(avantika, A).
A = shivkami ;
false.
```

## Note for Prolog
 - Press '.' to stop execution of current command
 - Press ';' to find another possible solution to current command
