import kotlin.math.sqrt

fun isValidSudoku(board: Array<Array<String>>): Boolean {
    // Check for correct board size
    val n = board.size
    val subgridSize = sqrt(n.toDouble()).toInt()


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
                    if (row < n && col < n) {
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