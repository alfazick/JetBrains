import random

from collections import defaultdict

def game():
    words = 'python', 'java', 'kotlin', 'javascript'
    idx = int(random.random() * len(words))
    hidden_word = words[idx]

    dic_letters = defaultdict(list)
    for idx,letter in enumerate(hidden_word):
        dic_letters[letter].append(idx)
        
    hidden_word_list = list(hidden_word)
    current_state = ["-" for _ in range(len(hidden_word))]
    guessed_letter = set()
    lives = 8
    guessed_letter_wrong = set()
    while lives > 0:
        print()
        print("".join(current_state))
        letter = input("Input a letter: ").strip()
        
        if len(letter) > 1 or not letter:
            print("You should input a single letter")
            continue
        
        if ord(letter) < 97 or ord(letter) > 122 :
            print("Please enter a lowercase English letter")
            continue
            
        if letter in guessed_letter_wrong or letter in guessed_letter:
            print("You've already guessed this letter")
            continue
        
        
        if letter in dic_letters and letter not in guessed_letter:
            for pos in dic_letters[letter]:
                current_state[pos] = letter
            guessed_letter.add(letter)
            
        elif letter in guessed_letter:
            print("No improvements")
            lives -= 1
            continue
        else:
            print("That letter doesn't appear in the word")
            lives -= 1
            guessed_letter_wrong.add(letter)
            continue
            
        if "".join(current_state) == hidden_word:
            break
        
        
    if lives > 0:
        print("You guessed the word!\nYou survived!") 
    else:
        print("You lost!")
        
def main():
    print("H A N G M A N")
    while True:
        print('Type "play" to play the game, "exit" to quit: ')
        choice = input()
        if choice == "play":
            game()
        elif choice == "exit":
            break
        else:
            continue
    
main() 
    
    