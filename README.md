# Data-Structures
For our project, we used Compressed Sparse Column (CSC) formatting for our sparse matrices. However, our input was .mtx files. In order to convert our input files into CSC format, we created a SparseMatrix class that would take in the matrix files, and convert them into CSC format. To find strongly connected components (SCC), we used the Kosaraju and Tarjan algorithms. Both algorithms are used to find SCCs within a matrix. Kosaraju does a depth-first-search on the matrix, before then transposing the matrix and running another depth-first search in order to find strongly connected components.

# To compile and this project
Run (in CPSC482 Project)
>javac *.java <br/>
>java Main

Results will be written to csv files and console