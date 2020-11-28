 -- Importating Libraries
import Data.List
import Data.Maybe
import System.Directory
import System.IO.Unsafe
import Data.IORef
import System.Random

-- System random requirements
listg :: IORef [[Char]]
listg = unsafePerformIO (newIORef [])

readg :: IO [[Char]]
readg = readIORef listg

writeg :: [[Char]] -> IO ()
writeg value = writeIORef listg value


 -- List of teams participating
listOfTeams = ["BS", "CM", "CH", "CV", "CS", "DS", "EE", "HU", "MA", "ME", "PH", "ST"]
-- List of date and times on which matches would be played
listOfTimesAndDates = [("1-12-2020","9:30AM"),("1-12-2020","7:30PM"),("2-12-2020","9:30AM"),("2-12-2020","7:30PM"),("3-12-2020","9:30AM"),("3-12-2020","7:30PM")]

numTeams = length listOfTeams -- Total number of teams
maxMatches = numTeams `div` 2 -- Total number of maximum matches that will happen


-- Prints fixture details for all the teams
fixture "all" = do
    g <- newStdGen
    let temp = randomR (1, 100000) g
    let newSeed = fst(temp)
    let shuffledTeams = (permutations listOfTeams)!!newSeed
    let newFixtures = getFixtures shuffledTeams

    writeg shuffledTeams
    printAllFixturesHelper 0 newFixtures


-- Prints fixture details of specified team
-- ALso if the fixtures have not been initialized, print "Fixtures not initialzed" 
-- If the team exists, print it's fixture, else the print "Team does not exist"
fixture team = do
    shuffledTeams <- readg
    let currentFixtures = getFixtures shuffledTeams

    -- If there are not fixtures scheduled yet, print error
    if null currentFixtures then
        putStrLn "Fixtures not initialzed"
    else
        case elemIndex team shuffledTeams of
            Just id -> 
                if id < maxMatches then 
                    printTeamDetails id currentFixtures
                else 
                    printTeamDetails (id-maxMatches) currentFixtures
            Nothing -> 
                putStrLn "Team does not exist!"


-- Prints next match details given date and time
-- ALso if the fixtures have not been initialized, print "Fixtures not initialzed" 
-- If the date or time is invalid, print that warning also
-- Also if there is no match ahead, print "No Match ahead"
nextMatch day time = do
    shuffledTeams <- readg
    let currentFixtures = getFixtures shuffledTeams

    -- If there are not fixtures scheduled yet, print error
    if null currentFixtures then
        putStrLn "Fixtures not initialzed"
    -- Invalid case for day
    else if day < 1 || day > 31 then
        putStrLn "Invalid day!"
    -- Invalid case for time
    else if time < 0 || time > 23.99 then
        putStrLn "Invalid time!"
    -- Print details of next match depending on day and time
    else
        if day >= 4 then
            putStrLn("No match ahead!")
        else if day == 3 && time > 19.5 then
            putStrLn("No match ahead!")
        else
            if time <= 9.5 then
                printTeamDetails (0 + day*2 - 2) currentFixtures
            else if time <= 19.5 then
                printTeamDetails (1 + day*2 - 2) currentFixtures
            else 
                printTeamDetails (2 + day*2 - 2) currentFixtures


-- Prints match information in format: Team1 vs Team2     Date     Time
printTeamDetails n fixtures = putStrLn ((fst(fixtures!!n))++" vs "++(snd(fixtures!!n))++"     "++(fst (listOfTimesAndDates!!n))++"     "++(snd(listOfTimesAndDates!!n)))


-- Call recursively printAllFixturesHelper function
-- Whenever totalMatches have been exhausted, just return
printAllFixturesHelper i fixtures = 
    if i == maxMatches then 
        return() 
    else do
        printTeamDetails i fixtures
        printAllFixturesHelper (i+1) fixtures


-- Gets the fixtures from the given list of shuffledTeams
getFixtures shuffledTeams = 
    -- Create fixtures by combining first half of teams and second half of teams
    zip firstHalfTeams secondHalfTeams
    where 
        -- First half of shuffled list
        firstHalfTeams = take maxMatches shuffledTeams
        -- Second half of shuffled list
        secondHalfTeams = drop maxMatches shuffledTeams
