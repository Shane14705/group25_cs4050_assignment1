# If you wish to verify the algorithms, you must remove <ins>all delays and `paintComponent` calls</ans>.

# Performance Measuring
The performance of these algorithms relative to each other is <ins>***greatly***</ins> affected by the amount of `paintComponent` calls, and due to the way the algorithms are written, not all methods can call `paintComponent` the exact same number of times.

Therefore, in order to get timings with relative differences similar to what one would see _without_ any `paintComponent` calls, `delay()` has been used in certain algorithms. 

### Notes:
- Iterative Merge sort has not had any of the above modifications done since it was given to us, so it may not line up with what would be expected after seeing the timing reports of the other algorithms
- Sorts can take longer than ~60 seconds to run, with selection sort and bubble sort being more than 100 seconds most of the time. (Due to `paintComponent`, of course). To change this, you can lower the number of bars to be sorted.

# Example of Operation
![](D:\group25_cs4050_assignment1\example_screenshot.png)
Here we have lowered the count to 100 lines so we can see bubble sort finish. As you can see, even though the difference in timings looks pretty close to what one would expect, it's impossible to guarantee this (for example, see shell sort being slightly faster than quicksort). It also seems to be hit or miss whether recursive merge sort or quick sort comes out on top, based on how the bars are scrambled and the number of bars.

