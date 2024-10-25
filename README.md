﻿# Self Checkout System
This project is meant to model the hardware and software of a self checkout system at a grocery store, including external resources like the attendant station and products. 

The GUI is built using java swing.

Each component is thoroughly tested via JUnit.

## Hardware
the hardware modeled include:
 - banknote dispenser, slot, and storage unit
 - coin dispenser, slot, tray, and storage unit
 - card reader
 - barcode scanner
 - scale
 - touchscreen
 - keyboard
 - receipt printer

The design for the hardware follows an observer pattern.

## Software
Software functions include a variety of necessary actions necessary for a self checkout system, such as adding items via scanning or entering an item code, checking out and payment via card, cash, or coin, ensuring the correct items (determined by weight) are added to the bagging area etc. Other more niche functions are also provided, such as blocking all interaction at the self checkout system and notifying an attendant at their station that a customer needs help, reminding customers to place their item in the bagging area if they have not done so for a period of time after scanning an item, etc.
