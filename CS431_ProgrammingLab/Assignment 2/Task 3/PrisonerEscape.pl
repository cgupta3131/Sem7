% edge(sourceGate, destinationGate, DistanceBetweenGates)
edge('G1', 'G5', 4).
edge('G2', 'G5', 6).
edge('G3', 'G5', 8).
edge('G4', 'G5', 9).
edge('G1', 'G6', 10).
edge('G2', 'G6', 9).
edge('G3', 'G6', 3).
edge('G4', 'G6', 5).
edge('G5', 'G7', 3).
edge('G5', 'G10', 4).
edge('G5', 'G11', 6).
edge('G5', 'G12', 7).
edge('G5', 'G6', 7).
edge('G5', 'G8', 9).
edge('G6', 'G8', 2).
edge('G6', 'G12', 3).
edge('G6', 'G11', 5).
edge('G6', 'G10', 9).
edge('G6', 'G7', 10).
edge('G7', 'G10', 2).
edge('G7', 'G11', 5).
edge('G7', 'G12', 7).
edge('G7', 'G8', 10).
edge('G8', 'G9', 3).
edge('G8', 'G12', 3).
edge('G8', 'G11', 4).
edge('G8', 'G10', 8).
edge('G10', 'G15', 5).
edge('G10', 'G11', 2).
edge('G10', 'G12', 5).
edge('G11', 'G15', 4).
edge('G11', 'G13', 5).
edge('G11', 'G12', 4).
edge('G12', 'G13', 7).
edge('G12', 'G14', 8).
edge('G15', 'G13', 3).
edge('G13', 'G14', 4).
edge('G14', 'G17', 5).
edge('G14', 'G18', 4).
edge('G17', 'G18', 8).

% All the source gates from which prisoner can start it's journey
source('G1').
source('G2').
source('G3').
source('G4').

destination('G17'). % Destination gate

% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


:- dynamic (visitedArray/1). % Dynamic predicate indicating the set of visited vertices
:- dynamic (optimalPath/1). % Dynamic predicate for optimal path variable
:- dynamic (minDistance/1). % Dynamic predicate for minimium Distance
minDistance(2147483647). % Setting minDistance to a very large value



% PART A: PRINT ALL PATHS : call allPaths().
% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %



% BASE CASE: If G1 is an ending gate, print the path
findAllPathsFromG1(G1, CurPath) :- 

    destination(G1), % For base case, G1 should be the destination gate
    append(CurPath, [G1], UpdatedPath), % Append current Path and G1 and store it to UpdatedPath variable
	printSinglePath(UpdatedPath), % Print the path List
	fail.

findAllPathsFromG1(G1, CurPath) :- % Finds a path from gate G1

    not(destination(G1)), % G1 should not be destination gate
    append(CurPath, [G1], UpdatedPath), % Append current Path and G1 and store it to UpdatedPath variable
    checkIfEdgeExists(G1, G2, _), % Check if there exists G2 such that there is an edge between G1 and G2
    not(visitedArray(G2)), % Also G2 should not be visited before
    asserta(visitedArray(G2)), % Set G2 as visited
    not(findAllPathsFromG1(G2, UpdatedPath)), % Recusive call with G2 as starting gate
	retract(visitedArray(G2)), % Unset G2 from visited array
	fail.


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


checkIfEdgeExists(G1, G2, D) :- % Check if there exists an edge from G1 to G2
    edge(G1, G2, D).
checkIfEdgeExists(G1, G2, D) :- % Check if there exists an edge from G2 to G1
    edge(G2, G1, D).


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% BASE CASE: If no value in list, print newLine
printSinglePath([]) :-
    write('\n').

printSinglePath([H|T]) :- % Prints the list of given path
    (isEmptyList(T) -> write(H); write(H), write(' --> ')), % Print the current value and if it's empty, don't print the arrow
	printSinglePath(T). % Recursive call


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


isEmptyList(List) :- % Checks if the list is empty or not
    not(member(_, List)).

allPaths() :- % Print all the possible paths for prisoner to escape from any Source gate to Ending gate with valid Path

    source(G1), % Find the source gate and store it in G1
    asserta(visitedArray(G1)), % Set G1 as visited
    not(findAllPathsFromG1(G1, [])), % Find all paths starting from G1
	retract(visitedArray(G1)), % Unset G1 from visited array
	fail.



% PART B: Find Optimal Path : call optimal().
% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %




startDFS() :- % Initialize DFS from all starting gates
    source(G1), % G1 should be starting gate
    asserta(visitedArray(G1)), % Mark G1 as visited
    not(performDFS(G1, 0, [])), % Call DFS from G1
	retract(visitedArray(G1)), % Unset G1
	fail.

% BASE CASE: If distance is greater than minDistance, don't proceed 
performDFS(_, CurDistance, _) :-
    % Get min distance
    minDistance(MinDistance), % Get minDstiance
	CurDistance > MinDistance, % Check if Distance is greater than minDistance
	fail.

% BASE CASE: If G1 is ending gate, then update minimum distance
performDFS(G1, CurDistance, CurPath):-

    destination(G1), % G1 should be destiantion gate
    append(CurPath, [G1], UpdatedPath), % Append current Path and G1 and store it to UpdatedPath variable
    minDistance(MinDistance), % get Current minium distance
	updateMinDistance(MinDistance, CurDistance, UpdatedPath), % Update the minimum distance
	fail.

% Call depth first search from G1
performDFS(G1, CurDistance, CurPath) :- % DFS from G1 gate

    not(destination(G1)), % G1 should not be a destination gate
    append(CurPath, [G1], UpdatedPath), % Append current Path and G1 and store it to UpdatedPath variable
    checkIfEdgeExists(G1, G2, D), % Check if some gate G2 exists such that there is an edge between G1 and G2 and D as the distance
    not(visitedArray(G2)), % G2 should not be visited yet
    asserta(visitedArray(G2)), % Set G2 as visited

    TotalDistance is CurDistance+D, % Update totalDistance variable
    not(performDFS(G2, TotalDistance, UpdatedPath)), % Recursive Call
	retract(visitedArray(G2)), % Unset G2 as visited from visited array
	fail.


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


updateMinDistance(MinDistance, CurDistance, CurPath) :- % If Distance is less than MinDistance, reset Optimals Paths

    MinDistance > CurDistance, % Check if Distance is less than Min Distance
    retract(minDistance(MinDistance)), % Unset minDistance 
    asserta(minDistance(CurDistance)), % Set minDistance to Distance 
    retractall(optimalPath(_)), % Unset all paths in optimal Path
	assertz(optimalPath(CurPath)). % Add path to optimalPathList

updateMinDistance(MinDistance, CurDistance, CurPath) :- % If Distance is same as MinDistance, add path to list of optimal Paths

    MinDistance =:= CurDistance, % Check if minDistance is same as distance
	assertz(optimalPath(CurPath)). % Add Path to optimalPathList


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


printAllOptimalPaths() :- % Print all optimal Paths

    optimalPath(OptimalPath), % Get all optimal paths
    printSinglePath(OptimalPath), % And print them one by one
    fail.


optimal() :- % Fins the optimal path with minimum distance
    not(startDFS()), % Initiate DFS for optimal path
    not(printAllOptimalPaths()), % Print all optimal paths
    minDistance(MinDistance), % Update MinDistance variable
    (MinDistance =:= 2147483647 -> write('No path exists!'), false; write('Minimum Distance: '), write(MinDistance)). % Print optimal distance if it exists



% PART C: Check if given path is Valid or Not : call valid([Path]).
% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %



% BASE CASE: If only one gate in path, that gate should be the endingGate
isValid(CurPath) :-

    [G1|T] = CurPath,
	destination(G1), % G1 should be ending gate
    isEmptyList(T). % T should be empty 

isValid(CurPath) :- % Check if path is valid by ensuring that edge exists between each gate

    [G1,G2|_] = CurPath, % Get G1 and G2 as two gates from the path
    checkIfEdgeExists(G1, G2, _), % Edge should exist between G1 and G2
    [_|T] = CurPath, 
	isValid(T). % Call recursively for rest of Path

valid(CurPath) :- % Check if given path is valid

    [G1|_] = CurPath, % Get first gate and that should be starting Gate
    source(G1), % Check if it is a starting gate
    isValid(CurPath), % Check if rest of path is valid or not
    !.


