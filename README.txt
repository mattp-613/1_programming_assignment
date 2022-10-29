Hi, this is programming assignment 1 by Matthew Petrucci, 300119235, for CSI 2110 2022 Fall.
The entire project was done soley by me, and the git repository to the project can be found here:
https://github.com/mattp-613/1_programming_assignment
For access email me at mpetr043@uottawa.ca. I mainly used git to prove that all work was done by me and to keep my work organized.

INSTRUCTIONS & HOW TO USE

ex: java DBScan Point_Cloud_1.csv 0.1 8

The java executable DBScan must be ran with three arguments given. You can run it with different amounts but this is the most optimal way to do so.
The first argument is the CSV file it will read. The second is the distance (EPS) between the points deemed acceptable. The third argument is the minimum points required to be in a cluster
Afterwards it will produce a save file depending on the text given in the main function in DBScan (i set it to "test1"). This save file will generate a CSV including the clusters for all points using an algorithm and assign each cluster a randomly generated RGB value using a randomly generated seed multiplier. All RGB colours will be between 0.0-1.0. The save file is formatted to also include EPS, distance, and unique n clusters found.

That's basically it. Thanks for reading this :)
