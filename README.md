# Kalc - A Simple Programming Language for Math

**Kalc** is a new programming language designed for simplicity and clarity in expressing mathematical calculations. With Kalc, you can easily define functions, perform arithmetic operations, and build more complex mathematical expressions using a straightforward syntax. 

Whether you're a beginner learning about programming or an experienced developer needing a concise tool for mathematical tasks, Kalc provides a user-friendly and efficient way to explore and express mathematical logic.

## Features

* **Clear and concise syntax:** Kalc's syntax is designed to be easy to read and write, even for those new to programming.
* **Functions:** Define reusable functions to encapsulate and organize your calculations.
* **Algebraic expressions:** Express mathematical calculations naturally using variables, operators, and functions.
* **Extensible:** Kalc is designed to be extensible, allowing for the addition of new features and capabilities in the future.

## Compiler Overview

The Kalc compiler is a program that translates Kalc code into executable instructions. It consists of several stages:

1. **Lexical Analysis (Lexer):** Reads the source code and breaks it down into a stream of tokens, such as numbers, operators, and keywords.

2. **Syntax Analysis (Parser):** Takes the tokens produced by the lexer and constructs an Abstract Syntax Tree (AST), which represents the grammatical structure of the code.

3. **Semantic Analysis:** Checks the AST for semantic errors, such as type mismatches or undefined variables.

4. **Code Generation:** Translates the AST into executable code, which can be run on a target machine or virtual machine.

## Example Code

```kalc
fn factorial(n) {
  if (n <= 1) {
    return 1;
  } else {
    return n * factorial(n - 1);
  }
}

factorial(5)  // Evaluates to 120
