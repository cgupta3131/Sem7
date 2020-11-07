- Djikstra Algorithm is used to find shortest path (for all the three types of weight) between specified nodes.
- Also, I have considered waiting time at bus stops while calulating shortest Time paths between noeds
## Steps to run the program
    swipl -l BusTravelPlanner.pl # If want to run in interactive manner
    swipl -l BusTravelPlanner.pl < test.txt # If want to run from a file test.txt
    
## How to execute
    route('A', 'B') # Print route between A and B cities

## Sample Input
```prolog
bus(121, 'Chandigarh', 'Jaipur', 14.5, 15, 120, 10).
bus(416, 'Chandigarh', 'Delhi', 16, 16.5, 80, 800).
bus(375, 'Jaipur', 'Agra', 16, 16.5, 200, 12).
bus(498, 'Agra', 'Delhi', 16, 16.5, 60, 9).
bus(547, 'Agra', 'Lucknow', 16, 16.5, 30, 8).
bus(748, 'Delhi', 'Panchkula', 16, 16.5, 90, 5).
bus(985, 'Panchkula', 'Mohali', 16, 16.5, 30, 13).
````
## Sample Example

```prolog
?- route('Chandigarh', 'Delhi').
Optimum Distance:
        PATH: [Chandigarh,416] -> [Delhi]
        Distance = 80, Time = 0.5, Cost = 800

Optimum Time:
        PATH: [Chandigarh,416] -> [Delhi]
        Distance = 80, Time = 0.5, Cost = 800

Optimum Cost:
        PATH: [Chandigarh,121] -> [Jaipur,375] -> [Agra,498] -> [Delhi]
        Distance = 380, Time = 26.0, Cost = 31
        
?- route('Agra', 'Chandigarh').
No path exists!
false.
```


## Note for Prolog
 - Press '.' to stop execution of current command
 - Press ';' to find another possible solution to current command
