
from collections import defaultdict
mat = [[" " for _ in range(3)] for _ in range(3)]

#user_state = input("Enter cells: ")

def fill_grade(state,grid):
    for row in range(len(grid)):
        for col in range(len(grid[0])):
            idx = row * len(grid[0]) + col
            grid[row][col] = state[idx]
        

def show_grid(grid):
    print("---------")
    for row in range(len(grid)):
        print("|", end = " " )
        for col in range(len(grid[0])):
            print(grid[row][col], end = " ")
        print("|")
    print("---------")
    


def accept_move(grid):
    move = True
    while move:
    
        row,col = input("Enter the coordinates:").split()
        
        if not (row.isnumeric() or col.isnumeric()):
            print("You should enter numbers!")
            continue
        
        
        row = int(row)
        col = int(col)
        
        if row > 3 or col > 3:
            print("Coordinates should be from 1 to 3!")
            continue
        
        if grid[row-1][col-1] != " ":
            print("This cell is occupied! Choose another one!")
            continue
            
        move = False
            
    return row-1,col-1
    
    

def check_grid(grid):
    isPossible = True
    dic_possible = defaultdict(int)
    for row in range(len(grid)):
        for col in range(len(grid[0])):
            dic_possible[grid[row][col]] += 1
    
    X_cnt = dic_possible["X"]
    Y_cnt = dic_possible["O"]
    
    if abs(X_cnt - Y_cnt) > 1:
        isPossible = False
        
    if not isPossible:
        return "Impossible"
        
        
    winner_by_diag = set()
    winner_by_antidiag = set()
    
    
    X_wins = False
    O_wins = False
    
    # check by row
    for row in range(len(grid)):
        check_by_row = set()
        check_by_col = set()
        for col in range(len(grid[0])):
            check_by_row.add(grid[row][col])
            check_by_col.add(grid[col][row])
            if row == col:
                winner_by_diag.add(grid[row][col])
            
            if (row + col) == len(grid) - 1 :
                winner_by_antidiag.add(grid[row][col])
            
        if len(check_by_row) == 1 and list(check_by_row)[0] != " ":
            if list(check_by_row)[0] == "X":
                X_wins = True
            if list(check_by_row)[0] == "O":
                O_wins = True
                
        if len(check_by_col) == 1 and list(check_by_col)[0] != " ":
            if list(check_by_col)[0] == "X":
                X_wins = True
            if list(check_by_col)[0] == "O":
                O_wins = True
                
                
                
    if X_wins and O_wins:
        return "Impossible"
    
    if X_wins or O_wins:
        return "X wins" if X_wins else "O wins"
        
    if len(winner_by_diag) == 1 and list(winner_by_diag)[0] != " ":
        return "{} wins".format(list(winner_by_diag)[0])
        
    if len(winner_by_antidiag) == 1 and list(winner_by_antidiag)[0] != " ":
        return "{} wins".format(list(winner_by_antidiag)[0])
            
    if " " not in dic_possible:
        return "Draw"
    else:
        return "Game not finished"
        


def game():
    mat = [[" " for _ in range(3)] for _ in range(3)]
    gameisOn = True
    player = "X"
    while gameisOn:
        show_grid(mat)
        row,col = accept_move(mat)
        mat[row][col] = player
        status = check_grid(mat)
        if "wins" in status or "Draw" in status:
            gameisOn = False
            show_grid(mat)
            print(status)
        if player == "X":
            player = "O"
        else:
            player = "X"

game()
  