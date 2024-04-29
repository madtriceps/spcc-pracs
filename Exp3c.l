%{
#include<stdio.h>
int operators = 0;
int operands = 0;
%}

%%
[\+\-\*\/\=] {printf("OPERATORS: %s\n", yytext); operators++;}
[a-zA-Z]+ {printf("OPERANDS: %s\n", yytext); operands++;}
[0-9]+ {printf("NUMBERS: %s", yytext); operands++;}
%%

int yywrap() {
	return 1;
}

int main() {
	printf("Enter the expression: ");
	yylex();
	printf("No. of operators are: %d\n", operators);
	printf("No. of operands are: %d\n", operands);
	if(operands=operators+1)
		printf("\nValid Expression");
	else
		printf("\nInvalid Expression");
	return 0;
}