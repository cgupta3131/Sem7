% parent(A,B) - Here A is defined as the parent of B
parent(jatin,avantika).
parent(jolly,jatin).
parent(jolly,kattappa).
parent(manisha,avantika).
parent(manisha,shivkami).
parent(bahubali,shivkami).

% male(A) - Here A is defined as Male
male(kattappa).
male(jolly).
male(bahubali).

% female(A) - Here A is defined as female
female(shivkami).
female(avantika).


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% uncle(A, B) - Here A is defined as Uncle of B
uncle(A, B) :-

    male(A), % A should be Male

    parent(C, B), % Parent of B is C
    parent(D, C), % And Parent of C is D, hence D is grandparent of B
    parent(D, A), % Now, D should be the parent of A

    not(parent(A, B)). % Now, A should not be the parent of B as above condition holds true for them


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% halfsister(A, B) :- Here A is defined as half-sister of B
halfsister(A, B) :-

    parent(C, A), % C is the parent of A
    parent(C, B), % C is also the common parent of B
    parent(E, A), % E is the parent of A
    parent(F, B), % F is the parent of B

    % Now E and F can't be equal as well as E and F can't be equal to C 
    % And also A and B can't be equal
    not(A = B),
    not(E = F),
    not(C = E),
    not(C = F),
    
    % A should be female
    female(A).


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %
