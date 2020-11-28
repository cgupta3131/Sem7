import Data.List -- Importating Libraries

 -- List of teams participating
teams = ["BS", "CM", "CH", "CV", "CS", "DS", "EE", "HU", "MA", "ME", "PH", "ST"]
-- List of date and times on which matches would be played
times = [("1-12-2020","9:30AM"),("1-12-2020","7:30PM"),("2-12-2020","9:30AM"),("2-12-2020","7:30PM"),("3-12-2020","9:30AM"),("3-12-2020","7:30PM")]

numTeams = length teams -- Total number of teams
maxMatches = numTeams `div` 2 -- Total number of maximum matches that will happen

seed = 1000 -- Seed for shuffling the list
shuffledTeams = (permutations teams)!!seed -- shuffled list of teams

firstHalfTeams = take maxMatches shuffledTeams -- First shuffled list
secondHalfTeams = drop maxMatches shuffledTeams -- Second shuffled list
fixtures = zip firstHalfTeams secondHalfTeams -- Combines the firstHalf of teams with secondHalf od teams

-- Prints match information in format: Team1 vs Team2     Date     Time
printTeamDetails n = putStrLn ((fst(fixtures!!n))++" vs "++(snd(fixtures!!n))++"     "++(fst (times!!n))++"     "++(snd(times!!n)))

-- Call recursivel allFixtureHelper function
-- Whenever totalMatches have been exhausted, just return
allFixtureHelper i = 
    if i == maxMatches then 
        return() 
    else do
        printTeamDetails i
        allFixtureHelper (i+1)

-- Prints fixture details for all the teams
fixture "all" = do
    allFixtureHelper 0

-- Prints fixture details of specified team
-- If the team exists, print it's fixture, else the print "Team does not exist"
fixture team = do
    case elemIndex team shuffledTeams of
        Just id -> 
            if id < maxMatches then 
                printTeamDetails id 
            else 
                printTeamDetails (id-maxMatches)
            
        Nothing -> 
            putStrLn "Team does not exist!"

-- Prints next match details given date and time
nextMatch day time = do
    -- Invalid case for day
    if day < 1 || day > 31 then
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
                printTeamDetails (0 + day*2 - 2)
            else if time <= 19.5 then
                printTeamDetails (1 + day*2 - 2)
            else 
                printTeamDetails (2 + day*2 - 2)