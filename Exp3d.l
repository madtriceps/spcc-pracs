%{
int vowels = 0;
int consonants = 0;
%}

%%
[aeiouAEIOU] {vowels++;}
[b-df-hj-np-tv-`zB-DF-HJ-NP-TV-Z] {consonants++;}
%%

int yywrap() {
	return 1;
}

int main() {
	printf("Enter the string: ");
	yylex();
	printf("The no. of vowels are: %d\n", vowels);
	printf("The no. of consonants are: %d\n", consonants);
	return 0;
}