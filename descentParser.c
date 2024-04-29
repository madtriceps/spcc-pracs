#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define SIZE 100

int i = 0;

void procF(char str[]);
void procT(char str[]);
void procE(char str[]);

void match(char str[], char token) {
    if (str[i] == token) {
        i++;
    } else {
        printf("ERROR\n");
        exit(1);
    }
}
void procF(char str[]) {
    if (str[i] == '(') {
        match(str, '(');
        procE(str);
        match(str, ')');
    } else if (str[i] == 'i') {
        match(str, 'i');
    } else {
        printf("ERROR\n");
        exit(1);
    }
}

void procT(char str[]) {
    procF(str);
    if (str[i] == '*') {
        match(str, '*');
        procT(str);
    }
}

void procE(char str[]) {
    procT(str);
    if (str[i] == '+') {
        match(str, '+');
        procE(str);
    }
}

int main() {
    printf("Enter the input: \n");
    char str[SIZE];
    fgets(str, SIZE, stdin);
    int n = strlen(str) - 1;

    procE(str);
    if (n == i) {
        printf("Input is accepted\n");
    } else {
        printf("Input isn't accepted\n");
        printf("ERROR\n");
    }

    return 0;
}
