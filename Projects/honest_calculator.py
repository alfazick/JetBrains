# write your code here

messages = [
            "Enter an equation", 
            "Do you even know what numbers are? Stay focused!", 
            "Yes ... an interesting math operation. You've slept through all classes, haven't you?",
            "Yeah... division by zero. Smart move...",
            "Do you want to store the result? (y / n):",
            "Do you want to continue calculations? (y / n):",
            " ... lazy",
            " ... very lazy",
            " ... very, very lazy",
            "You are",
            "Are you sure? It is only one digit! (y / n)",
            "Don't be silly! It's just one number! Add to the memory? (y / n)",
            "Last chance! Do you really want to embarrass yourself? (y / n)"
            ]
            

def convert(num):
    num = float(num)
    if int(num) != float(num):
        num = float(num)
    else:
        num = int(num)

    return num



def isNumber(digits):
    try:
        result = float(digits)
    except:
        return False
        
    return True
    
def save_continue(msgs,result):
    while True:
        print(msgs[4])
        response_save = input()
        if response_save in "yn":
            break

    if response_save == "y" and is_one_digit(convert(result)):
        msg_index = 10               
        while True:
            if msg_index > 12:
                break
            print(messages[msg_index])
            response_save = input()
            
            if response_save not in "yn":
                continue
        
            if response_save == "y":
                if msg_index < 13:
                    msg_index += 1
                    continue
                else:
                    break
            else:
                break
            
    save_result = False
    continue_calc = False
    
    if response_save == "y":
        save_result = True
        
    print(msgs[5])

    response_continue = input()
    
    if response_continue == "y":
        continue_calc = True
        
    return (save_result, continue_calc)
    
def is_one_digit(v):
    v = convert(v)
    output = False
    if (v > -10 and v < 10) and (isinstance(v, int)) :
        output = True
    return output
    

def check(v1, v2, v3, msgs):
    #print(v1,v2,v3)
    msg = ""
    
    if is_one_digit(v1) and is_one_digit(v2):
        msg += msgs[6]
        
    if (v1 == 1 or v2 == 1) and v3 == "*":
        msg += msgs[7]
        
    if (v1 == 0 or v2 == 0) and (v3 == "*" or v3 == "+" or v3 == "-"):
        msg += msgs[8]
        
    if msg!= "":
        msg = msgs[9] + msg
        print(msg)
    
    return
    

    
    
memory = 0
while True:
    #print(memory)
    print(messages[0])
    calc = input()
    x,oper,y = calc.split(" ")
    
    if x == "M":
        x = memory
        
    if y == "M":
        y = memory
        
    
    
    if not (isNumber(x) and isNumber(y)):
        print(messages[1])
        continue
    else:
        if oper not in "+-*/":
            print(messages[2])
        else:
            
            result = None

            x = convert(x)
            y = convert(y)
            
            check(x, y, oper, messages)
            
            if oper == "+":
                result = x + y
            elif oper == "-":
                result = x - y
                
            elif oper == "*":
                result = x * y
                
            elif oper == "/" and y != 0:
                result = x / y
            else:
                print(messages[3])
                continue
            
            if result is not None:
                
                print(float(result))
                
                s,c = save_continue(messages,result)
                
                if s:         
                    if int(result) == result:
                        memory = int(result)
                        #print(memory)
                    else:
                        memory = float(result)
                                    
                if not c:
                    break
                

