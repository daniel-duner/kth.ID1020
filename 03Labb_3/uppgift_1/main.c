#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#define CHUNK 1024

//README
//The program filter out all none alphabetic characters from the text by replacing
// them with a blank space. The new text is then saved into another text document
char buf[CHUNK];
FILE *inFile;
FILE *outFile;
size_t nread;

// Opens a file
void openFile(char* inOut, char* fileName, char*  fileMode){
    if(inOut == "in"){
        inFile = fopen(fileName, fileMode);
    } else if( inOut == "out"){
        outFile = fopen(fileName, fileMode);
    }
}

//closes files
void closeFiles(){
    fclose(inFile);
    fclose(outFile);
}

// counts the characters in a a textDocument
void charCounter(char* fileIn){
    openFile("in",fileIn, "r");
    int count = 0;

    while ((nread = fread(buf, 1, sizeof buf, inFile)) > 0){
        //printf("%d \n" ,nread);
        count += nread;
    }
    printf("%d \n", count);
    closeFiles();
}

//removes all characters that isn't alphabetic and replaces them with a blank space
//and prints it out into a new document. Text is handled in chunks, max 1024 Bytes at a time.
void alphaFilter(char *fileIn, char *fileOut){
    openFile("in",fileIn, "r");
    openFile("out",fileOut, "w");
    if (inFile) {
        while ((nread = fread(buf, 1, sizeof buf, inFile)) > 0) {
            for (int i = 0; i < CHUNK - 1; i++) {
                if (!isalpha(buf[i]) && !(buf[i] =='\n')) {
                    buf[i] = ' ';
                }
            }
            fwrite(buf, 1, nread, outFile);
        }
        closeFiles();
    }
};

//Test
int main() {
    alphaFilter("text.txt", "parsedText.txt");
    charCounter("text.txt");
    charCounter("parsedText.txt");
}