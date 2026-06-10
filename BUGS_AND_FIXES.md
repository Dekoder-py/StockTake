# Bugs, and fixes for them

This document outlines bugs or issues I encountered during development, and how I fixed them.

## Integer Overflow for InventoryItem.updateQuantity()

Setting the amount passed to this function to **2,147,483,648** (or -2,147,483,648) causes a wrap-around
to **-2,147,483,647** (or 2,147,483,647). I plan to fix this by adding an if statement to prevent users
checking in or out more than 2 billion / less than -2 billion items. I may also set the amount to an unsigned int and prevent any negative number.
