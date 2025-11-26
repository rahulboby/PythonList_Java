# PythonList_Java

A custom, Python-style list implementation written in Java.
This class mimics the behavior and flexibility of Python’s built-in list while running entirely on Java’s Object model.

The goal is simple:
Bring Python’s ease-of-use, dynamic typing, and indexing semantics into Java without generics or type restrictions.

# Features

- Supports mixed data types in a single list

- Implements Python‐like negative indexing

- Fully supports:

  - append()

  - insert()

  - remove()

  - pop()

  - extend()

  - count()

  - index()

  - reverse() (in-place)

  - reversed() (returns new list)

  - sort(), sort(reverse)

  - sorted(), sorted(reverse)

  - copy()

  - clear()

- Internally uses a singly linked list

- Handles out-of-range indexes like Python

- Auto-clamps negative indices exactly as Python does

- Works with any Java object via Object storage

- Clean, minimal API — no generics, no complexity

# Behavior Highlights

- Negative indices (-1, -2, etc.) work the same as Python

- Insertion beyond the end appends

- Insertion before index 0 clamps to the head

- Removal and popping follow Python semantics

- Sorting operations currently convert numeric types to double

- Planned improvement: store objects and use a Comparator for true type preservation

# Why This Exists

Java collections are type-safe and rigid; Python lists are dynamic and flexible.
This project combines the dynamic behavior of Python with the explicit control of Java, making it ideal for:

- practicing data structure internals

- Python-like scripting patterns in Java

- experimenting with linked–list behaviors

- learning how Python’s list operations behave under the hood

# Roadmap / Future Improvements

- Replace System.err errors with thrown exceptions

- Add optional Comparator for type-preserving sort()

- Add doubly linked list support

- Add iterator support for enhanced-for compatibility

- Optimize sorting using Dual-Pivot QuickSort or MergeSort
