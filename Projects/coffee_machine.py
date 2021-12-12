class CoffeeMachine:
    def __init__(self):
        self.dic_capacity = {"water": 400, "milk": 540, "bean": 120, "cups": 9, "money": 550}
        
        self.costcoffee = {
                            "espresso":{"water": -250,"milk": 0,"bean": -16, "cups": -1, "money": 4},
                            "latte":{"water": -350, "milk": -75, "bean": -20, "cups": -1, "money": 7},
                            "cappucino":{"water": -200,"milk": -100,"bean": -12, "cups": -1, "money": 6},
                    }
                    
        self.choices = {1:"espresso", 2: "latte", 3: "cappucino"}
        
    def show_state(self):
        print("The coffee machine has:")
        print("{} of water".format(self.dic_capacity["water"]))
        print("{} of milk".format(self.dic_capacity["milk"]))
        print("{} of coffee beans".format(self.dic_capacity["bean"]))
        print("{} of disposable cups".format(self.dic_capacity["cups"]))
        print("{} of money".format(self.dic_capacity["money"]))
        
    def take(self):
        print("I gave you ${}".format(self.dic_capacity["money"]))
        self.dic_capacity["money"] = 0
    
    def buy(self,key):
        i = self.costcoffee[self.choices[key]].keys()
        q = self.costcoffee[self.choices[key]].values()
        if self.fill(i,q):
            print("I have enough resources, making you a coffee!")
        
    def print_actions(self):
        print("Write action (buy, fill, take, remaining, exit):")
        decision = input()
        return decision
        
    def print_menu(self):
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        
        decision = input()
        return decision
        
    
        
        
        
    def fill(self,ingredients,quantities):
        for ingredient, quantity in zip(ingredients, quantities):
            val = self.dic_capacity[ingredient] + quantity
            if val < 0:
                return False
        
        for ingredient, quantity in zip(ingredients, quantities):
            self.dic_capacity[ingredient] += quantity
        return True
        
    def manage_fill(self):
        ingredients = ["water","milk","bean","cups"]
        quantity = [0 for _ in range(len(ingredients))]
        
        print("Write how many ml of water you want to add:")
        water = int(input())
        quantity[0] = water
        
        print("Write how many ml of milk you want to add:")
        milk = int(input())
        quantity[1] = milk

        print("Write how many grams of coffee beans you want to add:")
        bean = int(input())
        quantity[2] = bean

        print("Write how many disposable coffee cups you want to add:")
        cups = int(input())
        quantity[3] = cups
        
        self.fill(ingredients,quantity)


my_machine = CoffeeMachine()

while True:
    
    
    desire = my_machine.print_actions()

    if desire == "fill":
        my_machine.manage_fill()
    elif desire == "remaining":
        my_machine.show_state()
    elif desire == "take":
        my_machine.take()
    elif desire == "buy":
        choice = my_machine.print_menu()
        if choice == "back":
            continue
        my_machine.buy(int(choice))
        
    else:
        break
    






