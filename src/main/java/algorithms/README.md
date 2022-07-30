# Algorithmen und Datenstrukturen

## Abstrakter Datentyp / Datentyp

ADT = Interface
DT = Implementation

Examples

| ADT     | DT                                    |
|---------|---------------------------------------|
| List    | Dynamic Array List / Linked List      |
| Queue   | Array based Queue / Stack based Queue |
| Map     | Tree Map / Hash Map                   |
| Vehicle | Golf Cart / Bicacle / Smart Car       |

## Zeit- und Speicherkomplexität

Big-O Notation ( Worst-Case)

Klassen

| Bezeichnung       | Formel        |
|-------------------|---------------|
| Constant Time     | O(1)          |
| Logarithmic Time  | O(log(n))     |
| Linear Time       | O(n)          |
| Linearithmic Time | O(nlog(n))    |
| Quadratic Time    | O(n²)         |
| Cubic Time        | O(n³)         |
| Exponential Time  | O(b^n), b > 1 |
| Factorial Time    | O(n!)         |

Nur der am stärksten wachsende Teil der Gleichung zählt.

f(n) = 7log(n)³ + 15n² + 2n³ + 8
O(f(n)) = O(n³)

Constant Time O(1)

```
a := 1
b := 2
c := a + 5*b
```

Linear Time O(n)

```
i := 1
While i < n Do
    i = i + 3
```

Big-O Examples

| Bezeichnung                                             | Laufzeit   |
|---------------------------------------------------------|------------|
| Finding all subsets of a set                            | O(2^n)     |
| Finding all permutations of a string                    | O(n!)      |
| Sorting use mergesort                                   | O(nlog(n)) |
| Iterating over all the cells in a matrix of size n by m | O(nm)      |
