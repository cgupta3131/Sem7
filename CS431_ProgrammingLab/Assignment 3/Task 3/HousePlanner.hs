
------------------------------------------------ Forms tuples given a list and element -----------------------------------


-- Forms tuple (a, b) given list aList and bElement
formTuple1 aList bElement = [(a, b) | a <- aList, b <- bElement]

-- Forms tuple (a, b, c) given list aList and cElement
formTuple2 aList cElement = [(a, b, c) | (a,b) <- aList, c <- cElement]

-- Forms tuple (a, b, c, d) given list aList and dElement
formTuple3 aList dElement = [(a, b, c, d) | (a,b,c) <- aList, d <- dElement]

-- Forms tuple (a, b, c, d, e) given list aList and eElement
formTuple4 aList eElement = [(a, b, c, d, e) | (a,b,c,d) <- aList, e <- eElement]

-- Forms tuple (a, b, c, d, e, f) given list aList and fElement
formTuple5 aList fElement = [(a, b, c, d, e, f) | (a,b,c,d,e) <- aList, f <- fElement]


--------------------------------------------------------------------------------------------------------------------------

----------------------------------------------- Finds all the possible areas --------------------------------------------


-- Finds all possible dimensions given smallest dimension (a, b1) and largest dimension (a, b2)
listAreasHelper from to =
    if snd from == snd to then -- Even if the second one is equal, then only one option
        [from]
    -- If b1 != b2, we need to get (a, b1) and recursively call listAreasHelper for (a, b1+1)
    else
        from : listAreasHelper new_from to
    where new_from = (fst from, snd from + 1)

-- Finds all possible dimensions given smallest dimension (a1, b1) and largest dimension (a2, b2). Here a1 != a22
listAllPossibleAreas from to =
    if fst from == fst to then -- If a1==a2, then call for listAreasHelper
        listAreasHelper from to
    -- If a1 != a2, we need to get (a1, b) s.t. b ranges from b1 to b2
    -- and call listAllPossibleAreas recursively for (a1+1, b1), (a2, b2)
    else
        listAreasHelper from to ++ listAllPossibleAreas new_from to
    where
        new_from = (fst from + 1, snd from)


--------------------------------------------------------------------------------------------------------------------------

---------------------------------------------- Remove Redundant from the list --------------------------------------------


-- BASE CASE: If list is empty, return empty list
removeUnwantedTuples3 [] _ _ _ _ _ = []
-- Removes redundant combinations with same total area
removeUnwantedTuples3 ((x,y,z,p):comb3) unique numBedrooms numHalls numKitchens numBathrooms =
    -- If total area is already present in unique list, then call removeUnwantedTuples3 recursively for rest of the list
    if elem (fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens
        +fst(p)*snd(p)*numBathrooms) unique then
        
        removeUnwantedTuples3 comb3 unique numBedrooms numHalls numKitchens numBathrooms
    -- Else, put the total area in unique list and call removeUnwantedTuples3 recursively for rest
    -- of the list and append (x,y) to the answer
    else
        (x,y,z,p) : removeUnwantedTuples3 comb3 (fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls
            +fst(z)*snd(z)*numKitchens+fst(p)*snd(p)*numBathrooms:unique) numBedrooms
            numHalls numKitchens numBathrooms


-- BASE CASE: If list is empty, return empty list
removeUnwantedTuples4 [] _ _ _ _ _ _ = []
-- Removes redundant combinations with same total area
removeUnwantedTuples4 ((x,y,z,p,q):comb4) unique numBedrooms numHalls numKitchens numBathrooms numGardens =
    -- If total area is already present in unique list, then call removeUnwantedTuples4 recursively
    -- for rest of the list
    if elem (fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens
        +fst(p)*snd(p)*numBathrooms+fst(q)*snd(q)*numGardens) unique then
        
        removeUnwantedTuples4 comb4 unique numBedrooms numHalls numKitchens numBathrooms numGardens
    -- Else, put the total area in unique list and call removeUnwantedTuples4 recursively for rest
    -- of the list and append (x,y) to the answer
    else
        (x,y,z,p,q) : removeUnwantedTuples4 comb4 (fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls
            +fst(z)*snd(z)*numKitchens+fst(p)*snd(p)*numBathrooms+fst(q)*snd(q)*numGardens
            :unique) numBedrooms numHalls numKitchens numBathrooms numGardens


-- BASE CASE: If list is empty, return empty list
removeUnwantedTuples5 [] _ _ _ _ _ _ _ = []
-- Removes redundant combinations with same total area
removeUnwantedTuples5 ((x,y,z,p,q,r):comb5) unique numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies =
    -- If total area is already present in unique list, then call removeUnwantedTuples5 recursively
    -- for rest of the list
    if elem (fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens
        +fst(p)*snd(p)*numBathrooms+fst(q)*snd(q)*numGardens+fst(r)*snd(r)*numBalconies)
        unique then
        
        removeUnwantedTuples5 comb5 unique numBedrooms numHalls numKitchens numBathrooms numGardens
            numBalconies
    -- Else, put the total area in unique list and call removeUnwantedTuples5 recursively for rest
    -- of the list and append (x,y) to the answer
    else
        (x,y,z,p,q,r) : removeUnwantedTuples5 comb5 (fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls
            +fst(z)*snd(z)*numKitchens+fst(p)*snd(p)*numBathrooms+fst(q)*snd(q)*numGardens
            +fst(r)*snd(r)*numBalconies:unique) numBedrooms numHalls numKitchens numBathrooms
            numGardens numBalconies


--------------------------------------------------------------------------------------------------------------------------

-------------------------------------------- Finds the maximum area from given list --------------------------------------


-- BASE CASE: When the input list is empty, return the calculateMaximumArea as 0
calculateMaximumArea [] _ _ _ _ _ _ = 0

-- Returns the total maxium area from the list of combinations
calculateMaximumArea ( (x,y,z,p,q,r):nextCombination) numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies =

    maximum [curArea, calculateMaximumArea nextCombination numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies]
    where 
        allBedroomAreas = fst(x)*snd(x)*numBedrooms
        allHallAreas = fst(y)*snd(y)*numHalls
        allKitchenAreas = fst(z)*snd(z)*numKitchens
        allBathroomAreas = fst(p)*snd(p)*numBathrooms
        allGardenAreas = fst(q)*snd(q)*numGardens
        allBalconyAreas = fst(r)*snd(r)*numBalconies
        curArea = allBedroomAreas + allHallAreas + allKitchenAreas + allBathroomAreas + allGardenAreas + allBalconyAreas


--------------------------------------------------------------------------------------------------------------------------

----------------------------Returns all the sextuples possible with given constraints------------------------------------


-- Get all possible combinations of bedrooms and halls
getAllPossibleCouples area numBedrooms numHalls = 
    
    -- Keep only those tuples whose total area is less than or equal to given area
    filter (\(x,y) -> fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls <= area) comb1
    where
        -- Combine bedroom and hall dimensions to form combination tuple: (bedroom, hall)
        comb1 = formTuple1 allPossibleAreaBedroom allPossibleAreaHall

-- Get all possible combinations of bedrooms and halls and kitchens
getAllPossibleTriplets coupleComb area numBedrooms numHalls numKitchens = 

    filter (\(x,y,z) -> fst(z) <= fst(x) && fst(z) <= fst(y) && snd(z) <= snd(x) && snd(z) <= snd(y)) filter_comb2
    where
        -- Combine previous and kitchen dimensions to form combination tuple: (bedroom, hall, kitchen)
        comb2 = formTuple2 coupleComb allPossibleAreaKitchen
        -- Keep only those tuples whose total area is less than or equal to given area
        filter_comb2 = filter (\(x,y,z) -> fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens <= area) comb2

-- Get all possible combinations of bedrooms and halls and kitchens and bathrooms
getAllPossibleQuadruple tripleComb area numBedrooms numHalls numKitchens numBathrooms = 

    -- Remove redundant tuples whose total area is same
    removeUnwantedTuples3 filter2_comb3 [] numBedrooms numHalls numKitchens numBathrooms
    where 
        -- Combine previous and bathroom dimensions to form combination tuple: (bedroom, hall, kitchen, bathroom)
        comb3 = formTuple3 tripleComb allPossibleAreaBathroom
        -- Keep only those tuples whose total area is less than or equal to given area
        filter_comb3 = filter (\(x,y,z,p) -> fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens+fst(p)*snd(p)*numBathrooms <= area) comb3
        -- dimension of a bathroom must not be larger than that of a kitchen
        filter2_comb3 = filter (\(_,_,z,p) -> fst(p) <= fst(z) && snd(p) <= snd(z)) filter_comb3
    
-- Get all possible combinations of bedrooms and halls and kitchens and bathrooms and gardens
getAllPossibleQintuple quadrupleComb area numBedrooms numHalls numKitchens numBathrooms numGardens = 
    -- Remove redundant tuples whose total area is same
    removeUnwantedTuples4 filter_comb4 [] numBedrooms numHalls numKitchens numBathrooms numGardens
    where
        -- Combine previous and garden dimensions to form combination tuple: (bedroom, hall, kitchen, bathroom, garden)
        comb4 = formTuple4 quadrupleComb allPossibleAreaGarden
        -- Keep only those tuples whose total area is less than or equal to given area
        filter_comb4 = filter (\(x,y,z,p,q) -> fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens+fst(p)*snd(p)*numBathrooms+fst(q)*snd(q)*numGardens <= area) comb4

-- Get all possible combinations of bedrooms and halls and kitchens and bathrooms and gardens and balconies within given constraints
getAllPossibleSexTuples area numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies = 

    removeUnwantedTuples5 filter_comb5 [] numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies
    where 
        
        coupleComb = getAllPossibleCouples area numBedrooms numHalls
        tripleComb = getAllPossibleTriplets coupleComb area numBedrooms numHalls numKitchens
        quadrupleComb = getAllPossibleQuadruple tripleComb area numBedrooms numHalls numKitchens numBathrooms
        quintupleComb = getAllPossibleQintuple quadrupleComb area numBedrooms numHalls numKitchens numBathrooms numGardens
        
        -- Combine previous and balcony dimensions to form combination tuple: (bedroom, hall, kitchen, bathroom, garden, balcony)
        comb5 = formTuple5 quintupleComb allPossibleAreaBalcony
        -- Keep only those tuples whose total area is less than or equal to given area
        filter_comb5 = filter (\(x,y,z,p,q,r) -> fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens+fst(p)*snd(p)*numBathrooms+fst(q)*snd(q)*numGardens+fst(r)*snd(r)*numBalconies <= area) comb5


--------------------------------------------------------------------------------------------------------------------------

------------------------------------------------- Print Output -----------------------------------------------------------


-- Prints the final output
printOutput totalAreaGiven maximumAreaAchieved output numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies = do
    if null output then do
        putStrLn "No design possible for the given constraints"
    -- Else, print the dimension details of the first combination with maximum area
    else do
        let (x,y,z,p,q,r) = output!!0
        putStrLn("Bedroom: " ++ show(numBedrooms) ++ " " ++ show(x))
        putStrLn("Hall: " ++ show(numHalls) ++ " " ++ show(y))
        putStrLn("Kitchen: " ++ show(numKitchens) ++ " " ++ show(z))
        putStrLn("Bathroom: " ++ show(numBathrooms) ++ " " ++ show(p))
        putStrLn("Garden: " ++ show(numGardens) ++ " " ++ show(q))
        putStrLn("Balcony: " ++ show(numBalconies) ++ " " ++ show(r))
        putStrLn("Unused Space: " ++ show(totalAreaGiven-maximumAreaAchieved))


--------------------------------------------------------------------------------------------------------------------------


-- Get all possible dimensions of the components
allPossibleAreaBedroom = listAllPossibleAreas (10,10) (15,15)
allPossibleAreaHall = listAllPossibleAreas (15,10) (20,15)
allPossibleAreaKitchen = listAllPossibleAreas (7,5) (15,13)
allPossibleAreaBathroom = listAllPossibleAreas (4,5) (8,9)
allPossibleAreaBalcony = listAllPossibleAreas (5,5) (10,10)
allPossibleAreaGarden = listAllPossibleAreas (10,10) (20,20)

-- Prints dimensions of all components given area, number of bedrooms and number of halls
design area numBedrooms numHalls = do

    -- One kitchen per 3 bedrooms
    let numKitchens = ceiling(fromIntegral numBedrooms/3)::Integer
    -- Number of bathrooms is one more than the number of bedrooms
    let numBathrooms = numBedrooms + 1
    -- Exactly one Garden
    let numGardens = 1
    -- Exactly one balcony
    let numBalconies = 1
    
    -- Find all the possible combinations of sextuples
    let allCombinationsPossible = getAllPossibleSexTuples area numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies
    -- Find the maximum area possible from the given dimension combinations
    let maximumAreaAchieved = calculateMaximumArea allCombinationsPossible numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies
    
    -- Keep only those whose area is equal to the maximumArea
    let output = filter (\(x,y,z,p,q,r) -> fst(x)*snd(x)*numBedrooms+fst(y)*snd(y)*numHalls+fst(z)*snd(z)*numKitchens+fst(p)*snd(p)*numBathrooms+fst(q)*snd(q)*numGardens+fst(r)*snd(r)*numBalconies == maximumAreaAchieved) allCombinationsPossible
    
    printOutput area maximumAreaAchieved output numBedrooms numHalls numKitchens numBathrooms numGardens numBalconies
