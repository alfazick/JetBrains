import random
# write your code here
print("Enter the number of friends joining (including you):")
guest_num = int(input())
if guest_num < 1:
    print()
    print("No one is joining for the party")
else:
    names = []
    print("Enter the name of every friend (including you), each on a new line:")
    for _ in range(guest_num):
        names.append(input())
        
    print("Enter the total bill value:")
    total_bill = int(input())
    split = round(total_bill / len(names) , 2)
    guests = dict.fromkeys(names, split)
    
    print('Do you want to use the "Who is lucky?" feature? Write Yes/No:')
    lucky = input()
    if lucky != "Yes":
        print("No one is going to be lucky")
        print()
        print(guests)
    else:
        name = random.choice(list(guests.keys()))
        print()
        print("{} is the lucky one!".format(name))
        split = round(total_bill / (len(names) - 1) , 2) 
        guests = dict.fromkeys(names, split)
        guests[name] = 0
        print()
        print(guests)
        