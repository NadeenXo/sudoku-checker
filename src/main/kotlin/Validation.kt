import kotlin.math.sqrt

fun isValidSudoku(board: Array<Array<String>>): Boolean {
    // Check for correct board size
    val n = board.size
    val subgridSize = sqrt(n.toDouble()).toInt()

    // Ensure all rows are of equal length
    // check if the number of columns in each row (it.size) =n
    if (board.any { it.size != n }) return false


    for (i in 0 until n) {
        val rowSet = mutableSetOf<String>()
        val colSet = mutableSetOf<String>()

        for (j in 0 until n) {
            // Row check
            val rowCell = board[i][j]
            if (rowCell != "-" && !rowSet.add(rowCell)) {
                return false
            }

            // Column check
            val colCell = board[j][i]
            if (colCell != "-" && !colSet.add(colCell)) {
                return false
            }
        }
    }

    //  subgrids
    for (rowStart in 0 until n step subgridSize) {
        for (colStart in 0 until n step subgridSize) {
            val seen = mutableSetOf<String>()
            for (row in rowStart until rowStart + subgridSize) {
                for (col in colStart until colStart + subgridSize) {
                    if (row < n && col < n) { //ensures we don't go out of bounds if n is not a perfect square.
                        val cell = board[row][col]
                        if (cell != "-" && !seen.add(cell)) {
                            return false
                        }
                    }
                }
            }
        }
    }

    return true
}