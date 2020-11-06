/* Bus(Number, Origin, Destination Place, Departure Time, Arrival Time, Distance, Cost) */
bus(1, 'A', 'J', 14.5, 15, 1, 10).
bus(2, 'A', 'C', 16, 16.5, 7, 8).
bus(3, 'J', 'Pan', 16, 16.5, 1, 8).
bus(4, 'Pan', 'C', 16, 16.5, 2, 8).
bus(5, 'Pan', 'Pal', 16, 16.5, 7, 8).
bus(6, 'C', 'M', 16, 16.5, 7, 8).
bus(7, 'M', 'L', 16, 16.5, 7, 8).


route(SourceVertex, DestVertex) :- % Our main calling function, calculates the minimum distance, time and cost between SourceVertex and DestVertex
	(findMinWeight(SourceVertex, DestVertex, 'Distance') -> true ; write('No path exists!'), false), % If no path exists, return false
	findMinWeight(SourceVertex, DestVertex, 'Time'),
	findMinWeight(SourceVertex, DestVertex, 'Cost').


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


findMinWeight(SourceVertex, DestVertex, Type) :- % Finds the minimum weight with Type between SourceVertex and DestVertex
	dict_create(ParentList, parent, [SourceVertex='']),
	runDjikstra([SourceVertex-0], [], Type, _, ParentList, NewParent), % Call Djikstra for  SourceVertex and return final parent as NewParent
	get_dict(DestVertex, NewParent, _), % Checks if Destination is reachable from SourceVertex or not

	% PRINT THE WHOLE OUTPUT
	write('Optimum '), write(Type), write(':\n'),
	write('\tPATH: '), printCompletePath(SourceVertex, DestVertex, NewParent, Distance, Time, Cost), write(']\n'),
	write('\tDistance = '), write(Distance), write(','),
	write(' Time = '), write(Time),  write(','),
	write(' Cost = '), write(Cost), write('\n'), write('\n').


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


minWeightVertex([H|T], MinV, VertexSet) :- % Returns the vertex with minimum weight from VertexSet
	minWeightVertexHelper(H, T, MinV, VertexSet).

% BASE CASE: If no vertex is left
minWeightVertexHelper(Cur, [], Cur, []). 

minWeightVertexHelper(Cur, [H|T], MinV, [H2|RestCurSet]) :- % Just a helper function to solve minVertex
	H = _-D1, Cur = _-D,
	(D1 < D -> NextM = H, H2 = Cur ; NextM = Cur, H2 = H),
	minWeightVertexHelper(NextM, T, MinV, RestCurSet). % Call recursivly


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


findAdjacentSet(U, AdjSet, Type) :- % Given a vertex U, returns the set of Adjacent vertices in AdjSet
	(setof(V-D, checkIfEdgeExists(U, V, D, Type), TempAdjSet) *-> TempAdjSet = AdjSet; AdjSet = []).


checkIfEdgeExists(X, Y, W, 'Distance') :- % Returns true if there is an edge between X and Y and returns distance as weight W
	bus(_, X, Y, _, _, W, _).

checkIfEdgeExists(X, Y, W, 'Cost') :- % Returns true if there is an edge between X and Y and returns cost as weight W
	bus(_, X, Y, _, _, _, W).

checkIfEdgeExists(X, Y, W, 'Time') :-  % Returns true if there is an edge between X and Y and returns time as weight W
	bus(_, X, Y, T1, T2, _, _),
	(T2 > T1 -> W is T2-T1; W is 24+T2-T1).

checkIfEdgeExists(X, Y, B, D, T, C) :- % Returns true if there is an edge between X and Y and returns all the parameters
	bus(B, X, Y, T1, T2, D, C),
	(T2 > T1 -> T is T2-T1 ; T is 24+T2-T1).


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% BASE CASE: If no vertex is left
findUpdatedAdjacentSet([], _, []).

findUpdatedAdjacentSet([H|T], VisitedSet, UpdatedAdjSet) :- % Given a set of vertices, check if any vertex in it is visited or not and creates new List
	H = V-_,
	(member(V-_, VisitedSet) -> UpdatedAdjSet = SubNewAdjSet ; UpdatedAdjSet = [H|SubNewAdjSet]), % If vertex V is present in visited, do not add it to
	findUpdatedAdjacentSet(T, VisitedSet, SubNewAdjSet). % Recursive call


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% BASE CASE: If parent is null, then return waiting time is 0
calculateWaitingTime('', _, _, WaitingTime) :- 
	WaitingTime is 0.

calculateWaitingTime(ParentOfU, U, V, WaitingTime) :- % Calculates the waiting time if we wish to go from U and V

	bus(_, ParentOfU, U, _, ArrivalTime, _, _), % Find the arrival time at current vertex U
	bus(_, U, V, DepartureTime, _, _, _), % Find the departure time from U to V

	(DepartureTime > ArrivalTime -> WaitingTime is DepartureTime-ArrivalTime ; WaitingTime is 24+DepartureTime-ArrivalTime). %


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% BASE CASE: If no vertex is left, return the remaining set of vertices
updateParentAndRelaxEdges([], CurSet, _, _, CurSet, ParentList, ParentList). 

updateParentAndRelaxEdges([NeighbourV-EdgeWeight|T], CurSet, CurVertex, GlobalWeightOfV, NewCurSet, ParentList, FinalParentList) :- % Updates the weights of the vertices from adjSet and returns newCurSet
	
	calculateWaitingTime(ParentList.get(CurVertex), CurVertex, NeighbourV, WaitingTime),

	( 
		removeFromList(CurSet, NeighbourV-GlobalWeightV1, RestCurSet) -> 
		(GlobalWeightOfV+EdgeWeight+WaitingTime < GlobalWeightV1 -> NewWeight is GlobalWeightOfV+EdgeWeight+WaitingTime, NewParent = ParentList.put(NeighbourV, CurVertex); NewWeight is GlobalWeightV1, NewParent = ParentList);
		RestCurSet = CurSet, NewWeight is GlobalWeightOfV+EdgeWeight+WaitingTime, NewParent = ParentList.put(NeighbourV, CurVertex)
	),

	NewCurSet = [NeighbourV-NewWeight|SubNewCurSet],
	updateParentAndRelaxEdges(T, RestCurSet, CurVertex, GlobalWeightOfV, SubNewCurSet, NewParent, FinalParentList). % Call this function recursively


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% BASE CASE: If current vertex is same as X, return the remaining list
removeFromList([X|T], X, T).

removeFromList([H|T], X, [H|NT]) :- % Removes a vertex X from given list and returns the remaining list

	H \= X, % Check if H is not equal to X
	removeFromList(T, X, NT). % Call recursively


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% BASE CASE: If no vertex is left, return the final cost and ParentList list
runDjikstra([], VisitedSet, _, VisitedSet, ParentList, ParentList).

runDjikstra(CurSet, VisitedSet, Type, MinDist, ParentList, FinalParentList) :-
	% Computes the vertex with minimum Vertex, RestCurSet removes the minVertex from CurSet
	% Here V is the minVertex and D is it's global Cost till now
	minWeightVertex(CurSet, V-D, RestCurSet), 
	findAdjacentSet(V, AdjSet, Type),  % Computes all adjacent vertex to vertex V
	findUpdatedAdjacentSet(AdjSet, VisitedSet, UpdatedAdjSet), % Selects only those vertices which are not visited yet
	updateParentAndRelaxEdges(UpdatedAdjSet, RestCurSet, V, D, NewCurSet, ParentList, NewParent), % Relaxes the adjacent edges and NewCurSet is the new current set with updated cost
	runDjikstra(NewCurSet, [V-D|VisitedSet], Type, MinDist, NewParent, FinalParentList). % Recursively call Djikstra for remaining vertices


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %


% BASE CASE: When dest becomes SourceVertex, simply print Source`
printCompletePath(SourceVertex, SourceVertex, _, 0, 0, 0) :- 
	write('['), write(SourceVertex).

printCompletePath(SourceVertex, DestVertex, ParentList, Distance, Time, Cost) :- % Prints the path between SourceVertex and DestVertex using ParentList list

	printCompletePath(SourceVertex, ParentList.get(DestVertex), ParentList, Distance1, Time1, Cost1),

	checkIfEdgeExists(ParentList.get(DestVertex), DestVertex, B, Distance2, Time2, Cost2), % Compute Current Weights between edge ParentList(DestVertex) and DestVertex
	(get_dict(ParentList.get(DestVertex), ParentList, _) -> calculateWaitingTime(ParentList.get(ParentList.get(DestVertex)), ParentList.get(DestVertex), DestVertex, WaitingTime); WaitingTime is 0),

	Distance is Distance1+Distance2, Time is Time1+Time2+WaitingTime, Cost is Cost1+Cost2,
	write(','), write(B), write('] -> '), write('['), write(DestVertex).


% -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %
