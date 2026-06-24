# Lexical Analyzer

A lexical analyzer (tokenizer) built in Java that reads source code from a file and breaks it down into categorized tokens. This project was built as part of a compiler design course.

## Features

- Reads source code from a text file
- Removes single-line and multi-line comments
- Tokenizes the input into categories:
  - **ID** — Identifiers (variable names, function names)
  - **NUM** — Integer numbers
  - **REAL** — Real/decimal numbers
  - **KEYWORD** — Reserved language keywords
  - **OPERATOR** — Arithmetic and logical operators
  - **String** — String literals
  - **Unknown** — Unrecognized tokens

## Supported Token Types

### Keywords
`if`, `while`, `for`, `int`, `String`, `var`, `double`, `import`, `do`, `boolean`, `const`, `public`, `private`, `void`, `class`, `return`, `Begin`, `End`

### Operators
`+`, `-`, `*`, `/`, `%`, `=`, `$`, `#`, `!`, `&`, `^`

### Comments
- Single-line comments starting with `!!`
- Multi-line comments enclosed in `/*! ... !/`

## Technologies

- Java

## How to Run

### Prerequisites

- Java JDK 8 or higher

### Run the Application

1. **Clone this repository:**
   ```
   git clone https://github.com/mohamadalbik/basic-compiler-code-analysis.git
   cd lexical-analyzer
   ```

2. **Create a source file** called `sc.txt` in the project root with the code you want to analyze

3. **Compile:**
   ```
   javac Lex.java
   ```

4. **Run:**
   ```
   java Lex
   ```

5. **Output will show:**
   - Original source code
   - Source code after comment removal
   - Tokenized output with each token's category

## Example Input (sc.txt)

```
int x = 10;
!! This is a comment
String name = "Hello";
```

## Example Output

```
int  KEYWORD
x  ID
=  OPERATOR
10  NUM

String  KEYWORD
name  ID
=  OPERATOR
Hello  Is String
```

## Project Structure

```
lexical-analyzer/
├── Lex.java          # Main source file (entire compiler logic)
├── sc.txt            # Input source file to analyze
```

## Notes

- The analyzer reads from `sc.txt` by default
- Identifiers must start with a letter or underscore
- Supports integer and real number formats
- String literals must be enclosed in double quotes

## About This Project

Built as a compiler design course project to demonstrate lexical analysis — the first phase of compilation. The program reads source code, strips comments, and categorizes each token for further processing by a parser.
