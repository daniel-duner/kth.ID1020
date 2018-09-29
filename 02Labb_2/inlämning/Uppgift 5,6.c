#include <stdio.h>

//README The array is the same size and contains the same elements before and after the loop,
//after the loop the array is sorted so that all the negative values are placed with a lower index than the positive values
void printArray(int* list, int size){ ;
    int k = 0;
    while(k < size){
        printf("[%d],", list[k]);
        k++;
    }
    printf("\n\n");
}

//Takes in an array and seperates the negative and positive values
//Loop invariants:
//n = number of elements in the list,
//i < j,  i + j = n,
//all elements following list[0],list[1].. < list[i] and list[j]<....,list[n-1],list[n] are sorted
void seperator(int* list, int size){
    int i = 0;
    int j = size;
    printf(" This is the array to order:\n");
    printArray(list, size);
    //stops when pointing at a positive value to go into the if-statement
    while((i < size) && (i < j)){
        if (list[i] > 0){
            //stops when pointing at a negative value to go into the if statement or untill the next value to look at is list[i]
            while(j > i){
                //swaps the two values and breaks the inner loop to start looking for a positive values
                if(list[j] < 0){
                    int swap = list[j];
                    list[j] = list[i];
                    list[i] = swap;
                    break;
                }
                j--;
            }
        }
        i++;
    }
    //printf("j(%d) points at: %d\n", j,list[j]);
    //printf("i(%d) points at: %d\n",i, list[i]);
}
//Test
int main() {
    int list[] = {1,-2,3,4,-4,-5,-6,10,-2,-5,1,15,-2,-2,3,4,-4,-67};
    int size = sizeof(list)/ sizeof(list[0]);
    seperator(list, size);
    printArray(list,size);
    return 0;
}