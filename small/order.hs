-- import packaged sort
import Data.List (sort)

-- IO is performed when we use "main ="
-- interact allows us to accept keyboard input and output to console.
-- unlines joins the input into a single newline delimited string
-- map will take that each of those elements and apply the classify function to use them
-- lines will break up the unified elements the unlines joined and output them

main = do
  putStrLn "Please enter a string to evaluate its order"
  interact $ unlines . map classify . lines

  -- we use where to denote a function in this case where we make use of the '|'
  -- the '|' is called a guard. it is a boolean expression. if it evaluates to true, then that corresponding body is used.
  -- we are evaluating if the string is in alpha numeric order. Numbers occur before letters.
  -- in the first guard out expression is evaluating if our string, t, is equal to the sorted version if so, it is ORDERED
  -- in the second guard we check for the REVERSE order
  -- otherwise our string is unordered

  where classify t
    | t == sort t = t ++ " is ORDERED"
    | t == reverse (sort t) = t ++ " is REVERSED"
    | otherwise = t ++ " is UNORDERED"
