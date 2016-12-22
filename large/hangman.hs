import System.IO
import System.Random

-- main function that is a do loop. this is an imperative use of this language

main = do
  -- left arrow extracts the value and stores it into variable handle
  handle <- openFile "enable1.txt" ReadMode

  -- hGetContents extracts the contents of handle to one single long string and stores into contents
  contents <- hGetContents handle

  -- getStdGen returns seed for random number generator. The function uses IO to better generate random numbers
  gen <- getStdGen

  -- lines contents simply breaks the contents into lines and stores it into variable words
  -- NOTE: using map init will remove the trailing character in each line (varies system to system)
  let words = (lines contents)

  -- randomR takes a specific range (0 - length of words &quot;array&quot; - 1) and our random gen seed
  -- after the :: we have to give a type annotation, since other types are possible
  -- we return our random number, n and our next seed _, which we don't need
    (n, _) = randomR (0, (length words) - 1) gen :: (Int, StdGen)

    -- this is how we choose a random word, out of all the words, choose the nth one
    word = words !! n
  -- we replace the characters of our word with underscore to hide it as its displayed
  play word (map (\x -> '_') word) 6
  hClose handle

play word known guesses
