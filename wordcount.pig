-- Step 1: Load the input text file
lines = LOAD 'input.txt' AS (line:chararray);

-- Step 2: Break each line into words
words = FOREACH lines GENERATE FLATTEN(TOKENIZE(line)) AS word;

-- Step 3: Group the words
grouped_words = GROUP words BY word;

-- Step 4: Count occurrences
word_count = FOREACH grouped_words GENERATE group AS word, COUNT(words) AS count;

-- Step 5: Store or dump the result
DUMP word_count;
