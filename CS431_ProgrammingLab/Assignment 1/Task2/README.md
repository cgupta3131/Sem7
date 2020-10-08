To compile the program, Execute:

    make

- Before running the program, make sure that ./Resources/tud_info.txt is populated with the Student Data
- Also if taking input from a file, make sure that ./Resources/input.txt is populated.
- Also, empty AllData folder should be present

To run the program interactively:

    java evalsystem/EvaluationSystem

To run the program from file:

    java evalsystem/EvaluationSystem < ./Resources/input.txt
    
- Whenver the input presses 2(UpdateAllData), we can see that three files will be generated in AllData directory.
- SortedName.txt : Contains all the students and their marks sorted by their Name.
- SortedRoll.txt : Contains all the students and their marks sorted by their Roll Number.
- UnSorted.txt   : Contains all the students and their marks in unsorted manner.

To clean the project

    make clean
 
