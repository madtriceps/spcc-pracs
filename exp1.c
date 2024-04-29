#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_SYMBOLS 100

struct SymbolTable {
    char symbol;
    char type[20];
    int address;
};

struct SymbolTable symbolTable[MAX_SYMBOLS];
int symbolCount = 0;

void insertExpression();
void displayExpression();
void deleteSymbol();
void modifySymbol();
void searchSymbol();
int isAlphabet(char);
int isNumber(char);

int main() {
    int choice;
    srand(time(NULL));

    do {
        printf("\nSymbol Table Operations:\n");
        printf("1. Insert Expression\n");
        printf("2. Display Expression\n");
        printf("3. Delete Symbol\n");
        printf("4. Search Symbol\n");
        printf("5. Modify Symbol\n");
        printf("6. Exit\n");
        printf("Enter Your Choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                insertExpression();
                break;
            case 2:
                displayExpression();
                break;
            case 3:
                deleteSymbol();
                break;
            case 4:
                searchSymbol();
                break;
            case 5:
                modifySymbol();
                break;
            case 6:
                printf("Exiting Program...\n");
                break;
            default:
                printf("Please Enter a Valid Number\n");
        }
    } while (choice != 6);

    return 0;
}

void insertExpression() {
    printf("Enter the Expression: ");
    fflush(stdin);
    char expression[100];
    scanf("%s", expression);

    for (int i = 0; i < strlen(expression); i++) {
        char character = expression[i];
        if (isAlphabet(character)) {
            int found = 0;
            for (int j = 0; j < symbolCount; j++) {
                if (symbolTable[j].symbol == character) {
                    found = 1;
                    break;
                }
            }
            if (found == 0) {
                symbolTable[symbolCount].symbol = character;
                strcpy(symbolTable[symbolCount].type, "Identifier");
                symbolTable[symbolCount].address = rand() % 9000 + 1000;
                symbolCount++;
            }
        }
        else if (isNumber(character)) {
            // Assuming numbers are not stored in the symbol table
        }
        else {
            int found = 0;
            for (int j = 0; j < symbolCount; j++) {
                if (symbolTable[j].symbol == character) {
                    found = 1;
                    break;
                }
            }
            if (found == 0) {
                symbolTable[symbolCount].symbol = character;
                strcpy(symbolTable[symbolCount].type, "Operator");
                symbolTable[symbolCount].address = rand() % 9000 + 1000;
                symbolCount++;
            }
        }
    }
}

void displayExpression() {
    printf("\nSymbol\tAddress\tType\n");
    for (int i = 0; i < symbolCount; i++) {
        printf("%c\t%d\t%s\n", symbolTable[i].symbol, symbolTable[i].address, symbolTable[i].type);
    }
}

void deleteSymbol() {
    printf("Please Enter the symbol to be deleted: ");
    char symbol;
    scanf(" %c", &symbol);

    int found = 0;
    for (int i = 0; i < symbolCount; i++) {
        if (symbolTable[i].symbol == symbol) {
            found = 1;
            for (int j = i; j < symbolCount - 1; j++) {
                symbolTable[j].symbol = symbolTable[j + 1].symbol;
            }
            symbolCount--;
            break;
        }
    }
    if (!found) {
        printf("Symbol not found.\n");
    }
    else {
        printf("Symbol deleted successfully !\n");
    }
}

void searchSymbol() {
    printf("Please Enter the symbol to be searched: ");
    char symbol;
    scanf(" %c", &symbol);

    int found = 0;
    for (int i = 0; i < symbolCount; i++) {
        if (symbolTable[i].symbol == symbol) {
            found = 1;
            printf("Symbol Found !\n");
            break;
        }
    }
    if (!found) {
        printf("Symbol not found !\n");
    }
}

void modifySymbol() {
    printf("Enter the symbol to be modified: ");
    char symbol;
    scanf(" %c", &symbol);

    int found = 0;
    for (int i = 0; i < symbolCount; i++) {
        if (symbolTable[i].symbol == symbol) {
            found = 1;
            printf("Symbol has been found.\n");
            printf("Please enter the new type: ");
            scanf("%s", symbolTable[i].type);
            printf("Symbol Modified successfully !\n");
            break;
        }
    }
    if (!found) {
        printf("Oops Symbol to be modified not present.\n");
    }
}

int isAlphabet(char ch) {
    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
}

int isNumber(char ch) {
    return (ch >= '0' && ch <= '9');
}
