# 2D Array Sorter & Duplicate Finder

A Java program that reads a 2D array of numbers from a CSV file, sorts all the values using **Insertion Sort**, finds duplicate numbers and their positions, and outputs a version of the matrix with duplicates removed.

## How It Works

1. Reads numbers from `numbers.csv` (comma-separated, one row per line) into a 2D array
2. Sorts all values using insertion sort, writing the result to `sorted_num.csv`
3. Finds duplicate values and their `(row, col)` positions, writing them to `duplicated_num.txt`
4. Removes duplicate values (keeping only the first occurrence) and appends the cleaned matrix to `duplicated_num.txt`

## Files

| File | Description |
|------|-------------|
| `Main.java` | Contains all logic: sorting, duplicate detection, duplicate removal, and file I/O |
| `numbers.csv` | Sample input file — place your comma-separated numbers here before running |

## How to Run

1. Make sure `numbers.csv` is in the same folder as `Main.java`
2. Compile: `javac Main.java`
3. Run: `java Main`
4. Check `sorted_num.csv` and `duplicated_num.txt` for output
