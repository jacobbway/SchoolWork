/*
* Jacob Way
* Data Structures Section 001
* Project 1 - Stack Usage (PostFix Evaluation)
*/



#include <iostream>
#include <stack>
#include <cstring>
#include <sstream>

/*This is the class I created to handle the basic post fix notation calls*/
class PostFix{
    private:
        int total, num1, num2;
        std::stack<int> nums;
    
    public:

        //default constructor for my class
        PostFix(){
            total = 0;
            num1 = 0;
            num2 = 0;
        }

        //perfoms the add operation for post fix
        void add(){
            num1 = nums.top();
            nums.pop();
            num2 = nums.top();
            nums.pop();
            nums.push(num1 + num2);
        }
        
        //perfroms the subtract operation for post fix
        void multiply(){
            int num1 = nums.top();
            nums.pop();
            int num2 = nums.top();
            nums.pop();
            nums.push(num1 * num2);
        }

        //i call this when i want to out put the total at this point the stack size should just be one
        void oTotal(){
            total = nums.top();
            nums.pop();
            std::cout << "The total is: " << total << "\n\n\n";
        }

        //i call this when i want to add a number to the stack
        void push(int num){
            nums.push(num);
        }
};

//this function makes sure there is the correct number of operands and numbers in the expression
bool opChecker(std::stringstream &exp){
    std::string token;
    int ops = 0, digs = 0;

    std::stringstream opCopy(exp.str());

    while(opCopy >> token){
        if(token == "="){
            continue;
        }
        else if(isdigit(token[0])){
            digs++;
        }
        else if(token == "+" || "*"){
            ops++;
        }
    }

    if(digs - ops != 1){
        std::cout << "There should be one less operand (+ or *) than amount of numbers!\n";
        return 0;
    }
    else{
        return 1;
    }
}

//this function makes sure the postfix expression is correct
bool expChecker(std::stringstream &exp){
    std::string token;
    int truth = 1;
    std::stringstream expCopy(exp.str());

    while(expCopy >> token){
        if(token[0] == '='){
            continue;
        }
        else if(!isdigit(token[0]) && !(token[0] == '+' || token[0] == '*')){
            std::cout << "The Expression can't contain letters or incorrect symbols!\n";
            return false;
            break;
        }
        else if((token.length() > 1) && ((token[0] == '+' || token[0] == '*' ) && (token[1] == '+' || token[1] == '*'))){
            std::cout << "There must be a space between operands!\n";
            return false;
            break;
        }
        else{
            truth = 1;
        }
    }

    if(!opChecker(exp)){
        return false;
    }

    if(truth == 1){
        return true;
    }
    else{
        return false;
    }
}

/*
*This function handles gathering user input
*This was the only hard part of the lab to be honest. 
*/
void stackCompiler(){
    std::cout << "Please enter a PostFix expression (ending with =): \n";
    std::cout << "Type 'q' to quit the program.\n\n";

    std::string input, token;
    PostFix evalulate;

    /*
    *This while loop just infinitely loops until broken from by a nested while loop
    */
    while(true) {
        std::cout << "\n\nPlease enter a PostFix expression (ending with =): \n";
        std::string expression; //I take the user input and turn it into a string. its also created every loop to start with a fresh string for multiple iterations

        //this while loop is also set to infinitely loop but i just exit program if 'q' is entered
        //or i break from the loop if the '=' operand is read
        while(true){
            std::getline(std::cin, input);
            if(input == "q"){
                std::cout << "Bye!\n";
                return;
            }
            if(input == "="){
                break;
            }
            expression += input + " "; //adding each new section to the string expression to later read with stringstream
        }

        //creating this string stream lets me use >> (extraction operator) to take pieces out at a time and check what they are
        std::stringstream ss(expression);

        if(!expChecker(ss)){
            continue;;
        }
        /*
        * this while loop iterates until there is no more tokens left from the stringstream
        */
        while(ss >> token){
            if(isdigit(token[0])) { //checks that the token is a digit (no handling for negative numbers)
                evalulate.push(std::stoi(token)); //pushes the token but converts it to an integer first
            }
            else if(token == "+"){
                evalulate.add();
            }
            else if(token == "*"){
                evalulate.multiply();
            }
        }

        evalulate.oTotal(); //displays the total
    }
}


int main(){
    stackCompiler();   
}