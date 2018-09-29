#include <stdio.h>

//README
//A iterative and recursive function that reads an input
//from the command line and stores the characters until enter is pressed,
//when enter is pressed the characters will be returned in order with the
//last added printed first

char myString[30];
int i = -1;
char key;

//reads each char and stores them in the array
// until enter is pressed then it will print out the chars backwards
void recursiveKeyReader(){
    key = getchar();

    if(key != '\n'){
        i++;
        myString[i] = key;
        recursiveKeyReader();
    }
    if(i >= 0){
        putchar(myString[i]);
        i--;

    }

};

//Reads each char and stores them in the array
// until enter is pressed then it will print out the chars backwards
void iterativeKeyReader(){
    i = -1;
    int condition = 1;
    while(condition){
        key = getchar();
        if(key != '\n'){
            i++;
            myString[i] = key;
        } else{
            condition = 0;
        }
    }
    while(i > -1){
        putchar(myString[i]);
        i--;
    }

};

//Test, read from the command line
int main(){
    recursiveKeyReader();
    putchar('\n');
    iterativeKeyReader();
    putchar('\n');
    return 0;
}