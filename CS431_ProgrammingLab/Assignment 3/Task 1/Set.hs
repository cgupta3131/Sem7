-- Import required libraries
import Data.List

-------------------------------- Remove Duplicates from list ----------------------------


-- BASE CASE: If list is empty, return empty list
deleteDuplicatesFromList [] = []
-- Remove duplicates from a List
deleteDuplicatesFromList (currentEle1:list) =
    -- If currentEle1 is present in remaining list, recursively call for remaining list
    if elem currentEle1 list then
        deleteDuplicatesFromList list
    -- Else add currentEle1 and call recursively for remaining list
    else
        currentEle1 : deleteDuplicatesFromList list


-------------------------------------------------------------------------------------

-------------------------------- Check if Set is empty ----------------------------


-- Checks whether a set is empty or not
isEmpty list = 
    null list


-------------------------------------------------------------------------------------

-------------------------------- Get union of two lists  ----------------------------


-- Returns the union of two lists
findUnionLists inputList1 inputList2 = 
    deleteDuplicatesFromList (inputList1 ++ inputList2)


-------------------------------------------------------------------------------------

--------------------------- Get intersection of two lists ---------------------------


-- BASE CASE: If list is empty, return empty list
findIntersectionLists [] _ = []
findIntersectionLists _ [] = []
-- Gives the inersection of two lists
findIntersectionLists (currentEle1:inputList1) inputList2 =
    -- If currentEle1 is present in list 2, then add currentEle1 to new list and call recursively for remaining inputList1 and inputList2
    if elem currentEle1 inputList2 then
        findUnionLists [currentEle1] (findIntersectionLists inputList1 inputList2)
    -- Else just call recursively for remaining inputList1 and inputList2
    else
        findIntersectionLists inputList1 inputList2


-------------------------------------------------------------------------------------

-------------------------------- Get addtion of two lists  --------------------------


-- BASE CASE: If list is empty, return empty list
findAdditionLists [] _ = []
findAdditionLists _ [] = []
-- Performs addition on two sets and returns output set by adding currentEle1+currentEle2 to new set
-- Calls findAdditionLists recursively for inputList1 & remaining inputList2, and remaining inputList1 & inputList2
findAdditionLists (currentEle1:inputList1) (currentEle2:inputList2) = 
    findUnionLists [currentEle1+currentEle2] (findUnionLists (findAdditionLists (currentEle1:inputList1) inputList2) (findAdditionLists inputList1 (currentEle2:inputList2)))


-------------------------------------------------------------------------------------

-------------------------------- Get subtraction of two lists  ----------------------


-- BASE CASE: If list is empty, return empty list
findSubtractionLists [] _ = []
-- Subtracts set2 from set1
findSubtractionLists (currentEle1:inputList1) inputList2 =
    -- If element currentEle1 of inputList1 is also present in inputList2, then do not add currentEle1 in new
    -- list and call findSubtractionLists recursively for remaining inputList1
    if elem currentEle1 inputList2 then
        findSubtractionLists inputList1 inputList2
    -- Else, add currentEle1 to new list and call findSubtractionLists recursively for remaining inputList1
    else
        findUnionLists [currentEle1] (findSubtractionLists inputList1 inputList2)


-------------------------------------------------------------------------------------

