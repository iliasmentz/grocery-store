# Grocery Store

## What is this?🤔

An e-commerce grocery store that calculates the order total and prints its receipt, 
applying the following business rules:
- One order can have many items.
- Items can only be breads or beers.
- Breads always have discounts like "take 2 pay 1" or "take 3 pay 1”. In this type of
  discount the client always pays at least 100% of one unit of bread, but he/she takes
  more unit(s) as a bonus). If the bread is two days old or newer, it has no discounts. If
  it's between 3 and 5 days old, each bread you pay makes you eligible to take one more
  unit for free, and if it's exactly 6 days old, the bonus is 2 extra units. Breads older than
  6 days cannot be added to orders.
- Beers have only discounts if bought in packs containing 6 beers. The discount rules are
  fixed per packs: € 3,00 for each Belgium beer pack, € 2,00 for Dutch ones and € 1,00
  for German ones. Single bottles/cans of beer can always be added to the order, but in
  that case there is no discount. Buying 6 separated bottles of the same beer is the same
  as buying one pack of the same beer.


## How it works? 🧐
### How we identify unique products 🧺

In a production system, the term sku (stock keeping unit) is used to refer to a brand’s unique product 
that is uniquely described by a part number or an [EAN](https://en.wikipedia.org/wiki/International_Article_Number). 

In almost all the products there is an abstraction called `model`. 
This term is used to describe the process of feature extraction aims to identify specific attributes in 
the text  representation of the product and tag them or even better link.

_e.g. 
Model -> Iphone 12 Pro Max
SKU -> Iphone 12 Pro Max 128GB Graphite (contains color, capacity, etc.)_


In this exercise, we decided to keep things simpler and consider items the same with SKUs.
So in order to provide the same id (EAN) for every item, we "cheated" by generating the id 
based on the type and characteristics of the item.

This means that the id for breads will be like `BREAD_{DATE_PRODUCED}`, whereas for the beers the id will be
`BEER_{COUNTRY_ORIGINATED}`.

### Discounts 🤑

The total amount that will be printed on the receipt, is calculated the same way most modern grocery stores implement. 
All items are added up and if any of the business rules conditions are satisfied, the appropriate discount is 
calculated and subtracted from the current total amount.

For example, a customer selects two tubs of “Simple Yogurt”, where each tub costs 4.99$. 
Currently, there’s a “Buy one, get one free” promotion, which means that the receipt that will be printed will 
display the original total amount, which is equal to 9.98$, followed by the discount, which is 4.99$. 
The discount will be subtracted from the total amount (i.e. 9.98$ - 4.99$), and that will be the final total amount (here, 4.99$).

## Requirements 📋

```
Java 11+ 
Gradle 7.0
```

## How to run? 

Run build:
```shell
./gradlew build
```

Run tests with all the edge cases:
```shell
./gradlew test
```

Run main with a dummy order:
```shell
./gradlew run
```

## How can I add a new beer? 🍻

Easy-peasy 😉. Just go to `beers.yml` add a new beer and if that beer is 
from a new country, don't forget to add a new value to the `Country` enum 🌎.
